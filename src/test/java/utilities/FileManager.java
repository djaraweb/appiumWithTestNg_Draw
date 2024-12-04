package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final static String screenshotPath = "src/test/resources/screenshots";
    private final static String pageStructurePath = "src/test/resources/pagesources";

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Tomando screenshot");
        final var screenshotFile = ((TakesScreenshot) new DriverProvider().get())
                .getScreenshotAs(OutputType.FILE);

        final var path = String.format("%s/%s.png", screenshotPath, screenshotName);
        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error al tomar screenshot: %s", ioException.getLocalizedMessage());
        }
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) new DriverProvider().get())
                .getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "pagesource", type = "text/html", fileExtension = "txt")
    public static String getPageSource() {
        final var pageSource = new DriverProvider().get().getPageSource();

        return pageSource != null
                ? Jsoup.parse(pageSource).toString()
                : "Errpr al tomar el page source";
    }

    public static void getPageSource(String fileName) {
        Logs.debug("Tomando Page source");
        final var path = String.format("%s/%s.html", pageStructurePath, fileName);
        final var file = new File(path);

        try {
            Logs.debug("Creando los directorios padres si es que no existen");
            if (file.getParentFile().mkdirs()) {
                final var fileWriter = new FileWriter(file);
                final var pageSource = new DriverProvider().get().getPageSource();
                fileWriter.write(Jsoup.parse(pageSource).toString());
                fileWriter.close();
            }

        } catch (IOException ioException) {
            Logs.error("Error al tomar page source: %s", ioException.getLocalizedMessage());
        }
    }

    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Borrando la evidencia anterior");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageStructurePath));

        } catch (IOException ioException) {
            Logs.error("Error al tomar screenshot: %s", ioException.getLocalizedMessage());

        }
    }
}
