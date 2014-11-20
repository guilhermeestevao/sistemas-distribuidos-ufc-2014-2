package br.ufc.si.sd.activities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import br.ufc.si.sd.R;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.lists.ListaProdutosPorVendedor;
import br.ufc.si.sd.rest.ProdutoREST;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditaProdutoActivity extends Activity{

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Bitmap imagem = null;
	private Produto produto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_produto);
		Intent it = getIntent();
		produto = (Produto) it.getExtras().get("produto");
		
	    this.imagem = converterBlobParaImagem(produto.getFoto());
	    
	    final EditText editNomeProduto = (EditText) findViewById(R.id.edit_nome_produto);
		final EditText editDescricaoProduto = (EditText) findViewById(R.id.edit_descricao_produto);
		final EditText editPrecoProduto = (EditText) findViewById(R.id.edit_preco_produto);
		final EditText editQtdProduto = (EditText) findViewById(R.id.edit_qtd_produto);
		
		editNomeProduto.setText(produto.getNome());
		editDescricaoProduto.setText(produto.getDescricao());
		editPrecoProduto.setText(String.valueOf(produto.getPreco()));
		editQtdProduto.setText(String.valueOf(produto.getQuantidade()));
		Button btnTirarFoto = (Button) findViewById(R.id.btn_tirar_foto);
		Button btnVerFoto = (Button) findViewById(R.id.btn_ver_foto);
		Button btnAtualizar = (Button) findViewById(R.id.btn_atuaalizar_produto);
		
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
				Intent intent = new Intent(EditaProdutoActivity.this, ActivityVerFoto.class);
				intent.putExtra("imagem",produto.getFoto());
			    startActivity(intent);
			}
		});
		
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
				if(imagem != null){
					produtoEdit.setFoto(convertBitMap(imagem));
				}
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
	public static Bitmap converterBlobParaImagem(byte[] bytes) { 

		try{ 
		ByteArrayInputStream imageStream = new ByteArrayInputStream(bytes); 
		Bitmap bmp = BitmapFactory.decodeStream(imageStream); 

		return bmp; // retorna a imagem 
		} catch (Exception e) { 
			System.out.println("exception aqui");
			return null;
		} 
	}
}
