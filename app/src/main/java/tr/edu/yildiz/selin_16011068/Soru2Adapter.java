package tr.edu.yildiz.selin_16011068;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Soru2Adapter  extends RecyclerView.Adapter<Soru2Adapter.Soru2VH>{

   private  ArrayList<Soru> sorular;
   private Context context;
   private int zorluk;
   private ArrayList<String> siklar;

    public Soru2Adapter(ArrayList<Soru> sorular , Context context,int zorluk){
        this.sorular=sorular;
        this.context=context;
        this.zorluk=zorluk;
    }

    public void setSorular(ArrayList<Soru> sorular){
        this.sorular=new ArrayList<>();
        this.sorular=sorular;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Soru2VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_soru2,parent,false);
        Soru2VH svh = new Soru2VH(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull Soru2VH holder, int position) {


        final Soru s = sorular.get(position);
        holder.soru.setText(s.getSoru());

        holder.bind(sorular.get(position));

        siklar = new ArrayList<>();
        String[] yanlisSiklar =new String[4];

        siklar.add(s.getDogruCevap());

        yanlisSiklar[0]=s.getYanlisCevap1();
        yanlisSiklar[1]=s.getYanlisCevap2();
        yanlisSiklar[2]=s.getYanlisCevap3();
        yanlisSiklar[3]=s.getYanlisCevap4();

        List<String> stringList= Arrays.asList(yanlisSiklar);
        Collections.shuffle(stringList);
        stringList.toArray(yanlisSiklar);

        for(int i=0;i<zorluk-1;i++){
            siklar.add(yanlisSiklar[i]);
        }

        Collections.shuffle(siklar);



        int size = siklar.size();
        int count = 0;
        if(count <size){
            holder.Asikki.setText(siklar.get(0));
            s.dogruCevap=siklar.get(0);
            count++;
        }
        if(count< size){
            holder.Bsikki.setText(siklar.get(1));
            s.yanlisCevap1=siklar.get(1);
            count++;
        }
        if(count< size){
            holder.Csikki.setText(siklar.get(2));
            s.yanlisCevap2=siklar.get(2);
            count++;
        }

        if(count< size){
            holder.Dsikki.setText(siklar.get(3));
            s.yanlisCevap3=siklar.get(3);
            count++;
        }

        if(count< size){
            holder.Esikki.setText(siklar.get(4));
            s.yanlisCevap4=siklar.get(4);
            count++;
        }

        if(zorluk<5){
            holder.EsikkiLayout.setVisibility(View.GONE);
        }
        if(zorluk<4){
            holder.DsikkiLayout.setVisibility(View.GONE);
        }
        if(zorluk<3){
            holder.CsikkiLayout.setVisibility(View.GONE);
        }



    }


    @Override
    public int getItemCount() {
        return sorular.size();
    }
    public ArrayList<String> getSiklar(){
        return siklar;
    }



    class Soru2VH extends RecyclerView.ViewHolder {

        TextView soru,Asikki,Bsikki,Csikki,Dsikki,Esikki,AsikkiText,BsikkiText,CsikkiText,DsikkiText,EsikkiText;
        LinearLayout AsikkiLayout,BsikkiLayout,CsikkiLayout,DsikkiLayout,EsikkiLayout,layout;
        ImageView selectedImage;

        public Soru2VH(@NonNull View v) {
            super(v);

            soru= v.findViewById(R.id.Soru2);
            Asikki=v.findViewById(R.id.Asikki);
            Bsikki=v.findViewById(R.id.Bsikki);
            Csikki=v.findViewById(R.id.Csikki);
            Dsikki=v.findViewById(R.id.Dsikki);
            Esikki=v.findViewById(R.id.Esikki);
            layout = v.findViewById(R.id.mainlayout);

            AsikkiLayout = v.findViewById(R.id.AsikkiLayout);
            BsikkiLayout = v.findViewById(R.id.BsikkiLayout);
            CsikkiLayout = v.findViewById(R.id.CsikkiLayout);
            DsikkiLayout = v.findViewById(R.id.DsikkiLayout);
            EsikkiLayout = v.findViewById(R.id.EsikkiLayout);

            selectedImage=v.findViewById(R.id.imageSelected);

        }
        void bind(final Soru s){
            selectedImage.setVisibility(s.isChecked()? View.VISIBLE:View.GONE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.setChecked(!s.isChecked());
                    selectedImage.setVisibility(s.isChecked() ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

    public ArrayList<Soru> getAll(){
        return sorular;
    }

    public ArrayList<Soru> getSelected() {
        ArrayList<Soru> selected=new ArrayList<>();
        for(int i=0; i< sorular.size();i++){
            if(sorular.get(i).isChecked()){
                selected.add(sorular.get(i));
            }
        }
        return selected;
    }


}

