package me.kaaninan.acclook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Alinacak extends Fragment {
	 
    public static Fragment newInstance(Context context) {
    	Alinacak f = new Alinacak();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.alinacak, null);
        return root;
    }
 
}