package com.dbdeploy;

import com.dbdeploy.logging.SimpleLogger;

/**
 * Log messages to System.out and System.err
 */
public class SystemLogger implements SimpleLogger {
    public void info(Class<?> source, String message) {
        System.err.println(message);
    }

    public void error(Class<?> source, String message) {
        System.err.println(message);
    }

    public void printStackTrace(Throwable t) {
        t.printStackTrace();
    }
}
