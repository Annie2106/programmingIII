package com.uptc.prg3.dinner.server.constants;

public class Exceptions {
    public static class WrongValueException extends Exception {
        public WrongValueException() {
            super("The value sent or got is not valid!");
        }
    }
}
