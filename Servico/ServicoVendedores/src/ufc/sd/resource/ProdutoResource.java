package ufc.sd.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ufc.sd.controller.ProdutoController;
import ufc.sd.modelo.Produto;

@Path("/produto")
public class ProdutoResource {
	
	@POST
	@Path("/novo")
	@Produces("application/json")
	public String cadastrarItem(Produto item){
		return new ProdutoController().cadastrarItem(item);
	}
	
	
	
	
	
}
