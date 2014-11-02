package br.ufc.si.sd.lists;

import java.util.ArrayList;
import java.util.List;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.rest.ProdutoREST;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListaProdutosActivity extends ListActivity {

	private List<Produto> produtos = new ArrayList<Produto>();
	private Usuario usuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_lista_produtos);
		Intent it = getIntent();
		usuario = (Usuario) it.getExtras().get("usuario");	
		new DownloadJsonAsyncTask().execute();
	}
	
	class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Produto>>{

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaProdutosActivity.this, "Aguarde", "Buscando produtos...");
		}

		@Override
		protected List<Produto> doInBackground(String... params) {
			// TODO Auto-generated method stub
			produtos = new ProdutoREST().listarProdutosPorUsuario(usuario);
			return produtos;
		}

		@Override
		protected void onPostExecute(List<Produto> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			if(result.size() > 0){
				ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(ListaProdutosActivity.this, android.R.layout.simple_list_item_1, result);
				setListAdapter(adapter);
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(ListaProdutosActivity.this).setTitle("Atencao") .setMessage("Não foi possivel acessar essas informções...") .setPositiveButton("OK", null); 
				builder.create().show();
			}
		}
	}	
}