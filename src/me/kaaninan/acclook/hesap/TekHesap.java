package me.kaaninan.acclook.hesap;

import java.util.ArrayList;

import me.kaaninan.acclook.R;
import me.kaaninan.acclook.constructor.HesapConstructor;
import me.kaaninan.acclook.db.DatabaseManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TekHesap extends Fragment {
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	public TekHesap() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.hesap_tek, container, false);
		
		TextView textToplam = (TextView) rootView.findViewById(R.id.textHesapTotal);
		
		DatabaseManager manager = new DatabaseManager(getActivity().getApplicationContext());
		ArrayList<HesapConstructor> listHesap = manager.getHesaplar();
		
		int position = getArguments().getInt(ARG_SECTION_NUMBER);
		HesapConstructor hesap = listHesap.get(position);
		
		String toplam = Double.toString(hesap.getToplam());
		
		textToplam.setText(toplam);
		return rootView;
	}
}// END Tek Hesap