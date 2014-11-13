package br.ufc.si.sd.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.ufc.si.sd.entidades.Compra;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.util.WebServiceCliente;

public class CompraREST {

	private static final String URL_WS = "http://200.129.38.181:8080/ServicoVendedores/compra/";

	public String realizarCompra(Compra compra){
		JSONObject jo = new JSONObject();
		try {
			jo.put("id", compra.getId());
			jo.put("idVendedor", compra.getIdVendedor());
			jo.put("idComprador", compra.getIdComprador());
			jo.put("idProduto", compra.getIdProduto());
			jo.put("valorVenda", compra.getValorVenda());
			jo.put("quantidadeProduto", compra.getQuantidadeProduto());
			jo.put("data", compra.getData());
			
			String compraJson = jo.toString();
			String[] respostaServidor = new WebServiceCliente().post(URL_WS+"nova", compraJson);
			return respostaServidor[1];
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	public List<Compra> comprasDoUsuario(Usuario usuario){
		String[] resposta = new WebServiceCliente().get(URL_WS +"compras-usuario/"+ usuario.getId());
		if(resposta[0].equals("200")){
			List<Compra> compras = getCompras(resposta[1]);
			return compras;
		}else{
			return new ArrayList<Compra>();
		}
	}
	
	public List<Compra> vendasDoUsuario(Usuario usuario){
		String[] resposta = new WebServiceCliente().get(URL_WS +"vendas-usuario/"+ usuario.getId());
		if(resposta[0].equals("200")){
			List<Compra> compras = getCompras(resposta[1]);
			return compras;
		}else{
			return new ArrayList<Compra>();
		}
	}
	
	
	private List<Compra> getCompras(String jsonCompras){
		List<Compra> compras = new ArrayList<Compra>();
		JSONObject jasonObject;
		try{
			jasonObject = new JSONObject(jsonCompras);
			JSONArray jarray = jasonObject.getJSONArray("compra");
			JSONObject compra;
			for (int i = 0; i < jarray.length(); i++) {
				compra = new JSONObject(jarray.getString(i));
				Compra compraAux = new Compra();
				compraAux.setId(Long.parseLong(compra.getString("id")));
				compraAux.setIdComprador(Long.parseLong(compra.getString("idComprador")));
				compraAux.setIdVendedor(Long.parseLong(compra.getString("idVendedor")));
				compraAux.setIdProduto(Long.parseLong(compra.getString("idProduto")));
				compraAux.setQuantidadeProduto(Integer.parseInt(compra.getString("quantidadeProduto")));
				compraAux.setValorVenda(Double.parseDouble(compra.getString("valorVenda")));
				compraAux.setData(toDate(compra.getString("data")));
				compras.add(compraAux);
			}
		}catch(JSONException e){
			try {
				JSONObject compra1 = new JSONObject(jsonCompras);
				JSONObject compra = compra1.getJSONObject("compra");
				Compra compraAux = new Compra();
				compraAux.setId(Long.parseLong(compra.getString("id")));
				compraAux.setIdComprador(Long.parseLong(compra.getString("idComprador")));
				compraAux.setIdVendedor(Long.parseLong(compra.getString("idVendedor")));
				compraAux.setIdProduto(Long.parseLong(compra.getString("idProduto")));
				compraAux.setQuantidadeProduto(Integer.parseInt(compra.getString("quantidadeProduto")));
				compraAux.setValorVenda(Double.parseDouble(compra.getString("valorVenda")));
				compraAux.setData(toDate(compra.getString("data")));
				compras.add(compraAux);
			} catch (JSONException e1) {
				Log.i(this.getClass().getSimpleName(), e.getMessage());
			}
		}finally{
			return compras;
		}
	}
	
	private Date toDate(String stringDate){
		//2014-11-03T23:12:16.145-03:00
		
		Calendar calendar = Calendar.getInstance();
		
		String[] partes = stringDate.split("T");
		String dia = partes[0];
		String hora = partes[1];
		
		String[] diasTokens = dia.split("-");
		calendar.set(Calendar.YEAR, Integer.parseInt(diasTokens[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(diasTokens[1]));
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diasTokens[2]));
		
		hora = hora.substring(0, 8);
		
		String[] hrasTokens = hora.split(":");
		
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hrasTokens[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(hrasTokens[1]));
		calendar.set(Calendar.SECOND, Integer.parseInt(hrasTokens[2]));
		
		return calendar.getTime();
	}
	
}
