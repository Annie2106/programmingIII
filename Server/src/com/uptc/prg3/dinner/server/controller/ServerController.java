package com.uptc.prg3.dinner.server.controller;

import com.uptc.prg3.dinner.server.constants.Exceptions;
import com.uptc.prg3.dinner.server.constants.Strings;
import com.uptc.prg3.dinner.server.constants.Values;
import com.uptc.prg3.dinner.server.model.AdminServer;
import com.uptc.prg3.dinner.server.model.ClientServer;
import com.uptc.prg3.dinner.server.model.Dinner;
import com.uptc.prg3.dinner.server.persistence.JsonManager;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main controller for the server application.
 *
 * @since 16/09/2019
 */
public class ServerController {
    private static final Long TIME_TO_START = 60000L;

    private ServerSocket mServerSocket;
    private Dinner mDinner;

    /**
     * Main constructor for the server controller.
     *
     * @param port The port on which the server is going to run on.
     */
    public ServerController(String port) {
        try {
            Timer timer = new Timer("DinnerTimer");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mDinner.startDinner();
                    System.out.println("The dinner has started!");
                }
            }, TIME_TO_START);
            this.mDinner = Dinner.getInstance();
            this.mServerSocket = new ServerSocket(Integer.parseInt(port));
            this.startServer();
        } catch (IOException e) {
            System.out.println(Strings.PORT_EXCEPTION);
            e.printStackTrace();
        }
    }

    /**
     * Start the server.
     */
    private void startServer() {
        while (!Dinner.getDinnerStatus()) {
            System.out.println(Strings.WAITING_FOR_CONNECTION + this.mServerSocket.getLocalPort());
            try {
                this.connect(this.mServerSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Connect to the socket that requested the connection.
     *
     * @param socket The socket to connect with.
     * @throws IOException If the socket does not have an input stream or else.
     * @see ObjectInputStream
     */
    private void connect(Socket socket) throws IOException {
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        JsonManager manager = new JsonManager(input, output);
        /*
        Get the connection type; it can be "client" or "admin" and creates the new servers,
        if the connection sends an object and the object is not recognized or the value is not the
        expected, it throws the two exceptions below.
        */
        try {
            switch (manager.getValue(Values.CONNECTION_TYPE)) {
                case Values.ADMIN:
                    new AdminServer(socket);
                    break;
                case Values.CLIENT:
                    new ClientServer(socket);
                    break;
                default:
                    throw new Exceptions.WrongValueException();
            }
        } catch (Exceptions.WrongValueException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }
}
