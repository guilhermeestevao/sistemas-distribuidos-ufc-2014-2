package ufc.sd.dao.impl;

import ufc.sd.dao.UsuarioDAO;
import ufc.sd.modelo.Usuario;

public class UsuarioJPADAO extends GenericJPADAO<Usuario> implements UsuarioDAO{

	public UsuarioJPADAO() {
		// TODO Auto-generated constructor stub
		super();
		this.persistentClass = Usuario.class;
	}
	
}
