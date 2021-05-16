package tr.edu.yildiz.selin_16011068;

import android.provider.ContactsContract;
import android.widget.ImageView;

public class Kullanici {
    int id;
    String ad;
    String soyad;
    String email;
    String telNo;
    String sifre ;
    public Kullanici(){

    }
    public Kullanici(int id,String ad,String soyad,String email,String telNo,String sifre){
        this.id=id;
        this.ad=ad;
        this.soyad=soyad;
        this.email=email;
        this.telNo=telNo;
        this.sifre=sifre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
