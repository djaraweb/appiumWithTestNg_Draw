package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class Gestures {
    private static final PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH, "pointer");

    public static AndroidDriver getDriver() {
        return new DriverProvider().get();
    }

    public static void tap(String cadena) {
        final var arrayChar = cadena.toCharArray();
        for (var c : arrayChar) {
            String elementText = String.format("text(\"%s\")", c);
            Gestures.tap(
                    getDriver().findElement(AppiumBy.androidUIAutomator(elementText))
            );
        }
    }

    public static void tap(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(pointer, 1); // creo la secuencia

        // 1. Muevo el puntero hacia el centro del elemento
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        centerPoint
                )
        );
        // 2. Toco la pantalla del celular
        sequence.addAction(
                pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );
        //3. Agrego una breve pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(1000))
        );

        // 4. Dejo de tocar la pantalla del celular
        sequence.addAction(
                pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );
        // 5 Realizo las acciones
        getDriver().perform(List.of(sequence)); // la aplico
    }

    public static void doubleTap(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(pointer, 1); // creo la secuencia

        // 1. Muevo el puntero hacia el centro del elemento
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        centerPoint
                )
        );

        for (var i = 0; i < 2; i++) {
            // 2. Toco la pantalla del celular
            sequence.addAction(
                    pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
            );
            // 3. Dejo de tocar la pantalla del celular
            sequence.addAction(
                    pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
            );
            // 4. Agrego una breve pausa
            sequence.addAction(
                    new Pause(pointer, Duration.ofMillis(200))
            );
        }
        // 5 Realizo las acciones
        getDriver().perform(List.of(sequence)); // la aplico
    }

    public static void longTap(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(pointer, 1); // creo la secuencia

        // 1. Muevo el puntero hacia el centro del elemento
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        centerPoint
                )
        );
        // 2. Toco la pantalla del celular
        sequence.addAction(
                pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );
        //3. Agrego una breve pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(3500))
        );

        // 4. Dejo de tocar la pantalla del celular
        sequence.addAction(
                pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );
        // 5 Realizo las acciones
        getDriver().perform(List.of(sequence)); // la aplico
    }

    private static Point getCenterPoint(WebElement element) {
        final var ubication = element.getLocation();
        final var dimentions = element.getSize();

        final var centerx = ubication.getX() + (dimentions.getWidth() / 2);
        final var centery = ubication.getY() + (dimentions.getHeight() / 2);
        return new Point(centerx, centery);
    }

    public static void dragAndDrop(
            WebElement elementOrigin,
            WebElement elementDestiny
    ) {
        final var origincenterPoint = getCenterPoint(elementOrigin);
        final var destinycenterPoint = getCenterPoint(elementDestiny);
        final var sequence = new Sequence(pointer, 1); // creo la secuencia

        // 1. Mover el puntero hacia el centro del origen
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(500),
                        PointerInput.Origin.viewport(),
                        origincenterPoint
                )
        );

        // 2. Tocamos la pantalla
        sequence.addAction(
                pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );

        // 3. Agregamos una breve pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(1000))
        );

        // 4. Arrastramos/movemos al elemento destino
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        destinycenterPoint
                )
        );

        // 5. Agregamos una breve pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(1500))
        );

        // 6. Dejamos de tocar la pantalla
        sequence.addAction(
                pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );

        // 7 Realizo las acciones
        getDriver().perform(List.of(sequence)); // la aplico

    }

    private static void swipeGeneralPuntos(Point origin, Point destiny) {
        Logs.debug("Haciendo swipe desde %s hasta %s", origin, destiny);
        final var sequence = new Sequence(pointer, 1);

        // 1. Muevo el puntero al elemento origen
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        origin
                )
        );
        // 2. Tocamos la pantalla con el puntero
        sequence.addAction(
                pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );

        // 3. Agreamos una pausa
        sequence.addAction(
                new Pause(pointer, Duration.ofMillis(1000))
        );

        // 4. Muevo el puntero al elemento destino
        sequence.addAction(
                pointer.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        destiny
                )
        );

        // 5. Dejamos de tocar la pantalla
        sequence.addAction(
                pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );

        // 6. Ejecutamos las acciones
        getDriver().perform(List.of(sequence));
    }

    private static Point getElementPointUsingPercentages(
            double percentageX,
            double percentageY,
            WebElement element
    ) {
        final var location = element.getLocation();
        final var size = element.getSize();

        final var xDelta = (percentageX / 100) * size.getWidth();
        final var yDelta = (percentageY / 100) * size.getHeight();

        final var xPrima = (int) (location.getX() + xDelta);
        final var yPrima = (int) (location.getY() + yDelta);

        return new Point(xPrima, yPrima);
    }

    public static void swipe(
            double percentageXInicial,
            double percentageYInicial,
            double percentageXFinal,
            double percentageYFinal,
            WebElement element
    ) {
        final var puntoInicial = getElementPointUsingPercentages(percentageXInicial, percentageYInicial, element);
        final var puntoFinal = getElementPointUsingPercentages(percentageXFinal, percentageYFinal, element);

        swipeGeneralPuntos(puntoInicial, puntoFinal);
    }

    public static void swipeHorizontal(
            double percentageY,
            double percentageXInicial,
            double percentageXFinal,
            WebElement element
    ) {
        swipe(percentageXInicial, percentageY, percentageXFinal, percentageY, element);
    }

    public static void swipeVertical(
            double percentageX,
            double percentageYInicial,
            double percentageYFinal,
            WebElement element
    ) {
        swipe(percentageX, percentageYInicial, percentageX, percentageYFinal, element);
    }

}
