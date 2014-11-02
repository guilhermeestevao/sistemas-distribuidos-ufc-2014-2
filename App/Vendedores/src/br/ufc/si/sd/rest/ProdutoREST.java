package br.ufc.si.sd.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.ufc.si.sd.Produto;
import br.ufc.si.sd.Usuario;
import br.ufc.si.sd.util.WebServiceCliente;

public class ProdutoREST {

	private static final String URL_WS = "http://192.168.0.123:8181/ServicoVendedores/produtos/";

	public String cadastrarProduto(Produto produto){
		JSONObject jo = new JSONObject();
		try {
			jo.put("id", produto.getId());
			jo.put("nome", produto.getNome());
			jo.put("descricao", produto.getDescricao());
			jo.put("quantidade", produto.getQuantidade());
			jo.put("preco", produto.getPreco());
			jo.put("usuarioId", produto.getUsuarioId());
			String produtoJson = jo.toString();
			String[] respostaServidor = new WebServiceCliente().post(URL_WS+"novo", produtoJson);
			return respostaServidor[1];
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public List<Produto> listarProdutosPorUsuario(Usuario usuario){
		String[] resposta = new WebServiceCliente().get(URL_WS + usuario.getId());
		if(resposta[0].equals("200")){
			List<Produto> produtos = getProdutos(resposta[1]);
			return produtos;
		}else{
			return new ArrayList<Produto>();
		}
		
	}

	public String deletarProdito(Produto produto){
		String[] resposta = new WebServiceCliente().delete(URL_WS +"deletar/"+produto.getId());
		return resposta[1];
	}
	
	public String atualizarProduto(Produto produto){
		JSONObject jo = new JSONObject();
		try {
			jo.put("id", produto.getId());
			jo.put("nome", produto.getNome());
			jo.put("descricao", produto.getDescricao());
			jo.put("quantidade", produto.getQuantidade());
			jo.put("preco", produto.getPreco());
			jo.put("usuarioId", produto.getUsuarioId());
			String produtoJson = jo.toString();
			Log.i("produto json", produtoJson);
			String[] respostaServidor = new WebServiceCliente().put(URL_WS+"atualizar", produtoJson);
			return respostaServidor[1];
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	
	
	private List<Produto> getProdutos(String json) {
		
		List<Produto> produtos = new ArrayList<Produto>();
		JSONObject jasonObject;
		try{
			jasonObject = new JSONObject(json);
			JSONArray jarray = jasonObject.getJSONArray("produto");
			
			JSONObject produto;

			for (int i = 0; i < jarray.length(); i++) {
				produto = new JSONObject(jarray.getString(i));
				Produto produtoAux = new Produto();
				produtoAux.setId(Long.parseLong(produto.getString("id")));
				produtoAux.setNome(produto.getString("nome"));
				produtoAux.setDescricao(produto.getString("descricao"));
				produtoAux.setPreco(Double.parseDouble(produto.getString("preco")));
				produtoAux.setQuantidade(Integer.parseInt(produto.getString("quantidade")));
				produtos.add(produtoAux);
			}
			
		}catch(JSONException e){
			try {
				Log.i(this.getClass().getSimpleName(), json);
				JSONObject produto1 = new JSONObject(json);
				Produto produtoAux = new Produto();
				JSONObject produto = produto1.getJSONObject("produto");
				
				produtoAux.setNome(produto.getString("nome"));
				produtoAux.setId(Long.parseLong(produto.getString("id")));
				produtoAux.setDescricao(produto.getString("descricao"));
				produtoAux.setPreco(Double.parseDouble(produto.getString("preco")));
				produtoAux.setQuantidade(Integer.parseInt(produto.getString("quantidade")));
				produtoAux.setUsuarioId(Long.parseLong(produto.getString("usuarioId")));
				produtos.add(produtoAux);
				
			} catch (JSONException e1) {
				
				Log.i(this.getClass().getSimpleName(), e1.getMessage());
			}
			
		}finally{
			return produtos;
		}
	}
	
	
}
