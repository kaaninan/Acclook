package me.kaaninan.acclook.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.kaaninan.acclook.R;
import me.kaaninan.acclook.constructor.KayitConstructor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class KayitExpandableAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader;
	private HashMap<String, List<KayitConstructor>> _listDataChild;

	public KayitExpandableAdapter(Context context, List<String> listDataHeader, HashMap<String, List<KayitConstructor>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		KayitConstructor as = this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
		List<String> list = new ArrayList<String>();
		list.add(as.getNot());
		list.add(as.getTarih());
		return list;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		@SuppressWarnings("unchecked")
		final ArrayList<String> childText = (ArrayList<String>) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.kayit_list_item, null);
		}

		TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
		TextView txtListChild2 = (TextView) convertView.findViewById(R.id.lblListItem2);
		
		txtListChild.setText(childText.get(0));
		txtListChild2.setText(childText.get(1));
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		String headerTitle = (String) getGroup(groupPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.kayit_list_group, null);
		}

		TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
		
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
