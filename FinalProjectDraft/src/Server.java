import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(5112);
		
		Socket connected = ss.accept();
		
		OutputStream out = connected.getOutputStream();
		
		InputStream in = connected.getInputStream();
		
		Scanner input = new Scanner(in, "UTF-8");
		
		PrintWriter output = new PrintWriter(new OutputStreamWriter(out));
		
	//	out.writeChars("Hello there client");
		
		boolean done = false;
		
		while(!done) {
	
				String line = input.nextLine();
			//	System.out.println("Got here");
				output.println(line);
				output.flush();
				if(line.trim().equals("BYE")) {
					done = true;
				}
			
			
		}
		
	}
	
}
