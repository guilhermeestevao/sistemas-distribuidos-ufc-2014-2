package br.ufc.si.sd.activities;

import java.io.ByteArrayOutputStream;

import br.ufc.si.sd.R;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.lists.ListaProdutosPorVendedor;
import br.ufc.si.sd.rest.ProdutoREST;
import android.R.id;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CadastraProdutoActivity extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Bitmap imagem = null;
	
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
		Button btnTirarFoto = (Button) findViewById(R.id.btn_tirar_foto);
		Button btnVerFoto = (Button) findViewById(R.id.btn_ver_foto);
		
		btnTirarFoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			    startActivityForResult(cameraIntent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
		});
		
		btnVerFoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CadastraProdutoActivity.this, ActivityVerFoto.class);
				intent.putExtra("imagem",convertBitMap(imagem));
			    startActivity(intent);
			}
		});
		
		btnCadastrarProduto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {
					@Override
					public void run() {
						try {

							String nome = editNomeProduto.getText().toString();
							String descricao = editDescricaoProduto.getText().toString();
							double preco = Double.parseDouble(editPrecoProduto.getText().toString());
							int quantidade = Integer.parseInt(editQtdProduto.getText().toString());
							long idUsuario = usuario.getId();						
							Produto produto = new Produto(nome, descricao, quantidade, preco, idUsuario);
							if(imagem != null){
								produto.setFoto(convertBitMap(imagem));
							}
							
							String resposta = rest.cadastrarProduto(produto);
							Intent it = new Intent(CadastraProdutoActivity.this, ListaProdutosPorVendedor.class);
							it.putExtra("usuario", usuario);
							startActivity(it);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
	        imagem = (Bitmap)data.getExtras().get("data");        
	        //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
	        //imageView.setImageBitmap(photo);}
		}
	}
	
	public byte[] convertBitMap(Bitmap bmp){
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		return byteArray;
	}
}
