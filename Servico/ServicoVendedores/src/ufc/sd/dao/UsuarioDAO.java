package ufc.sd.dao;

import ufc.sd.modelo.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public boolean verificaUsuario(long id);
	
}
