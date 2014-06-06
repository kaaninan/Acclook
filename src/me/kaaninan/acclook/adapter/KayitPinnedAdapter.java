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
	protected static ArrayList<KayitConstructor> list;
	private int layoutResourceId;
	
	// LOG
	@SuppressWarnings("unused")
	private String log = "KayitPinnedAdapter";
	
	private KayitConstructor kayit;
	private KayitConstructor kayit2;
    
    public KayitPinnedAdapter(Context context, int layoutResourceId, ArrayList<KayitConstructor> list) {
    	super();
    	this.context = context;
    	this.layoutResourceId = layoutResourceId;
    	KayitPinnedAdapter.list = list;
    	
    	int a = 0;
    	
    	for(int i = 0; i < list.size(); i++){
    		kayit2 = list.get(i);
    		if(kayit2.type == 1){
	        	a = a+1;
	        }
    	}
    	prepareSections(a);
    	
    	for(int i = 0; i < list.size(); i++){
    		kayit2 = list.get(i);
    		if(kayit2.type == 1){
	        	onSectionAdded(kayit2, kayit2.sectionPosition);
	        }
    	}
    	
    	
    }
    
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
    	kayit = list.get(position);
        return kayit;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
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
    	return kayit.type;
    }

    @Override
	public boolean isItemViewTypePinned(int viewType) {
		return viewType == KayitConstructor.SECTION;
	}
	
    public void prepareSections(int sectionsNumber) { }
    public void onSectionAdded(KayitConstructor section, int sectionPosition) { }
    
}