package tr.edu.yildiz.selin_16011068;

import java.io.Serializable;

public class Soru implements Serializable {
    int id;
    String soru;
    String dogruCevap;
    String yanlisCevap1;
    String yanlisCevap2;
    String yanlisCevap3;
    String yanlisCevap4;
    int kullaniciId;

    private boolean isChecked=false;

    public boolean isChecked(){
        return isChecked;
    }
    public void setChecked(boolean checked){
        isChecked=checked;
    }

    public Soru(){

    }
    public Soru(int id, String soru, String dogruCevap, String yanlisCevap1, String yanlisCevap2, String yanlisCevap3, String yanlisCevap4, int kullaniciId) {
        this.id=id;
        this.soru=soru;
        this.dogruCevap=dogruCevap;
        this.yanlisCevap1=yanlisCevap1;
        this.yanlisCevap2=yanlisCevap2;
        this.yanlisCevap3=yanlisCevap3;
        this.yanlisCevap4=yanlisCevap4;
        this.kullaniciId=kullaniciId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSoru() {

        return soru;
    }
    public String getCevap1(){
        return this.dogruCevap;
    }
    public String getCevap2(){
        return this.yanlisCevap1;
    }
    public String getCevap3(){
        return this.yanlisCevap2;
    }public String getCevap4(){
        return this.yanlisCevap3;
    }public String getCevap5(){
        return this.yanlisCevap4;
    }




    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getDogruCevap() {
        return dogruCevap;
    }

    public void setDogruCevap(String dogruCevap) {
        this.dogruCevap = dogruCevap;
    }

    public String getYanlisCevap1() {
        return yanlisCevap1;
    }

    public void setYanlisCevap1(String yanlisCevap1) {
        this.yanlisCevap1 = yanlisCevap1;
    }

    public String getYanlisCevap2() {
        return yanlisCevap2;
    }

    public void setYanlisCevap2(String yanlisCevap2) {
        this.yanlisCevap2 = yanlisCevap2;
    }

    public String getYanlisCevap3() {
        return yanlisCevap3;
    }

    public void setYanlisCevap3(String yanlisCevap3) {
        this.yanlisCevap3 = yanlisCevap3;
    }

    public String getYanlisCevap4() {
        return yanlisCevap4;
    }

    public void setYanlisCevap4(String yanlisCevap4) {
        this.yanlisCevap4 = yanlisCevap4;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }
}
