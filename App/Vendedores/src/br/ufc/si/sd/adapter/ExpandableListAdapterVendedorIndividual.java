package br.ufc.si.sd.adapter;

import java.util.List;








import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import br.ufc.si.sd.R;
import br.ufc.si.sd.activities.ActivityVerFoto;
import br.ufc.si.sd.activities.CurtirFacebook;
import br.ufc.si.sd.entidades.Compra;
import br.ufc.si.sd.entidades.Produto;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.lists.ListaProdutosDoVendedorIndividual;
import br.ufc.si.sd.rest.CompraREST;

public class ExpandableListAdapterVendedorIndividual extends BaseExpandableListAdapter{

	private List<Produto> produtos;
	private LayoutInflater inflater;
	private Activity activity;
	private Usuario usuarioPrincipal;
	private Usuario usuarioVendedor;
	
	public ExpandableListAdapterVendedorIndividual(List<Produto> produtos, Activity activity, Usuario usuarioPrincipal) {
		this.produtos = produtos;
		this.activity = activity;
		this.inflater = activity.getLayoutInflater();
		this.usuarioPrincipal = usuarioPrincipal;
		
	}

	@Override
	public int getGroupCount() {
		return produtos.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return produtos.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return produtos.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	
	private Bitmap converterToBitmap(byte[] bytesFoto){
		Bitmap bitNew = BitmapFactory.decodeByteArray(bytesFoto, 0, bytesFoto.length);
		return bitNew;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if(convertView == null)
			convertView = inflater.inflate(R.layout.list_group_produtos_vendedor_individual, null);
		Produto group = (Produto) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(group.getNome());
		((CheckedTextView) convertView).setChecked(isExpanded);

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Produto produto = (Produto) getChild(groupPosition, childPosition);
		if(convertView == null)
			convertView = inflater.inflate(R.layout.list_itens_produtos_vendedor_individual, null);
		TextView t1 = (TextView) convertView.findViewById(R.id.tv_descricao_produto_vendedor_individual);
		t1.setText(produto.getDescricao());
		TextView t2 = (TextView) convertView.findViewById(R.id.tv_preco_produto_vendedor_individual);
		t2.setText("Preco: "+String.valueOf(produto.getPreco()));
		final byte[] imagem = produto.getFoto();
		final ImageView img = (ImageView) convertView.findViewById(R.id.img_foto_produto_vendedor);
		
		img.setImageBitmap(converterToBitmap(imagem));
		
		img.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(activity, ActivityVerFoto.class);
				it.putExtra("imagem", imagem);
				activity.startActivity(it);
			}
		});
		
		Button btnComprar = (Button) convertView.findViewById(R.id.comprar);
		
		ImageButton btnCurtir = (ImageButton) convertView.findViewById(R.id.bt_curtir);
		
		btnCurtir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(activity, CurtirFacebook.class);
				intent.putExtra("produto", produto);
				activity.startActivity(intent);
			}
		});
		
		
		btnComprar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(produto.getQuantidade()>0){

					AlertDialog.Builder builder = new AlertDialog.Builder(activity);

					final NumberPicker picker = new NumberPicker(activity);

					picker.setMaxValue(produto.getQuantidade());
					picker.setMinValue(1);
					builder.setView(picker);
					builder.setTitle("Determine a quantidade");
					builder.setCancelable(false);
					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							usuarioVendedor = (Usuario) activity.getIntent().getExtras().get("usuario");
							int quantidade = picker.getValue();
							double valor = quantidade*produto.getPreco();
							final Compra compra = new Compra();
							compra.setIdComprador(usuarioPrincipal.getId());
							compra.setIdProduto(produto.getId());
							compra.setQuantidadeProduto(quantidade);
							compra.setIdVendedor(usuarioVendedor.getId());
							compra.setValorVenda(valor);

							new RealizarCompraAsyncTask().execute(compra);

						}
					});
					builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User cancelled the dialog
						}
					});
					builder.show();
					
				}else{
					AlertDialog.Builder builder = new AlertDialog.Builder(activity).setTitle("Atencao") .setMessage("N�o esta disponivel para venda. Quantidade = 0 ") .setPositiveButton("OK", null); 
					builder.create().show();
				}
			}

		});


		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	class RealizarCompraAsyncTask extends AsyncTask<Compra, Void, String>{
		
		Usuario usuario = usuarioVendedor;
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(activity, "Aguarde", "Buscando produtos...");
		}

		@Override
		protected String doInBackground(Compra... params) {
			Compra compra = params[0];
			
			String resposta = new CompraREST().realizarCompra(compra);
			return resposta;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			AlertDialog.Builder builder = new AlertDialog.Builder(
					activity).setTitle("Aten��o").setMessage(result)
					.setPositiveButton("OK", null);
			
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					Intent it = new Intent(activity, ListaProdutosDoVendedorIndividual.class);
					it.putExtra("usuario", usuarioVendedor);
					it.putExtra("usuario_principal", usuario);
					activity.startActivity(it);
				}
			}); 
			
			builder.create().show();
			
			//11223344566778
		}

	}

}
