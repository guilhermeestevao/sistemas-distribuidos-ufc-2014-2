package ufc.sd.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="usuarios")
@XmlRootElement
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String nome;
	private String email;
	@OneToMany(mappedBy="usuario")
	private List<Produto> itens;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.itens = new ArrayList<Produto>();
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
	
	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
	
}
