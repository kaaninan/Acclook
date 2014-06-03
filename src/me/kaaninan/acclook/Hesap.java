package me.kaaninan.acclook;

import java.util.ArrayList;

import me.kaaninan.acclook.constructor.HesapConstructor;
import me.kaaninan.acclook.db.DatabaseManager;
import me.kaaninan.acclook.hesap.AnaHesap;
import me.kaaninan.acclook.hesap.TekHesap;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
 
public class Hesap extends Fragment{
	
	private DatabaseManager manager;
	private static HesapConstructor hesap;
	private static ArrayList<HesapConstructor> listHesap;
	private int sayi;
	
	@SuppressWarnings("unused")
	private Fragment fragment;
	
 
    public static Fragment newInstance(Context context) {
    	Hesap f = new Hesap();
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.hesap_main, null);
        
        ViewPager pager = (ViewPager)root.findViewById(R.id.pager);
        
        manager = new DatabaseManager(getActivity().getApplicationContext());
        
        sayi = manager.hesaplarCount();
        listHesap = manager.getHesaplar();
     
        FragmentManager fm = getActivity().getSupportFragmentManager();
        
        // Pager Set Etme
		if(sayi == 1){
			
			pager.setAdapter(new FragmentStatePagerAdapter(fm) {
            	
            	public int getCount() {
            		return sayi;
            	}

            	public Fragment getItem(int position) {
            		Fragment fragment = new TekHesap();
            		Bundle args = new Bundle();
            		args.putInt(TekHesap.ARG_SECTION_NUMBER, position);
            		fragment.setArguments(args);
            		return fragment;
            	}
            	
            	@Override
        		public CharSequence getPageTitle(int position) {
        			hesap = listHesap.get(position);
        			return hesap.getIsim();
        		}
			});
			
		} // END sayi == 1
		
		if(sayi > 1){
			
			pager.setAdapter(new FragmentStatePagerAdapter(fm) {
            	
            	public int getCount() {
            		return sayi;
            	}

            	public Fragment getItem(int position) {
            		switch(position){
            			case 0:
            				return fragment = new AnaHesap();
            			default:
            				Fragment fragment = new TekHesap();
            				Bundle args = new Bundle();
            				args.putInt(TekHesap.ARG_SECTION_NUMBER, position);
            				fragment.setArguments(args);
            				return fragment;
            		}
            	}
            	
            	@Override
        		public CharSequence getPageTitle(int position) {
        			hesap = listHesap.get(position);
        			return hesap.getIsim();
        		}
			});
			
		}// END sayi > 1
		
        return root;
    }
    
}