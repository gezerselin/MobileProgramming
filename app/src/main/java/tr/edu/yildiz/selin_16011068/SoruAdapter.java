package tr.edu.yildiz.selin_16011068;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SoruAdapter  extends RecyclerView.Adapter<SoruAdapter.SoruVH>{

    ArrayList<Soru> sorular;
    Context context;

    public SoruAdapter(ArrayList<Soru> sorular , Context context){
        this.sorular=sorular;
        this.context=context;

    }

    @NonNull
    @Override
    public SoruVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_soru,parent,false);
        SoruVH svh = new SoruVH(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SoruVH holder, int position) {
        final Soru s = sorular.get(position);
        holder.soru.setText(s.getSoru());
        String[] siklar = new String[5];
        siklar[0]=s.getDogruCevap();
        siklar[1]=s.getYanlisCevap1();
        siklar[2]=s.getYanlisCevap2();
        siklar[3]=s.getYanlisCevap3();
        siklar[4]=s.getYanlisCevap4();

        List<String> stringList= Arrays.asList(siklar);
        Collections.shuffle(stringList);
        stringList.toArray(siklar);

        holder.Asikki.setText(siklar[0]);
        holder.Bsikki.setText(siklar[1]);
        holder.Csikki.setText(siklar[2]);
        holder.Dsikki.setText(siklar[3]);
        holder.Esikki.setText(siklar[4]);

        holder.cardUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(context, SoruGuncellemeActivity.class);
                intent.putExtra("SORU",s);
                context.startActivity(intent);
            }
        });
        holder.cardDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Uyarı!!!");
                builder.setMessage("Soruyu silmek istediğinize emin misiniz?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final veriKaynagi vk = new veriKaynagi(context);
                        vk.open();
                        int result=vk.soruSil(s.getId());
                        if(result>0){
                            Toast.makeText(context, "Soru Silindi ", Toast.LENGTH_SHORT).show();
                            sorular.remove(s);
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Soru silinirken hata oluştu ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hayır",null);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sorular.size();
    }

    class SoruVH extends RecyclerView.ViewHolder {

        TextView soru,Asikki,Bsikki,Csikki,Dsikki,Esikki;
        CardView cardUpdate,cardDelete;

        public SoruVH(@NonNull View v) {
            super(v);

            soru= v.findViewById(R.id.Soru);
            Asikki=v.findViewById(R.id.Asikki);
            Bsikki=v.findViewById(R.id.Bsikki);
            Csikki=v.findViewById(R.id.Csikki);
            Dsikki=v.findViewById(R.id.Dsikki);
            Esikki=v.findViewById(R.id.Esikki);

            cardDelete=v.findViewById(R.id.cardDelete);
            cardUpdate=v.findViewById(R.id.cardUpdate);
        }
    }

}

