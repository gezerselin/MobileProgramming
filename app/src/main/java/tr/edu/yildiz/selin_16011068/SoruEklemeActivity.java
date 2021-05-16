package tr.edu.yildiz.selin_16011068;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SoruEklemeActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String email;
    private String password;
    private EditText soru;
    private EditText dogruCevap;
    private EditText yanlisCevap1;
    private EditText yanlisCevap2;
    private EditText yanlisCevap3;
    private EditText yanlisCevap4;
    private int kullaniciId;
    private Button buttonSoruKaydet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_ekleme);

        final veriKaynagi vk = new veriKaynagi(this);
        vk.open();

        sp=getSharedPreferences("GirisBilgi",MODE_PRIVATE);
        editor=sp.edit();
        email=sp.getString("Email","email yok");
        password = sp.getString("Password", "Şifre Yok");
        drawerLayout=findViewById(R.id.drawer_Layout);

        buttonSoruKaydet= (Button) findViewById(R.id.buttonSoruGuncelle);
        soru=(EditText) findViewById(R.id.multiAutoCompleteTextSoruUpdate);
        dogruCevap=(EditText) findViewById(R.id.multiAutoCompleteTextDogruCevapUpdate);
        yanlisCevap1=(EditText)findViewById(R.id.multiAutoCompleteTextYanlisCevap1Update);
        yanlisCevap2=(EditText)findViewById(R.id.multiAutoCompleteTextYanlisCevap2Update);
        yanlisCevap3=(EditText)findViewById(R.id.multiAutoCompleteTextYanlisCevap3Update);
        yanlisCevap4=(EditText)findViewById(R.id.multiAutoCompleteTextYanlisCevap4Update);


        buttonSoruKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!soru.getText().toString().equals("") && !dogruCevap.getText().toString().equals("") && !yanlisCevap1.getText().toString().equals("") && !yanlisCevap2.getText().toString().equals("") && !yanlisCevap3.getText().toString().equals("")&&!yanlisCevap4.getText().toString().equals("")){
                    Soru s= new Soru();
                    s.soru= soru.getText().toString();
                    s.dogruCevap=dogruCevap.getText().toString();
                    s.yanlisCevap1=yanlisCevap1.getText().toString();
                    s.yanlisCevap2=yanlisCevap2.getText().toString();
                    s.yanlisCevap3=yanlisCevap3.getText().toString();
                    s.yanlisCevap4=yanlisCevap4.getText().toString();
                    s.kullaniciId=vk.kullaniciId(email);
                    vk.soruEkle(s);
                    Toast.makeText(getApplicationContext(), "Soru başarıyla eklendi :)", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SoruEklemeActivity.this, SoruListelemeActivity.class));

                }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen tüm alanları doldurun :)", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        recreate();
    }

    public void ClickSoruListeleme(View view){
        AnaEkranActivity.redirectActivity(this,SoruListelemeActivity.class);
        finish();
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
        startActivity(new Intent(SoruEklemeActivity.this, MainActivity.class));
        finish();
    }

    protected void onPause() {
        super.onPause();
        AnaEkranActivity.closeDrawer(drawerLayout);
    }

}