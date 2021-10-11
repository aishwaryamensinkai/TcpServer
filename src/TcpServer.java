import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import postgresql.PostgreConnection;

public class TcpServer {

	private static ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {
		try {
			String SqlQuery = "select o.order_id,o.order_status,addr.city,addr.pin_code,u.email_id,u.user_name \n" + 
					"from public.orders as o \n" + 
					"JOIN public.address as addr\n" + 
					"ON o.address_id = addr.address_id \n" + 
					"JOIN public.users as u \n" + 
					"ON u.id = o.user_id";
			Connection connection = new PostgreConnection().connect();
			System.out.println("Connected to database.");
			
			CopyManager copManager = new CopyManager((BaseConnection) connection);
			
			serverSocket = new ServerSocket(5000);
			
			while(true) {
				Socket clienSocket = serverSocket.accept();
				System.out.println("STARTING COPY");
				OutputStream outputStream = clienSocket.getOutputStream();
				copManager.copyOut("COPY (" + SqlQuery + ") TO STDOUT WITH (FORMAT CSV,HEADER TRUE)", outputStream);
				outputStream.flush();
				System.out.println("COPY ENDED");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
