package br.com.sky.banco;

public class ContaInvalidaException extends Exception {

    public ContaInvalidaException(int numero) {
        super("Conta " + numero + " inválida!");
    }
}
