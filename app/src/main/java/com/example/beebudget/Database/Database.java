package com.example.beebudget.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.beebudget.Model.kullanicilar_tablosu;

import java.util.ArrayList;
import java.util.List;

public class Database  extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "Budgetdb";
    public static final int DATABASE_VERSION = 1;


    public static final String KULLANICI_TABLE = "Kullanicilar";          //Kullanici tablosu satırları
   // public static final String ROW_ID = "id";
    public static final String KULLANICI_ADI = "Kullanici_adi";
    public static final String SIFRE = "sifre";


    public static final String GELIR_TABLE = "Gelir";               //gelir tablosu satırları
    public static final String ROW_ID_GELIR = "gelir_id";
    public static final String KULL_ADI_GELIR = "kull_adi_gelir";
    public static final String GELIR_TUTAR = "gelir_tutar";
    public static final String GELIR_TURU = "gelir_turu";
    public static final String GELIR_TARIH = "gelir_tarih";


    public static final String GIDER_TABLE = "Gider";               //gider tablosu satırları
    public static final String ROW_ID_GIDER = "gider_id";
    public static final String KULL_ADI_GIDER = "kull_adi_gider";
    public static final String GIDER_TUTAR = "gider_tutar";
    public static final String GIDER_TURU = "gider_turu";
    public static final String GIDER_TARIH = "gider_tarih";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {                                 // tablolari oluşturma

        db.execSQL("CREATE TABLE " + KULLANICI_TABLE + "(" + KULLANICI_ADI + " TEXT NOT NULL UNIQUE, " + SIFRE + " TEXT NOT NULL )");
        db.execSQL("CREATE TABLE " + GELIR_TABLE + "(" + ROW_ID_GELIR + " TEXT PRIMARY KEY UNIQUE, " + KULL_ADI_GELIR + " TEXT NOT NULL, " + GELIR_TUTAR + " TEXT NOT NULL, " + GELIR_TURU + " TEXT NOT NULL, " + GELIR_TARIH + " TEXT NOT NULL )");
        db.execSQL("CREATE TABLE " + GIDER_TABLE + "(" + ROW_ID_GIDER + " TEXT PRIMARY KEY UNIQUE, " + KULL_ADI_GIDER + " TEXT NOT NULL, " + GIDER_TUTAR + " TEXT NOT NULL, " + GIDER_TURU + " TEXT NOT NULL, "+ GELIR_TARIH + " TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {                   //güncelleme
        db.execSQL("DROP TABLE IF EXISTS " + KULLANICI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GELIR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GIDER_TABLE);
        onCreate(db);
    }



    public void kullaniciEkle(String kullanici, String sifre) {          //kullanici ekle
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KULLANICI_ADI, kullanici.trim());
        cv.put(SIFRE, sifre.trim());

        db.insert(KULLANICI_TABLE, null, cv);
        db.close();
    }

    public void gelirEkle(String gelirid, String kullad, String tutar, String gelirturu, String tarih) {     //GELIR TABLOSUNE VERI EKLEME

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_ID_GELIR, gelirid.trim());
        cv.put(KULL_ADI_GELIR, kullad.trim());
        cv.put(GELIR_TUTAR, tutar.trim());
        cv.put(GELIR_TURU, gelirturu.trim());
        cv.put(GELIR_TARIH, tarih.trim());

        db.insert(GELIR_TABLE, null, cv);
        db.close();
    }

    public void gelirSil(String gelirid) {     //GELIR TABLOSUNdan VERI silme

        SQLiteDatabase db = this.getWritableDatabase();
        String id=gelirid.trim();

        db.delete(GELIR_TABLE,ROW_ID_GELIR+"="+id,null);
        db.close();
    }


    public void  giderEkle(String giderid, String kullad, String tutar, String giderturu, String tarih) {     //GIDER TABLOSUNA VERİ EKLEME
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_ID_GIDER, giderid.trim());
        cv.put(KULL_ADI_GIDER, kullad.trim());
        cv.put(GIDER_TUTAR, tutar.trim());
        cv.put(GIDER_TURU, giderturu.trim());
        cv.put(GIDER_TARIH, tarih.trim());

        db.insert(GIDER_TABLE, null, cv);
        db.close();
    }

    public void giderSil(String giderid) {     //GIDER TABLOSUNdan VERI silme

        SQLiteDatabase db = this.getWritableDatabase();
        String id=giderid.trim();

        db.delete(GIDER_TABLE,ROW_ID_GIDER+"="+id,null);
        db.close();
    }






    public List<String> gelirleri_listele_dt(String sql) {                                      //giris cıkışları listele

        SQLiteDatabase db = this.getWritableDatabase();
        List<String> veriler_gelirler = new ArrayList<String>();
        String[] sutunlar_gelir = {ROW_ID_GELIR, KULL_ADI_GELIR, GELIR_TUTAR, GELIR_TURU, GELIR_TARIH};
        String gelirler_select = "select * from Gelir where kull_adi_gelir=" + sql;
        Cursor cursor = db.rawQuery(gelirler_select, null);
        while (cursor.moveToNext()) {
            veriler_gelirler.add(cursor.getInt(0) + "  -  " + cursor.getString(1) + "  -  " + cursor.getString(2) + "  -  " + cursor.getString(3) + "  -  " + cursor.getString(4));
        }
        return veriler_gelirler;

    }


}
