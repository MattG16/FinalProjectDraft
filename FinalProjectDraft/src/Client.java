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
import java.io.PrintWriter;
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
	private PrintWriter writer;
	private String name;
	private Scanner reader;
	
	public Client() {
		
		frame = new JFrame("Messenger Client");
		textField = new JTextField(100);
		textArea = new JTextArea(20, 100);
		main = new JPanel();
		main.add(textField, BorderLayout.SOUTH);
		main.add(new JScrollPane(textArea), BorderLayout.CENTER);
	
		textArea.setEditable(false);
		textField.setEditable(false);
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String m = textField.getText();
					if(!m.isEmpty()) {
						writer.println(m);
					}
				//	textArea.append(m);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");
			}
		});

		
		main.setVisible(true);
		frame.add(main);
		
	}
	
	public void program() throws IOException {
		
		Socket socket = new Socket("10.70.20.9", 5112);
		
		OutputStream out = socket.getOutputStream();
		
		OutputStreamWriter outWriter = new OutputStreamWriter(out);
		
		writer = new PrintWriter(socket.getOutputStream(), true);
		
		InputStream in = socket.getInputStream();
		
		InputStreamReader inReader = new InputStreamReader(in);
		
		reader = new Scanner(socket.getInputStream());
		
//		Scanner sc = new Scanner(System.in);
		
		name = JOptionPane.showInputDialog(frame, "What is your name", "Input Name", JOptionPane.PLAIN_MESSAGE);
		
		writer.println(name);
		
		textField.setEditable(true);
		
		while(reader.hasNextLine()) {
			
			String message = reader.nextLine();
			textArea.append(message + "\n");
			
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		
		
		Client client = new Client();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.setSize(new Dimension(1400, 500));
		client.main.setSize(new Dimension(1400, 500));
		client.frame.setVisible(true);
		client.program();
	
	}
	
}
