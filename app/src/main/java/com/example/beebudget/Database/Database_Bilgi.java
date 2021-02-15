package com.example.beebudget.Database;

public class Database_Bilgi {

    public static final String DB_NAME = "Budget.db";
    public static final int DB_VERSION = 1;

    public static final String KULLANICILAR = "Kullanicilar";

    public static final String GELIRLER = "Gelirler";

    public static final String GIDERLER = "Giderler";


    public static final String KULLANICI_TABLOSU_OLUSTUR  =
            "CREATE TABLE Kullanicilar (Kullanici_adi TEXT PRIMARY KEY NOT NULL UNIQUE, sifre TEXT);";


    public static final String GELIRLER_TABLOSU_OLUSTUR  =
          " CREATE TABLE Gelir (gelir_id INTEGER NOT NULL UNIQUE,kull_adİ TEXT,tutar INT,”gelir_turu TEXT,tarih TEXT,FOREIGN KEY(kull_adi)REFERENCES Kullanicilar(Kullanici_adi),PRIMARY KEY(gelir_id));";


    public static final String GIDERLER_TABLOSU_OLUSTUR  =
            "CREATE TABLE Giderler (gider_id INTEGER NOT NULL UNIQUE,Kull_adi TEXT,tutar INT,gider_turu TEXT, tarih TEXT, PRIMARY KEY(gider_id),FOREIGN KEY(kull_adi) REFERENCES Kullanicilar(Kullanici_adi));";
}