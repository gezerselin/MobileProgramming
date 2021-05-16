package tr.edu.yildiz.selin_16011068;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
public class UyeOlmaActivity extends AppCompatActivity {

    private EditText ad;
    private EditText soyad;
    private EditText numara;
    private EditText email;
    private EditText parola1;
    private EditText parola2;
    private Button buttonUyeOl;
    private Button buttonResimEkle;
    private ImageView imageView;
    static final int SELECT_IMAGE = 12 ;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_olma);

        final veriKaynagi vk = new veriKaynagi(this);
        vk.open();

        buttonUyeOl= (Button) findViewById(R.id.buttonKayit) ;
        buttonResimEkle=(Button) findViewById(R.id.buttonResimSec);
        imageView= findViewById(R.id.imageView);
        ad=(EditText) findViewById(R.id.editTextName);
        soyad=(EditText) findViewById(R.id.editTextLastname);
        numara=(EditText) findViewById(R.id.editTextPhone);
        email=(EditText) findViewById(R.id.editTextEmailAddress);
        parola1=(EditText) findViewById(R.id.editTextPassword1);
        parola2=(EditText) findViewById(R.id.editTextTextPassword2);

        buttonResimEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_IMAGE);
            }
        });

        buttonUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((!ad.getText().toString().equals("")) && (!soyad.getText().toString().equals("") && (!email.getText().toString().equals("")) && (!numara.getText().toString().equals("")) && (!parola1.getText().toString().equals("")) && (!parola2.getText().toString().equals("") ))) {
                    boolean check = vk.aynıMailVarMi(email.getText().toString());
                    if(!check) {
                        if (parola1.getText().toString().equals(parola2.getText().toString())) {

                            vk.kullaniciOlustur(ad.getText().toString(), soyad.getText().toString(), numara.getText().toString(), email.getText().toString(), parola1.getText().toString());

                            startActivity(new Intent(UyeOlmaActivity.this, MainActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Girilen parolalar aynı olmalı", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Bu Email ile bir kayıt bulunmakta :(", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Lütfen bilgileri eksiksiz doldurun :)", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_IMAGE && resultCode==RESULT_OK){
            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }
    }

}