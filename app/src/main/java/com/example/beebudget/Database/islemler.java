package com.example.beebudget.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.TextView;

import com.example.beebudget.Model.Gelir_Tablosu;
import com.example.beebudget.Model.Gider_Tablosu;
import com.example.beebudget.Model.kullanicilar_tablosu;
import com.example.beebudget.R;

import java.util.ArrayList;
import java.util.List;

public class islemler {
    SQLiteDatabase db;
    Database_ budgetdb;


    public islemler (Context c) { budgetdb= new Database_(c);
    }
    public void ac()  { db=budgetdb.getWritableDatabase(); }
    public void kapat()  { budgetdb.close(); }


    public void kullanici_ekle (kullanicilar_tablosu k){
        ContentValues val=new ContentValues();
        val.put("Kullanici_adi",k.getKullanici_adi());
        val.put("sifre",k.getSifre());
        db.insert("Kullanicilar",null,val);
    }

    public void kullanici_sil (kullanicilar_tablosu k){
//        String ad=k.getKullanici_adi();
//        db.delete("Kullanicilar","Kullanici_adi="+ad,null);
    }

    public void gelir_ekle (Gelir_Tablosu g){
        ContentValues val=new ContentValues();
        val.put("kull_adi",g.getKull_adi());
        val.put("tutar",g.getTutar());
        val.put("gelir_turu",g.getGelir_turu());
        val.put("tarih",g.getTarih());
        db.insert("Gelirler",null,val);
    }

    public void gelir_sil (Gelir_Tablosu g){
        int id=g.getGelir_id();
        db.delete("Gelirler","gelir_id="+id,null);
    }


    public void gider_ekle (Gider_Tablosu g){
        ContentValues val=new ContentValues();
        val.put("kull_adi",g.getKull_adi());
        val.put("tutar",g.getTutar());
        val.put("gider_turu",g.getGider_turu());
        val.put("tarih",g.getTarih());
        db.insert("Giderler",null,val);
    }

    public void gider_sil (Gider_Tablosu g){
        int id=g.getGider_id();
        db.delete("Giderler","gider_id="+id,null);
    }

    public Cursor viewData()
    {
        SQLiteDatabase db=budgetdb.getReadableDatabase();
        String query=("Select gelir_turu,tutar,tarih from Gelirler");
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }


    public List<Gelir_Tablosu> gelirleri_listele()
    {
        String kolonlar[]= {"gelir_id","gelir_turu","tutar","tarih"};
        List<Gelir_Tablosu> liste= new ArrayList<>();
        //db=budgetdb.getReadableDatabase();
        Cursor c=db.query("Gelirler",kolonlar,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            int id=c.getInt(0);
            String gelir_turu=c.getString(1);
            int tutar=c.getInt(2);
            String tarih=c.getString(3);

            String gelirsira=tarih+" "+gelir_turu+" "+tutar+" TL";

            Gelir_Tablosu g=new Gelir_Tablosu(id,gelir_turu,tutar,tarih);
            liste.add(g);
            c.moveToNext();
        }
        c.close();
        return liste;
    }

    public int gelir_toplam(){
        String kolonlar []= {"gelir_id","gelir_turu","tutar","tarih"};
        int toplam=0;
        db =budgetdb.getReadableDatabase();
        Cursor c  =db.query("Gelirler",kolonlar,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            int tutar =c.getInt(2);
            toplam+=tutar;
            c.moveToNext();
        }

        c.close();

        return toplam;
    }

    public List<Gider_Tablosu> giderleri_listele()
    {
        String kolonlar[]= {"gider_id","gider_turu","tutar","tarih"};
        List<Gider_Tablosu> liste= new ArrayList<>();
        db=budgetdb.getReadableDatabase();
        Cursor c=db.query("Giderler",kolonlar,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            int id=c.getInt(0);
            String gider_turu=c.getString(3);
            int tutar=c.getInt(2);
            String tarih=c.getString(4);

            String gidersira=tarih+" "+gider_turu+" "+tutar+" TL";

            Gider_Tablosu g=new Gider_Tablosu(id,gider_turu,tutar,tarih);
            liste.add(g);
            c.moveToNext();
        }
        c.close();
        return liste;
    }

    public int gider_toplam(){
        String kolonlar []= {"gider_id","gider_turu","tutar","tarih"};
        int toplam=0;
        db =budgetdb.getReadableDatabase();
        Cursor c =db.query("Giderler",kolonlar,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            int tutar =c.getInt(2);
            toplam+=tutar;
            c.moveToNext();
        }

        c.close();

        return toplam;
    }

}


