import cajero.CajeroService;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CajeroService cajeroService = new CajeroService(scanner);
        cajeroService.iniciar();
        scanner.close();
    }
}
