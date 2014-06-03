package me.kaaninan.acclook.adapter;

import java.util.ArrayList;

import me.kaaninan.acclook.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KayitAdapter extends BaseAdapter {
    
	private Context context;
	private ArrayList<String> list;
	private int layoutResourceId;
    
    public KayitAdapter(Context context, int layoutResourceId, ArrayList<String> list) {
    	super();
    	this.context = context;
    	this.layoutResourceId = layoutResourceId;
    	this.list = list;
    }
    
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
        not.setText(list.get(position));

        return view;
   
    }
       
}