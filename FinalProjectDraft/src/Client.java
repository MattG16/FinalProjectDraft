import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	
	private JFrame frame;
	private JPanel main;
	private JTextField textField;
	private JTextArea textArea;
	private BufferedWriter writer;
	private String name;
	
	public Client() {
		
		frame = new JFrame("Messenger Client");
		textField = new JTextField(100);
		textArea = new JTextArea(20, 100);
		main = new JPanel();
		main.add(textField, BorderLayout.SOUTH);
		main.add(new JScrollPane(textArea), BorderLayout.CENTER);
	
		textArea.setEditable(false);
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					writer.write(textField.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");
			}
		});
		

		name = JOptionPane.showInputDialog(frame, "What is your name", "Input Name", JOptionPane.PLAIN_MESSAGE);
		main.setVisible(true);
		frame.add(main);
		
	}
	
	public void program() throws IOException {
		
		Socket socket = new Socket("localhost", 5112);
		
		OutputStream out = socket.getOutputStream();
		
		OutputStreamWriter outWriter = new OutputStreamWriter(out);
		
		writer = new BufferedWriter(outWriter);
		
		InputStream in = socket.getInputStream();
		
		InputStreamReader inReader = new InputStreamReader(in);
		
		BufferedReader reader = new BufferedReader(inReader);
		
		Scanner sc = new Scanner(System.in);
		
		String message = sc.nextLine();
		
		String line = message + "\n";
		
		writer.write(line);
		writer.flush();
		
		String output = reader.readLine();
		
		System.out.println("Message received from server: " + output);
		
		socket.close();
		
		
	}

	public static void main(String[] args) throws IOException {
		
		
		Client client = new Client();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.setSize(new Dimension(110, 80));
		client.main.setSize(new Dimension(110, 80));
		client.frame.setVisible(true);
	
	}
	
}
