package br.com.sky.banco;

public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException() {
        super("Saldo da conta insuficiente");
    }
}
