package br.ufc.si.sd.lists;

import java.util.List;

import br.ufc.si.sd.entidades.Compra;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.rest.CompraREST;
import br.ufc.si.sd.rest.ProdutoREST;
import br.ufc.si.sd.rest.UsuarioREST;
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

	Produto produto;
	List<Compra> compras;
	Usuario usuarioComprador;
	
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
		new DownloadJsonProdutoAsyncTask().execute(compras.get(position));
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
			compras = new CompraREST().vendasDoUsuario(usuario);
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
	
	class DownloadJsonProdutoAsyncTask extends AsyncTask<Compra, Void, Produto> {

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaVendasUsuario.this, "Aguarde",
					"Carregando compras");
		}

		@Override
		protected Produto doInBackground(Compra... params) {
			Compra compra = params[0];
			produto = new ProdutoREST().getProdutoById(compra.getIdProduto());
			usuarioComprador = new UsuarioREST().getUsuarioById(compra.getIdComprador());
			return produto;
		}

		@Override
		protected void onPostExecute(Produto result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result != null) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ListaVendasUsuario.this);
				builder.setMessage("PRODUTO: " + result.getNome() + "\n\n"
						+ "DESCRIÇÃO: " + result.getDescricao() + "\n\n"
						+ "QUANTIDADE COMPRADA: " + result.getQuantidade() + "\n\n" 
						+ "PREÇO TOTAL: " + result.getPreco() + "\n\n" 
						+ "COMPRADOR: " + usuarioComprador.getNome());
				builder.setPositiveButton("OK", null);

				builder.create().show();
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ListaVendasUsuario.this)
						.setTitle("Atençãoo")
						.setMessage(
								"NÃ£o foi possivel acessar essas informações...")
						.setPositiveButton("OK", null);
				builder.create().show();
			}
		}

	}
	
}