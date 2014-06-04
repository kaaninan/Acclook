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
    
    public KayitPinnedAdapter(Context context, int layoutResourceId, ArrayList<KayitConstructor> list) {
    	super();
    	this.context = context;
    	this.layoutResourceId = layoutResourceId;
    	KayitPinnedAdapter.list = list;
    	
    	prepareSections(list.size());
    	
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

        if(kayit.type == 1){
        	onSectionAdded(kayit, kayit.sectionPosition);
        }
        	
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