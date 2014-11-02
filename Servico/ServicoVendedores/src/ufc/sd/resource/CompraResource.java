package ufc.sd.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ufc.sd.controller.CompraController;
import ufc.sd.modelo.Compra;

@Path("/compra")
public class CompraResource {
	@POST
	@Path("/nova")
	@Produces("text/plain")
	@Consumes("application/json")
	public String cadastrarCompra(Compra compra){
		return new CompraController().cadastrarCompra(compra);
	}
	
	
}
