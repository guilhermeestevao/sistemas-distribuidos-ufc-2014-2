package ufc.sd.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ufc.sd.controller.ProdutoController;
import ufc.sd.modelo.Produto;

@Path("/produtos")
public class ProdutoResource {
	
	@POST
	@Path("/novo")
	@Produces("text/plain")
	@Consumes("application/json")
	public String cadastrarProduto(Produto produto){	
		return new ProdutoController().cadastrarProduto(produto);
	}
	
	@GET
	@Path("/{idUsuario}")
	@Produces("application/json")
	public List<Produto> ListarProdutosPorUsuario(@PathParam("idUsuario") long idUsuario){
		return new ProdutoController().listarProdutosPorUsuario(idUsuario);
	}
	
	@DELETE
	@Path("/deletar/{idProduto}")
	@Produces("text/plain")
	public String deletarProduto(@PathParam("idProduto") long id){
		return new ProdutoController().deletarProduto(id);
	}
		
	@PUT
	@Path("/atualizar/")
	@Consumes("application/json")
	@Produces("text/plain")
	public String atualizarProduto(Produto produto){
		return new ProdutoController().atualizarProduto(produto);
	}
}