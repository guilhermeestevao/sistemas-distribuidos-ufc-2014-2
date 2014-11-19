package caseRemoto;

import java.io.Serializable;

public class Navio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private int navio1;
	private int navio2;

	public Navio() {
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getNavio1() {
		return navio1;
	}
	public void setNavio1(int navio1) {
		this.navio1 = navio1;
	}
	
	public int getNavio2() {
		return navio2;
	}
	public void setNavio2(int navio2) {
		this.navio2 = navio2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + navio1;
		result = prime * result + navio2;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Navio other = (Navio) obj;
		if (navio1 != other.navio1)
			return false;
		if (navio2 != other.navio2)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
