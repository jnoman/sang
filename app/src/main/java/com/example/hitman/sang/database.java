package com.example.hitman.sang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by hitman on 22/06/2019.
 */

public class database extends SQLiteOpenHelper{
    public database(Context context) {
        super(context, "data111.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table admin(id INTEGER PRIMARY KEY AUTOINCREMENT,nom TEXT,prenom TEXT,username TEXT,numbre_message INTEGER ,mdps TEXT)");
        db.execSQL("create table donneur(cin TEXT PRIMARY KEY,nom TEXT,prenom TEXT,sexe TEXT,age INTEGER,poids INTEGER,type_s TEXT,ville TEXT,telephone TEXT,email TEXT,mdps TEXT,numbre_message INTEGER )");
        db.execSQL("create table rendez_vous (id_rendez INTEGER PRIMARY KEY AUTOINCREMENT,date_rendez_vous DATE,cin TEXT references donneur)");
        db.execSQL("create table donnation(id_donnation INTEGER PRIMARY KEY AUTOINCREMENT,cin TEXT REFERENCES donneur,date_donnation DATE)");
        db.execSQL("create table message(id INTEGER PRIMARY KEY AUTOINCREMENT,cin TEXT REFERENCES donneur,text_message TEXT,date_message DATE)");
        db.execSQL("insert into admin(nom,prenom,username,numbre_message,mdps) values('noman','jamal eddine','admin',0,'111')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insert(String cin,String nom,String prenom,String sexe,String age,String poids,String type,String ville,String tele,String email,String mdps)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cin",cin);
        contentValues.put("nom",nom);
        contentValues.put("prenom",prenom);
        contentValues.put("sexe",sexe);
        contentValues.put("age",age);
        contentValues.put("poids",poids);
        contentValues.put("type_s",type);
        contentValues.put("ville",ville);
        contentValues.put("telephone",tele);
        contentValues.put("email",email);
        contentValues.put("mdps",mdps);
        contentValues.put("numbre_message",1);
        long retour = db.insert("donneur",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("cin",cin);
        contentValues.put("text_message","merci de votre inscrit");
        Date date=new Date();
        contentValues.put("date_message",date.toString());
        db.insert("donneur",null,contentValues);
        if (retour==-1) return false;
        else return true;
    }

    public ArrayList<String> getListVille(){
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("Safi");
        liste.add("Casablanca");
        liste.add("Rabat");
        liste.add("Fes");
        liste.add("Marrackech");
        liste.add("Agadir");
        liste.add("Tanger");
        return liste;
    }
    public ArrayList<String> getListtype(){
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("AB+");
        liste.add("AB-");
        liste.add("A+");
        liste.add("A-");
        liste.add("B+");
        liste.add("B-");
        liste.add("O+");
        liste.add("O-");
        return liste;
    }
    public ArrayList<String> getListsexe(){
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("Homme");
        liste.add("Femme");
        return liste;
    }
    public ArrayList<Hashtable<String,String>> getListdonneur(String ville,String type){
        ArrayList<Hashtable<String,String>> arrayList=new ArrayList<Hashtable<String,String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from donneur where ville='"+ville+"' and type_s='"+type+"'",null);
        Hashtable<String,String> hashtable;
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            hashtable=new Hashtable<String,String>();
            hashtable.put("nom",cursor.getString(1)+" "+cursor.getString(2));
            hashtable.put("cin",cursor.getString(0));
            arrayList.add(hashtable);
            cursor.moveToNext();
        }
        return arrayList;
    }
    public Hashtable<String,String> getdonneur(String cin) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select nom,prenom,sexe,age,telephone,email from donneur where cin='"+cin+"'", null);
        Hashtable<String, String> hashtable;
        cursor.moveToFirst();
        hashtable = new Hashtable<String, String>();
        hashtable.put("nom", cursor.getString(0) + " " + cursor.getString(1));
        hashtable.put("sexe", cursor.getString(2));
        hashtable.put("age", cursor.getString(3));
        hashtable.put("telephone", cursor.getString(4));
        hashtable.put("email", cursor.getString(5));
        return hashtable;
    }

    public Hashtable<String,String> connection(String log,String pass){
        Hashtable<String,String> ht;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from donneur where cin='"+log+"' and mdps='"+pass+"'",null);
        cs.moveToFirst();
        if (cs.isAfterLast()==false) {
            ht = new Hashtable<String,String>();
            ht.put("cin", log);
            ht.put("type", "u");
            return ht;
        }
        else {
            Cursor cs1 = db.rawQuery("select id from admin where username='"+log+"' and mdps='"+pass+"'",null);
            cs1.moveToFirst();
            if (cs1.isAfterLast()==false) {
                ht = new Hashtable<String,String>();
                ht.put("cin", cs1.getString(0));
                ht.put("type", "a");
                return ht;
            }
            else return null;
        }
    }

    public ArrayList<String> getListdonnation(String cin){
        ArrayList<String> arrayList=new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select date_donnation from donnation where cin='"+cin+"'",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            arrayList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public Boolean ajouter_rendez_vous(String c, String d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cin",c);
        contentValues.put("date_rendez_vous",d);
        long retour = db.insert("rendez_vous",null,contentValues);
        contentValues=new ContentValues();
        contentValues.put("cin",c);
        Date date=new Date();
        contentValues.put("date_message",date.toString());
        contentValues.put("text_message","votre rendez_vous est ajouter avex succes, la date de votre rendez vous est "+d);
        db.insert("message",null,contentValues);
        db.execSQL("update admin set numbre_message=numbre_message+1");
        if (retour==-1) return false;
        else return true;
    }

    public Boolean envoyer_message(String c, String t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Date date=new Date();
        contentValues.put("cin",c);
        contentValues.put("date_message",date.toString());
        contentValues.put("text_message",t);
        long retour =db.insert("message",null,contentValues);
        if (retour==-1) return false;
        else return true;
    }
    public ArrayList<String> getListmessage(String cin){
        ArrayList<String> arrayList=new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select text_message from message where cin='"+cin+"'",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            arrayList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<Hashtable<String,String>> getListdonneurs(){
        ArrayList<Hashtable<String,String>> arrayList=new ArrayList<Hashtable<String,String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from donneur",null);
        Hashtable<String,String> hashtable;
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            hashtable=new Hashtable<String,String>();
            hashtable.put("nom",cursor.getString(1)+" "+cursor.getString(2));
            hashtable.put("cin",cursor.getString(0));
            arrayList.add(hashtable);
            cursor.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Hashtable<String,String>> getListdonnation(){
        ArrayList<Hashtable<String,String>> arrayList=new ArrayList<Hashtable<String,String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from rendez_vous",null);
        Hashtable<String,String> hashtable;
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            hashtable=new Hashtable<String,String>();
            hashtable.put("date",cursor.getString(1));
            hashtable.put("cin",cursor.getString(2));
            arrayList.add(hashtable);
            cursor.moveToNext();
        }
        return arrayList;
    }
    public Boolean Update_admin(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("numbre_message",0);
        db.update("admin",contentValues,null,null);
        return true;
    }
    public Boolean Delete_donneur(String cin){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("message", "cin=?",new String[]{cin});
        db.delete("donnation", "cin=?",new String[]{cin});
        db.delete("rendez_vous", "cin=?",new String[]{cin});
        db.delete("donneur", "cin=?",new String[]{cin});
        return true;
    }


    public Boolean Delete_rendez_vous(String cin){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("rendez_vous", "cin=?",new String[]{cin});
        return true;
    }

    public Boolean valide_rendez_vous(String cin,String d){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("rendez_vous", "cin=?",new String[]{cin});
        ContentValues contentValues = new ContentValues();
        contentValues.put("cin",cin);
        contentValues.put("date_donnation",d);
        db.insert("donneur",null,contentValues);
        return true;
    }
}
