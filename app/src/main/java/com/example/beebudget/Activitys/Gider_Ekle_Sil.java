package com.example.beebudget.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beebudget.Database.Database_Yeni;
import com.example.beebudget.Database.islemler;
import com.example.beebudget.Model.Gelir_Tablosu;
import com.example.beebudget.Model.Gider_Tablosu;
import com.example.beebudget.R;

public class Gider_Ekle_Sil extends AppCompatActivity {

    EditText tur,tarih,tutar,id,kullanici_adi;
    Button ekle,sil;
    Button anasayfa;
    Database_Yeni DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gider_ekle_sil);
        DB = new Database_Yeni(this);

        anasayfa=findViewById(R.id.gider_anasayfaya_don);
        ekle=findViewById(R.id.gider_ekle);
        sil=findViewById(R.id.gider_sil);

        id=findViewById(R.id.edtgiderid);
        kullanici_adi=findViewById(R.id.edtgiderkullad);
        tur=findViewById(R.id.edtgider_turu);
        tutar=findViewById(R.id.edtgider_tutar);
        tarih=findViewById(R.id.edtgidertarih);

        Intent intent3=getIntent();
        String kullad3=intent3.getStringExtra("kull3");
        kullanici_adi.setText(kullad3);


        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);
                intent4.putExtra("isim4",kullanici_adi.getText().toString());
                startActivity(intent4);
            }
        });

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sifre="0";
                String kullaniciad=kullanici_adi.getText().toString();
                String islem_turu="Gider";
                String aciklama=tur.getText().toString();
                String gidertarih=tarih.getText().toString();
              //  String giderid=id.getText().toString();
                int gider_tutar_int=Integer.parseInt(tutar.getText().toString());

                String deneme = DB.son_id();
                int idd=(Integer.parseInt(deneme))+1;
                String son_id_str=String.valueOf(idd);

                Boolean checkdata = DB.insertdata(son_id_str,kullaniciad, sifre,islem_turu,gider_tutar_int,aciklama, gidertarih);
                if(checkdata==true)
                    Toast.makeText(Gider_Ekle_Sil.this, "Kayıt eklendi", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Gider_Ekle_Sil.this, "Kaydınız eklenemedi!", Toast.LENGTH_SHORT).show();

            }
        });


        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelirid=id.getText().toString();

                Boolean checkdata = DB.deletedata(gelirid);
                if(checkdata==true)
                    Toast.makeText(Gider_Ekle_Sil.this, "Gider kaydınız silinmiştir.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Gider_Ekle_Sil.this, "Kaydınızın numarasını kontrol ediniz!", Toast.LENGTH_SHORT).show();

            }
        } );

    }



}
