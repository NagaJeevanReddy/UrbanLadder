package utilsPack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;


public class ExtentReporterManager {

	public static ExtentReports report;
	public static ExtentHtmlReporter htmlReporter;

//create extent report and return the report
	public static ExtentReports getReportInstance() {

		if (report == null) {
			String reportName = GettingDate.getdate() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "//ExtentReports//" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAI");
			report.setSystemInfo("BuildNumber", "09.24.432");
			report.setSystemInfo("Browser", "Chrome");

			htmlReporter.config().setDocumentTitle(
					"Automation Results for hackathon : New bikes , Used cars , Log-in Functionality");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMMM dd yyyy HH:mm:ss");

		}
		return report;
	}
}