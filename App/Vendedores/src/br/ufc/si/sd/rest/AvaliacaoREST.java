package br.ufc.si.sd.rest;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufc.si.sd.entidades.Avaliacao;
import br.ufc.si.sd.util.WebServiceCliente;

public class AvaliacaoREST {
	
	private static final String URL_WS = "http://192.168.0.118:8080/ServicoVendedores/avaliacao/";
	
	public String avaliar(Avaliacao avaliacao){
		JSONObject jo = new JSONObject();
		try {
			jo.put("id", avaliacao.getId());
			jo.put("avaliadoId", avaliacao.getAvaliadoId());
			jo.put("avaliadorId", avaliacao.getAvaliadorId());
			jo.put("produtoId", avaliacao.getProdutoId());
			jo.put("avaliacao", avaliacao.getAvaliacao());
			
			String avaliacaoJson = jo.toString();
			String[] respostaServidor = new WebServiceCliente().post(URL_WS+"nova", avaliacaoJson);
			return respostaServidor[1];
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public double obterAvaliacao(long id){
		String[] respostaServidor = new WebServiceCliente().get(URL_WS+"usuario/"+id);
		if(respostaServidor[0].equals("200"))
			return Double.parseDouble(respostaServidor[1]);
		 
		return 0;
	}
	
	
}
