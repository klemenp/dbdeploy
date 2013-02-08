package com.dbdeploy;

import com.dbdeploy.exceptions.UsageException;
import com.dbdeploy.logging.SimpleLogger;

public class CommandLineTarget {
    private static final DbDeployCommandLineParser commandLineParser = new DbDeployCommandLineParser();
    public static final SystemLogger logger = new SystemLogger();

    public static void main(String[] args) {
		try {
			DbDeploy dbDeploy = new DbDeploy(logger);
			commandLineParser.parse(args, dbDeploy);
			dbDeploy.go();
		} catch (UsageException ex) {
			logger.error(CommandLineTarget.class, "ERROR: " + ex.getMessage());
			commandLineParser.printUsage();
		} catch (Exception ex) {
			logger.error(CommandLineTarget.class, "Failed to apply changes: " + ex);
			ex.printStackTrace();
			System.exit(2);
		}

		System.exit(0);
	}
}
