package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
	public static void main(String args[]) {
		try {
			ImplAutenticador refObjetoRemoto = new ImplAutenticador();
			ImplServicoLojaDeCarros refObjRemotoServico = new ImplServicoLojaDeCarros();
			Autenticador skeleton = (Autenticador) UnicastRemoteObject.exportObject(refObjetoRemoto, 0);
			ServicoLojaDeCarros skeletonServico = (ServicoLojaDeCarros)
					UnicastRemoteObject.exportObject(refObjRemotoServico, 1);
			
			LocateRegistry.createRegistry(20003);
			Registry registro = LocateRegistry.getRegistry(20003);
			
			registro.bind("Autenticador", skeleton);
			registro.bind("ServicoLojaDeCarros", skeletonServico);
			System.out.println("Servidor pronto:");
		} catch (Exception e) {
			System.err.println("Servidor: " + e.toString());
			e.printStackTrace();
		}
	}
}
