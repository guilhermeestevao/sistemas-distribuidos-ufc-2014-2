package caseRemoto;
import java.rmi.*;
import java.util.List;

public interface CaseRemoto extends Remote{
	
	public String mensagemGameOver(int pontuacao, String user, int retorno) throws RemoteException;
	
	public User criarUsuario(String nome) throws RemoteException;
	
	public Pontuacao verificarPosicao(int posicao) throws RemoteException;
	
}
