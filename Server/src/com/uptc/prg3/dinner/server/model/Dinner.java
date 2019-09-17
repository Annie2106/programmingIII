package com.uptc.prg3.dinner.server.model;

import com.sun.security.ntlm.Client;

/**
 * Models a the dinner for the philosophers
 */
public class Dinner {
    // The unique instance of this class
    private volatile static Dinner mDinner;
    // Actual status of the dinner
    private static boolean mDinningStatus;
    // The group of philosophers
    private ClientServer[] mClients;

    /**
     * Main constructor for the class.
     */
    private Dinner() {
        mDinningStatus = false;
        this.mClients = new ClientServer[]{};
    }

    public synchronized static Dinner getInstance() {
        if (mDinner == null) {
            mDinner = new Dinner();
        }
        return mDinner;
    }

    public void startDinner() {
        this.forkAssign();
        mDinningStatus = true;
        for (ClientServer sClient : this.mClients) {
            sClient.start();
        }
    }

    /**
     * Returns the dinner status, if true, the dinner has started, if not... well it hasn't.
     *
     * @return Dinner status.
     */
    public static boolean getDinnerStatus() {
        return Dinner.mDinningStatus;
    }

    void addClient(ClientServer client) {
        this.increasingVector(client);
    }

    /**
     * Assign the forks, left and right, to every philosopher on the dinner.
     */
    private void forkAssign() {
        Fork[] forks = new Fork[this.mClients.length];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
        }
        for (int i = 0; i < this.mClients.length - 1; i++) {
            int nextPosition = (i + 1) % this.mClients.length;
            this.mClients[i].getPhilosopher().setForks(forks[i], forks[nextPosition]);
        }
    }

    private void increasingVector(ClientServer user) {
        if (mClients != null) {
            ClientServer[] vector = new ClientServer[mClients.length + 1];
            System.arraycopy(mClients, 0, vector, 0, mClients.length);
            vector[vector.length - 1] = user;
            mClients = vector;
        } else {
            mClients = new ClientServer[]{user};
        }
    }
}
