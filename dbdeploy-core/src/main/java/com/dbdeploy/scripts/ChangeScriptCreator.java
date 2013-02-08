package com.dbdeploy.scripts;

import com.dbdeploy.exceptions.UsageException;
import com.dbdeploy.logging.SimpleLogger;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Responsible for creating a new change script file
 * to be used by dbdeploy.  This class will generate
 * a new change script using a timestamp as the change
 * script number and any supplied text as the rest
 * of the filename.
 *
 * @author jbogan
 */
public class ChangeScriptCreator {
    private final SimpleLogger logger;
    private String changeScriptSuffix = ".sql";
    private String changeScriptTimestampFormat = "yyyyMMddHHmmss";
    private String scriptDescription;
    private File scriptDirectory;
    private DateFormat dateFormatter;

    public ChangeScriptCreator(SimpleLogger logger) {
        this.logger = logger;
        dateFormatter = new SimpleDateFormat(changeScriptTimestampFormat);
    }

    public File go() throws IOException {
        validate();

        return createScript();
    }

    private void validate() {
        if (scriptDirectory == null || !scriptDirectory.isDirectory()) {
            throw new UsageException("Script directory must point to a valid directory");
        }
    }

    public File createScript() throws IOException {
        final String newScriptFileName = getChangeScriptFileName();
        final String fullScriptPath = scriptDirectory + File.separator + newScriptFileName;

        final File newChangeScriptFile = new File(fullScriptPath);
        if (newChangeScriptFile.createNewFile()) {
            return newChangeScriptFile;
        } else {
            throw new IOException("Unable to create new change script " + fullScriptPath);
        }
    }

    private String getChangeScriptFileName() {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(getFileTimestamp());
        if (scriptDescription != null && !scriptDescription.equals("")) {
            fileNameBuilder.append("_");
            fileNameBuilder.append(scriptDescription);
        }
        fileNameBuilder.append(changeScriptSuffix);
        
        return fileNameBuilder.toString();
    }

    private String getFileTimestamp() {
        return dateFormatter.format(new Date());
    }
    
    public void setScriptDescription(final String scriptDescription) {
        this.scriptDescription = scriptDescription;
    }

    public void setScriptDirectory(final File scriptDirectory) {
        this.scriptDirectory = scriptDirectory;
    }

}
