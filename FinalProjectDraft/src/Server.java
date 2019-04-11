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
import java.util.Scanner;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(5112);
		
		Socket connected = ss.accept();
		
		InputStream in = connected.getInputStream();
		
		InputStreamReader inReader = new InputStreamReader(in);
		
		BufferedReader reader = new BufferedReader(inReader);
		
		OutputStream out = connected.getOutputStream();
		
		OutputStreamWriter outWriter = new OutputStreamWriter(out);
		
		BufferedWriter writer = new BufferedWriter(outWriter);
		
		String line = reader.readLine();
		
		System.out.println("Input from client: " + line);
		
		line = line.concat(" server checked");
		
		writer.write(line);
		
		writer.flush();
		
			
		
		
	}
	
}
