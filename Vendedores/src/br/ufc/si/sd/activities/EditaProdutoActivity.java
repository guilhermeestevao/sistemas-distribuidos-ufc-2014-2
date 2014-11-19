package br.ufc.si.sd.activities;

import br.ufc.si.sd.R;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.lists.ListaProdutosPorVendedor;
import br.ufc.si.sd.rest.ProdutoREST;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditaProdutoActivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_produto);
		Intent it = getIntent();
		final Produto produto = (Produto) it.getExtras().get("produto");
		final EditText editNomeProduto = (EditText) findViewById(R.id.edit_nome_produto);
		final EditText editDescricaoProduto = (EditText) findViewById(R.id.edit_descricao_produto);
		final EditText editPrecoProduto = (EditText) findViewById(R.id.edit_preco_produto);
		final EditText editQtdProduto = (EditText) findViewById(R.id.edit_qtd_produto);
		
		editNomeProduto.setText(produto.getNome());
		editDescricaoProduto.setText(produto.getDescricao());
		editPrecoProduto.setText(String.valueOf(produto.getPreco()));
		editQtdProduto.setText(String.valueOf(produto.getQuantidade()));
		
		Button btnAtualizar = (Button) findViewById(R.id.btn_atuaalizar_produto);
		btnAtualizar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Produto produtoEdit = new Produto();
				produtoEdit.setId(produto.getId());
				produtoEdit.setNome(editNomeProduto.getText().toString());
				produtoEdit.setDescricao(editDescricaoProduto.getText().toString());
				produtoEdit.setPreco(Double.parseDouble(editPrecoProduto.getText().toString()));
				produtoEdit.setQuantidade(Integer.parseInt(editQtdProduto.getText().toString()));
				produtoEdit.setUsuarioId(produto.getUsuarioId());
				new Thread(){
					public void run() {
						String resposta = new ProdutoREST().atualizarProduto(produtoEdit);
						Intent it2 = new Intent(EditaProdutoActivity.this, ListaProdutosPorVendedor.class);
						Usuario usuario = new Usuario();
						usuario.setId(produto.getUsuarioId());
						it2.putExtra("usuario", usuario);
						startActivity(it2);
					};
				}.start();
				
			}
		});
	}
}
