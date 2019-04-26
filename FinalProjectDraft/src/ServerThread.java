import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {

	private Socket connected;
	
	ServerThread(Socket socket) {
		this.connected = socket;
	}
		
	@Override
	public void run() {
		
		System.out.println("Client connected");
		
		InputStream in;
		
		try {
			in = connected.getInputStream();
			
			InputStreamReader inReader = new InputStreamReader(in);
			
			Scanner reader = new Scanner(connected.getInputStream());
			
			OutputStream out = connected.getOutputStream();
			
			OutputStreamWriter outWriter = new OutputStreamWriter(out);
			
			PrintWriter writer = new PrintWriter(connected.getOutputStream(), true);
			
			boolean done = false;
			
			String line = "";
		
			while(!done) {
				
			//	System.out.println("Executed");
				
			//	if(reader.ready()) {
					
				//	System.out.println("Execute");
					
					line = reader.nextLine();
					
					System.out.println("Input from client: " + line);
					
					writer.println(line);
					
					writer.flush();
					
			//	}
				
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				connected.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
		
		

		
	}

}
