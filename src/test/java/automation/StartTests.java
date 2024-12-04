package automation;

import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Logs;

public class StartTests extends BaseTests {

    @Test(groups = {regression, smoke})
    public void initAppDrawTest() {
        Logs.info("Ejercicio2: Verificando que se cargue la Apk de Draw");
        sleep(3000);
    }
}
