package br.ufc.si.sd.entidades;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private String email;
	private double lat;
	private double lng;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}
	
}