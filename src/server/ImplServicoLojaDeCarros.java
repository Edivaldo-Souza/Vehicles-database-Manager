package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Categorias;
import model.Veiculo;

public class ImplServicoLojaDeCarros implements ServicoLojaDeCarros{
	private List<Veiculo> database;
	
	public ImplServicoLojaDeCarros() {
		database = new ArrayList<Veiculo>();
		database.add(new Veiculo(Categorias.ECONOMICO,"83948234534","Fiat Novo Uno",2010,11999.90));
		database.add(new Veiculo(Categorias.ECONOMICO,"00139308591","Chevrolet onix",2014,23999.90));
		database.add(new Veiculo(Categorias.ECONOMICO,"83290238491","Ford Ka",2012,18999.99));
		database.add(new Veiculo(Categorias.ECONOMICO,"74839124532","Ford Ka",2011,17999.99));
		database.add(new Veiculo(Categorias.ECONOMICO,"00238394823","hyundai hb20",2015,28999.99));
		database.add(new Veiculo(Categorias.INTERMEDIARIO,"93820024198","Ford Ka Sedan",2016,37989.99));
		database.add(new Veiculo(Categorias.INTERMEDIARIO,"77823091342","Chevrolet Onix plus",2016,45989.99));
		database.add(new Veiculo(Categorias.INTERMEDIARIO,"08392184833","Hyundai hb20s",2013,39789.99));
		database.add(new Veiculo(Categorias.INTERMEDIARIO,"09237582384","Hyundai hb20s",2013,31989.99));
		database.add(new Veiculo(Categorias.EXECUTIVO,"12047392857","Toyota corolla",2011,55789.99));
		database.add(new Veiculo(Categorias.EXECUTIVO,"90284821234","Honda civic",2017,55789.99));
		database.add(new Veiculo(Categorias.EXECUTIVO,"83749238753","Chevrolet cruze",2020,74999.99));
		database.add(new Veiculo(Categorias.EXECUTIVO,"02384721924","Audi a3",2022,83999.99));
	}
	@Override
	public Veiculo adicionar(Veiculo v) throws RemoteException {
		v.setDisponivel(true);
		database.add(v);
		return database.get(database.size()-1);
	}
	@Override
	public List<Veiculo> buscar(String renavam) throws RemoteException {
		List<Veiculo> resultado = new ArrayList<Veiculo>();
		for(Veiculo c : database) {
			if(c.getRenavam().equals(renavam) || c.getNome().equals(renavam)) {
				resultado.add(c);
			}
		}
		return resultado;
	}
	@Override
	public List<Veiculo> listar(String categoria) throws RemoteException {
		List<Veiculo> resultado = new ArrayList<Veiculo>();
		if(categoria.equals("ECONOMICO")) {
			for(Veiculo v : database) {
				if(v.getCategoria()==Categorias.ECONOMICO) resultado.add(v);
			}
		}
		else if(categoria.equals("INTERMEDIARIO")) {
			for(Veiculo v : database) {
				if(v.getCategoria()==Categorias.INTERMEDIARIO) resultado.add(v);
			}
		}
		else if(categoria.equals("EXECUTIVO")) {
			for(Veiculo v : database) {
				if(v.getCategoria()==Categorias.EXECUTIVO) resultado.add(v);
			}
		}
		else {
			for(Veiculo v : database) {
				resultado.add(v);
			}
		}
		Collections.sort(resultado);
		return resultado;
	}
	@Override
	public Veiculo atualizar(String renavam, Veiculo v) throws RemoteException {
		for(int i = 0; i<database.size(); i++) {
			if(database.get(i).getRenavam().equals(renavam)) {
				v.setRenavam(renavam);
				v.setDisponivel(database.get(i).isDisponivel());
				database.set(i, v);
				return v;
			}
		}
		return null;
	}
	@Override
	public boolean deletar(String v) throws RemoteException {
		for(int i = 0; i<database.size(); i++) {
			if(database.get(i).getRenavam().equals(v)) {
				database.remove(i);
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean comprar(String v) throws RemoteException {
		for(int i = 0; i<database.size(); i++) {
			if(database.get(i).getRenavam().equals(v) && database.get(i).isDisponivel()) {
				database.get(i).setDisponivel(false);;
				return true;
			}
		}
		return false;
	}
	@Override
	public int getQuantidade() throws RemoteException {
		
		return database.size();
	}
	
	
	

}
