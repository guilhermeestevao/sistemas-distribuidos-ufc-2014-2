package br.ufc.si.sd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufc.si.sd.rest.UsuarioREST;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ListaVendedoresActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new DownloadJsonAsyncTask().execute("http://192.168.0.107:8080/ServicoVendedores/usuario/listar-todos");
		
	}
	
		
	class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Usuario>>{

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaVendedoresActivity.this, "Aguarde", "Buscando vendedores...");

		}

		@Override
		protected List<Usuario> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Usuario> usuarios = new UsuarioREST().listarUsuarios();
			if(usuarios != null){
				return usuarios;
			}else{
				return new ArrayList<Usuario>();
			}
		}

		private List<Usuario> getUsuarios(String json) {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			try{
				JSONObject Jasonobject = new JSONObject(json);
				JSONArray Jarray = Jasonobject.getJSONArray("usuario");
				JSONObject usuario;
				
				for (int i = 0; i < Jarray.length(); i++) {
					usuario = new JSONObject(Jarray.getString(i));
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

		@Override
		protected void onPostExecute(List<Usuario> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			if(result.size() > 0){
				ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(ListaVendedoresActivity.this, android.R.layout.simple_list_item_1, result);
				setListAdapter(adapter);
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(ListaVendedoresActivity.this).setTitle("Atenção") .setMessage("Não foi possivel acessar essas informções...") .setPositiveButton("OK", null); 
				builder.create().show();
			}
		}
		
		private String toString(InputStream is) throws IOException {
			// TODO Auto-generated method stub
			byte[] bytes = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int lidos;
			while ((lidos = is.read(bytes)) > 0) {
				baos.write(bytes, 0, lidos); 
			} 
			return new String(baos.toByteArray());
		}
	}
}
