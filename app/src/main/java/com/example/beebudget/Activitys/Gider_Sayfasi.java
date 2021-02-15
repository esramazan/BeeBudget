package com.example.beebudget.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beebudget.Database.Database_Yeni;
import com.example.beebudget.Database.islemler;
import com.example.beebudget.R;

import java.util.ArrayList;

public class Gider_Sayfasi extends AppCompatActivity {
    Database_Yeni DB;

    TextView ad,gidertoplami;
    ListView giderler_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gider_sayfasi);

        giderler_list=findViewById(R.id.gider_list);

        ad=findViewById(R.id.giderlist_kullanici_ad);
        gidertoplami=findViewById(R.id.giderlist_bakiye_tl);

        Intent intent=getIntent();
        String kullad=intent.getStringExtra("kull");
        ad.setText(kullad);

        DB = new Database_Yeni(this);

        Cursor res = DB.getdata_gider(kullad);
        if(res.getCount()==0){

            int toplam=0;
            gidertoplami.setText(String.valueOf(toplam));
            Toast.makeText(Gider_Sayfasi.this, "Gider kaydınız yoktur!", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> arl = new ArrayList<String>();
        while(res.moveToNext()){
            StringBuffer buffer = new StringBuffer();
            buffer.append(res.getString(0)+".   ");
            String adi=res.getString(1);
            ad.setText(res.getString(1));
            buffer.append(res.getString(6)); //+"\n"
            buffer.append("   "+res.getString(5)+"   ");
            buffer.append(res.getInt(4));
            arl.add(buffer.toString());

            int toplam = DB.Gider_Toplam(adi);
            gidertoplami.setText(String.valueOf(toplam));
        }

        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, arl);

        giderler_list.setAdapter(veriAdaptoru);
    }
}

