package utilities;

import com.github.javafaker.Faker;
import io.appium.java_client.android.AndroidDriver;
import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListeners.class, SuiteListeners.class})
public abstract class BaseTests {
    protected SoftAssert softAssert = new SoftAssert();
    protected Faker faker = new Faker();
    protected final String smoke = "smoke";
    protected final String regression = "regression";
    protected AndroidDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUpMaster() {
        softAssert = new SoftAssert();
        Logs.info("Inicializando el driver");
        driver = initDriver();
        Logs.info("Guardando el driver en el DriverProvider");
        new DriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMaster() {
        Logs.info("Matando el driver");
        driver.quit();
    }

    protected void sleep(long timeMs) {
        try {
            Logs.info("Esperando por %d ms", timeMs);
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            Logs.error("Error al esperar: %s", e.getLocalizedMessage());
        }
    }


    private static AndroidDriver initDriver() {
        try {
            final var appiumUrl = "http://127.0.0.1:4723/";
            return new AndroidDriver(new URL(appiumUrl), getDesiredCapabilities());
        } catch (MalformedURLException malformedURLException) {
            Logs.error("Fallo al iniciar el driver: %s", malformedURLException.getLocalizedMessage());
            throw new RuntimeException();
        }
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        final var desiredCapabilities = new DesiredCapabilities();
        final var fileAPK = new File("src/test/resources/apk/draw.apk");

        desiredCapabilities.setCapability("appium:autoGrantPermissions", true);
        desiredCapabilities.setCapability("appium:platformName", "Android");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:appActivity", "com.simplemobiletools.draw.pro.activities.MainActivity");

        desiredCapabilities.setCapability("appium:app", fileAPK.getAbsolutePath());
        return desiredCapabilities;
    }
}
