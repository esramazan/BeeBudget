package com.example.beebudget.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.beebudget.Database.Database_;
import com.example.beebudget.Database.Database_Yeni;
import com.example.beebudget.Database.islemler;
import com.example.beebudget.Model.Gider_Tablosu;
import com.example.beebudget.R;

import java.util.ArrayList;

public class MainActivity<ad> extends AppCompatActivity {


    Button btn_gelir,btn_gider,btn_gelir_es,btn_gider_es;
    ArrayList<Gelir_Sayfasi> gelirler;
    ListView gelirler_listesi;
    TextView ad,gelirtoplami,gidertoplami,bakiye;
    Database_Yeni DB;
    int toplamgelir,toplamgider;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gelirler_listesi=findViewById(R.id.gider_list);
        DB = new Database_Yeni(this);

        btn_gelir=findViewById(R.id.btn_gelir_list);
        btn_gider=findViewById(R.id.btn_gider_list);
        btn_gelir_es=findViewById(R.id.btn_gelir_eklesil);
        btn_gider_es=findViewById(R.id.btn_gider_eklesil);
        gelirtoplami=findViewById(R.id.txtgelir_toplamlari);
        gidertoplami=findViewById(R.id.txtgider_toplamlari);

        bakiye=findViewById(R.id.txtbakiye);



        ad=(TextView) findViewById(R.id.txtkulladi);

        Intent intent=getIntent();
        String kullad=intent.getStringExtra("isim");
        ad.setText(kullad);
        if(kullad==null)
        {
            Intent intent2=getIntent();
            String kullad2=intent2.getStringExtra("isim2");
            ad.setText(kullad2);
            if(kullad2==null)
            {
                Intent intent4=getIntent();
                String kullad4=intent4.getStringExtra("isim4");
                ad.setText(kullad4);
                int toplam4 = DB.Gelir_Toplam(kullad4);
                gelirtoplami.setText(String.valueOf(toplam4)+" TL");
                int gidertoplam4 = DB.Gider_Toplam(kullad4);
                gidertoplami.setText(String.valueOf(gidertoplam4) +" TL");
                int bakiye_tl=toplam4-gidertoplam4;
                bakiye.setText(String.valueOf(bakiye_tl)+" ₺");

            }
            else
            {
                ad.setText(kullad2);
                int toplam2 = DB.Gelir_Toplam(kullad2);
                gelirtoplami.setText(String.valueOf(toplam2)+" TL");
                int gidertoplam2 = DB.Gider_Toplam(kullad2);
                gidertoplami.setText(String.valueOf(gidertoplam2) +" TL");
                int bakiye_tl=toplam2-gidertoplam2;
                bakiye.setText(String.valueOf(bakiye_tl)+" ₺");
            }
        }
        else
        {
            ad.setText(kullad);
            int toplam = DB.Gelir_Toplam(kullad);
            gelirtoplami.setText(String.valueOf(toplam)+" TL");
            int gidertoplam = DB.Gider_Toplam(kullad);
            gidertoplami.setText(String.valueOf(gidertoplam)+" TL");
            int bakiye_tl=toplam-gidertoplam;
            bakiye.setText(String.valueOf(bakiye_tl)+" ₺");

        }

    }


    public void btn_glr_lst(View v) {

        Intent intent = new Intent(getApplicationContext(), Gelir_Sayfasi.class);
        intent.putExtra("kull",ad.getText().toString());
        startActivity(intent);

    }


    public void btn_gdr_lst(View v) {
        Intent intent = new Intent(getApplicationContext(), Gider_Sayfasi.class);
        intent.putExtra("kull",ad.getText().toString());
        startActivity(intent);

    }

    public void btn_glr_eklesil(View v) {

        Intent intent2 = new Intent(getApplicationContext(), Gelir_Ekle_Sil.class);
        intent2.putExtra("kull",ad.getText().toString());
        startActivity(intent2);

    }

    public void btn_gdr_eklesil(View v) {

        Intent intent3 = new Intent(getApplicationContext(), Gider_Ekle_Sil.class);
        intent3.putExtra("kull3",ad.getText().toString());
        startActivity(intent3);

    }


}






/*
        verikatmani=new islemler( this);
        verikatmani.ac();*/


      /*  toplamgelir=verikatmani.gelir_toplam();
        toplamgider=verikatmani.gider_toplam();
        if(toplamgelir==0)
        {
            gelirtoplami.setText("0");
        }
        else {

            gelirtoplami.setText(String.valueOf(toplamgelir));
        }
        if(toplamgider==0)
        {
            gidertoplami.setText("0");
        }
        else {

            gidertoplami.setText(String.valueOf(toplamgelir));
        }*/



/*
        database_ = new Database_(getApplicationContext());

        gelirler=database_.gelir_lst("myDB");
        adapter =new Gelirler_Adapter(getApplicationContext(),gelirler);
        gelirler_listesi.setAdapter();
 */