package com.uptc.prg3.dinner.server.runner;

import com.uptc.prg3.dinner.server.constants.Strings;
import com.uptc.prg3.dinner.server.controller.ServerController;

/**
 * Main runner for the server application.
 *
 * @since 16/09/2019
 */
public class Runner {
    /**
     * Runner of the application.
     *
     * @param args Application variables needed for a correct run.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(Strings.ARGUMENT_EXCEPTION);
        } else {
            new ServerController(args[0]);
        }
    }
}
