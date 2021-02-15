package com.example.beebudget.Activitys;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beebudget.Database.Database;
import com.example.beebudget.Database.Database_Yeni;
import com.example.beebudget.Database.islemler;
import com.example.beebudget.R;

import java.util.ArrayList;
import java.util.List;


public class Gelir_Sayfasi extends AppCompatActivity {

    Database_Yeni DB;

    TextView ad,gelirtoplami;
    ListView gelirler_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelir_sayfasi);

        gelirler_list=findViewById(R.id.liste_gelir);
        ad=findViewById(R.id.gelirlistkullad);
        gelirtoplami=findViewById(R.id.gelirlistbakiye);

        DB = new Database_Yeni(this);

        Intent intent=getIntent();
        String kullad=intent.getStringExtra("kull");
        ad.setText(kullad);

        Cursor res = DB.getdata_gelir(kullad);
        if(res.getCount()==0){

            int toplam=0;
            gelirtoplami.setText(String.valueOf(toplam));
            Toast.makeText(Gelir_Sayfasi.this, "Gelir kaydınız yoktur!", Toast.LENGTH_SHORT).show();
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

            int toplam = DB.Gelir_Toplam(adi);
            gelirtoplami.setText(String.valueOf(toplam));
        }

        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, arl);

        gelirler_list.setAdapter(veriAdaptoru);



    }

    }



/*


   String[] gelirler = {"Maas 5000TL 01.12.2020","Tahsilat 2300TL 12.12.2020"};

        ad=findViewById(R.id.gelirlistkullad);

        Intent intentgelir=getIntent();
        String kullad=intentgelir.getStringExtra("kull");
        if(kullad==null)
        {
            ad.setText("Esra");
        }
        else
        {
            ad.setText(kullad);

        }

        listItem=new ArrayList<>();

        //  viewData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, gelirler);
        gelirler_list.setAdapter(adapter);

--------------------------------





islem=new islemler(this);
        islem.ac();
*//*

        listItem=new ArrayList<>();

      //  viewData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, gelirler);
        gelirler_list.setAdapter(adapter);


    }
}
*/

 /*   public void viewData()
    {


        Cursor cursor=islem.viewData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(Gelir_Sayfasi.this,"Tablonuzda kayıt yoktur.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                listItem.add(cursor.getString(1));
            }
            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            gelirler_list.setAdapter(adapter);

        }

    }
*/
/*

    protected void onResume() {
        islem.ac();
        super.onResume();
    }
    protected void onPause() {
        islem.kapat();
        super.onPause();
    }
*/




/*


public class Gelir_Sayfasi extends AppCompatActivity {

    View view;
    ListView liste1;
    List sonuc;
    Database veritabani = null;
    List<String> vVeriler = null;

    protected View onCreate(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_gelir_sayfasi, container, false);
        liste1 = (ListView) view.findViewById(R.id.liste_listview);
//        final EditText arama_text = (EditText) view.findViewById(R.id.arama_edittext);
//        Button arama_button = (Button) view.findViewById(R.id.arama_button);

        Database veritabani = new Database(this);
        //sayfa açılınca listviewde veri görüntüleme
        if (Bee.oto) {
            vVeriler = veritabani.gelirleri_listele_dt("1");
        } else {
            vVeriler = veritabani.gelirleri_listele_dt("0");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
        liste1.setAdapter(adapter);


        return view;

    }


    public static class Bee {
        public static  boolean oto=false;
    }


}


*/





/*

public class Gelir_Sayfasi extends AppCompatActivity {
   islemler islem;
    TextView ad;
ListView gelirler_list;
ArrayList listItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelir_sayfasi);

        gelirler_list=findViewById(R.id.listgelir);
        String[] gelirler = {"Maas 5000TL 01.12.2020","Tahsilat 2300TL 12.12.2020"};

        ad=findViewById(R.id.gelirlistkullad);

        Intent intentgelir=getIntent();
        String kullad=intentgelir.getStringExtra("kull");
        ad.setText(kullad);
     */
/* islem=new islemler(this);
        islem.ac();
*//*

        listItem=new ArrayList<>();

      //  viewData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, gelirler);
        gelirler_list.setAdapter(adapter);


    }
}
*/

 /*   public void viewData()
    {


        Cursor cursor=islem.viewData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(Gelir_Sayfasi.this,"Tablonuzda kayıt yoktur.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                listItem.add(cursor.getString(1));
            }
            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            gelirler_list.setAdapter(adapter);

        }

    }
*/
/*

    protected void onResume() {
        islem.ac();
        super.onResume();
    }
    protected void onPause() {
        islem.kapat();
        super.onPause();
    }
*/

