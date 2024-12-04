package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.FileManager;
import utilities.Logs;

public class TestListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Logs.info("START TEST: (%s)", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.info("TEST (%s): PASSED!!%n", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.info("TEST (%s): FAILED!!%n", result.getName());
        FileManager.getScreenshot(result.getName());
        FileManager.getPageSource(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.info("TEST (%s): SKIPPED!!%n", result.getName());
    }

}
