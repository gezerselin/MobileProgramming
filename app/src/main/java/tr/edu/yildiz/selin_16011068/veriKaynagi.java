package tr.edu.yildiz.selin_16011068;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class veriKaynagi {
    SQLiteDatabase db;
    sqliteKatmani bdb;

    public veriKaynagi(Context c){
        bdb=new sqliteKatmani(c);
    }
    public void open(){
        db=bdb.getWritableDatabase();
    }
    public void close(){
        bdb.close();
    }

    public void kullaniciOlustur(String ad, String soyad, String numara, String email, String sifre){
        Kullanici k =new Kullanici();
        k.ad=ad;
        k.soyad=soyad;
        k.telNo=numara;
        k.email=email;
        k.sifre=sifre;
        ContentValues val = new ContentValues();
        val.put("ad",k.getAd());
        val.put("soyad",k.getSoyad());
        val.put("email",k.getEmail());
        val.put("telNo",k.getTelNo());
        val.put("sifre",k.getSifre());
        long pkey =db.insert("Kullanici",null,val);
    }

    public void soruEkle(Soru s){
        ContentValues val = new ContentValues();
        val.put("soru",s.getSoru());
        val.put("dogruCevap",s.getDogruCevap());
        val.put("yanlisCevap1",s.getYanlisCevap1());
        val.put("yanlisCevap2",s.getYanlisCevap2());
        val.put("yanlisCevap3",s.getYanlisCevap3());
        val.put("yanlisCevap4",s.getYanlisCevap4());
        val.put("kullaniciId",s.getKullaniciId());
        long pkey =db.insert("Sorular",null,val);
    }

    public int soruGuncelle(Soru s){
        ContentValues cv = new ContentValues();
        cv.put("soru",s.getSoru());
        cv.put("dogruCevap",s.getDogruCevap());
        cv.put("yanlisCevap1",s.getYanlisCevap1());
        cv.put("yanlisCevap2",s.getYanlisCevap2());
        cv.put("yanlisCevap3",s.getYanlisCevap3());
        cv.put("yanlisCevap4",s.getYanlisCevap4());
        cv.put("kullaniciId",s.getKullaniciId());
        int id=s.getId();

        return db.update("Sorular",cv,"id=?",new String[]{String.valueOf(id)});
    }

    public int soruSil(int id){
        return db.delete("Sorular","id=?",new String[]{String.valueOf(id)});
    }


    public ArrayList<Soru> getAllSoruById(int gelen_id){
        ArrayList<Soru> sorular = new ArrayList<>();
        String q="SELECT * FROM Sorular WHERE  kullaniciId="+gelen_id;
        Cursor cursor = db.rawQuery(q,null);
        if(cursor.moveToFirst()){
            do{
                int id= cursor.getInt(0);
                String soru=cursor.getString(1);
                String dogruCevap=cursor.getString(2);
                String yanlisCevap1=cursor.getString(3);
                String yanlisCevap2=cursor.getString(4);
                String yanlisCevap3=cursor.getString(5);
                String yanlisCevap4=cursor.getString(6);
                int kullaniciId=cursor.getInt(7);
                Soru s = new Soru(id,soru,dogruCevap,yanlisCevap1,yanlisCevap2,yanlisCevap3,yanlisCevap4,kullaniciId);
                sorular.add(s);
            }
            while(cursor.moveToNext());
        }

        return sorular;
    }
    public Kullanici getKullanici(int gelen_id){
        String q="SELECT * FROM Kullanici WHERE  id="+gelen_id;
        Cursor cursor = db.rawQuery(q,null);
        cursor.moveToNext();
        int id= cursor.getInt(0);
        String ad=cursor.getString(1);
        String soyad=cursor.getString(2);
        String email =cursor.getString(3);
        String telNo=cursor.getString(4);
        String sifre=cursor.getString(5);
        Kullanici k = new Kullanici(id,ad,soyad,email,telNo,sifre);

        return k;
    }

    public boolean girisIzni(String gelenEmail,String gelenSifre){
        String[] columns ={ "id" };
        return bdb.checkUser(gelenEmail,gelenSifre);

    }

    public boolean aynıMailVarMi(String gelenEmail){
        String[] columns ={ "id" };
        return bdb.checkEmail(gelenEmail);

    }

    public int  kullaniciId(String email){
        return bdb.getUserId(email);
    }

}
