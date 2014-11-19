package caseRemoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;

public class CaseServente extends UnicastRemoteObject implements CaseRemoto{

	private static final long serialVersionUID = 1L;

	private List<String> posicoes; 
	private String posicoesNavio[];
	
	protected CaseServente() throws RemoteException {
		super();
	}

	public List<String> getPosicoes() {
		return posicoes;
	}

	public void setPosicoes(List<String> posicoes) {
		this.posicoes = posicoes;
	}
	
	public String[] getPosicoesNavio() {
		return posicoesNavio;
	}

	public void setPosicoesNavio(String posicoesNavio[]) {
		this.posicoesNavio = posicoesNavio;
	}
	
	@Override
	public String mensagemGameOver(int pontuacao, String user, int retorno) throws RemoteException {
		if(retorno == 0)
			return user+", você atingiu o número máximo de tentativas!\n"
					+ "Sua pontuação é: "+pontuacao+" pontos!";
		else
			return user+", você conseguiu completar o jogo!\n"
					+ "Sua pontuação é: "+pontuacao+" pontos!";
	}

	@Override
	public User criarUsuario(String nome) throws RemoteException {
		
		User user = new User();
		user.setNome(nome);
		user.setPontuacao(0);
		posicoesNavio = new String[100];
		
		Random random = new Random();
		
		int qtdSubmarino = 0;
		int qtdTorpedeiro = 0;
		int qtdCruzador = 0;
		int qtdCouradoro = 0;
		int qtdPortaAvioes = 0;
		
		for(int i=0; i<100; i++){
			int r = random.nextInt(25);
			if(r == user.SUBMARINO){
				posicoesNavio[i] = "submarino";
				qtdSubmarino += 1;
			}else if(r == user.TORPEDEIRO && i%10 < 9){
				posicoesNavio[i] = "left_torpedeiro";
				posicoesNavio[i+1] = "right_torpedeiro";
				i += 1;
				qtdTorpedeiro += 1;
			}else if(r == user.CRUZADOR && i%10 < 8){
				posicoesNavio[i] = "left_cruzador";
				posicoesNavio[i+1] = "center_cruzador";
				posicoesNavio[i+2] = "right_cruzador";
				i += 2;
				qtdCruzador += 2;
			}else if(r == user.COURADORO && i%10 < 7){
				posicoesNavio[i] = "left_couradoro";
				posicoesNavio[i+1] = "center_couradoro";
				posicoesNavio[i+2] = "center_couradoro";
				posicoesNavio[i+3] = "right_couradoro";
				i += 3;
				qtdCouradoro += 1;
			}else if(r == user.PORTA_AVIOES && i%10 < 6){
				posicoesNavio[i] = "left_porta_avioes";
				posicoesNavio[i+1] = "center_porta_avioes";
				posicoesNavio[i+2] = "center_porta_avioes";
				posicoesNavio[i+3] = "center_porta_avioes";
				posicoesNavio[i+4] = "right_porta_avioes";
				i += 4;
				qtdPortaAvioes += 1;
			}else {
				posicoesNavio[i] = "erro";
			}
		}
		
		int qtdNavios[] = new int[5];
		qtdNavios[user.SUBMARINO] = qtdSubmarino;
		qtdNavios[user.TORPEDEIRO] = qtdTorpedeiro;
		qtdNavios[user.CRUZADOR] = qtdCruzador;
		qtdNavios[user.COURADORO] = qtdCouradoro;
		qtdNavios[user.PORTA_AVIOES] = qtdPortaAvioes;
		
		user.setQtdNavios(qtdNavios);
		
		for(int i=0; i<100; i++){
			System.out.println(i + " " + posicoesNavio[i]);
		}
		
		return user;
	}

	@Override
	public Pontuacao verificarPosicao(int posicao) throws RemoteException {
		
		Pontuacao pontuacao = new Pontuacao();
		
		pontuacao.setNomePosicao(posicoesNavio[posicao]);
		
		if(posicoesNavio[posicao].contains("submarino")){
			pontuacao.setPontuacao(5);
		}else if(posicoesNavio[posicao].contains("torpedeiro")){
			pontuacao.setPontuacao(4);
		}else if(posicoesNavio[posicao].contains("cruzador")){
			pontuacao.setPontuacao(3);
		}else if(posicoesNavio[posicao].contains("couradoro")){
			pontuacao.setPontuacao(2);
		}else if(posicoesNavio[posicao].contains("porta_avioes")){
			pontuacao.setPontuacao(1);
		}else{
			pontuacao.setPontuacao(0);
		}
		
		return pontuacao;
	
	}
	

}
