package server.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import persistence.FileManager;
import server.controller.Actions;
import server.controller.Controller;

public class UserServer extends Thread {
	/**
	 * 
	 */
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private Controller controller;
	private Socket socket;

	public UserServer(Socket socket, Controller controller) {
		this.socket = socket;
		this.controller = controller;
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public synchronized void write(String mess) {
		try {
			out.writeUTF(mess);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				String command = in.readUTF();
				switch (Actions.valueOf(command)) {
				case ADMIN:
					controller.setAdmin(this);
					break;
				case THINK:
//					controller.****; pensando
					break;
				case EAT:

					break;
				case STOP:
					break;
				case CONNECT:
					break;
				case USER_LIST:
					String arrayInGson = in.readUTF();
					Philosopher[] lu = FileManager.toArray(arrayInGson);
					break;
				default:
//						break;
				}
			} catch (IOException e) {
				System.out.println("Client leave...");
				break;
			}
		}

	}

	public String getIp() {
		return socket.getRemoteSocketAddress().toString();
	}

}
