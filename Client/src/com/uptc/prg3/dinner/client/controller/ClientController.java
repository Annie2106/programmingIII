package com.uptc.prg3.dinner.client.controller;

import com.uptc.prg3.dinner.client.constants.Values;
import com.uptc.prg3.dinner.client.model.Philosopher;
import com.uptc.prg3.dinner.client.persistence.JsonManager;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Primary controller for the client.
 */
public class ClientController {
    private static ClientController mClientController;

    private GUIController mGui;
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
                Socket socket = new Socket("localhost", 42069);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                JsonManager manager = new JsonManager(output);
                output.writeObject(manager.toJsonValue(Values.CONNECTION_TYPE, Values.CLIENT));
                // For the differentiation on the server side.
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
