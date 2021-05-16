package tr.edu.yildiz.selin_16011068;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SoruGuncellemeActivity extends AppCompatActivity {
    private EditText soru;
    private EditText dogruCevap;
    private EditText yanlisCevap1;
    private EditText yanlisCevap2;
    private EditText yanlisCevap3;
    private EditText yanlisCevap4;
    private Button buttonSoruGuncelle;
    int id;
    int kullaniciId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_guncelleme);

        final veriKaynagi vk = new veriKaynagi(this);
        vk.open();

        Soru s = (Soru) getIntent().getExtras().getSerializable("SORU");

        id=s.getId();
        kullaniciId=s.getKullaniciId();
        soru=(EditText) findViewById(R.id.multiAutoCompleteTextSoruUpdate);
        dogruCevap=(EditText) findViewById(R.id.multiAutoCompleteTextDogruCevapUpdate);
        yanlisCevap1=(EditText)findViewById(R.id.multiAutoCompleteTextYanlisCevap1Update);
        yanlisCevap2=(EditText)findViewById(R.id.multiAutoCompleteTextYanlisCevap2Update);
        yanlisCevap3=(EditText)findViewById(R.id.multiAutoCompleteTextYanlisCevap3Update);
        yanlisCevap4=(EditText)findViewById(R.id.multiAutoCompleteTextYanlisCevap4Update);
        buttonSoruGuncelle=(Button)findViewById(R.id.buttonSoruGuncelle) ;

        soru.setText(s.getSoru());
        dogruCevap.setText(s.getDogruCevap());
        yanlisCevap1.setText(s.getYanlisCevap1());
        yanlisCevap2.setText(s.getYanlisCevap2());
        yanlisCevap3.setText(s.getYanlisCevap3());
        yanlisCevap4.setText(s.getYanlisCevap4());

        buttonSoruGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soruUpd= soru.getText().toString().trim();
                String dogruCevapUpd=dogruCevap.getText().toString().trim();
                String yanlisCevap1Upd=yanlisCevap1.getText().toString().trim();
                String yanlisCevap2Upd=yanlisCevap2.getText().toString().trim();
                String yanlisCevap3Upd=yanlisCevap3.getText().toString().trim();
                String yanlisCevap4Upd=yanlisCevap4.getText().toString().trim();


                Soru so = new Soru(id,soruUpd,dogruCevapUpd,yanlisCevap1Upd,yanlisCevap2Upd,yanlisCevap3Upd,yanlisCevap4Upd,kullaniciId);

                int result= vk.soruGuncelle(so);

                if(result>0){
                    startActivity(new Intent(SoruGuncellemeActivity.this, SoruListelemeActivity.class));
                    finish();
                    Toast.makeText(getApplicationContext(), "Soru Güncellendi :)", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Soru Güncellenirken hata oluştu :)", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}