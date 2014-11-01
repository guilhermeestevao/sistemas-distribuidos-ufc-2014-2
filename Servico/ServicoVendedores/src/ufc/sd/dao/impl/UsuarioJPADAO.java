package ufc.sd.dao.impl;

import ufc.sd.dao.UsuarioDAO;
import ufc.sd.modelo.Usuario;

public class UsuarioJPADAO extends GenericJPADAO<Usuario> implements UsuarioDAO{

	public UsuarioJPADAO() {
		// TODO Auto-generated constructor stub
		super();
		this.persistentClass = Usuario.class;
	}

	@Override
	public boolean verificaUsuario(long id) {
		try{
			Usuario usuario = em.find(Usuario.class, id);
			
			if(usuario != null)
				return true;
		}catch(Exception e){
			return false;
		}
		
		return false;
	}

	
}
