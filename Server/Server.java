/**
 * @(#)ControlPanel.java
 *
 * @author Gavin O'Neil
 * @version 1.00 2019/6/16
 */

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
        
public class Server implements Runnable{
	private Socket serverSocket;     
    public static Vector<BufferedWriter> clients = new Vector<BufferedWriter>();
    private String message;
    private  JTextArea text;
    private JScrollPane sPane;
            
        public Server(Socket s){
                 try{
                	 
                	System.out.println("Server Started" + "\n");
                   	serverSocket =s;
                   	
                    }catch(Exception e){
                    	System.out.println(e.getMessage());
                    }
                    
            }     
        public void run(){
                   try{
                	   //Reader and writer for client messages
                	   BufferedReader reader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                       BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
                            
                            clients.add(writer); 
                            
                        while(true){
                        	
                        	message = reader.readLine().trim();

                       System.out.println("\n" + "New Message: " + message);
                            
                            for (int i=0;i<clients.size();i++){
                               try{
                                    BufferedWriter write = (BufferedWriter)clients.get(i); //Write the message to each client
                                    write.write(message);
                                    write.write("\n");
                                    write .flush();
                                    
                                }catch(Exception e){e.printStackTrace();}
                            }
                        }  
                    }catch(Exception e){e.printStackTrace();}
        }
            
        public static void main(String args[]) throws Exception{
        	
            ServerSocket sSocket = new ServerSocket(88);
                while(true){
                Socket socket = sSocket.accept();
                Server server=new Server(socket);
                Thread serverThread=new Thread(server);
                serverThread.start();
                }
            }
}
