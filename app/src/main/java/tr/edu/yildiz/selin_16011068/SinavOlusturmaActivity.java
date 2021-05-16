package tr.edu.yildiz.selin_16011068;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SinavOlusturmaActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String email;
    private String password;
    private String ayarlar;
    private int sinavSuresi;
    private int zorlukDerecesi;
    private int sinavPuani;
    int id;
    private TextView soruSayisi;
    Soru2Adapter soru2Adapter;
    RecyclerView recyclerView;
    ArrayList<Soru> sorular;
    Kullanici kullanici;
    veriKaynagi vk;
    private Button buttonAyar;
    private Button buttonKaydet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinav_olusturma);

        vk = new veriKaynagi(this);
        vk.open();


        sp=getSharedPreferences("GirisBilgi",MODE_PRIVATE);
        editor=sp.edit();
        email=sp.getString("Email","email yok");
        password = sp.getString("Password", "Şifre Yok");
        ayarlar = sp.getString(email,"def");
        ParseSettings(ayarlar);

        drawerLayout=findViewById(R.id.drawer_Layout);


        recyclerView=findViewById(R.id.recycleView2);
        soruSayisi= (TextView) findViewById(R.id.SoruSayisi2);
        buttonAyar=(Button)findViewById(R.id.sinavAyar);
        buttonKaydet=(Button)findViewById(R.id.sinavKaydet) ;

        id=vk.kullaniciId(email);
        sorular=vk.getAllSoruById(id);
        soruSayisi.setText("Toplam soru sayısı: "+sorular.size());

        soru2Adapter= new Soru2Adapter(sorular,this,zorlukDerecesi);
        recyclerView.setAdapter(soru2Adapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);

        CreateListOfData();

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(soru2Adapter.getSelected().size()>0){
                    StringBuilder stringBuilder=new StringBuilder();

                    for (int i=0;i < soru2Adapter.getSelected().size();i++){
                        int count = 1;
                        stringBuilder.append("Soru"+(i+1)+":\n\n");
                        stringBuilder.append(soru2Adapter.getSelected().get(i).getSoru());
                        stringBuilder.append("\n");

                        if(count<zorlukDerecesi){
                            stringBuilder.append("A)"+soru2Adapter.getSelected().get(i).getCevap1());
                            stringBuilder.append("\n");
                            stringBuilder.append("B)"+soru2Adapter.getSelected().get(i).getCevap2());
                            stringBuilder.append("\n");
                            count++;
                        }
                        if(count<zorlukDerecesi){
                            stringBuilder.append("C)"+soru2Adapter.getSelected().get(i).getCevap3());
                            stringBuilder.append("\n");
                            count++;
                        }
                        if(count < zorlukDerecesi){
                            stringBuilder.append("D)"+soru2Adapter.getSelected().get(i).getCevap4());
                            stringBuilder.append("\n");
                            count++;
                        }
                        if(count<zorlukDerecesi){

                            stringBuilder.append("E)"+soru2Adapter.getSelected().get(i).getCevap5());
                            stringBuilder.append("\n");
                            count++;
                        }

                        stringBuilder.append("----------------\n");
                    }
                    writeToFile(stringBuilder.toString().trim());
                }
                else{
                    ShowToast("Seçilen soru yok");
                }
            }

        });

        buttonAyar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SinavOlusturmaActivity.this, SinavAyarlariActivity.class));
            }
        });

    }

    void writeToFile(String text){

        try {
            File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+File.separator+"sinavlarim");
            filePath.mkdirs();
            FileOutputStream fos = new FileOutputStream(filePath+"example.txt");
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "İNDİRME İŞLEMİ TAMAMLANDI", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void CreateListOfData(){
        sorular=new ArrayList<>();
        for(int i =0; i<20;i++){
            Soru sor=new Soru();
            sor.setSoru("Soru" + i+1);
            if(i==0){
                sor.setChecked(true);
            }
            sorular.add(sor);
        }

    }
    private void ShowToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void ClickMenu(View view){
        AnaEkranActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        AnaEkranActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        AnaEkranActivity.redirectActivity(this,AnaEkranActivity.class);
        finish();
    }

    public void ClickSoruEkleme(View view){
        AnaEkranActivity.redirectActivity(this,SoruEklemeActivity.class);
        finish();
    }

    public void ClickSoruListeleme(View view){
        AnaEkranActivity.redirectActivity(this,SoruListelemeActivity.class);
        finish();
    }

    public void ClickSinavOlusturma(View view){
        recreate();
    }

    public void ClickSinavAyarlari(View view){
        AnaEkranActivity.redirectActivity(this,SinavAyarlariActivity.class);
        finish();
    }

    public void ClickCikisYap(View view){
        editor.remove("Email");
        editor.remove("Password");
        editor.commit();
        startActivity(new Intent(SinavOlusturmaActivity.this, MainActivity.class));
        finish();
    }

    protected void onPause() {
        super.onPause();
        AnaEkranActivity.closeDrawer(drawerLayout);
    }
    private void ParseSettings(String ayarlar){
        String[] ayarDizisi = ayarlar.split(",");
         sinavSuresi = Integer.parseInt(ayarDizisi[0]);
         sinavPuani= Integer.parseInt(ayarDizisi[1]);
         zorlukDerecesi= Integer.parseInt(ayarDizisi[2]);
    }
}