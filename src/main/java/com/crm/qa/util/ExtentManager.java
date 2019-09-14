package com.crm.qa.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager 
{
	    static ExtentReports extent;
	    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	    //private static ExtentReports extent = ExtentManager.getInstance();
	    public static ExtentReports getInstance() {
	        return extent;
	    }
	    public static File setFilepath(String appName) {
	        File classpathRoot = new File(System.getProperty("user.dir"));
	        File appDir = new File(classpathRoot, "/test_output/");
	        File app = new File(appDir, appName);
	        return app;
	    }
	    public static ExtentReports createInstance(String fileName) {
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(setFilepath(fileName));
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);

	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);

	        return extent;
	    }
}
