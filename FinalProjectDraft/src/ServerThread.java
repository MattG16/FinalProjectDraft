import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
			
			BufferedReader reader = new BufferedReader(inReader);
			
			OutputStream out = connected.getOutputStream();
			
			OutputStreamWriter outWriter = new OutputStreamWriter(out);
			
			BufferedWriter writer = new BufferedWriter(outWriter);
			
			boolean done = false;
			
			String line = "";
			
			writer.write("Hello this is a message from the server");
			
			while(!done) {
				
				System.out.println("Executed");
				
				if(reader.ready()) {
					
					line = reader.readLine();
					
					System.out.println("Input from client: " + line);
					
					writer.write(line);
					
					writer.flush();
					
				}
				
				
				
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
