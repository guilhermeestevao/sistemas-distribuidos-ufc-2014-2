package br.ufc.si.sd.rest;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.ufc.si.sd.Produto;
import br.ufc.si.sd.util.WebServiceCliente;

public class ProdutoREST {

	private static final String URL_WS = "http://192.168.0.123:8080/ServicoVendedores/produto/";

	public String cadastrarProduto(Produto produto){
		JSONObject jo = new JSONObject();
		try {
			jo.put("id", produto.getId());
			jo.put("nome", produto.getNome());
			jo.put("descricao", produto.getDescricao());
			jo.put("quantidade", produto.getQuantidade());
			jo.put("preco", produto.getPreco());
			jo.put("usuario", produto.getUsuario());
			String produtoJson = jo.toString();
			String[] respostaServidor = new WebServiceCliente().post(URL_WS+"novo", produtoJson);
			return respostaServidor[1];
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
}
