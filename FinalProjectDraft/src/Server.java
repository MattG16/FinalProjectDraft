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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	public static void main(String[] args) throws Exception {
	
		try(ServerSocket ss = new ServerSocket(5112);) {
			ExecutorService pool = Executors.newFixedThreadPool(20);
			while(true) {
				pool.execute(new ServerThread(ss.accept()));
			}
		} 
		
		
	}
	
}
