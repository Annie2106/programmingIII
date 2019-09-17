package server.model;

/**
 * Models a fork that can be given to a philosopher.
 */
class Fork {
    // Whether is or is not available to a philosopher
    private boolean mAvailable;

    /**
     * @return If the fork is available.
     */
    boolean isAvailable() {
        return this.mAvailable;
    }

    /**
     * Sets the availability of the fork.
     *
     * @param available If is available or not.
     */
    void setAvailable(boolean available) {
        this.mAvailable = available;
    }
}
