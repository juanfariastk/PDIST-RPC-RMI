package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private static final long serialVersionUID = 1L;
	private final Map<String, Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new HashMap<>();
        contas.put("1", new Conta("1", 100.0));
        contas.put("2", new Conta("2", 156.0));
        contas.put("3", new Conta("3", 950.0));
    }

    @Override
    public double saldo(String numero) throws RemoteException {
        Conta conta = contas.get(numero);
        return (conta != null) ? conta.getSaldo() : 0;
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta(String numero, double saldo) throws RemoteException {
        contas.put(numero, new Conta(numero, saldo));
    }

    @Override
    public Conta pesquisarConta(String numero) throws RemoteException {
        return contas.get(numero);
    }

    @Override
    public boolean removerConta(String numero) throws RemoteException {
        return contas.remove(numero) != null;
    }
}
