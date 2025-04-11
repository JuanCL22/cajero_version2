package cajero;

public class Cuenta {
    private final int PIN_CORRECTO = 1234;
    private double saldo;
    private int intentos;
    private boolean cuentaBloqueada;

    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
        this.intentos = 0;
        this.cuentaBloqueada = false;
    }

    public boolean autenticar(int pin) {
        if (pin == PIN_CORRECTO) {
            System.out.println("Bienvenido Pichurria");
            return true;
        } else {
            intentos++;
            System.out.println("PIN incorrecto. Mosca pues!!! Intento: " + intentos);
            if (intentos == 3) {
                cuentaBloqueada = true;
                System.out.println("Mera G** cuenta bloqueada");
            }
            return false;
        }
    }

    public boolean isCuentaBloqueada() {
        return cuentaBloqueada;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        }
    }

    public boolean transferir(Cuenta destino, double cantidad) {
        if (retirar(cantidad)) {
            destino.depositar(cantidad);
            return true;
        }
        return false;
    }
}