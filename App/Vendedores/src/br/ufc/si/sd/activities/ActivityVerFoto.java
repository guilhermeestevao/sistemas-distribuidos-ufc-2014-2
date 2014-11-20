package br.ufc.si.sd.activities;

import java.io.ByteArrayInputStream;

import br.ufc.si.sd.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityVerFoto extends Activity{

	private Bitmap imagem = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostra_foto);

        Bundle dados = getIntent().getExtras();
        byte[] array = dados.getByteArray("imagem");
        if(array==null){
        	Toast.makeText(this, "O Produto não possui imagem.", Toast.LENGTH_SHORT);
        }else{
        	imagem = converterBlobParaImagem(array);
			ImageView imageView = (ImageView) findViewById(R.id.imageView1);
			imageView.setImageBitmap(this.imagem);	
        }
        	
	}
	
	public static Bitmap converterBlobParaImagem(byte[] bytes) { 

		try{ 
		ByteArrayInputStream imageStream = new ByteArrayInputStream(bytes); 
		Bitmap bmp = BitmapFactory.decodeStream(imageStream); 

		return bmp; // retorna a imagem 
		} catch (Exception e) { 
			System.out.println("exception");
			return null;
		} 
	}
}
