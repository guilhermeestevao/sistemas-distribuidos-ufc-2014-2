package br.ufc.si.sd.lists;

import java.util.List;

import br.ufc.si.sd.entidades.Compra;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.rest.CompraREST;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListaComprasUsuario extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Usuario usuario = (Usuario) getIntent().getExtras().get("usuario");
		new DownloadJasonComprasAsyncTask().execute(usuario);
	}
	
	class DownloadJasonComprasAsyncTask extends AsyncTask<Usuario, Void, List<Compra>>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaComprasUsuario.this, "Aguarde", "Carregando compras");
		}
		
		@Override
		protected List<Compra> doInBackground(Usuario... params) {
			Usuario usuario = params[0];
			List<Compra> compras = new CompraREST().comprasDoUsuario(usuario);
			return compras;
		}
		
		@Override
		protected void onPostExecute(List<Compra> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			if(result.size() > 0){
				ArrayAdapter<Compra> adapter = new ArrayAdapter<Compra>(ListaComprasUsuario.this, android.R.layout.simple_list_item_1, result);
				setListAdapter(adapter);
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(ListaComprasUsuario.this).setTitle("Atenção") .setMessage("Não foi possivel acessar essas informções...") .setPositiveButton("OK", null); 
				builder.create().show();
			}
		}	
	}
}