import java.io.BufferedInputStream;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	
	public static Set<PrintWriter> w = new HashSet<>();
	
	public static void main(String[] args) throws Exception {
		
		try(ServerSocket ss = new ServerSocket(5112);) {
			ExecutorService pool = Executors.newFixedThreadPool(20);
			while(true) {
				pool.execute(new ServerThread(ss.accept()));
			}
		} 
		
	}
	
private static class ServerThread implements Runnable {

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
			
				String name = reader.nextLine();
	
				w.add(writer);
				
				while(!done) {
					
				//	System.out.println("Executed");
					
				//	if(reader.ready()) {
						
					//	System.out.println("Execute");
						
						line = reader.nextLine();
						
						System.out.println("Input from client: " + line);
						
						for(PrintWriter wr : w) {
							wr.println(name + ": " + line);
						}
						
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

	
}




