package me.kaaninan.acclook.constructor;

public class KayitConstructor {

	public  int ITEM = 0;
	public static  int SECTION = 1;

	public  int type;
	public  String text;

	public int sectionPosition;
	public int listPosition;
	
    int _id;
    String _tur;
    double _tutar;
    String _not;
    int _hesap_id;
    int _kategori_id;
    String _tarih;
    
    public KayitConstructor(){}

    // ID
    public int getId(){
        return this._id;
    }
    
    public void setId(int id){
        this._id = id;
    }
    
    // TUR
    public String getTur(){
        return this._tur;
    }
     
    public void setTur(String tur){
        this._tur = tur;
    }
    
    // TUTAR
    public double getTutar(){
        return this._tutar;
    }
     
    public void setTutar(double tutar){
        this._tutar = tutar;
    }
    
    //NOT
    public String getNot(){
        return this._not;
    }
     
    public void setNot(String not){
        this._not = not;
    }
    
    // KATEGORI
    public int getKategoriId(){
        return this._hesap_id;
    }
     
    public void setKategoriId(int kategori_id){
        this._kategori_id = kategori_id;
    }
    
    // HESAP
    public int getHesapId(){
        return this._hesap_id;
    }
     
    public void setHesapId(int hesap_id){
        this._hesap_id = hesap_id;
    }
    
    // TARÝH
    public String getTarih(){
        return this._tarih;
    }
    
    public void setTarih(String tarih){
        this._tarih = tarih;
    }
    
    
    
    
}