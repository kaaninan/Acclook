package me.kaaninan.acclook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	public static final String
	DATABASE_CREATE_HESAP = "CREATE TABLE " + DatabaseContract.Hesap.TABLE_NAME + " (" 
		+ DatabaseContract.Hesap.ID + " integer primary key autoincrement,"
		+ DatabaseContract.Hesap.COLUMN_ISIM + " text , "
		+ DatabaseContract.Hesap.COLUMN_TOPLAM + " double );";
	
	public static final String 
	DATABASE_CREATE_KAYIT = "CREATE TABLE " + DatabaseContract.Kayit.TABLE_NAME + " (" 
		+ DatabaseContract.Kayit.ID + " integer primary key autoincrement,"
		+ DatabaseContract.Kayit.COLUMN_TUR + " text,"
		+ DatabaseContract.Kayit.COLUMN_TUTAR + " double , "
		+ DatabaseContract.Kayit.COLUMN_NOT + " text , "
		+ DatabaseContract.Kayit.COLUMN_HESAP_ID + " text , "
		+ DatabaseContract.Kayit.COLUMN_KATEGORI_ID + " text , "
		+ DatabaseContract.Kayit.COLUMN_TARIH + " datetime );";
	
	public static final String 
	DATABASE_CREATE_TOPLAM = "CREATE TABLE " + DatabaseContract.Toplam.TABLE_NAME + " (" 
		+ DatabaseContract.Toplam.ID + " integer primary key autoincrement,"
		+ DatabaseContract.Toplam.COLUMN_GELIR + " double,"
		+ DatabaseContract.Toplam.COLUMN_GIDER + " double );";
	
	public static final String DATABASE_DROP_HESAP ="DROP TABLE IF EXISTS " + DatabaseContract.Hesap.TABLE_NAME;
	public static final String DATABASE_DROP_KAYIT ="DROP TABLE IF EXISTS " + DatabaseContract.Kayit.TABLE_NAME;
	public static final String DATABASE_DROP_TOPLAM ="DROP TABLE IF EXISTS " + DatabaseContract.Toplam.TABLE_NAME;

	public DatabaseHelper(Context context){
		super(context, DatabaseContract.DATABASE_NAME , null, DatabaseContract.DATABASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_HESAP);
		db.execSQL(DATABASE_CREATE_KAYIT);
		db.execSQL(DATABASE_CREATE_TOPLAM);
		
		// Ekleme
		db.execSQL("INSERT INTO "+DatabaseContract.Hesap.TABLE_NAME+" ("+DatabaseContract.Hesap.COLUMN_ISIM+","+DatabaseContract.Hesap.COLUMN_TOPLAM+") VALUES ('Nakit','30')");
		db.execSQL("INSERT INTO "+DatabaseContract.Hesap.TABLE_NAME+" ("+DatabaseContract.Hesap.COLUMN_ISIM+","+DatabaseContract.Hesap.COLUMN_TOPLAM+") VALUES ('Paypal','20')");
		db.execSQL("INSERT INTO "+DatabaseContract.Hesap.TABLE_NAME+" ("+DatabaseContract.Hesap.COLUMN_ISIM+","+DatabaseContract.Hesap.COLUMN_TOPLAM+") VALUES ('Kredi Kartý','55')");
		
		}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("DatabaseHelper", "Veritabani " + oldVersion + "\'dan " + newVersion + "\'a guncelleniyor.");
		db.execSQL(DATABASE_DROP_HESAP);
		db.execSQL(DATABASE_DROP_KAYIT);
		db.execSQL(DATABASE_DROP_TOPLAM);
		onCreate(db);
	}
}
