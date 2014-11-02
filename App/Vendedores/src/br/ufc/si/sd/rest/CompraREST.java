package br.ufc.si.sd.rest;

import org.json.JSONException;
import org.json.JSONObject;
import br.ufc.si.sd.entidades.Compra;
import br.ufc.si.sd.util.WebServiceCliente;

public class CompraREST {

	private static final String URL_WS = "http://192.168.0.123:8181/ServicoVendedores/compra/";

	public String realizarCompra(Compra compra){
		JSONObject jo = new JSONObject();
		try {
			jo.put("id", compra.getId());
			jo.put("idVendedor", compra.getIdVendedor());
			jo.put("idCOmprador", compra.getIdComprador());
			jo.put("idProduto", compra.getIdProduto());
			jo.put("valorVenda", compra.getValorVenda());
			jo.put("quantidadeProduto", compra.getQuantidadeProduto());
			String compraJson = jo.toString();
			String[] respostaServidor = new WebServiceCliente().post(URL_WS+"nova", compraJson);
			return respostaServidor[1];
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return "";
	}
	
}
