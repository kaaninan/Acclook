package me.kaaninan.acclook.test;

import java.util.ArrayList;

import me.kaaninan.acclook.R;
import me.kaaninan.acclook.constructor.HesapConstructor;
import me.kaaninan.acclook.constructor.KayitConstructor;
import me.kaaninan.acclook.db.DatabaseContract;
import me.kaaninan.acclook.db.DatabaseHelper;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class DatabaseList extends Activity{
	
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	private ArrayList<KayitConstructor> kayitlar;
	private	ArrayList<HesapConstructor> hesaplar;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		kayitlar = getKayitlar();
		hesaplar = getHesaplar();
		
		ListView kayit = (ListView) findViewById(R.id.listKayit);
		ListView hesap = (ListView) findViewById(R.id.listHesap);
		
		KayitAdapter adapterKayit = new KayitAdapter (this, R.layout.test_list_kayit, kayitlar);
		kayit.setAdapter(adapterKayit);
	}
	

	public ArrayList<KayitConstructor> getKayitlar() {
		
		ArrayList<KayitConstructor> todos = new ArrayList<KayitConstructor>();
		
		String selectQuery = "SELECT * FROM " +DatabaseContract.Kayit.TABLE_NAME+ " ORDER BY " +DatabaseContract.Kayit.COLUMN_TARIH+ " DESC";	
		
		helper = new DatabaseHelper(this);
		
	    db = helper.getReadableDatabase();
	    Cursor c = db.rawQuery(selectQuery, null);
	    //Cursor c = sorgulaKayitlar();
	    if (c.moveToFirst()) {
	        do {
	        	KayitConstructor td = new KayitConstructor();
	        	td.setId(c.getInt(c.getColumnIndex(DatabaseContract.Kayit.ID)));
	    	    td.setTur((c.getString(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_TUR))));
	    	    td.setTutar((c.getDouble(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_TUTAR))));
	    	    td.setNot((c.getString(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_NOT))));
	    	    td.setHesapId((c.getInt(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_HESAP_ID))));
	    	    td.setKategoriId((c.getInt(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_KATEGORI_ID))));
	    	    td.setTarih((c.getString(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_TARIH))));
	    	    td.type = ((c.getInt(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_TYPE))));
	    	    
	            todos.add(td);
	        } while (c.moveToNext());
	    }
	    return todos;
	}

	public ArrayList<HesapConstructor> getHesaplar(){
		
		ArrayList<HesapConstructor> hes = new ArrayList<HesapConstructor>();
	    
		String selectQuery = "SELECT * FROM " + DatabaseContract.Hesap.TABLE_NAME;
	    
	    db = helper.getReadableDatabase();
	    Cursor hesapC = db.rawQuery(selectQuery, null);
	    //Cursor c = sorgulaKayitlar();
	    if (hesapC.moveToFirst()) {
	        do {
	        	HesapConstructor hp = new HesapConstructor();
	        	hp.setId((hesapC.getInt(hesapC.getColumnIndex(DatabaseContract.Hesap.ID))));
	        	hp.setIsim((hesapC.getString(hesapC.getColumnIndex(DatabaseContract.Hesap.COLUMN_ISIM))));
	        	hp.setToplam((hesapC.getInt(hesapC.getColumnIndex(DatabaseContract.Hesap.COLUMN_TOPLAM))));
	        	hp.setBirim((hesapC.getString(hesapC.getColumnIndex(DatabaseContract.Hesap.ID))));
	 
	        	hes.add(hp);
	        } while (hesapC.moveToNext());
	    }
	    return hes;
	}
	
}