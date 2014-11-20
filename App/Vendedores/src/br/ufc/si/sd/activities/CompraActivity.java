package br.ufc.si.sd.activities;

import br.ufc.si.sd.R;
import br.ufc.si.sd.entidades.Avaliacao;
import br.ufc.si.sd.entidades.Compra;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.lists.ListaComprasUsuario;
import br.ufc.si.sd.rest.AvaliacaoREST;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CompraActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_compra);
		final Usuario usuarioVendedor = (Usuario) getIntent().getExtras().get("vendedor");
		final Produto produto = (Produto) getIntent().getExtras().get("produto");
		final Compra compra = (Compra) getIntent().getExtras().get("compra");
		final Usuario usuario = (Usuario) getIntent().getExtras().get("usuario");
		
		TextView nome = (TextView) findViewById(R.id.produto_nome);
		TextView descricao = (TextView)findViewById(R.id.produto_desc);
		TextView quantidade = (TextView)findViewById(R.id.produto_quantidade);
		TextView preco = (TextView)findViewById(R.id.produto_preco);
		TextView vendedor = (TextView) findViewById(R.id.produto_vendedor);
		final RatingBar nota = (RatingBar) findViewById(R.id.avaliacao_produto);
		
		nome.setText(produto.getNome());
		descricao.setText(produto.getDescricao());
		vendedor.setText(usuarioVendedor.getNome());
		quantidade.setText(String.valueOf(compra.getQuantidadeProduto()));
		preco.setText(String.valueOf(compra.getValorVenda()));
		
		Button avaliar = (Button) findViewById(R.id.avaliar_produto);
		
		avaliar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				float avaliacaoResult = nota.getRating(); 
				if(avaliacaoResult != 0.0){
					final Avaliacao avaliacao = new Avaliacao();
					avaliacao.setAvaliacao(avaliacaoResult);
					avaliacao.setAvaliadoId(usuario.getId());
					avaliacao.setProdutoId(produto.getId());
					avaliacao.setAvaliadorId(usuario.getId());
					new AvaliarAsyncTask().execute(avaliacao);
				}
			}
		});
	}
	
	class AvaliarAsyncTask extends AsyncTask<Avaliacao, Void, String>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(CompraActivity.this, "Aguarde",
					"Avaliando...");
		}
		
		@Override
		protected String doInBackground(Avaliacao... params) {
			// TODO Auto-generated method stub
			Avaliacao avaliacao = params[0];
			String resposta = new AvaliacaoREST().avaliar(avaliacao);
			return resposta;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-gene
			super.onPostExecute(result);
			Toast.makeText(CompraActivity.this, result, Toast.LENGTH_LONG).show();
			Intent it = new Intent(CompraActivity.this, MainActivity.class);
			startActivity(it);
		}
	}
	
}
