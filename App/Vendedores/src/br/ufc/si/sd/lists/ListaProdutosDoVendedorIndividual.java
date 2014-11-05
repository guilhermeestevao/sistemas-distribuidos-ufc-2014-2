package br.ufc.si.sd.lists;

import java.util.ArrayList;
import java.util.List;

import br.ufc.si.sd.R;
import br.ufc.si.sd.adapter.ExpandableListAdapterVendedorIndividual;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.rest.ProdutoREST;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

public class ListaProdutosDoVendedorIndividual extends Activity {

	private List<Produto> produtos = new ArrayList<Produto>();
	private Usuario usuario;
	private Usuario usuarioPrincipal;
	private ExpandableListAdapterVendedorIndividual expListAdap;
	private ExpandableListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_produtos_do_vendedor_individual);
		Intent it = getIntent();
		usuario = (Usuario) it.getExtras().get("usuario");
		usuarioPrincipal = (Usuario) it.getExtras().get("usuario_principal");
		new DownloadJsonAsyncTask().execute();
	}
	
	class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Produto>>{

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(ListaProdutosDoVendedorIndividual.this, "Aguarde", "Buscando produtos...");
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
				expListAdap = new ExpandableListAdapterVendedorIndividual(result,
						ListaProdutosDoVendedorIndividual.this, usuarioPrincipal);
				listView = (ExpandableListView) findViewById(R.id.listView);
				listView.setAdapter(expListAdap);
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(ListaProdutosDoVendedorIndividual.this).setTitle("Atencao") .setMessage("Não foi possivel acessar essas informções...") .setPositiveButton("OK", null); 
				builder.create().show();
			}
		}
	}	
}
