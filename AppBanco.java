package com.gugawag.rpc.banco;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppBanco {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    	System.setProperty("java.rmi.server.hostname", "10.0.72.70");
        BancoServiceIF bancoService = new BancoServiceServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("BancoService", bancoService);
        System.out.println("Service de banco registrado ....");
        System.out.println("Servidor iniciado por Juan Farias");
    }
}
