package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.controller.Controller;

public class Server {

	private ServerSocket ss;
	private Controller controller;

	public Server(Controller controller) {
		this.controller = controller;
	}

	public void createSocket() throws IOException {
		ss = new ServerSocket(1234);
	}

	public void accepConnection() {
		new Thread(() -> {
			System.out.println("Waiting for clients");
			while (true) {
				Socket s;
				try {
					s = ss.accept();
					System.out.println("User accepted");
					controller.addPhilosopher(s);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}