package com.dbdeploy;

import com.dbdeploy.exceptions.UsageException;
import com.dbdeploy.scripts.ChangeScriptCreator;

import java.io.File;

/**
 * @author hisg029
 */
public class ChangeScriptCreatorCli {

    public static final SystemLogger logger = new SystemLogger();

    public static void main(String[] args) {
        ChangeScriptCreator creator = new ChangeScriptCreator(logger);

        try {
            parseArguments(args, creator);
            creator.go();
        } catch (UsageException ex) {
            logger.error(ChangeScriptCreatorCli.class, "ERROR: " + ex.getMessage());
            logger.error(ChangeScriptCreatorCli.class, "Usage: java " + ChangeScriptCreatorCli.class.getName() + " scriptDirectory [scriptName]");
        } catch (Exception ex) {
            logger.error(ChangeScriptCreatorCli.class, "Failed to create script: " + ex);
            ex.printStackTrace();
            System.exit(2);
        }

        System.exit(0);
    }

    private static void parseArguments(String[] args, ChangeScriptCreator creator) {
        if (args.length >= 1) {
            final String scriptDirectoryPath = args[0];
            creator.setScriptDirectory(new File(scriptDirectoryPath));
        }

        if (args.length >= 2) {
            final String scriptDescription = args[1];
            creator.setScriptDescription(scriptDescription);
        }
    }
}
