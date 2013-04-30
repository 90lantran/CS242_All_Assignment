package network;

import gui.Action;
import gui.Graphic;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import chessGame.MyGame;


public class MyClient {
	Socket requestSocket;
	static ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	public static String myClientSend;
	Scanner user_input = new Scanner(System.in);

	MyClient() {
	}

	void run() {
		try {
			requestSocket = new Socket("localhost", 2013);
			System.out.println("Connected to localhost in port 2013");
			
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			Graphic clientGame = new Graphic();
		
			sendMessage("client confirm connection");
			do {
				try {
					message = (String) in.readObject();
					System.out.println("client receive>" + message);
					
			//		translateMessage(message);
				
					
				
				} catch (ClassNotFoundException classNot) {
					System.err.println("data received in unknown format");
				}
			} while (!message.equals("bye"));
				
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	
	public static void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("client send>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) {
		MyClient client = new MyClient();
		client.run();
	}
	
	private static void translateMessage(String msg){
		
			String[] tokens = msg.split(",");
			
			if(tokens.length > 0){
			int serverPickPiece = new Integer(tokens[0]);
			int serverMoveLocation = new Integer(tokens[1]);
			
			System.out.println("serverPickPiece = " + serverPickPiece);
			System.out.println("serverMoveLocation = " + serverMoveLocation);
			}
	}

}
