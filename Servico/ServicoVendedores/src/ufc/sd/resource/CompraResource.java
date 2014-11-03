package ufc.sd.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@GET
	@Path("/compras-vendedor/{idComprador}")
	@Produces("application/json")
	public List<Compra> listarComprasDoVendedor(@PathParam("idComprador") long idComprador){
		return new CompraController().listarComprasDoVendedor(idComprador);
	}
	
	@GET
	@Path("/vendas-vendedor/{idVendedor}")
	@Produces("application/json")
	public List<Compra> listarVendasDoVendedor(@PathParam("idVendedor") long idVendedor){
		return new CompraController().listarVendasDoVendedor(idVendedor);
	} 
}