package me.kaaninan.acclook.hesap;

import java.util.ArrayList;

import me.kaaninan.acclook.R;
import me.kaaninan.acclook.adapter.HesapAdapter;
import me.kaaninan.acclook.constructor.HesapConstructor;
import me.kaaninan.acclook.db.DatabaseManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class AnaHesap extends Fragment {
	 
    public static Fragment newInstance(Context context) {
    	AnaHesap f = new AnaHesap();
        return f;
    }
    
    private DatabaseManager manager;
    private View empty;
    
    private ListView listViewHesap;
    private ArrayList<HesapConstructor> arrayListHesap;
    private HesapAdapter adapterHesap;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.hesap_ana, null);
        
        manager = new DatabaseManager(getActivity());
        
        listViewHesap = (ListView) root.findViewById(R.id.listHesapTumList);
		//listViewKayit2 = (PullToRefreshListView) root.findViewById(R.id.hadi);
		
		arrayListHesap = manager.getHesaplar();
		
		//empty = root.findViewById(R.id.emptyView);
		/*
		if(arrayListHesap.size() == 0){
			listViewHesap.setEmptyView(empty);
		}else{*/
			adapterHesap = new HesapAdapter(getActivity().getApplicationContext(), R.layout.hesap_ana_list, arrayListHesap);
			listViewHesap.setAdapter(adapterHesap);
		//}
		
		/* Hesap Listesinin Click Listener'ý */
		
		
		listViewHesap.setOnItemClickListener(new OnItemClickListener()
		{
		    @Override 
		    public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
		    { 
		        Toast.makeText(getActivity(), "Seçilen hesap id -> " + position, Toast.LENGTH_SHORT).show();
		    }
		});
		
        return root;
    }
 
}
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText(mContent);
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(text);

        return layout;
    }
}*/
