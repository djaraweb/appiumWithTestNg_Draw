package utilities;

public class Helpers {

    public static int getFactorial(int n) {
        var factorial = 1;
        for (var i = 2; i <= n; ++i) {
            factorial *= i;
        }
        return factorial;
    }
}
