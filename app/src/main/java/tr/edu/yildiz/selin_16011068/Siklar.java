package tr.edu.yildiz.selin_16011068;

public class Siklar {
    String secenek;
    String secenekDetay;

    public Siklar(){
    }

    public Siklar( String secenek, String secenekDetay) {
        this.secenek=secenek;
        this.secenekDetay = secenekDetay;
    }


    public String getSecenek() {
        return secenek;
    }

    public void setSecenek(String secenek) {
        this.secenek = secenek;
    }

    public String getSecenekDetay() {
        return secenekDetay;
    }

    public void setSecenekDetay(String secenekDetay) {
        this.secenekDetay = secenekDetay;
    }
}
