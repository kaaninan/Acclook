package me.kaaninan.acclook.constructor;

public class HesapConstructor {
    
    int _id;
    String _isim;
    double _toplam;
    String _birim;
    
    public HesapConstructor(){}
    
    public int getId(){
        return this._id;
    }
     
    public void setId(int id){
        this._id = id;
    }
     
    public String getIsim(){
        return this._isim;
    }
     
    public void setIsim(String isim){
        this._isim = isim;
    }
     
    public double getToplam(){
        return this._toplam;
    }
     
    public void setToplam(double toplam){
        this._toplam = toplam;
    }
    
    public String getBirim(){
        return this._birim;
    }
     
    public void setBirim(String birim){
        this._birim = birim;
    }

}