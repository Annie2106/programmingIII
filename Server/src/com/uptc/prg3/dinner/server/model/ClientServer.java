package com.uptc.prg3.dinner.server.model;

import com.uptc.prg3.dinner.server.constants.Values;
import com.uptc.prg3.dinner.server.persistence.JsonManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientServer extends Thread {
    private Socket mSocket;
    private ObjectInputStream mInput;
    private ObjectOutputStream mOutput;
    private Philosopher mPhilosopher;
    private Dinner mDinner;

    private JsonManager mManager;

    public ClientServer(Socket socket) {
        try {
            this.mSocket = socket;
            this.mInput = new ObjectInputStream(socket.getInputStream());
            this.mOutput = new ObjectOutputStream(socket.getOutputStream());
            this.mManager = new JsonManager(this.mInput, this.mOutput);
            this.mDinner = Dinner.getInstance();
            this.mPhilosopher = new Philosopher();
            this.mDinner.addClient(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        this.mPhilosopher.start();
        try {
            this.mOutput.writeObject(this.mManager.toJsonValue(Values.DINNER_STATUS, Values.DINNER_START));
            while (true) {
                switch (this.mManager.getValue(Values.PHILOSOPHER_EAT)) {
                    case Values.PHILOSOPHER_YES:
                        this.mPhilosopher.eat(true);
                        break;
                    case Values.PHILOSOPHER_NO:
                        this.mPhilosopher.eat(false);
                        break;
                    default:
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Philosopher getPhilosopher() {
        return this.mPhilosopher;
    }
}
