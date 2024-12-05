package com.gugawag.rpc.banco;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException {

        String ipServidor = args.length > 0 ? args[0] : "10.0.72.70";
        Registry registry = LocateRegistry.getRegistry(ipServidor, 1099);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        Scanner entrada = new Scanner(System.in);
        int opcao;
        do {
            menu();
            opcao = entrada.nextInt();
            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite o número da conta:");
                    String numeroConta = entrada.next();
                    System.out.println("Saldo: " + banco.saldo(numeroConta));
                }
                case 2 -> System.out.println("Total de contas: " + banco.quantidadeContas());
                case 3 -> {
                    System.out.println("Digite o número da nova conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial:");
                    double saldo = entrada.nextDouble();
                    banco.adicionarConta(numero, saldo);
                    System.out.println("Conta adicionada com sucesso!");
                }
                case 4 -> {
                    System.out.println("Digite o número da conta para pesquisar:");
                    String numero = entrada.next();
                    Conta conta = banco.pesquisarConta(numero);
                    System.out.println((conta != null) ? conta : "Conta não encontrada.");
                }
                case 5 -> {
                    System.out.println("Digite o número da conta a ser removida:");
                    String numero = entrada.next();
                    System.out.println(banco.removerConta(numero) ? "Conta removida!" : "Conta não encontrada.");
                }
            }
        } while (opcao != 6);
    }

    private static void menu() {
        System.out.println("\n ----- Banco -----");
        System.out.println("1 - Consultar Saldo");
        System.out.println("2 - Quantidade de Contas");
        System.out.println("3 - Adicionar Conta");
        System.out.println("4 - Pesquisar Conta");
        System.out.println("5 - Remover Conta");
        System.out.println("6 - Sair");
    }
}
