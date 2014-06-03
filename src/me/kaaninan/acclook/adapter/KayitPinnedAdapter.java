package me.kaaninan.acclook.adapter;

import java.util.ArrayList;

import me.kaaninan.acclook.R;
import me.kaaninan.acclook.constructor.KayitConstructor;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hb.views.PinnedSectionListView.PinnedSectionListAdapter;

public class KayitPinnedAdapter extends BaseAdapter implements PinnedSectionListAdapter {
	    
	private Context context;
	private ArrayList<KayitConstructor> list;
	private int layoutResourceId;
	
	private KayitConstructor kayit;
    
    public KayitPinnedAdapter(Context context, int layoutResourceId, ArrayList<KayitConstructor> list) {
    	super();
    	this.context = context;
    	this.layoutResourceId = layoutResourceId;
    	this.list = list;
    }
    
    @Override
    public int getCount() {
    	if(list.size() == 0)
    		return 1;
        return list.size();
    }

    
    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	
        View view = convertView;
        
        if(view==null){ 
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResourceId, null);
        }
        
        TextView tutar = (TextView) view.findViewById(R.id.textKayitTutar);
        TextView not = (TextView) view.findViewById(R.id.textKayitNot);
        TextView tarih = (TextView) view.findViewById(R.id.textKayitTarih);
        	/*
        KayitConstructor kayit = list.get(position);
        String tutarDouble = Double.toString(kayit.getTutar());
*/
        kayit = list.get(position);
        
        not.setText(kayit.getNot());

        return view;
   }
    
    @Override
    public int getViewTypeCount(){
    	return 2;
    }

    @Override 
    public int getItemViewType(int position) {
    	kayit = list.get(position);
    	return kayit.getType();
    }

    @Override
	public boolean isItemViewTypePinned(int viewType) {
		Log.i("is›tem..",String.valueOf(viewType));
		return viewType == 1;
	}
	
}