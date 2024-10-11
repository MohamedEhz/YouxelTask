package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    public static ExtentReports createInstance() {
        // Define the location of the report
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/TestReport.html");
        sparkReporter.config().setReportName("E-Commerce Test Report");
        sparkReporter.config().setDocumentTitle("Test Report");

        // Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        return extent;
    }
}
