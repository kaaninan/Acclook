package me.kaaninan.acclook.test;

import java.util.ArrayList;

import me.kaaninan.acclook.R;
import me.kaaninan.acclook.constructor.KayitConstructor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KayitAdapter extends BaseAdapter {
    
	private Context context;
	private ArrayList<KayitConstructor> list;
	private int layoutResourceId;
    
    public KayitAdapter(Context context, int layoutResourceId, ArrayList<KayitConstructor> list) {
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
        
        TextView a = (TextView) view.findViewById(R.id.textView1);
        TextView b = (TextView) view.findViewById(R.id.textView2);
        TextView c = (TextView) view.findViewById(R.id.textView3);
        TextView d = (TextView) view.findViewById(R.id.textView4);
        TextView e = (TextView) view.findViewById(R.id.textView5);
        
        
        KayitConstructor kayit = list.get(position);
        
        a.setText(kayit._not);
        b.setText(kayit.getTarih());
        c.setText(String.valueOf(kayit.getTutar()));
        d.setText(kayit.getTur());

        return view;
   
    }
       
}