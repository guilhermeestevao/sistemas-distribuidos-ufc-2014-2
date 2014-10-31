package ufc.sd.controller;

import java.util.List;
import ufc.sd.dao.UsuarioDAO;
import ufc.sd.dao.impl.UsuarioJPADAO;
import ufc.sd.modelo.Usuario;

public class UsuarioController {

	public List<Usuario> listarTodosUsuarios(){
		UsuarioDAO dao = new UsuarioJPADAO();
		return dao.find();
	}
	
	public String cadastrarUsuario(Usuario usuario){
		UsuarioDAO dao = new UsuarioJPADAO();
		try{
		dao.beginTransaction();
		dao.save(usuario);
		dao.commit();
		return "Usuário cadastrado com sucesso!";
		}catch(Exception e){
			
			return e.getMessage();
		}
	}
}
	