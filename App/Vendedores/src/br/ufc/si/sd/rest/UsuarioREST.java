package br.ufc.si.sd.rest;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.util.WebServiceCliente;

public class UsuarioREST {

	private static final String URL_WS = "http://10.0.103.97:8080/ServicoVendedores/usuario/";

	public String cadastrarUsario(Usuario usuario) throws Exception{
		JSONObject jo = new JSONObject();
		jo.put("id", usuario.getId());
		jo.put("nome", usuario.getNome());
		jo.put("email", usuario.getEmail());
		jo.put("lat", String.valueOf(usuario.getLat()));
		jo.put("lng", String.valueOf(usuario.getLng()));
		String jsonUsuario = jo.toString();
		String[] respostaServidor = new WebServiceCliente().post(URL_WS+"novo", jsonUsuario);
		return respostaServidor[1];
	}

	public boolean verificarUsuario(long id){
		String[] respostaServidor = new WebServiceCliente().get(URL_WS+"verificar/"+id);
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
			
		}
		return usuarios;
	}
	
	public Usuario getUsuarioById(Long id){
		String[] json = new WebServiceCliente().get(URL_WS+id);
		JSONObject usuario;
		try{
			usuario = new JSONObject(json[1]);
			Usuario usuarioAux = new Usuario();
			usuarioAux.setId(Long.parseLong(usuario.getString("id")));
			usuarioAux.setNome(usuario.getString("nome"));
			usuarioAux.setEmail(usuario.getString("email"));
			return usuarioAux;
		}catch(JSONException e){
			Log.i(this.getClass().getSimpleName(), e.toString());
		}
		return null;
	}
	
}
