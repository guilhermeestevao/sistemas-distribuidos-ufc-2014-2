package br.ufc.si.sd;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class ExpandableListAdapterVendedorIndividual extends BaseExpandableListAdapter{

	private List<Produto> produtos;
	private LayoutInflater inflater;
	private Activity activity;
	
	public ExpandableListAdapterVendedorIndividual(List<Produto> produtos, Activity activity) {
		this.produtos = produtos;
		this.activity = activity;
		this.inflater = activity.getLayoutInflater();
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
			convertView = inflater.inflate(R.layout.list_group_produtos_vendedor_individual, null);
		Produto group = (Produto) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(group.getNome());
		((CheckedTextView) convertView).setChecked(isExpanded);
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Produto children = (Produto) getChild(groupPosition, childPosition);
		if(convertView == null)
			convertView = inflater.inflate(R.layout.list_itens_produtos_vendedor_individual, null);
		TextView t1 = (TextView) convertView.findViewById(R.id.tv_descricao_produto_vendedor_individual);
		t1.setText("Descricao: "+children.getDescricao());
		TextView t2 = (TextView) convertView.findViewById(R.id.tv_preco_produto_vendedor_individual);
		t2.setText("Preco: "+String.valueOf(children.getPreco()));
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
