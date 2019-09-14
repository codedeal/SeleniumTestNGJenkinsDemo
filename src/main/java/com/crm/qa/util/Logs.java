package com.crm.qa.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Logs
{
	// Initialize Log4j logs
	private static Logger log= Logger.getLogger(Logs.class.getName());
	public static File setFilepath() 
	{
        File classpathRoot = new File(System.getProperty("user.dir"));
        File logPath = new File(classpathRoot, "log4j.xml");
        return logPath;
    }
	
	public static void info(String message) {
        DOMConfigurator.configure(setFilepath().getAbsolutePath());
        log.info(message);
        Reports.logmessage(message);
       
	}
}
