package br.ufc.si.sd.lists;

import java.util.ArrayList;
import java.util.List;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.rest.UsuarioREST;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaVendedoresActivity extends ListActivity{

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private Usuario usuarioPrincipal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent it = getIntent();
		usuarioPrincipal = (Usuario) it.getExtras().get("usuario_principal");
		new DownloadJsonAsyncTask().execute();
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Usuario usuario = usuarios.get(position);
		Intent it = new Intent(this, ListaProdutosDoVendedorIndividual.class);
		it.putExtra("usuario", usuario);
		it.putExtra("usuario_principal", usuarioPrincipal);
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