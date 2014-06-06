package me.kaaninan.acclook.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import me.kaaninan.acclook.constructor.HesapConstructor;
import me.kaaninan.acclook.constructor.KayitConstructor;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;

public class DatabaseManager extends FragmentActivity {
	
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	@SuppressWarnings("unused")
	private Context context;
	
	public DatabaseManager(Context context) {
		this.context = context;
		helper = new DatabaseHelper(context);
	}

	// Cursor ##############################################################
	
	// Hesaplar
	public Cursor sorgulaKayitlar() {
		db = helper.getReadableDatabase();
		
		selectQuery = "SELECT * FROM "+ DatabaseContract.Kayit.TABLE_NAME +" ORDER BY "+DatabaseContract.Kayit.COLUMN_TARIH+" DESC "; //  
		Cursor c = db.rawQuery(selectQuery, null);
		//Cursor c = db.query(DatabaseContract.Kayit.TABLE_NAME, DatabaseContract.Kayit.FULL_PROJECTION, null, null, null, null, DatabaseContract.Kayit.COLUMN_TARIH_YIL+" ASC");
		return c;
	}
	
	// Kayýtlar
	public Cursor sorgulaHesaplar() {
    	db = helper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseContract.Hesap.TABLE_NAME, DatabaseContract.Hesap.FULL_PROJECTION, null, null, null, null, null);
        return cursor;
	}

	// End Cursor ##############################################################


	
	// Database Insert ##############################################################
	
	@SuppressLint("SimpleDateFormat")
	public String getDate(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String strDate = dateFormat.format(c.getTime());
		return strDate;
	}
	
	// Sadece SQL için
	@SuppressLint("SimpleDateFormat")
	public String getDateTime(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = dateFormat.format(c.getTime());
		return strDate;
	}
	
	// DB formatindaki tarihi ikiye bölme
	public String[] splitDateTime(String date){
		String[] tokens = date.split("(?<=\\G.{10})");
		String[] yeni = {tokens[0],tokens[1]};
		return yeni;
	}
	
	
	
	// DB formatýndaki tarihi "."ya çevir
	public String[] convertDateTimeTireNokta(String date){
		String[] tokens = date.split("(?<=\\G.{10})");
		String date2 = convertDateTireNokta(tokens[0]);
		String[] yeni = {date2,tokens[1]};
		return yeni;
	}
	
		public String convertDateTireNokta(String date){
			String delims = "[-]+";
			String[] tokens = date.split(delims);
			String newDate = tokens[2]+"."+tokens[1]+"."+tokens[0];
			return newDate;
		}
	
	
	// getDateTime'ý DB formatýna çevir
	public String[] convertDateTimeNoktaTire(String date){
		String[] tokens = date.split("(?<=\\G.{10})");
		String date2 = convertDateNoktaTire(tokens[0]);
		String[] yeni = {date2,tokens[1]};
		return yeni;
	}
	
		public String convertDateNoktaTire(String date){
			String delims = "[.]+";
			String[] tokens = date.split(delims);
			String newDate = tokens[2]+"-"+tokens[1]+"-"+tokens[0];
			return newDate;
		}
	
	
	public void ekleKayit(float tutar, String not, String tarih) {
		db = helper.getWritableDatabase();
		
    	ContentValues satir = new ContentValues();
    	satir.put(DatabaseContract.Kayit.COLUMN_TUTAR, tutar);
    	satir.put(DatabaseContract.Kayit.COLUMN_NOT, not);
    	satir.put(DatabaseContract.Kayit.COLUMN_TYPE, 0);
    	
    	if(!tarih.isEmpty()){
    		String[] dbTarih = convertDateTimeNoktaTire(tarih);
    		satir.put(DatabaseContract.Kayit.COLUMN_TARIH, dbTarih[0]+" "+dbTarih[1]);
    	}else{
    		satir.put(DatabaseContract.Kayit.COLUMN_TARIH, getDateTime());
    	}
    	
    	db.insert(DatabaseContract.Kayit.TABLE_NAME, null, satir);    	
	}
	
	// End Database Insert ##############################################################

	
	
	// Hesaplar ###############################################################
	
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
	
	// Hesap isimleri #
	public ArrayList<String> getHesapIsimler(){
		
		ArrayList<String> hes = new ArrayList<String>();
	    
		String selectQuery = "SELECT * FROM " + DatabaseContract.Hesap.TABLE_NAME;
	    
	    db = helper.getReadableDatabase();
	    Cursor hesapC = db.rawQuery(selectQuery, null);
	    //Cursor c = sorgulaKayitlar();
	    if (hesapC.moveToFirst()) {
	        do {
	        	hes.add((hesapC.getString(hesapC.getColumnIndex(DatabaseContract.Hesap.COLUMN_ISIM))));
	        } while (hesapC.moveToNext());
	    }
	    return hes;
	}
	
	public ArrayList<String> getHesapToplam(){
		
		ArrayList<String> hes = new ArrayList<String>();
	    
		String selectQuery = "SELECT * FROM " + DatabaseContract.Hesap.TABLE_NAME;
	    
	    db = helper.getReadableDatabase();
	    Cursor hesapC = db.rawQuery(selectQuery, null);
	    //Cursor c = sorgulaKayitlar();
	    if (hesapC.moveToFirst()) {
	        do {
	        	hes.add((hesapC.getString(hesapC.getColumnIndex(DatabaseContract.Hesap.COLUMN_TOPLAM))));
	        } while (hesapC.moveToNext());
	    }
	    return hes;
	}
	
	public HashMap<String, List<String>> getHesapIcerik(){
		HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
	    
		ArrayList<HesapConstructor> list = new ArrayList<HesapConstructor>();
		list = getHesaplar();
		
		ArrayList<String> isim = new ArrayList<String>();
		isim = getHesapIsimler();
		
		String selectQuery = "SELECT * FROM " + DatabaseContract.Hesap.TABLE_NAME;
	    
	    db = helper.getReadableDatabase();
	    Cursor hesapC = db.rawQuery(selectQuery, null);
	    //Cursor c = sorgulaKayitlar();
	    if (hesapC.moveToFirst()) {
	        do {
	        	HesapConstructor hesap = list.get(0);
	    		listDataChild.put(hesap.getIsim(), isim);
	        	
	        } while (hesapC.moveToNext());
	    }
	    return listDataChild;
		
	}
	
	
	public int hesaplarCount(){
		Cursor c = sorgulaHesaplar();
		int count = 0;
		if(c.moveToNext()){
			do{
				count = count+1;
			}while(c.moveToNext());
		}
		return count;
	}
	
	// End Hesaplar ##############################################################
	
	
	
	// Kayitlar ##############################################################
	
	private String selectQuery;
	
	public int sectionPosition = 0;
	public int listPosition = 0;
	
	public ArrayList<KayitConstructor> getKayitlar(String dbTarih, String tercih) {
		
		ArrayList<KayitConstructor> todos = new ArrayList<KayitConstructor>();
		
		if(dbTarih != null){
			if(tercih == "gun"){
				String[] tarih = convertDateTimeTireNokta(dbTarih);
				String delims = "[.]+";
				String[] gun_ay_yil = tarih[0].split(delims);
				selectQuery = "SELECT * FROM "+DatabaseContract.Kayit.TABLE_NAME+ " WHERE "+DatabaseContract.Kayit.COLUMN_TARIH+" LIKE '%"+gun_ay_yil[2]+"-"+gun_ay_yil[1]+"-"+gun_ay_yil[0]+"%'";
			}else if(tercih == "ay"){
				String[] tarih = convertDateTimeTireNokta(dbTarih);
				String delims = "[.]+";
				String[] gun_ay_yil = tarih[0].split(delims);
				selectQuery = "SELECT * FROM "+DatabaseContract.Kayit.TABLE_NAME+ " WHERE "+DatabaseContract.Kayit.COLUMN_TARIH+" LIKE '%"+gun_ay_yil[2]+"-"+gun_ay_yil[1]+"%'";
			}else if(tercih == "yil"){
				String[] tarih = convertDateTimeTireNokta(dbTarih);
				String delims = "[.]+";
				String[] gun_ay_yil = tarih[0].split(delims);
				selectQuery = "SELECT * FROM " +DatabaseContract.Kayit.TABLE_NAME+ " WHERE "+DatabaseContract.Kayit.COLUMN_TARIH+" LIKE '%"+gun_ay_yil[2]+"%'";
			}
			
		}else{
			selectQuery = "SELECT * FROM " +DatabaseContract.Kayit.TABLE_NAME+ " ORDER BY " +DatabaseContract.Kayit.COLUMN_TARIH+ " DESC";	
		}
		
		
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
	 
	    	    td.sectionPosition = sectionPosition;
	    	    td.listPosition = listPosition++;
	    	    
	            todos.add(td);
	        } while (c.moveToNext());
	    }
	    return todos;
	}
	
	public int kayitlarCount(String tarih){
		Cursor c = sorgulaKayitlar();
		int count = 0;
		if(c.moveToNext()){
			do{
				count = count+1;
			}while(c.moveToNext());
		}
		return count;
	}
	
	
	public KayitConstructor getKayit(long kayit_id) {
	    db = helper.getReadableDatabase();
	 
	    String selectQuery = "SELECT  * FROM " + DatabaseContract.Kayit.TABLE_NAME + " WHERE "
	            + DatabaseContract.Kayit.COLUMN_HESAP_ID + " = " + kayit_id;
	 
	 
	    //Cursor c = db.rawQuery(selectQuery, null);
	    Cursor c = db.rawQuery(selectQuery, null);
	    
	    if (c != null)
	        c.moveToFirst();
	 
	    KayitConstructor td = new KayitConstructor();
	    td.setHesapId(c.getInt(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_HESAP_ID)));
	    td.setTur((c.getString(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_TUR))));
	    td.setTutar((c.getDouble(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_TUTAR))));
	    td.setNot((c.getString(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_NOT))));
	    td.setTarih((c.getString(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_NOT))));
	    td.setHesapId((c.getInt(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_NOT))));
	    td.setKategoriId((c.getInt(c.getColumnIndex(DatabaseContract.Kayit.COLUMN_NOT))));
	 
	    return td;
	}
	
	// End Kayitlar ##############################################################

}
