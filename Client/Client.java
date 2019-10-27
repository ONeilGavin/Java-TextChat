/**
 * @(#)ControlPanel.java
 *
 * @author Gavin O'Neil
 * @version 1.00 2019/6/16
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client implements Runnable {
	private String name;
	private BufferedWriter write;
	private BufferedReader read;
	private ControlPanel c;
	
	public Client(String name, ControlPanel c) {
		this.name = name;
		this.c = c;
		
		try {
			Socket clientSocket = new Socket("localhost", 88); //New Socket with server
			read = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			write = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			
		}catch(IOException e) {
			c.appendToTextArea(e.getMessage());
		}
		
		c.appendToTextArea("Connected to server, chatting as " + "'" + name + "'");
		
	}
	
	public void sendMessage(String m) {
		String message = "[" + name + "]: " + m;
			try {
				write.write(message); //write message to server
				write.write("\r\n");
				write.flush();
			} catch (IOException e) {
				c.appendToTextArea(e.getMessage());
			}
			
		
	}
	
	public void run() {
		try {
			String message;
			while((message = read.readLine()) != null) { //read message from server
				c.appendToTextArea("\n" + message);
				
			}
		}catch(IOException e) {
			c.appendToTextArea(e.getMessage());
		}
		
	}

}
