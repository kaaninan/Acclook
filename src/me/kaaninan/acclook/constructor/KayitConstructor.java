package me.kaaninan.acclook.constructor;

public class KayitConstructor {
	
	
	// PinnedSectionListView

	public static int ITEM = 0;
	public static int SECTION = 1;
	
	public int sectionPosition;
	public int listPosition;
	
	public int type;
	
	// ###
	
	
	

	public int _id;
	public String _tur;
	public double _tutar;
	public String _not;
	public int _hesap_id;
	public int _kategori_id;
	public String _tarih;
    
    String _header;
    
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
    
    
    
    // HEADER
    public String getHeader(){
    	return this._header;
    }
    
    public void setHeader(String header){
    	this._header = header;
    }
    
    
    
    
}