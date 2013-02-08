package com.dbdeploy.mojo;

import com.dbdeploy.logging.SimpleLogger;
import org.apache.maven.plugin.logging.Log;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Logs to the standard Maven logger
 */
public class MojoLogger implements SimpleLogger {
    private final Log log;

    public MojoLogger(Log log) {
        this.log = log;
    }

    public void info(Class<?> source, String message) {
        log.info(message);
    }

    public void error(Class<?> source, String message) {
        log.error(message);
    }

    public void printStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        error(t.getClass(), sw.toString());
    }
}
