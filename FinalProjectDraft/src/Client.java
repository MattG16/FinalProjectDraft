import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class Client {
	
	private JButton button;
	private JFileChooser fileChooser;
	private JFrame frame;
	private JPanel main;
	private JTextField textField;
	private JTextArea textArea;
	private PrintWriter writer;
	private String name;
	private File selectedFile;
	private boolean file = false;
	private Scanner reader;
	
	public Client() {
		
		frame = new JFrame("Messenger Client");
		textField = new JTextField(100);
		textArea = new JTextArea(20, 100);
		button = new JButton("Attach File");
		fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		main = new JPanel();
		main.add(textField, BorderLayout.SOUTH);
		main.add(new JScrollPane(textArea), BorderLayout.CENTER);
		main.add(button, BorderLayout.WEST);
	
		textArea.setEditable(false);
		textField.setEditable(false);
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String m = textField.getText();
					if(!m.trim().isEmpty()) {
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
		
		String ip = JOptionPane.showInputDialog(frame, "Enter IP", "What is the IP of the server you want to chat on", JOptionPane.PLAIN_MESSAGE);
		
		Socket socket = new Socket(ip , 5112);
		
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
		
	/*	 int filesize = 6022386;
		
		 byte[] fileByte  = new byte[filesize];

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					FileOutputStream fos;
					FileInputStream fin;
					
					fileChooser.setDialogTitle("Choose a file to send: ");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					
					int returnValue = fileChooser.showOpenDialog(new JFrame());
					
					if(returnValue == fileChooser.APPROVE_OPTION) {
						selectedFile = fileChooser.getSelectedFile();
			            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			            file = true;
			            fos = new FileOutputStream(selectedFile);
			            fin = new FileInputStream(selectedFile);
			            
			            int count;
			            while ((count = in.read(fileByte)) > 0) {
			                out.write(fileByte, 0, count);
			            }
			            
					} 
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		*/
		
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
