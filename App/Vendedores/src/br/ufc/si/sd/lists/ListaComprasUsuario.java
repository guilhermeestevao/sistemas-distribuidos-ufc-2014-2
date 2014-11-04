package br.ufc.si.sd.lists;

import java.util.List;

import br.ufc.si.sd.R;
import br.ufc.si.sd.entidades.Compra;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.rest.CompraREST;
import br.ufc.si.sd.rest.ProdutoREST;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaComprasUsuario extends ListActivity{

	List<Compra> compras;
	Produto produto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Usuario usuario = (Usuario) getIntent().getExtras().get("usuario");
		new DownloadJsonComprasAsyncTask().execute(usuario);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		AlertDialog.Builder builder = new AlertDialog.Builder(ListaComprasUsuario.this);
		new DownloadJsonProdutoAsyncTask().execute(compras.get(position));
		builder.setMessage(produto.getNome()+
					"\n\n"+"Descrição do produto"+
					"\n\n"+"Quantidade comprada"+
					"\n\n"+"Preço do produto"+
					"\n\n"+"Vendedor do produto");
		builder.setPositiveButton("OK", null);
		
		final Dialog dialog = builder.create();
		dialog.setTitle("Compra do Usuário");
		
		dialog.show();
	}
	
	class DownloadJsonComprasAsyncTask extends AsyncTask<Usuario, Void, List<Compra>>{

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
			compras = new CompraREST().comprasDoUsuario(usuario);
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
				AlertDialog.Builder builder = new AlertDialog.Builder(ListaComprasUsuario.this).setTitle("Atençãoo") .setMessage("NÃ£o foi possivel acessar essas informÃ§Ãµes...") .setPositiveButton("OK", null); 
				builder.create().show();
			}
		}	
	}
	
	class DownloadJsonProdutoAsyncTask extends AsyncTask<Compra, Void, Produto>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaComprasUsuario.this, "Aguarde", "Carregando compras");
		}
		
		@Override
		protected Produto doInBackground(Compra... params) {
			Compra compra = params[0];
			produto = new ProdutoREST().getProdutoById(compra.getIdProduto());
			return produto;
		}

	}
}
