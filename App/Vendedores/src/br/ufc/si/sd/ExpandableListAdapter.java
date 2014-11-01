package br.ufc.si.sd;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
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
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Produto children = (Produto) getChild(groupPosition, childPosition);
		if(convertView == null)
			convertView = inflater.inflate(R.layout.list_itens, null);
		TextView t1 = (TextView) convertView.findViewById(R.id.textView1);
		t1.setText("Descricao: "+children.getDescricao());
		TextView t2 = (TextView) convertView.findViewById(R.id.textView2);
		t2.setText("Preco: "+String.valueOf(children.getPreco()));
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
