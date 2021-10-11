import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class TcpClient {

	public static void main(String[] args) {
		try {
			Socket clienSocket =  new  Socket("localhost",5000);
			System.out.println("Connected");
			File file = new File("/home/pelatro/eclipse-workspace/Server/src/tcporder_details.csv");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			InputStream inpuStream = clienSocket.getInputStream();
			System.out.println("Writing into file");
			int fileByte;
			while ((fileByte = inpuStream.read()) != -1) {
				fileOutputStream.write(fileByte);
			}
			System.out.println("Completed Writing into file");
			clienSocket.close();
			fileOutputStream.close();
			inpuStream.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
