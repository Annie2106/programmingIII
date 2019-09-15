package com.uptc.prg3.dinner.client.runner;

import com.uptc.prg3.dinner.client.controller.ClientController;

/**
 * Main runner for the client.
 */
public class ClientRunner {
    public static void main(String[] args) {
        ClientController.getInstance();
    }
}
