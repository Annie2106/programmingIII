package com.uptc.prg3.dinner.client.controller;

import com.uptc.prg3.dinner.client.model.Philosopher;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Primary controller for the client.
 */
public class ClientController {
    private static ClientController mClientController;

    private static final int PORT = 1234;
    private static final String HOST = "localhost";

    private GUIController mGui;
    private Socket mSocket;
    private DataInputStream mDataInputStream;
    private DataOutputStream mDataOutputStream;
    private Philosopher mPhilosopher;

    /**
     * Base constructor for the class.
     */
    private ClientController() {
        this.initialize();
    }

    /**
     * <p>Singleton</p>
     * <p>
     * Gets an unique instance of this controller.
     *
     * @return A unique instance of this controller.
     */
    public synchronized static ClientController getInstance() {
        if (mClientController == null) {
            return mClientController = new ClientController();
        } else {
            return mClientController;
        }
    }

    /**
     * Makes this philosopher start eating.
     */
    void eat() {
        boolean is = false;
        // TODO:
    }

    /**
     * Makes this philosopher stop eating.
     */
    void stop() {
        // TODO:
    }

    /**
     * Connect to the server with a base host and port. This method is called via
     * {@link ActionListener}, from a button.
     *
     * @see ActionListener
     */
    void connect() {
        new Thread(() -> {
            mGui.showIndefiniteProgressBar(true);
            try {
                mSocket = new Socket(HOST, PORT);
                mDataInputStream = new DataInputStream(mSocket.getInputStream());
                mDataOutputStream = new DataOutputStream(mSocket.getOutputStream());
                // For the differentiation on the server side.
                mDataOutputStream.writeUTF("client");
                mGui.showMessage("Connected to the server!");

            } catch (IOException e) {
                e.printStackTrace();
                mGui.showMessage("The connection with the server was not successful!");
            } finally {
                mGui.showIndefiniteProgressBar(false);
            }
        }).start();
    }

    private void initialize() {
        this.mGui = new GUIController(this);
    }
}
