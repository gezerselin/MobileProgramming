package tr.edu.yildiz.selin_16011068;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SoruListelemeActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String email;
    private String password;
    private int id;

    private TextView soruSayisi;
    SoruAdapter soruAdapter;
    RecyclerView recyclerView;
    ArrayList<Soru> sorular;
    Kullanici kullanici;
    veriKaynagi vk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_listeleme);

        vk = new veriKaynagi(this);
        vk.open();

        sp=getSharedPreferences("GirisBilgi",MODE_PRIVATE);
        editor=sp.edit();
        email=sp.getString("Email","email yok");
        password = sp.getString("Password", "Şifre Yok");
        drawerLayout=findViewById(R.id.drawer_Layout);

        recyclerView=findViewById(R.id.recycleView);
        soruSayisi= (TextView) findViewById(R.id.SoruSayisi);


    }

    @Override
    protected void onStart(){
        super.onStart();
        id=vk.kullaniciId(email);
        sorular=vk.getAllSoruById(id);
        soruSayisi.setText("Toplam soru sayısı: "+sorular.size());

        soruAdapter= new SoruAdapter(sorular,this);
        recyclerView.setAdapter(soruAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
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
        recreate();
    }

    public void ClickSinavOlusturma(View view){
        AnaEkranActivity.redirectActivity(this,SinavOlusturmaActivity.class);
        finish();
    }

    public void ClickSinavAyarlari(View view){
        AnaEkranActivity.redirectActivity(this,SinavAyarlariActivity.class);
        finish();
    }

    public void ClickCikisYap(View view){
        editor.remove("Email");
        editor.remove("Password");
        editor.commit();
        startActivity(new Intent(SoruListelemeActivity.this, MainActivity.class));
        finish();
    }

    protected void onPause() {
        super.onPause();
        AnaEkranActivity.closeDrawer(drawerLayout);
    }

}
