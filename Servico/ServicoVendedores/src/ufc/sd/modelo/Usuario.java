package ufc.sd.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author guilherme
 *
 */
@Entity
@Table(name="usuarios")
@XmlRootElement
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String nome;
	private String email;
	private double lat;
	private double lng;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(long id, String nome, String email, double lat, double lng) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.lat =lat;
		this.lng = lng;
	}
	
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
		return nome;
	}
	
	
	
}
