import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(5112);
		Socket connect = ss.accept();
		
		
		
	}
	
}
