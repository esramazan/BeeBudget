package com.example.beebudget.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beebudget.Database.Database;
import com.example.beebudget.Database.Database_Yeni;
import com.example.beebudget.Database.islemler;
import com.example.beebudget.Model.Gelir_Tablosu;
import com.example.beebudget.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Gelir_Ekle_Sil extends AppCompatActivity  {

    EditText tur,tarih,tutar,id,kullanici_adi;
    Button ekle,sil,anasayfa;
    TextView ad;
    Database_Yeni DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelir_ekle_sil);

        DB = new Database_Yeni(this);

        ekle=findViewById(R.id.gelir_ekle);
        sil=findViewById(R.id.gelir_sil);
        anasayfa=findViewById(R.id.anasayfaya_don);


        id=findViewById(R.id.edtgelirid);
        kullanici_adi=findViewById(R.id.edtgelirkullad);
        tutar=findViewById(R.id.edtgelir_tutar);
        tur=findViewById(R.id.edtgelir_turu);
        tarih=findViewById(R.id.edttarih);


        Intent intent2=getIntent();
        String kullad2=intent2.getStringExtra("kull");
        kullanici_adi.setText(kullad2);




        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                intent2.putExtra("isim2",kullanici_adi.getText().toString());
                startActivity(intent2);
            }
        });

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sifre="0";
                String kullaniciad=kullanici_adi.getText().toString();
                String islem_turu="Gelir";
                String aciklama=tur.getText().toString();
                String gelirtarih=tarih.getText().toString();
               // String gelirid=id.getText().toString();
                int gelir_tutar_int=Integer.parseInt(tutar.getText().toString());

                String deneme = DB.son_id();
                int idd=(Integer.parseInt(deneme))+1;
                String son_id_str=String.valueOf(idd);

                Boolean checkdata = DB.insertdata(son_id_str,kullaniciad, sifre,islem_turu,gelir_tutar_int,aciklama, gelirtarih);
                if(checkdata==true)
                    Toast.makeText(Gelir_Ekle_Sil.this, "Kayıt eklendi.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Gelir_Ekle_Sil.this, "Kaydınız eklenemedi.", Toast.LENGTH_SHORT).show();

            }
        });

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelirid=id.getText().toString();

                Boolean checkdata = DB.deletedata(gelirid);
                if(checkdata==true)
                    Toast.makeText(Gelir_Ekle_Sil.this, "Gelir kaydınız silinmiştir.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Gelir_Ekle_Sil.this, "Kaydınızın numarasını kontrol ediniz!", Toast.LENGTH_SHORT).show();

            }
        });
    }

}


/*


public class Gelir_Ekle_Sil extends AppCompatActivity  {

    EditText tur,tarih,tutar,id,kulad;
    Button ekle,sil,anasayfa;
 //  Database verikatmani;
 private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelir_ekle_sil);

        ekle=findViewById(R.id.gelir_ekle);
        sil=findViewById(R.id.gelir_sil);


        kulad=findViewById(R.id.edtgelirkullad);
        id=findViewById(R.id.edtgiderid);
        tutar=findViewById(R.id.edtgelir_tutar);
        tur=findViewById(R.id.edtgelir_turu);
        tarih=findViewById(R.id.edttarih);

       //verikatmani= new Database( this);

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                 Database VeriTabani = new Database(getActivity());
                    VeriTabani.gelirEkle(id.getText().toString(), kulad.getText().toString(),tutar.getText().toString(),tur.getText().toString(),tarih.getText().toString());

                    Toast.makeText(Gelir_Ekle_Sil.this, ""+tutar+"TL "+tur+" geliriniz eklenmiştir.", Toast.LENGTH_SHORT).show();

                    kulad.setText("");
                    id.setText("");
                    tutar.setText("");
                    tur.setText("");
                    tarih.setText("");

                }else{
                    Toast.makeText(Gelir_Ekle_Sil.this, "Tüm alanları doldurduktan sonra butona basınız.", Toast.LENGTH_SHORT).show();
                }
            }


        });


               if(!TextUtils.isEmpty(gelir_turu) && !TextUtils.isEmpty(gelir_tutar) && !TextUtils.isEmpty(gelirtarih)){
                    Gelir_Tablosu gelir =new Gelir_Tablosu(gelir_turu,gelir_tutar_int,gelirtarih);
                    verikatmani.gelir_ekle(gelir);
                    Toast.makeText(Gelir_Ekle_Sil.this, ""+gelir_tutar+"TL "+gelir_turu+" geliriniz eklenmiştir.", Toast.LENGTH_SHORT).show();

                    tur.setText("");
                    tutar.setText("");
                    tarih.setText("");

                }
                else{
                    Toast.makeText(Gelir_Ekle_Sil.this, "Gelir eklemek için tüm alanları doldurduktan sonra butona basınız.", Toast.LENGTH_SHORT).show();
                }

    public boolean emptyValidation() {
        if (TextUtils.isEmpty(tur.getText().toString()) || TextUtils.isEmpty(tarih.getText().toString())|| TextUtils.isEmpty(tutar.getText().toString())|| TextUtils.isEmpty(kulad.getText().toString()) || TextUtils.isEmpty(id.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }

    private Context getActivity() {
        context = context;
        return context;
    }

}



*/
