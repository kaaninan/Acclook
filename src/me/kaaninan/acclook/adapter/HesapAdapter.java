package me.kaaninan.acclook.adapter;

import java.util.ArrayList;

import me.kaaninan.acclook.R;
import me.kaaninan.acclook.constructor.HesapConstructor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HesapAdapter extends BaseAdapter {
    
	private Context context;
	private ArrayList<HesapConstructor> list;
	private int layoutResourceId;
    
    public HesapAdapter(Context context, int layoutResourceId, ArrayList<HesapConstructor> list) {
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
        
        TextView isim = (TextView) view.findViewById(R.id.textLayoutHesapListSag);
        TextView toplam = (TextView) view.findViewById(R.id.textLayoutHesapListSol);
        
        HesapConstructor kayit = list.get(position);
        String tutarDouble = Double.toString(kayit.getToplam());

        isim.setText(kayit.getIsim());
        toplam.setText(tutarDouble);

        return view;
   
    }
       
}
