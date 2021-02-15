package com.example.beebudget.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.beebudget.Model.Gelir_Tablosu;


public class Database_ extends SQLiteOpenHelper{

    private Context context;

    public Database_(Context context) {
        super(context, Database_Bilgi.DB_NAME, null, Database_Bilgi.DB_VERSION);
       // this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabloyu oluşturuyor
       db.execSQL(Database_Bilgi.KULLANICI_TABLOSU_OLUSTUR);
        db.execSQL(Database_Bilgi.GELIRLER_TABLOSU_OLUSTUR);
        db.execSQL(Database_Bilgi.GIDERLER_TABLOSU_OLUSTUR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Database_Bilgi.KULLANICILAR);
        db.execSQL("DROP TABLE IF EXISTS " + Database_Bilgi.GELIRLER);
        db.execSQL("DROP TABLE IF EXISTS " + Database_Bilgi.GIDERLER);

        onCreate(db);
    }


}
















/*
    public void kullanici_ekle (String dbName,String kullad,String sifre){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("insert into Kullanici (kullanici_adi,sifre) VALUES ('"+kullad+"','"+sifre+"')");
    }

    public void gelir_ekle_sql (String dbName, int gelirid,String kullad, Double gelir_tutari, String turu, String tarihi){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("insert into Gelir (gelir_id,kull_adi,tutar,gelir_turu,tarih) VALUES ("+gelirid+",'"+kullad+"',"+gelir_tutari+",'"+turu+"','"+tarihi+"')");
    }

    public  void gelirSil_sql(String dbName,int gelirid){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("delete from Gelir where id='"+gelirid+"'");
    }

    public void gider_ekle_sql (String dbName, int giderid,String kullad, Double gider_tutari, String turu, String tarihi){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("insert into Gider (gider_id,kull_adi,tutar,gider_turu,tarih) VALUES ("+giderid+",'"+kullad+"',"+gider_tutari+",'"+turu+"','"+tarihi+"')");
    }

    public  void giderSil_sql(String dbName,int giderid){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("delete from Gider where id='"+giderid+"'");
    }

   public ArrayList<Gelir_listesi> gelir_lst(String dbName){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Cursor c = db.rawQuery("Select * from Gelir",null);
        c.moveToFirst();
        ArrayList<Gelir_Tablosu> gelirler = new ArrayList<>();

        while(c.moveToNext())
        {
            gelirler.add(new Gelir_Tablosu(
                    c.getInt(c.getColumnIndex("gelir_id")),
                    c.getString((c.getColumnIndex("kull_adi"))),
                    c.getInt(c.getColumnIndex("tutar")),
                    c.getString(c.getColumnIndex("turu")),
                    c.getString(c.getColumnIndex("tarih"))
                    )
            );

        }

        return gelirler;
    }

    public void birikimGuncelle_sql(String dbName,String kullad, double tutari){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("update Birikim SET tutar='"+tutari+"' where ,kull_adi='"+kullad+"' ");
    }
    public void birikimEkle_sql(String dbName,String kullad, double tutari, String tarihi){
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("insert into Birikim (kull_adi,tutar,tarih) VALUES ('"+kullad+"',"+tutari+",'"+tarihi+"')");
    }
*/



