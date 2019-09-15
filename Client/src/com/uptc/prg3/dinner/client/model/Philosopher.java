package com.uptc.prg3.dinner.client.model;

/**
 * Models a philosopher.
 */
public class Philosopher extends Thread {
    // The count of philosophers that are on the dinner
    private static int mCount = 0;

    // The unique ID for the philosopher
    private int mId;
    // The forks
    private Fork mForkLeft;
    private Fork mForkRight;
    // The state of the philosopher
    private PhilosopherState mState;

    // Action given by the client, if the philosopher must eat or stop eating
    private boolean mMustEat;

    /**
     * Main constructor for the class, which initialize most of the variables.
     */
    public Philosopher() {
        Philosopher.mCount++;
        this.mId = Philosopher.mCount;
        this.mState = PhilosopherState.THINK;
        this.mMustEat = false;
    }

    public Fork getForkLeft() {
        return this.mForkLeft;
    }

    public Fork getForkRight() {
        return this.mForkRight;
    }

    /**
     * Starts the philosopher thread.
     */
    @Override
    public void run() {
        boolean eating = false;
        this.mState = PhilosopherState.THINK;
        while (Dinner.getDinnerStatus()) {
            System.out.println(mId + ": " + mState);
            if (this.mMustEat && eating) {
                if (this.mForkLeft.isAvailable() && this.mForkRight.isAvailable()) {
                    this.mState = PhilosopherState.EAT;
                } else {
                    this.dropForks();
                    this.mState = PhilosopherState.WAIT;
                }
            } else {
                this.dropForks();
            }
        }
    }

    /**
     * Action performed by the client, who tells the philosopher to start eating or stop eating.
     *
     * @param mustEat Start or stop eating.
     */
    public void eat(boolean mustEat) {
        this.mMustEat = mustEat;
    }

    /**
     * Drop both forks.
     */
    private void dropForks() {
        this.mForkLeft.setAvailable(true);
        this.mForkRight.setAvailable(true);
    }

    /**
     * Set the forks to an initial value.
     *
     * @param forkLeft  For the left fork.
     * @param forkRight For the right fork.
     */
    void setForks(Fork forkLeft, Fork forkRight) {
        this.mForkLeft = forkLeft;
        this.mForkRight = forkRight;
    }
}
