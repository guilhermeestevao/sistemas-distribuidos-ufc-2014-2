package caseRemoto;

import java.io.Serializable;

public class Pontuacao implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nomePosicao;
	private int pontuacao;
	
	public Pontuacao() {
	}
	
	public String getNomePosicao() {
		return nomePosicao;
	}

	public void setNomePosicao(String nomePosicao) {
		this.nomePosicao = nomePosicao;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomePosicao == null) ? 0 : nomePosicao.hashCode());
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
		Pontuacao other = (Pontuacao) obj;
		if (nomePosicao == null) {
			if (other.nomePosicao != null)
				return false;
		} else if (!nomePosicao.equals(other.nomePosicao))
			return false;
		if (pontuacao != other.pontuacao)
			return false;
		return true;
	}
	
}
