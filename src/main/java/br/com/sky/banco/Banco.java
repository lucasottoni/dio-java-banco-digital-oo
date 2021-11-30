package br.com.sky.banco;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Banco {

    private final String nome;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public Conta buscarConta(int numeroConta) throws ContaInvalidaException {
        for (Conta c: contas) {
            if (c.getNumero() == numeroConta) return c;
        }

        throw new ContaInvalidaException(numeroConta);
    }
}

