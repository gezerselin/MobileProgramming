package tr.edu.yildiz.selin_16011068;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SinavAyarlariActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String email;
    private String password;
    private String ayarlar;
    private int sinavSuresi;
    private int zorlukDerecesi;
    private int sinavPuani;
    private Button buttonAyarGuncelle;
    private TextView textViewSure, textViewPuan;
    String derece;
    String puan;
    String sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinav_ayarlari);

        sp=getSharedPreferences("GirisBilgi",MODE_PRIVATE);
        editor=sp.edit();
        email=sp.getString("Email","email yok");
        password = sp.getString("Password", "Şifre Yok");
        drawerLayout=findViewById(R.id.drawer_Layout);

        ayarlar = sp.getString(email,"def");
        ParseSettings(ayarlar);

        Spinner spinner =(Spinner) findViewById(R.id.spinnerZorluk);
        textViewPuan=(TextView) findViewById(R.id.editTextPuan) ;
        textViewSure=(TextView) findViewById(R.id.editTextSure);
        buttonAyarGuncelle=(Button) findViewById(R.id.buttonAyarGuncelle);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SinavAyarlariActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.zorlukDerecesi));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        spinner.setSelection(getIndex(spinner, String.valueOf(zorlukDerecesi)));

        textViewSure.setText(String.valueOf(sinavSuresi));
        textViewPuan.setText(String.valueOf(sinavPuani));
        derece=String.valueOf(zorlukDerecesi);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] dereceDizisi = getResources().getStringArray(R.array.zorlukDerecesi);
                derece = dereceDizisi[position];
                spinner.setSelection(getIndex(spinner,String.valueOf(derece)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAyarGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puan=textViewPuan.getText().toString().trim();
                sure=textViewSure.getText().toString().trim();
                String son = sure+","+puan+","+derece;
                editor.putString(email,son);
                editor.commit();

                startActivity(new Intent(SinavAyarlariActivity.this, SinavOlusturmaActivity.class));
                Toast.makeText(getApplicationContext(), "Sınav ayarları güncellendi :)", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private int getIndex (Spinner spinner, String s){
        for(int i=0;i<spinner.getCount();i++){
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(s)){
                return i;
            }
        }
        return 0;
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
        AnaEkranActivity.redirectActivity(this,SinavOlusturmaActivity.class);
        finish();
    }

    public void ClickSinavAyarlari(View view){
        recreate();
    }

    public void ClickCikisYap(View view){
        editor.remove("Email");
        editor.remove("Password");
        editor.commit();
        startActivity(new Intent(SinavAyarlariActivity.this, MainActivity.class));
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