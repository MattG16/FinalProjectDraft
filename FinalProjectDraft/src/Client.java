import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket("localhost", 5112);
		
		OutputStream out = socket.getOutputStream();
		
		OutputStreamWriter outWriter = new OutputStreamWriter(out);
		
		BufferedWriter writer = new BufferedWriter(outWriter);
		
		InputStream in = socket.getInputStream();
		
		InputStreamReader inReader = new InputStreamReader(in);
		
		BufferedReader reader = new BufferedReader(inReader);
		
		String line = "message\n";
		
		writer.write(line);
		writer.flush();
		
		String output = reader.readLine();
		
		System.out.println("Message received from server: " + output);
		
		socket.close();
		
	}
	
}
