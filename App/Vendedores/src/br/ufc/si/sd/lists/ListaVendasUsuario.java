package br.ufc.si.sd.lists;

import java.util.List;

import br.ufc.si.sd.entidades.Compra;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.rest.CompraREST;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaVendasUsuario extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Usuario usuario = (Usuario) getIntent().getExtras().get("usuario");
		new DownloadJasonVendasAsyncTask().execute(usuario);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		AlertDialog.Builder builder = new AlertDialog.Builder(ListaVendasUsuario.this);
		builder.setMessage("Produto comprado"+
					"\n\n"+"Descrição do produto"+
					"\n\n"+"Quantidade comprada"+
					"\n\n"+"Preço do produto"+
					"\n\n"+"Comprador do produto");
		builder.setPositiveButton("OK", null);
		
		final Dialog dialog = builder.create();
		dialog.setTitle("Compra do Usuário");
		
		dialog.show();
	}
	
	class DownloadJasonVendasAsyncTask extends AsyncTask<Usuario, Void, List<Compra>>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaVendasUsuario.this, "Aguarde", "Carregando vendas");
		}
		
		@Override
		protected List<Compra> doInBackground(Usuario... params) {
			Usuario usuario = params[0];
			List<Compra> compras = new CompraREST().vendasDoUsuario(usuario);
			return compras;
		}
		
		@Override
		protected void onPostExecute(List<Compra> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			if(result.size() > 0){
				ArrayAdapter<Compra> adapter = new ArrayAdapter<Compra>(ListaVendasUsuario.this, android.R.layout.simple_list_item_1, result);
				setListAdapter(adapter);
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(ListaVendasUsuario.this).setTitle("Atencao") .setMessage("NÃ£o foi possivel acessar essas informÃ§Ãµes...") .setPositiveButton("OK", null); 
				builder.create().show();
			}
		}	
	}	
}