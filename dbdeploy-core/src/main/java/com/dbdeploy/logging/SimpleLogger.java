package com.dbdeploy.logging;

/**
 * Simple interface for logging through different mechanisms
 */
public interface SimpleLogger {
    void info(Class<?> source, String message);
    void error(Class<?> source, String message);
    void printStackTrace(Throwable t);
}
