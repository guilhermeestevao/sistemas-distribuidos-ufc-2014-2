package caseRemoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CaseServente extends UnicastRemoteObject implements CaseRemoto{

	private static final long serialVersionUID = 1L;

	public static final int SUBMARINO = 0;
	public static final int TORPEDEIRO = 1;
	public static final int CRUZADOR = 2;
	public static final int COURADORO = 3;
	public static final int PORTA_AVIOES = 4;

	private List<String> posicoes;
	
	protected CaseServente() throws RemoteException {
		super();
	}


	public List<String> getPosicoes() {
		return posicoes;
	}

	public void setPosicoes(List<String> posicoes) {
		this.posicoes = posicoes;
	}
	
	@Override
	public String[] posicionarBatalhaNaval(List<Navio> navios)
			throws RemoteException {
		String posicoesNavio[] = new String[100];
		
		for(int i=0;i<100;i++){
			posicoesNavio[i] = "erro";
		}
		
		for (Navio n : navios) {
			if(n.getNome().equals("submarino")){
				posicoesNavio[n.getNavio1()] = "submarino";
				posicoesNavio[n.getNavio2()] = "submarino";
			}else if(n.getNome().equals("torpedeiro")){
				posicoesNavio[n.getNavio1()] = "left_torpedeiro";
				posicoesNavio[n.getNavio1()+1] = "right_torpedeiro";
				posicoesNavio[n.getNavio2()] = "left_torpedeiro";
				posicoesNavio[n.getNavio2()+1] = "right_torpedeiro";
			}else if(n.getNome().equals("cruzador")){
				posicoesNavio[n.getNavio1()] = "left_cruzador";
				posicoesNavio[n.getNavio1()+1] = "center_cruzador";
				posicoesNavio[n.getNavio1()+2] = "right_cruzador";
				posicoesNavio[n.getNavio2()] = "left_cruzador";
				posicoesNavio[n.getNavio2()+1] = "center_cruzador";
				posicoesNavio[n.getNavio2()+2] = "right_cruzador";
			}else if(n.getNome().equals("couradoro")){
				posicoesNavio[n.getNavio1()] = "left_couradoro";
				posicoesNavio[n.getNavio1()+1] = "center_couradoro";
				posicoesNavio[n.getNavio1()+2] = "center_couradoro";
				posicoesNavio[n.getNavio1()+3] = "right_couradoro";
				posicoesNavio[n.getNavio2()] = "left_couradoro";
				posicoesNavio[n.getNavio2()+1] = "center_couradoro";
				posicoesNavio[n.getNavio2()+2] = "center_couradoro";
				posicoesNavio[n.getNavio2()+3] = "right_couradoro";
			}else if(n.getNome().equals("porta_avioes")){
				posicoesNavio[n.getNavio1()] = "left_porta_avioes";
				posicoesNavio[n.getNavio1()+1] = "center_porta_avioes";
				posicoesNavio[n.getNavio1()+2] = "center_porta_avioes";
				posicoesNavio[n.getNavio1()+3] = "center_porta_avioes";
				posicoesNavio[n.getNavio1()+4] = "right_porta_avioes";
				posicoesNavio[n.getNavio2()] = "left_porta_avioes";
				posicoesNavio[n.getNavio2()+1] = "center_porta_avioes";
				posicoesNavio[n.getNavio2()+2] = "center_porta_avioes";
				posicoesNavio[n.getNavio2()+3] = "center_porta_avioes";
				posicoesNavio[n.getNavio2()+4] = "right_porta_avioes";
			}
		}
		
		return posicoesNavio;
	}


	@Override
	public String mensagemGameOver(int pontuacao, String user) throws RemoteException {
		return user+", você obteve uma pontuação de: "+pontuacao+" pontos!";
	}

	

}
