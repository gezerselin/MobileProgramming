package tr.edu.yildiz.selin_16011068;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class AnaEkranActivity extends AppCompatActivity {
  //  private Button buttonCikis;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String email;
    private String password;
    private TextView textViewHosgeldin;
    private DrawerLayout drawerLayout;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Kullanici kullanici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);

        final veriKaynagi vk = new veriKaynagi(this);
        vk.open();

        textViewHosgeldin =(TextView) findViewById(R.id.textViewHosgeldin);

        sp=getSharedPreferences("GirisBilgi",MODE_PRIVATE);
        editor=sp.edit();
        email=sp.getString("Email","email yok");
        password = sp.getString("Password", "Şifre Yok");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);

        int id=vk.kullaniciId(email);
        kullanici=vk.getKullanici(id);

        textViewHosgeldin.setText("Hoşgeldiniz\n\n"+kullanici.ad +" "+ kullanici.soyad );

    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void  ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        recreate();
    }

    public void ClickSoruEkleme(View view){
        redirectActivity(this,SoruEklemeActivity.class);
    }

    public void ClickSoruListeleme(View view){
        redirectActivity(this,SoruListelemeActivity.class);
    }

    public void ClickSinavOlusturma(View view){
        redirectActivity(this,SinavOlusturmaActivity.class);
    }

    public void ClickSinavAyarlari(View view){
        redirectActivity(this,SinavAyarlariActivity.class);
    }

    public void ClickCikisYap(View view){
        editor.remove("Email");
        editor.remove("Password");
        editor.commit();
        startActivity(new Intent(AnaEkranActivity.this, MainActivity.class));
        finish();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

}