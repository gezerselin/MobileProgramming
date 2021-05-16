package tr.edu.yildiz.selin_16011068;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static tr.edu.yildiz.selin_16011068.R.id.editTextPassword;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail , editTextPassword;
    private Button buttonGiris;
    private Button buttonUyeOlmaEkran;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String email;
    private String password;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=0;
        final veriKaynagi vk = new veriKaynagi(this);
        vk.open();

        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        buttonGiris = (Button) findViewById(R.id.buttonGirisYap) ;
        buttonUyeOlmaEkran= (Button) findViewById(R.id.buttonUyeOlmaEkrani) ;
        sp=getSharedPreferences("GirisBilgi",MODE_PRIVATE);
        editor=sp.edit();

        email=sp.getString("Email","email yok");
        password = sp.getString("Password", "Şifre Yok");

        if(email!="email yok" && password!="Şifre Yok"){
            startActivity(new Intent(MainActivity.this,AnaEkranActivity.class));
            finish();
        }

        buttonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    boolean giris = vk.girisIzni(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    if(giris){
                        editor.putString("Email",editTextEmail.getText().toString());
                        editor.putString("Password",editTextPassword.getText().toString());
                        if(!sp.contains(editTextEmail.getText().toString())){
                            editor.putString(editTextEmail.getText().toString(),"60,5,5");
                        }
                        editor.commit();

                        startActivity(new Intent(MainActivity.this,AnaEkranActivity.class));
                        Toast.makeText(getApplicationContext(), "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Giriş Hatalı", Toast.LENGTH_SHORT).show();
                        count++;
                        if(count==3){
                            startActivity(new Intent(MainActivity.this,UyeOlmaActivity.class));
                            Toast.makeText(getApplicationContext(), "3 kere üst üste hatalı giriş yaptınız !!!", Toast.LENGTH_SHORT).show();
                            count=0;
                        }
                    }
            }
        });
        buttonUyeOlmaEkran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UyeOlmaActivity.class));

            }
        });

    }
}