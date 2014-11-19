package caseRemoto;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Logica {

	private CaseRemoto iniciarServidor() {
		CaseRemoto refRemota = null;
		try {
			refRemota = (CaseRemoto) Naming.lookup("//localhost/caseServer");
			System.out.println("Servidor encontrado");
			return refRemota;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public User criarUsuario(String nome) {
		CaseRemoto remote = iniciarServidor();
		try {
			User user = remote.criarUsuario(nome);
			return user;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Pontuacao verificarPosicao(int posicao){
		CaseRemoto remote = iniciarServidor();
		try {
			Pontuacao pontuacao = remote.verificarPosicao(posicao);
			return pontuacao;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String msgGameOver(int pontuacao, String user, int retorno) {

		CaseRemoto remote = iniciarServidor();
		String msg;
		try {
			msg = remote.mensagemGameOver(pontuacao, user, retorno);
			return msg;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
