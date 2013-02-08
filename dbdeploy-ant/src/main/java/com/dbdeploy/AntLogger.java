package com.dbdeploy;

import com.dbdeploy.logging.SimpleLogger;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.LogLevel;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * A Logger for Ant Tasks
 */
public class AntLogger implements SimpleLogger {
    private final ProjectComponent component;

    public AntLogger(ProjectComponent component) {
        this.component = component;
    }

    public void info(Class<?> source, String message) {
        component.log(message);
    }

    public void error(Class<?> source, String message) {
        component.log(message, LogLevel.ERR.getLevel());
    }

    public void printStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        error(t.getClass(), sw.toString());
    }
}
