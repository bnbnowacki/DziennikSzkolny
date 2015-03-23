package siwek.krzysztof.dziennikszkolny;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Bartosz on 2015-03-06.
 */
public class DbHelper extends SQLiteOpenHelper {
    //Stałe Ogólne
    public static final String DATABASE_NAME="baza_danych";
    public static final int DATABASE_VERSION=1;

    //Stałe Tabela
    public static final String TABLE_NAME="uczniowie";
    public static final String COLUMN_NAME_ID="_id";
    public static final String COLUMN_NAME_NAZWA="nazwa";
    public static final String COLUMN_NAME_DATA="data";
    public static final String COLUMN_NAME_PESEL="pesel";
    public static final String COLUMN_NAME_MIEJSCE="miejsce";

    //Polecenie utworzenia tabeli
    public static final String CREATE_QUERY="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_NAME_NAZWA+" TEXT, "+COLUMN_NAME_DATA+" TEXT, "+COLUMN_NAME_PESEL+" INTEGER, "+COLUMN_NAME_MIEJSCE+" TEXT);";

    String kolumny[] = {COLUMN_NAME_ID, COLUMN_NAME_NAZWA, COLUMN_NAME_DATA, COLUMN_NAME_PESEL, COLUMN_NAME_MIEJSCE};

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("SQL", "Stworzono bazę danych: "+DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("SQL", "Stworzono tabelę: "+TABLE_NAME);
    }
    public Cursor WyswietlUczniow(DbHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, kolumny, null, null, null, null, null);
        return c;
    }

    public void DodajUcznia(DbHelper dbHelper, String nazwa, String data, long pesel, String miejsce){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAZWA, nazwa);
        values.put(COLUMN_NAME_DATA, data);
        values.put(COLUMN_NAME_PESEL, pesel);
        values.put(COLUMN_NAME_MIEJSCE, miejsce);
        db.insert(TABLE_NAME, null, values);
        Log.d("SQL", "Dodano ucznia: "+nazwa);
    }

    public void UsunUcznia(DbHelper dbHelper, String nazwaUcznia){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String where = COLUMN_NAME_NAZWA+" = ?";
        String[] whereArgs={nazwaUcznia};
        db.delete(TABLE_NAME, where, whereArgs);
        Log.d("SQL", "Usunięto ucznia: "+nazwaUcznia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
