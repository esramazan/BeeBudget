package com.example.beebudget.Activitys;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beebudget.Database.Database;
import com.example.beebudget.Database.Database_;
import com.example.beebudget.Database.Database_Yeni;
import com.example.beebudget.Database.islemler;
import com.example.beebudget.Model.Gelir_Tablosu;
import com.example.beebudget.Model.kullanicilar_tablosu;
import com.example.beebudget.R;



public class Giris_Activity extends AppCompatActivity {

    Button giris;
    Button kayit;

    EditText  etUser;
    EditText etPass;
  //  Database verikatmani;

Database_Yeni DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        DB = new Database_Yeni(this);

        giris=findViewById(R.id.girisyap);
        kayit=findViewById(R.id.kaydol);

        etUser=findViewById(R.id.etUser);
        etPass =findViewById(R.id.etPass);

        Intent intent=getIntent();
        String kullad=intent.getStringExtra("isim");



        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kullinput = etUser.getText().toString();
                String sifre = etPass.getText().toString();

                Boolean checkdata = DB.Giris_Kontrol(kullinput, sifre);
                if(checkdata==true)
                {
                Toast.makeText(Giris_Activity.this, "Hoşgeldin "+etUser.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("isim",etUser.getText().toString());
                startActivity(intent);
                }
                else
                    Toast.makeText(Giris_Activity.this, "Bilgilerinizi kontrol ediniz.", Toast.LENGTH_SHORT).show();

            }
        });


        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sifre=etPass.getText().toString();
                String kullaniciad=etUser.getText().toString();
                String islem_turu="0";
                String aciklama="0";
                String gelirtarih="0";
              //  String gelirid="0";
                int gelir_tutar_int=0;

                String deneme = DB.son_id();
                int idd=(Integer.parseInt(deneme))+1;
                String son_id_str=String.valueOf(idd);


                Boolean checkdata = DB.insertdata(son_id_str,kullaniciad, sifre,islem_turu,gelir_tutar_int,aciklama, gelirtarih);
                if(checkdata==true)
                    Toast.makeText(Giris_Activity.this, "Kullanıcı kaydınız oluşturuldu.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Giris_Activity.this, "Kaydınız oluşturulamadı. Tekrar deneyiniz.", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public boolean emptyValidation() {
        if (TextUtils.isEmpty(etUser.getText().toString()) || TextUtils.isEmpty(etPass.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}
/*

public class Giris_Activity extends AppCompatActivity {

    Button giris;
    Button kayit;

    EditText  etUser;
    EditText etPass;
    islemler verikatmani;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        giris=findViewById(R.id.girisyap);
        kayit=findViewById(R.id.kaydol);

        etUser=findViewById(R.id.etUser);
        etPass =findViewById(R.id.etPass);


        verikatmani= new islemler( this);
        verikatmani.ac();



        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Giris_Activity.this, "Hoşgeldin "+etUser.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("isim",etUser.getText().toString());
                startActivity(intent);

            }
        });


        kayit.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kulad=etUser.getText().toString();
                String sifre=etPass.getText().toString();

                if (!emptyValidation()) {
                    kullanicilar_tablosu kullanici =new kullanicilar_tablosu(kulad,sifre);
                    verikatmani.kullanici_ekle(kullanici);
                    Toast.makeText(Giris_Activity.this, "Kullanıcı kaydınız oluşturuldu giriş yapabilirsiniz.", Toast.LENGTH_SHORT).show();

                    etPass.setText("");
                }else{
                    Toast.makeText(Giris_Activity.this, "Kayıt olmak için alanları doldurduktan sonra butona basınız.", Toast.LENGTH_SHORT).show();
                }
            }
        }));


    }

    public boolean emptyValidation() {
        if (TextUtils.isEmpty(etUser.getText().toString()) || TextUtils.isEmpty(etPass.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}


*/



/*
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    dbHelper.addUser(new Kullanici(etUser.getText().toString(), etPass.getText().toString()));
                    Toast.makeText(Giris_Activity.this, "Kullanıcı kaydınız oluşturuldu. Giriş yapabilirsiniz.", Toast.LENGTH_SHORT).show();
                    etUser.setText("");
                    etPass.setText("");
                }else{
                    Toast.makeText(Giris_Activity.this, "Kayıt olmak için alanları doldurduktan sonra butona basınız.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    Kullanici user = dbHelper.queryUser(etUser.getText().toString(), etPass.getText().toString());
                    if (user != null) {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("Kullanici_adi", user.getKull_adi());
                        Intent intent = new Intent(Giris_Activity.this, MainActivity.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        Toast.makeText(Giris_Activity.this, "Hoşgeldiniz " + user.getKull_adi(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Giris_Activity.this, "Hatalı Giriş", Toast.LENGTH_SHORT).show();
                        etPass.setText("");
                    }
                }else{
                    Toast.makeText(Giris_Activity.this, "Boş geçilemez", Toast.LENGTH_SHORT).show();
                }
            }
        });
}

*/



    /*
    public void kaydol_oncl (View v)
    {

        final Database_ dbHelper = new Database_(this);

        if (!emptyValidation()) {
            dbHelper.addUser(new Kullanici(etUser.getText().toString(), etPass.getText().toString()));
            Toast.makeText(Giris_Activity.this, "Kullanıcı kaydınız oluşturuldu. Giriş yapabilirsiniz. "+etUser.getText().toString(), Toast.LENGTH_SHORT).show();
            etUser.setText("");
            etPass.setText("");
        }else{
            Toast.makeText(Giris_Activity.this, "Kayıt olmak için alanları doldurduktan sonra butona basınız.", Toast.LENGTH_SHORT).show();
        }
        }
     */




/*
    public void girisyap_oncl(View v) {
        Thread ana_sayfa_ac = new Thread()
        { @Override
        public void run()
        {
            try
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                super.run();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        };
        ana_sayfa_ac.start();


 */



       /*
        final Database_ dbHelper = new Database_(this);
        if (!emptyValidation()) {
            Kullanici user = dbHelper.queryUser(etUser.getText().toString(), etPass.getText().toString());
            if (user != null) {
                Bundle mBundle = new Bundle();
                mBundle.putString("Kullanici_adi", user.getKull_adi());
                mBundle.putString("sifre", user.getSifre());


                Intent intent = new Intent(Giris_Activity.this, MainActivity.class);
                intent.putExtras(mBundle);


            } else {
                Toast.makeText(Giris_Activity.this, "Hatalı Giriş", Toast.LENGTH_SHORT).show();
                etPass.setText("");
            }
        }else{
            Toast.makeText(Giris_Activity.this, "Boş geçilemez", Toast.LENGTH_SHORT).show();
        }



        */

/*
        public void kaydol_oncl (View v)
    {


    Thread ana_sayfa_ac = new Thread()
    { @Override
    public void run()
    {
        try
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            super.run();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    };
    ana_sayfa_ac.start();




        Thread kayit_sayfa_ac = new Thread()
        { @Override
        public void run()
        {
            try
            {
                Intent intent = new Intent(getApplicationContext(), Kayit_Ol.class);
                startActivity(intent);
                super.run();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        };
        kayit_sayfa_ac.start(); */




