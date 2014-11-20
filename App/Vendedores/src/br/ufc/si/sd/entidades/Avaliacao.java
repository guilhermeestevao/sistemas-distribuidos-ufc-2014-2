package br.ufc.si.sd.entidades;

import java.io.Serializable;

public class Avaliacao implements Serializable{

	private long id;
	private long avaliadoId;
	private long avaliadorId;
	private long produtoId;
	private double avaliacao;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getAvaliadoId() {
		return avaliadoId;
	}
	
	public void setAvaliadoId(long avaliadoId) {
		this.avaliadoId = avaliadoId;
	}
	
	public long getAvaliadorId() {
		return avaliadorId;
	}
	
	public void setAvaliadorId(long avaliadorId) {
		this.avaliadorId = avaliadorId;
	}
	
	public long getProdutoId() {
		return produtoId;
	}
	
	public void setProdutoId(long produtoId) {
		this.produtoId = produtoId;
	}
	
	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

}
