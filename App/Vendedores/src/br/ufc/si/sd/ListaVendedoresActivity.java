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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaVendedoresActivity extends ListActivity{

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new DownloadJsonAsyncTask().execute();
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Usuario usuario = usuarios.get(position);
		Toast.makeText(this, usuario.getNome(), Toast.LENGTH_SHORT).show();
		
		Intent it = new Intent(this, ListaProdutosActivity.class);
		it.putExtra("usuario", usuario);
		startActivity(it);
		
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
			usuarios = new UsuarioREST().listarUsuarios();
			if(usuarios != null){
				return usuarios;
			}else{
				return new ArrayList<Usuario>();
			}
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
	}
}
