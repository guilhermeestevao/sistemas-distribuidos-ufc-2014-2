package br.ufc.si.sd;
import java.util.ArrayList;
import java.util.List;

import br.ufc.si.sd.rest.ProdutoREST;
import br.ufc.si.sd.rest.UsuarioREST;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaProdutosVendedor extends ListActivity{

	private Usuario usuario;
	private List<Produto> produtos = new ArrayList<Produto>();
	private ArrayAdapter<Produto> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent it = getIntent();
		usuario = (Usuario) it.getExtras().get("usuario");
		new DownloadJsonAsyncTask().execute();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Produto produto = produtos.get(position);
		new DeletaProdutoAsyncTask().execute(produto);
		
	}
	
	class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Produto>>{
		ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaProdutosVendedor.this, "Aguarde", "Buscando produtos...");
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
			if(result.size() > 0){
				adapter = new ArrayAdapter<Produto>(ListaProdutosVendedor.this, android.R.layout.simple_list_item_1, result);
				setListAdapter(adapter);
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(ListaProdutosVendedor.this).setTitle("Atenção") .setMessage("Não foi possivel acessar essas informções...") .setPositiveButton("OK", null); 
				builder.create().show();
			}
		}
		
	}
	
	class DeletaProdutoAsyncTask extends AsyncTask<Produto, Void, String>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaProdutosVendedor.this, "Aguarde", "Apagando produto...");
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
			adapter.notifyDataSetChanged();
			dialog.dismiss();
			AlertDialog.Builder builder = new AlertDialog.Builder(ListaProdutosVendedor.this).setTitle("Atenção") .setMessage(result) .setPositiveButton("OK", null); 
			builder.create().show();
		}	
	}
}
