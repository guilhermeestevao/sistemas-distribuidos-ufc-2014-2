package br.ufc.si.sd;

import java.util.ArrayList;
import java.util.List;
import br.ufc.si.sd.rest.ProdutoREST;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class ListaProdutosPorVendedor extends Activity {

	private Usuario usuario;
	private List<Produto> produtos = new ArrayList<Produto>();
	private ExpandableListAdapter expListAdap;
	private ExpandableListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_produtos_por_vendedor);
		Intent it = getIntent();
		usuario = (Usuario) it.getExtras().get("usuario");
		new DownloadJsonAsyncTask().execute();
		
	}

	public class DownloadJsonAsyncTask extends
			AsyncTask<String, Void, List<Produto>> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaProdutosPorVendedor.this,
					"Aguarde", "Buscando produtos...");
		}

		@Override
		protected List<Produto> doInBackground(String... params) {
			produtos = new ProdutoREST().listarProdutosPorUsuario(usuario);
			return produtos;
		}

		@Override
		protected void onPostExecute(List<Produto> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			if (result.size() > 0) {
				expListAdap = new ExpandableListAdapter(result,
						ListaProdutosPorVendedor.this);
				listView = (ExpandableListView) findViewById(R.id.listView);
				listView.setAdapter(expListAdap);
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ListaProdutosPorVendedor.this)
						.setTitle("Atencao")
						.setMessage(
								"Não foi possivel acessar essas informções...")
						.setPositiveButton("OK", null);
				builder.create().show();
			}
		}

	}

	public class DeletaProdutoAsyncTask extends
			AsyncTask<Produto, Void, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaProdutosPorVendedor.this,
					"Aguarde", "Apagando produto...");
		}

		@Override
		protected String doInBackground(Produto... params) {
			// TODO Auto-generated method stub
			Produto produto = params[0];
			String resposta = new ProdutoREST().deletarProdito(produto);
			produtos.remove(produto);
			return resposta;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			expListAdap.notifyDataSetChanged();
			dialog.dismiss();
			AlertDialog.Builder builder = new AlertDialog.Builder(
					ListaProdutosPorVendedor.this).setTitle("Atencao")
					.setMessage(result).setPositiveButton("OK", null);
			builder.create().show();
		}
	}
}
