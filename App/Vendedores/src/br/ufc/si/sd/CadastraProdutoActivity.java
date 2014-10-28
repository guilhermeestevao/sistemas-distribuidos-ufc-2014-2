package br.ufc.si.sd;

import br.ufc.si.sd.rest.ProdutoREST;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CadastraProdutoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_produto);
		Intent it = getIntent();
		final Usuario usuario = (Usuario) it.getExtras().get("usuario");

		final EditText editNomeProduto = (EditText) findViewById(R.id.edit_nome_produto);
		final EditText editDescricaoProduto = (EditText) findViewById(R.id.edit_descricao_produto);
		final EditText editPrecoProduto = (EditText) findViewById(R.id.edit_preco_produto);
		final EditText editQtdProduto = (EditText) findViewById(R.id.edit_qtd_produto);

		final ProdutoREST rest = new ProdutoREST();
		

		Button btnCadastrarProduto = (Button) findViewById(R.id.btn_cadastrar_produto);
		btnCadastrarProduto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {
					@Override
					public void run() {
						try {
							final Produto produto = new Produto(editNomeProduto.getText()
									.toString(), editDescricaoProduto.getText().toString(),
									Integer.parseInt(editQtdProduto.getText().toString()),
									Double.parseDouble(editPrecoProduto.getText().toString()),
									usuario);
							String resposta = rest.cadastrarProduto(produto);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
			}
		});

	}
}
