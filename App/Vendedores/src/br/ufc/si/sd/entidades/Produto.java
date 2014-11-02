package br.ufc.si.sd.entidades;

import java.io.Serializable;

public class Produto implements Serializable{

	private static final long serialVersionUID = 2L;
	private Long id;
	private String nome;
	private String descricao;
	private int quantidade;
	private double preco;
	private long usuarioId;
	
	public Produto() {
	
	}
	
	public Produto(Long id, String nome, String descricao, int quantidade,
			double preco, long usuarioId) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.usuarioId = usuarioId;
	}

	
	


	public Produto(String nome, String descricao, int quantidade, double preco,
			long usuarioId) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.usuarioId = usuarioId;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return nome;
	}
	
}
