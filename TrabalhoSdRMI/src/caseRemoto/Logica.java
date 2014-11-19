package caseRemoto;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Logica {

	private CaseRemoto iniciarServidor(){
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
	
	public String[] posicionarTabuleiro(List<Navio> navios) {

		CaseRemoto remote = iniciarServidor();
		String nav[];
		try {
			nav = remote.posicionarBatalhaNaval(navios);
			return nav;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String msgGameOver(int pontuacao, String user) {

		CaseRemoto remote = iniciarServidor();
		String msg;
		try {
			msg = remote.mensagemGameOver(pontuacao, user);
			return msg;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
