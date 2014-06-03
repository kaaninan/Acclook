package me.kaaninan.acclook.db;

import me.kaaninan.acclook.R;
import android.provider.BaseColumns;

public final class DatabaseContract {
	
	public static final int DATABASE_VERSION = 24;
	public static final String DATABASE_NAME= "db";
	
	public static class Hesap implements BaseColumns {
		
		private Hesap() {}
		
		public static final String TABLE_NAME = "tablo_hesap";
		public static final String ID = "_id";
		public static final String COLUMN_ISIM = "isim_hesap";
		public static final String COLUMN_TOPLAM = "toplam_hesap";
		
		public static final String DEFAULT_SORT_ORDER = "ad ASC";
		
		public static final String[] FULL_PROJECTION = new String[] {
			ID, 
			COLUMN_ISIM,
			COLUMN_TOPLAM};
	}
	
	public static class Kategori implements BaseColumns {
		
		private Kategori() {}
		
		public static final String TABLE_NAME = "tablo_kategori";
		public static final String ID = "_id";
		public static final String COLUMN_ISIM = "isim_kategori";
		public static final String COLUMN_RENK = "renk_kategori";
		
		public static final String DEFAULT_SORT_ORDER = "ad ASC";
		
		public static final String[] FULL_PROJECTION = new String[] {
			ID, 
			COLUMN_ISIM,
			COLUMN_RENK};
	}
	
	public static class Kayit implements BaseColumns {
		
		private Kayit() {}
		
		public static final String TABLE_NAME = "tablo_kayit";
		public static final String ID = "_id";
		public static final String COLUMN_TUR = "tur_kayit";
		public static final String COLUMN_TUTAR = "tutar_kayit";
		public static final String COLUMN_NOT = "_not_kayit";
		public static final String COLUMN_HESAP_ID= "hesap_id_kayit";
		public static final String COLUMN_KATEGORI_ID = "kategori_id_kayit";
		public static final String COLUMN_TARIH = "tarih_kayit";
		public static final String COLUMN_TYPE = "type";
		
		
		public static final String DEFAULT_SORT_ORDER = "ad ASC";
		
		public static final String[] FULL_PROJECTION = new String[] {
			ID,
			COLUMN_TUR,
			COLUMN_TUTAR,
			COLUMN_NOT,
			COLUMN_HESAP_ID,
			COLUMN_KATEGORI_ID,
			COLUMN_TARIH};
		
		public static final String[] from_kayit = new String[] {
				//DatabaseContract.Kayit.COLUMN_TUR,
	    		DatabaseContract.Kayit.COLUMN_TUTAR ,
	    		DatabaseContract.Kayit.COLUMN_NOT};
	    		//DatabaseContract.Kayit.COLUMN_HESAP,
	    		//DatabaseContract.Kayit.COLUMN_KATEGORI,
	    		//DatabaseContract.Kayit.COLUMN_ZAMAN};
	}

	public static final int[] to_list = new int[]{
		R.id.textKayitTutar,
		R.id.textKayitNot};

	public static class Toplam implements BaseColumns {
	
		private Toplam() {}
	
		public static final String TABLE_NAME = "tablo_toplam";
		public static final String ID = "_id";
		public static final String COLUMN_GELIR = "gelir_kayit";
		public static final String COLUMN_GIDER = "gider_kayit";
	
		public static final String DEFAULT_SORT_ORDER = "ad ASC";
	
		public static final String[] FULL_PROJECTION = new String[] {
			ID, 
			COLUMN_GELIR,
			COLUMN_GIDER};
	}
}
