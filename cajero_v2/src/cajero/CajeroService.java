package cajero;

import cajero.Cuenta;
import java.util.Scanner;

public class CajeroService {
    private Scanner scanner;
    private Cuenta cuenta;

    public CajeroService(Scanner scanner) {
        this.scanner = scanner;
        this.cuenta = new Cuenta(50000);
    }

    public void iniciar() {
        if (!autenticacion()) {
            return;
        }
        menu();
    }

    // Authentication method
    private boolean autenticacion() {
        int intentos = 0;
        while (intentos < 3) {
            System.out.print("Ingrese su PIN: ");
            int pin = scanner.nextInt();
            if (cuenta.autenticar(pin)) {
                return true;
            }
            intentos++;
            if (cuenta.isCuentaBloqueada()) {
                return false;
            }
        }
        return false;
    }

    // Main menu for ATM options
    private void menu() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            manejarOpcion(opcion);
        } while (opcion != 5);
    }

    // Displays the menu options
    private void mostrarMenu() {
        System.out.println("\nBienvenido a su cajero Groserón");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Retirar dinero");
        System.out.println("3. Depositar dinero");
        System.out.println("4. Transferir dinero");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Handles user option selection
    private void manejarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("Tu saldo es: $ " + cuenta.getSaldo());
                break;
            case 2:
                retirarDinero();
                break;
            case 3:
                depositarDinero();
                break;
            case 4:
                transferirDinero();
                break;
            case 5:
                System.out.println("Gracias por usar nuestro cajero. Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Intenta de nuevo.");
                break;
        }
    }

    // Withdraw money method
    private void retirarDinero() {
        System.out.print("Ingrese la cantidad a retirar: ");
        double retiro = scanner.nextDouble();
        if (cuenta.retirar(retiro)) {
            System.out.println("Retiro exitoso. Nuevo saldo: $ " + cuenta.getSaldo());
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    // Deposit money method
    private void depositarDinero() {
        System.out.print("Ingrese la cantidad a depositar: ");
        double deposito = scanner.nextDouble();
        cuenta.depositar(deposito);
        System.out.println("Depósito exitoso. Nuevo saldo: $ " + cuenta.getSaldo());
    }

    // Transfer money method
    private void transferirDinero() {
        System.out.print("Ingrese la cantidad a transferir: ");
        double monto = scanner.nextDouble();
        Cuenta destino = new Cuenta(0);
        if (cuenta.transferir(destino, monto)) {
            System.out.println("Transferencia exitosa. Nuevo saldo: $ " + cuenta.getSaldo());
        } else {
            System.out.println("Saldo insuficiente para la transferencia.");
        }
    }
}