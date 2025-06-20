package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String path = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);

            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK); // or .STANDARD
            sparkReporter.config().setTimelineEnabled(true); // nice timeline view
            extent = new ExtentReports();

            // Add custom system info:
            extent.setSystemInfo("Project", "SelAE");
            extent.setSystemInfo("Tester", "Nirusha Manandhar");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

}
