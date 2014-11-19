package caseRemoto;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final int SUBMARINO = 0;
	public static final int TORPEDEIRO = 1;
	public static final int CRUZADOR = 2;
	public static final int COURADORO = 3;
	public static final int PORTA_AVIOES = 4;

	private String nome;
	private int pontuacao;
	private int qtdNavios[];
	
	public User() {
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}


	public int[] getQtdNavios() {
		return qtdNavios;
	}

	public void setQtdNavios(int qtdNavios[]) {
		this.qtdNavios = qtdNavios;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + pontuacao;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pontuacao != other.pontuacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [nome=" + nome + ", pontuacao=" + pontuacao + "]";
	}
	
}
