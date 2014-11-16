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
	
	public String atualizarUsuario(Usuario usuario){
		UsuarioDAO dao = new UsuarioJPADAO();
		try{
		dao.beginTransaction();
		System.out.println("Atualizar");
		System.out.println(usuario.getId());
		System.out.println(usuario.getNome());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getLat());
		System.out.println(usuario.getLng());
		dao.update(usuario);
		dao.commit();
		return "Usuário atualizado com sucesso!";
		}catch(Exception e){
			
			return e.getMessage();
		}
	}
	
	public boolean verificaUsuario(long id){
		UsuarioDAO dao = new UsuarioJPADAO();
		boolean status = dao.verificaUsuario(id);
		return status;
	}
	
	public Usuario getUsuario(long idUsuario){
		return new UsuarioJPADAO().find(idUsuario);
	}
	
	
	
}
	