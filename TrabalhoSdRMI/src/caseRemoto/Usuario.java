package caseRemoto;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private List<Navio> navio;
	
	public Usuario() {
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Navio> getNavio() {
		return navio;
	}

	public void setNavio(List<Navio> navio) {
		this.navio = navio;
	}
	
}
