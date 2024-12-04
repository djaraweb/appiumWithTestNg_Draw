package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.FileManager;
import utilities.Logs;

public class SuiteListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        Logs.info("Comenzando la suite : %s", suite.getName());
        FileManager.deletePreviousEvidence();
    }

    @Override
    public void onFinish(ISuite suite) {
        Logs.info("Terminando la suite : %s", suite.getName());
    }
}
