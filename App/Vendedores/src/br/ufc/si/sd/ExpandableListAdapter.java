package br.ufc.si.sd;

import java.util.List;

import br.ufc.si.sd.rest.ProdutoREST;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private List<Produto> produtos;
	private LayoutInflater inflater;
	private Activity activity;
	
	public ExpandableListAdapter(List<Produto> produtos, Activity activity) {
		this.produtos = produtos;
		this.inflater = activity.getLayoutInflater();
		this.activity = activity;
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

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if(convertView == null)
			convertView = inflater.inflate(R.layout.list_group, null);
		Produto group = (Produto) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(group.getNome());
		((CheckedTextView) convertView).setChecked(isExpanded);
		
		return convertView;
	}

	@Override
	public View getChildView(final int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Produto children = (Produto) getChild(groupPosition, childPosition);
		if(convertView == null)
			convertView = inflater.inflate(R.layout.list_itens, null);
		TextView t1 = (TextView) convertView.findViewById(R.id.textView1);
		t1.setText("Descricao: "+children.getDescricao());
		TextView t2 = (TextView) convertView.findViewById(R.id.textView2);
		t2.setText("Preco: "+String.valueOf(children.getPreco()));
		
		Button btnExcluir = (Button) convertView.findViewById(R.id.btn_excluir_lista);
		btnExcluir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Produto produto = produtos.get(groupPosition);
				new DeletaProdutoAsyncTask().execute(produto);
			}
		});
		
		
		
		Button btnEditar = (Button) convertView.findViewById(R.id.btn_editar_lista);
		btnEditar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Produto produto = produtos.get(groupPosition);
				Intent it = new Intent(activity, EditaProdutoActivity.class);
				it.putExtra("produto", produto);
				activity.startActivity(it);
			}
		});
		
		return convertView;
		
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
	
	public class DeletaProdutoAsyncTask extends AsyncTask<Produto, Void, String>{

		private ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(activity,
					"Aguarde", "Apagando produto...");
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
			notifyDataSetChanged();
			dialog.dismiss();
			AlertDialog.Builder builder = new AlertDialog.Builder(
					activity).setTitle("Atencao")
					.setMessage(result).setPositiveButton("OK", null);
			builder.create().show();
		}

	}


}
