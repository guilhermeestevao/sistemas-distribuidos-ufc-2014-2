package br.ufc.si.sd.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import br.ufc.si.sd.Produto;
import br.ufc.si.sd.Usuario;
import br.ufc.si.sd.util.WebServiceCliente;

public class UsuarioREST {

	private static final String URL_WS = "http://192.168.0.118:8080/ServicoVendedores/usuario/";

	public String cadastrarUsario(Usuario usuario) throws Exception{
		JSONObject jo = new JSONObject();
		jo.put("id", usuario.getId());
		jo.put("nome", usuario.getNome());
		jo.put("email", usuario.getEmail());
		String jsonUsuario = jo.toString();
		String[] respostaServidor = new WebServiceCliente().post(URL_WS+"novo", jsonUsuario);
		return respostaServidor[1];
	}

	public boolean verificarUsuario(long id){
		String[] respostaServidor = new WebServiceCliente().get(URL_WS+id);
		if(respostaServidor[0].equals("200"))
			return Boolean.parseBoolean(respostaServidor[1]);
		 
		return false;
	}
	
	public List<Usuario> listarUsuarios(){
		String[] resposta = new WebServiceCliente().get(URL_WS + "listar-todos");
		if(resposta[0].equals("200")){
			Log.i(URL_WS, resposta[1]);
			List<Usuario> usuarios = getUsuarios(resposta[1]);
			return usuarios;
		}else{
			return null;
		}
	}

	private List<Usuario> getUsuarios(String json) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try{
			JSONObject jasonObject = new JSONObject(json);
			JSONArray jarray = jasonObject.getJSONArray("usuario");
			JSONObject usuario;

			for (int i = 0; i < jarray.length(); i++) {
				usuario = new JSONObject(jarray.getString(i));
				Usuario usuarioAux = new Usuario();
				usuarioAux.setId(Long.parseLong(usuario.getString("id")));
				usuarioAux.setNome(usuario.getString("nome"));
				usuarioAux.setEmail(usuario.getString("email"));
				usuarios.add(usuarioAux);
			}
			return usuarios;
		}catch(JSONException e){
			Log.i(this.getClass().getSimpleName(), e.getMessage());
		}
		return usuarios;
	}

}
