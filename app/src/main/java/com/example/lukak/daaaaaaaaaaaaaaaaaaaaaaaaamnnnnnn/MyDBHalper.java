package com.example.lukak.daaaaaaaaaaaaaaaaaaaaaaaaamnnnnnn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lukak on 8/18/2016.
 */
public class MyDBHalper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="aaa1.db";
    //TABELA UREDJAJI
    public static final String TABLE_NAME="kaluTable";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_BROJ="brojUredjaja";
    public static final String COLUMN_TOG="toggle";


    //TABELA VENTILI
    public static final String TABLE_NAME1="ventili";
    public static final String COLUMN_ID1="_id";
    public static final String COLUMN_NAME1="broj";
    public static final String COLUMN_PALJENJE1="paljenje1";
    public static final String COLUMN_TRAJANJE1="trajanje1";
    public static final String COLUMN_PALJENJE2="paljenje2";
    public static final String COLUMN_TRAJANJE2="trajanje2";
    public static final String COLUMN_PALJENJE3="paljenje3";
    public static final String COLUMN_TRAJANJE3="trajanje3";
    public static final String COLUMN_PALJENJE4="paljenje4";
    public static final String COLUMN_TRAJANJE4="trajanje4";
    public static final String COLUMN_PALJENJE5="paljenje5";
    public static final String COLUMN_TRAJANJE5="trajanje5";

    public static final String COLUMN_UREDJAJ="uredjaj";
    public static final String  ID_UREDJAJA="id_uredjaja";

    //TABELA HISTORY

    public static final String TABLE_NAME2="history";
    public static final String COLUMN_ID2="_id";
    public static final String COLUMN_NAME2="broj";
    public static final String COLUMN_PALJENJE1H="paljenje1";
    public static final String COLUMN_TRAJANJE1H="trajanje1";
    public static final String COLUMN_PALJENJE2H="paljenje2";
    public static final String COLUMN_TRAJANJE2H="trajanje2";
    public static final String COLUMN_PALJENJE3H="paljenje3";
    public static final String COLUMN_TRAJANJE3H="trajanje3";
    public static final String COLUMN_PALJENJE4H="paljenje4";
    public static final String COLUMN_TRAJANJE4H="trajanje4";
    public static final String COLUMN_PALJENJE5H="paljenje5";
    public static final String COLUMN_TRAJANJE5H="trajanje5";

    public static final String COLUMN_UREDJAJH="uredjaj";
    public static final String COLUMN_DATUM="datum";
    public static final String ID_URE="id_uredjaja";

    //TABELA KONFIGURACIJA
    public static final String TABLE_NAME3="konfiguracija";
    public static final String COLUMN_ID3="_id";
    public static final String COLUMN_SMS="SMS";
    public static final String COLUMN_BLUE="Blue";
    public static final String COLUMN_BRUM="BRUM";



    public MyDBHalper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TABELA UREDJAJI
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_BROJ + " TEXT, "  +
                COLUMN_TOG + " TEXT "+
                ");";
        db.execSQL(query);
        //TABELA VENTILI
        String query1= "CREATE TABLE " + TABLE_NAME1 + " (" +
                COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME1 + " TEXT, " +
                COLUMN_UREDJAJ + " TEXT, " +
                COLUMN_PALJENJE1 + " TEXT, "  +
                COLUMN_TRAJANJE1 + " TEXT, "  +
                COLUMN_PALJENJE2 + " TEXT, "  +
                COLUMN_TRAJANJE2 + " TEXT, "  +
                COLUMN_PALJENJE3 + " TEXT, "  +
                COLUMN_TRAJANJE3 + " TEXT, "  +
                ID_UREDJAJA + " INTEGER, "  +
                COLUMN_PALJENJE4 + " TEXT, "  +
                COLUMN_TRAJANJE4 + " TEXT, "  +
                COLUMN_PALJENJE5 + " TEXT, "  +
                COLUMN_TRAJANJE5 + " TEXT "  +
                ");";
        db.execSQL(query1);

        String query2="CREATE TABLE " + TABLE_NAME2 + " (" +
                COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME2 + " TEXT, " +
                COLUMN_UREDJAJH + " TEXT, " +
                COLUMN_PALJENJE1H + " TEXT, "  +
                COLUMN_TRAJANJE1H + " TEXT, "  +
                COLUMN_PALJENJE2H + " TEXT, "  +
                COLUMN_TRAJANJE2H + " TEXT, "  +
                COLUMN_PALJENJE3H + " TEXT, "  +
                COLUMN_TRAJANJE3H + " TEXT, "  +
                COLUMN_DATUM + " TEXT, "  +
                ID_URE + " INTEGER ,"+
                COLUMN_PALJENJE4H + " TEXT, "  +
                COLUMN_TRAJANJE4H + " TEXT, "  +
                COLUMN_PALJENJE5H + " TEXT, "  +
                COLUMN_TRAJANJE5H + " TEXT "  +
                ");";
        db.execSQL(query2);

        String query3 = "CREATE TABLE " + TABLE_NAME3 + " (" +
                COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_SMS + " TEXT, " +
                COLUMN_BLUE+ " TEXT, "  +
                COLUMN_BRUM + " TEXT "+
                ");";
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME3);
        onCreate(db);
    }

    public long Addd(String str1, String str2,String tgl){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", str1);
        values.put (COLUMN_BROJ,str2);
        values.put (COLUMN_TOG,tgl);
        long  result=db.insert(TABLE_NAME, null, values);
        db.close();
        return result;


    }
    public void addVent(String bl,String ur,String S,String I,String T,String N, String R, String G,int id,String A,String B,String C,String D){
        ContentValues values = new ContentValues();
        values.put (COLUMN_UREDJAJ,ur);
        values.put(COLUMN_NAME1,bl);
        values.put (COLUMN_PALJENJE1,S);
        values.put (COLUMN_PALJENJE2,T);
        values.put (COLUMN_PALJENJE3,R);
        values.put (COLUMN_TRAJANJE1,I);
        values.put (COLUMN_TRAJANJE2,N);
        values.put (COLUMN_TRAJANJE3,G);
        values.put (ID_UREDJAJA,id);
        values.put(COLUMN_PALJENJE4,A);
        values.put(COLUMN_TRAJANJE4,B);
        values.put(COLUMN_PALJENJE5,C);
        values.put(COLUMN_TRAJANJE5,D);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME1,null,values);
        db.close();



    }

    public void addVentH(String bl,String ur,String S,String I,String T,String N, String R, String G,String H,int i,String A,String B,String C,String D){
        ContentValues values = new ContentValues();
        values.put (COLUMN_UREDJAJ,ur);
        values.put(COLUMN_NAME1,bl);
        values.put (COLUMN_PALJENJE1,S);
        values.put (COLUMN_PALJENJE2,T);
        values.put (COLUMN_PALJENJE3,R);
        values.put (COLUMN_TRAJANJE1,I);
        values.put (COLUMN_TRAJANJE2,N);
        values.put (COLUMN_TRAJANJE3,G);
        values.put (COLUMN_DATUM,H );
        values.put (ID_URE,i);
        values.put (COLUMN_PALJENJE4,A);
        values.put (COLUMN_TRAJANJE4,B);
        values.put (COLUMN_PALJENJE5,C);
        values.put (COLUMN_TRAJANJE5,D);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME2,null,values);
        db.close();



    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return res;
    }
    public Cursor getLastVent(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME1 + " WHERE "+ID_UREDJAJA+" = '"+i+"'"  ,null);
        return res;
    }

    public Cursor getHist(int ure,String datum,String brUr){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE "+ID_URE+" = '"+ure+"'"+" AND "+COLUMN_DATUM+ " = '"+datum+"'"+" AND "+COLUMN_NAME2+" = '"+brUr+"'" ,null);
        return res;
    }
    public void Del(int brisi){
        SQLiteDatabase db= getWritableDatabase();
       // db.delete(TABLE_NAME1,null,null);
        db.execSQL("delete from "+ TABLE_NAME1 + " where "+ID_UREDJAJA+" = '"+brisi+"'");
    }
    public Cursor getHead(int ure){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT DISTINCT "+COLUMN_DATUM+" FROM " + TABLE_NAME2 + " WHERE "+ID_URE+" = '"+ure+"' ORDER BY datum desc"  ,null);
        return res;
    }

    public Cursor getID(String uredjaj){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT "+COLUMN_ID+" FROM " + TABLE_NAME+" WHERE "+COLUMN_NAME+" = '"+uredjaj+"'",null);
        return res;
    }
    public Cursor isTogChecked(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT "+COLUMN_TOG+" FROM " + TABLE_NAME+" WHERE "+COLUMN_ID+" = '"+i+"'",null);
       return res;

    }
    public void AddConf(){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SMS, "On");
        values.put (COLUMN_BLUE,"On");
        values.put (COLUMN_BRUM,"100");
        db.insert(TABLE_NAME3, null, values);
        db.close();
    }
    public Cursor helpC(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] buf=new String[]{};
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE "+COLUMN_ID3+" = '"+i+"'"  ,null);
        return res;
    }

    public String bazalitmin(int i){
        SQLiteDatabase db=this.getReadableDatabase();
        String p=null;
        Cursor res=db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+COLUMN_ID+" = '"+i+"'"  ,null);
        while(res.moveToNext())
        p=res.getString(3);
        return p.trim();
    }

    public String [][] statistika(int i,String date,int c){
        SQLiteDatabase db=this.getReadableDatabase();
        String p[][];
        String x="";
        int br=0,j=0;
        Cursor res=db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE "+COLUMN_DATUM+" = '"+date+"'"+" AND "+ID_URE+" = '"+i+"'"+" AND "+COLUMN_NAME2+" = '"+c+"'"  ,null);
        while(res.moveToNext()) {
           br++;
        }
        p=new String[br][14];
        br=0;
        Cursor res1=db.rawQuery("SELECT paljenje1,trajanje1,paljenje2,trajanje2,paljenje3,trajanje3,paljenje4,trajanje4,paljenje5,trajanje5 FROM " + TABLE_NAME2 + " WHERE "+COLUMN_DATUM+" = '"+date+"'"+" AND "+ID_URE+" = '"+i+"'"+" AND "+COLUMN_NAME2+" = '"+c+"'"  ,null);
        while (res1.moveToNext()){
            for(int b=0;b<10;b++)
                p[br][b]=res1.getString(b);
           br++;
        }

        return p;

    }
}

