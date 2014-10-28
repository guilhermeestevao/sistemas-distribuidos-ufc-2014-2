package ufc.sd.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ufc.sd.controller.UsuarioController;
import ufc.sd.modelo.Usuario;

@Path("/usuario")
public class UsuarioResource {

	@GET
	@Path("/listar-todos")
	@Produces("application/json")
	public List<Usuario> listarTodos(){
		return new UsuarioController().listarTodosUsuarios();
	}
	
	@POST
	@Path("/novo")
	@Produces("text/plain")
	@Consumes("application/json")
	public String cadastrar(Usuario usuario){
		return new UsuarioController().cadastrarUsuario(usuario);
	}
	
}