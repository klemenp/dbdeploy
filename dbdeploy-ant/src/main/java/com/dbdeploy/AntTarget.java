package com.dbdeploy;

import com.dbdeploy.database.DelimiterType;
import com.dbdeploy.database.LineEnding;
import com.dbdeploy.exceptions.UsageException;
import com.dbdeploy.logging.SimpleLogger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.LogLevel;

import java.io.File;

public class AntTarget extends Task {
	private final DbDeploy dbDeploy;
    private final AntLogger logger;

	private static String ANT_USAGE = "\n\nDbdeploy Ant Task Usage"
			+ "\n======================="
			+ "\n\n\t<dbdeploy"
			+ "\n\t\tuserid=\"[DATABASE USER ID]\" *"
			+ "\n\t\tpassword=\"[DATABASE USER ID PASSWORD]\""
			+ "\n\t\tdriver=\"[DATABASE DRIVER]\" *"
			+ "\n\t\turl=\"[DATABASE URL]\" *"
			+ "\n\t\tdbms=\"[YOUR DBMS]\""
			+ "\n\t\ttemplatedir=\"[DIRECTORY FOR DBMS TEMPLATE SCRIPTS, IF NOT USING BUILT-IN]\""
			+ "\n\t\tdir=\"[YOUR SCRIPT FOLDER]\" *"
			+ "\n\t\tencoding=\"[CHARSET OF SQL SCRIPTS - default UTF-8]\""
			+ "\n\t\toutputfile=\"[OUTPUT SCRIPT PATH + NAME]\""
			+ "\n\t\tlastChangeToApply=\"[NUMBER OF THE LAST SCRIPT TO APPLY]\""
			+ "\n\t\tundoOutputfile=\"[UNDO SCRIPT PATH + NAME]\""
			+ "\n\t\tchangeLogTableName=\"[CHANGE LOG TABLE NAME]\""
			+ "\n\t\tdelimiter=\"[STATEMENT DELIMITER - default ;]\""
			+ "\n\t\tdelimitertype=\"[STATEMENT DELIMITER TYPE - row or normal, default normal]\""
			+ "\n\t/>"
			+ "\n\n* - Indicates mandatory parameter";

    public AntTarget() {
        logger = new AntLogger(this);
        dbDeploy = new DbDeploy(logger);
    }

    @Override
	public void execute() throws BuildException {
		try {
			dbDeploy.go();
		} catch (UsageException ex) {
			logger.error(getClass(), ANT_USAGE);
			throw new BuildException(ex.getMessage());
		} catch (Exception ex) {
			throw new BuildException(ex);
		}
	}

	public void setDir(File dir) {
		dbDeploy.setScriptdirectory(dir);
	}

	public void setDriver(String driver) {
		dbDeploy.setDriver(driver);
	}

	public void setUrl(String url) {
		dbDeploy.setUrl(url);
	}

	public void setPassword(String password) {
		dbDeploy.setPassword(password);
	}

	public void setUserid(String userid) {
		dbDeploy.setUserid(userid);
	}

	public void setOutputfile(File outputfile) {
		dbDeploy.setOutputfile(outputfile);
	}

	public void setDbms(String dbms) {
		dbDeploy.setDbms(dbms);
	}

	public void setLastChangeToApply(Long lastChangeToApply) {
		dbDeploy.setLastChangeToApply(lastChangeToApply);
	}

	public void setUndoOutputfile(File undoOutputfile) {
		dbDeploy.setUndoOutputfile(undoOutputfile);
	}

	public void setChangeLogTableName(String changeLogTableName) {
		dbDeploy.setChangeLogTableName(changeLogTableName);
	}

	public void setDelimiter(String delimiter) {
		dbDeploy.setDelimiter(delimiter);
	}

	public void setDelimitertype(DelimiterType delimiterType) {
		dbDeploy.setDelimiterType(delimiterType);
	}

	public void setTemplatedir(File templateDirectory) {
		dbDeploy.setTemplatedir(templateDirectory);
	}

	public void setEncoding(String encoding) {
		dbDeploy.setEncoding(encoding);
	}

	public void setLineEnding(LineEnding lineEnding) {
		dbDeploy.setLineEnding(lineEnding);
	}

}

