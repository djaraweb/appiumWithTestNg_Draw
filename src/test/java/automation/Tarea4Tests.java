package automation;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Gestures;
import utilities.Logs;

public class Tarea4Tests extends BaseTests {
    private WebElement canvas;

    @BeforeMethod
    public void setUp() {
        Logs.info("Obtenemos el canvan para empezar a dibujar");
        canvas = driver.findElement(AppiumBy.id("com.simplemobiletools.draw.pro:id/main_holder"));
    }

    @Test(groups = {regression, smoke})
    public void drawSimboloDeDivisionTest() {
        Logs.info("Dibujar el símbolo de división");

        Logs.info("Dibujando un punto");
        Gestures.swipePoint(50, 20, canvas);

        Logs.info("Dibujando una linea horizontal");
        Gestures.swipeHorizontal(25, 30, 70, canvas);

        Logs.info("Dibujando un punto");
        Gestures.swipePoint(50, 30, canvas);

        sleep(3000);
    }

    @Test(groups = {regression, smoke})
    public void drawSimboloDeFactorialTest() {
        Logs.info("Dibujar el símbolo de factorial");

        Logs.info("Dibujando una linea vertical");
        Gestures.swipeVertical(50, 20, 40, canvas);

        Logs.info("Dibujando un punto");
        Gestures.swipePoint(50, 45, canvas);

        sleep(3000);
    }

    @Test(groups = {regression, smoke})
    public void drawSimboloDePITest() {
        Logs.info("Dibujar el símbolo de PI");

        Logs.info("Dibujando una linea horizontal");
        Gestures.swipeHorizontal(20, 20, 60, canvas);

        Logs.info("Dibujando un linea vertical");
        Gestures.swipeVertical(30, 20, 30, canvas);

        Logs.info("Dibujando un linea vertical");
        Gestures.swipeVertical(50, 20, 30, canvas);

        sleep(3000);
    }

    @Test(groups = {regression, smoke})
    public void drawTrianguloTest() {
        Logs.info("Dibujar un triángulo recto");

        Logs.info("Dibujando un linea vertical");
        Gestures.swipeVertical(30, 20, 40, canvas);

        Logs.info("Dibujando un linea horizontal");
        Gestures.swipeHorizontal(40, 30, 60, canvas);

        Logs.info("Dibujando un linea desde un punto origen y un punto destino");
        Gestures.swipe(30, 20, 60, 40, canvas);
        sleep(3000);
    }

    @Test(groups = {regression, smoke})
    public void drawCuadradoTest() {
        Logs.info("Dibujar un cuadrado");

        Logs.info("Dibujando un linea vertical");
        Gestures.swipeVertical(30, 20, 40, canvas);

        Logs.info("Dibujando un linea horizontal");
        Gestures.swipeHorizontal(40, 30, 60, canvas);

        Logs.info("Dibujando un linea vertical");
        Gestures.swipeVertical(60, 40, 20, canvas);

        Logs.info("Dibujando un linea horizontal");
        Gestures.swipeHorizontal(20, 60, 30, canvas);

        sleep(3000);
    }

    @Test(groups = {regression, smoke})
    public void drawRomboTest() {
        Logs.info("Dibujar un Rombo");

        Gestures.swipe(50, 20, 20, 40, canvas);
        Gestures.swipe(20, 40, 50, 60, canvas);

        Gestures.swipe(50, 60, 80, 40, canvas);
        Gestures.swipe(80, 40, 50, 20, canvas);

        sleep(3000);
    }
}
