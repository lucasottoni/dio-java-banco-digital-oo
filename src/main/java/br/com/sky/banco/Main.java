package br.com.sky.banco;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco("DIO");
        Scanner leitor = new Scanner(System.in);

        String opcaoUsuario = obterOpcaoUsuario(leitor);

        while (!Objects.equals(opcaoUsuario, "X"))
        {
            switch (opcaoUsuario)
            {
                case "1":
                    listarContas(banco);
                    break;
                case "2":
                    criarConta(leitor, banco);
                    break;
                case "3":
                    transferir(leitor, banco);
                    break;
                case "4":
                    sacar(leitor, banco);
                    break;
                case "5":
                    depositar(leitor, banco);
                    break;

                default:
                    throw new IllegalArgumentException();
            }

            opcaoUsuario = obterOpcaoUsuario(leitor);
        }

        System.out.println("Obrigado por utilizar nossos serviços.");
    }

    private static void depositar(Scanner leitor, Banco banco)
    {
        System.out.println("Digite o número da conta: ");
        int numeroConta = Integer.parseInt(leitor.nextLine());

        System.out.println("Digite o valor a ser depositado: ");
        double valorDeposito = Double.parseDouble(leitor.nextLine());

        try {
            Conta c = banco.buscarConta(numeroConta);
            c.depositar(valorDeposito);
        } catch (ContaInvalidaException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void sacar(Scanner leitor, Banco banco)
    {
        System.out.println("Digite o número da conta: ");
        int numeroConta = Integer.parseInt(leitor.nextLine());

        System.out.println("Digite o valor a ser sacado: ");
        double valorSaque = Double.parseDouble(leitor.nextLine());

        try {
            Conta c = banco.buscarConta(numeroConta);
            c.sacar(valorSaque);
        } catch (ContaInvalidaException | SaldoInsuficienteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void transferir(Scanner leitor, Banco banco)
    {
        System.out.println("Digite o número da conta de origem: ");
        int numeroContaOrigem = Integer.parseInt(leitor.nextLine());

        System.out.println("Digite o número da conta de destino: ");
        int numeroContaDestino = Integer.parseInt(leitor.nextLine());

        System.out.println("Digite o valor a ser transferido: ");
        double valorTransferencia = Double.parseDouble(leitor.nextLine());


        try {
            Conta origem = banco.buscarConta(numeroContaOrigem);
            Conta destino = banco.buscarConta(numeroContaDestino);
            origem.transferir(valorTransferencia, destino);
        } catch (ContaInvalidaException | SaldoInsuficienteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void criarConta(Scanner leitor, Banco banco)
    {
        System.out.println("Criar nova conta");

        System.out.println("Digite 1 para Conta Corrente ou 2 para Poupança: ");
        int entradaTipoConta = Integer.parseInt(leitor.nextLine());

        System.out.println("Digite o Nome do Cliente: ");
        String entradaNome = leitor.nextLine();

        Cliente cliente = new Cliente(entradaNome);
        Conta novaConta = entradaTipoConta == 1 ?
                new ContaCorrente(cliente) :
                new ContaPoupanca(cliente);

        banco.adicionarConta(novaConta);

        System.out.println("Conta adicionada");
    }

    private static void listarContas(Banco banco)
    {
        System.out.println("Listar contas do banco " + banco.getNome());

        List<Conta> contas = banco.getContas();
        if (contas.size() == 0)
        {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        for (Conta c: contas) {
            String tipo = c instanceof ContaCorrente ? "Conta Corrente" : "Conta Poupança";
            System.out.printf("#%d - %s - %s%n", c.getNumero(), tipo, c.getCliente().getNome());
            c.imprimirInfosComuns();
        }

    }

    private static String obterOpcaoUsuario(Scanner leitor)
    {
        System.out.println();
        System.out.println("DIO Bank a seu dispor!!!");
        System.out.println("Informe a opção desejada:");

        System.out.println("1- Listar contas");
        System.out.println("2- Inserir nova conta");
        System.out.println("3- Transferir");
        System.out.println("4- Sacar");
        System.out.println("5- Depositar");
        System.out.println("X- Sair");
        System.out.println();

        String opcaoUsuario = leitor.nextLine().toUpperCase();
        System.out.println();
        return opcaoUsuario;
    }
}
