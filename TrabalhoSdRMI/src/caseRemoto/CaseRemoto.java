package caseRemoto;
import java.rmi.*;
import java.util.List;

public interface CaseRemoto extends Remote{

	public String[] posicionarBatalhaNaval(List<Navio> navios) throws RemoteException;
	
	public String mensagemGameOver(int pontuacao, String user) throws RemoteException;
	
}
