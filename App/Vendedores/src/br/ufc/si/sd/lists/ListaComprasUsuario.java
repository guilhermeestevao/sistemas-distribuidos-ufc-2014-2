package br.ufc.si.sd.lists;

import java.util.List;

import br.ufc.si.sd.R;
import br.ufc.si.sd.activities.CompraActivity;
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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaComprasUsuario extends ListActivity {

	List<Compra> compras;
	Produto produto;
	Usuario usuarioVendedor;
	Usuario usuario;
	Compra compra;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		usuario = (Usuario) getIntent().getExtras().get("usuario");
		new DownloadJsonComprasAsyncTask().execute(usuario);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		new DownloadJsonProdutoAsyncTask().execute(compras.get(position));
	}

	class DownloadJsonComprasAsyncTask extends
			AsyncTask<Usuario, Void, List<Compra>> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaComprasUsuario.this, "Aguarde",
					"Carregando compras");
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
			if (result.size() > 0) {
				ArrayAdapter<Compra> adapter = new ArrayAdapter<Compra>(
						ListaComprasUsuario.this,
						android.R.layout.simple_list_item_1, result);
				setListAdapter(adapter);
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ListaComprasUsuario.this)
						.setTitle("Aten��oo")
						.setMessage(
								"Não foi possivel acessar essas informções...")
						.setPositiveButton("OK", null);
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
			dialog = ProgressDialog.show(ListaComprasUsuario.this, "Aguarde",
					"Carregando compras");
		}

		@Override
		protected Produto doInBackground(Compra... params) {
			compra = params[0];
			produto = new ProdutoREST().getProdutoById(compra.getIdProduto());
			usuarioVendedor = new UsuarioREST().getUsuarioById(compra.getIdVendedor());
			return produto;
		}

		@Override
		protected void onPostExecute(Produto result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result != null) {
				/*
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ListaComprasUsuario.this);
				builder.setMessage("PRODUTO: " + result.getNome() + "\n\n"
						+ "DESCRI��O: " + result.getDescricao() + "\n\n"
						+ "QUANTIDADE COMPRADA: " + result.getQuantidade() + "\n\n" 
						+ "PRE�O TOTAL" + result.getPreco() + "\n\n" 
						+ "Vendedor do produto: " + usuarioVendedor.getNome());
				builder.setPositiveButton("OK", null);

				builder.create().show();
				*/
				Intent it = new Intent(ListaComprasUsuario.this, CompraActivity.class);
				it.putExtra("vendedor", usuarioVendedor);
				it.putExtra("produto", produto);
				it.putExtra("compra", compra);
				it.putExtra("usuario", usuario);
				startActivity(it);
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ListaComprasUsuario.this)
						.setTitle("Aten��oo")
						.setMessage(
								"Não foi possivel acessar essas informções...")
						.setPositiveButton("OK", null);
				builder.create().show();
			}
		}

	}
}
