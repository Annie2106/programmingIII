package com.uptc.prg3.dinner.client.controller;

import com.uptc.prg3.dinner.client.view.MainFrame;

/**
 * Controller for the main frame of the client.
 *
 * @see MainFrame
 */
class GUIController {
    // Reference to the client and the main frame for the controller
    private ClientController mClient;
    private MainFrame mFrame;

    /**
     * Main constructor of the class, which creates the communication between the UI and the
     * {@link ClientController}.
     *
     * @param client The client that called this instance of a GUI Controller.
     * @see ClientController
     */
    GUIController(ClientController client) {
        this.mFrame = new MainFrame();
        this.mClient = client;
        this.initializeButtons();
    }

    /**
     * Shows a simple message on the text area from the {@link MainFrame}.
     *
     * @param message The message to show.
     */
    void showMessage(String message) {
        mFrame.setMessage(message);
    }

    /**
     * Whether to show or not the indefinite progress bar.
     *
     * @param show If the bar should be shown.
     */
    synchronized void showIndefiniteProgressBar(boolean show) {
        this.mFrame.setIndefiniteProgressBar(show);
    }

    /**
     * Initialize the buttons from the UI, with the listeners pointing to some method on the
     * {@link ClientController}.
     */
    private void initializeButtons() {
        this.mFrame.setConnectButtonListener(e -> mClient.connect());
        this.mFrame.setEatButtonListener(e -> mClient.eat());
        this.mFrame.setStopEatingButtonListener(e -> mClient.stop());
    }
}
