package network;

import gui.Action;
import gui.Graphic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chessGame.MyGame;

public class MyServer {
	ServerSocket providerSocket;
	Socket connection = null;
	static ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	
	MyServer() {}

	void run() throws InterruptedException {
		try {
			providerSocket = new ServerSocket(2013, 10);
			System.out.println("Waiting for connection");
			
			connection = providerSocket.accept();
			
			System.out.println("Connection received from "
					+ connection.getInetAddress().getHostName());

			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			
			sendMessage("Connection successful");

			Graphic serverGame = new Graphic();
			do {
				try {
					
					message = (String) in.readObject();
					System.out.println("server receive>" + message);
			
				} catch (ClassNotFoundException classnot) {
					System.err.println("Data received in unknown format");
				}
			} while (!message.equals("bye"));
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
				providerSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	
	public static void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("server send>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) throws InterruptedException {
		MyServer server = new MyServer();
		while (true) {
			server.run();
		}
	}
	

}
