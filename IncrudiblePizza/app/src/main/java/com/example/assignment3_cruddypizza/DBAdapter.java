package com.example.assignment3_cruddypizza;

import android.content.*;

import android.database.*;

import android.database.sqlite.*;

import android.util.Log;


public class DBAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SIZE ="size";
    public static final String KEY_TOPPING1 ="topping1";
    public static final String KEY_TOPPING2 ="topping2";
    public static final String KEY_TOPPING3 ="topping3";
    public static final String KEY_TOPPING4 ="topping4";
    public static final String KEY_TOPPING5 ="topping5";
    public static final String KEY_TOPPING6 ="topping6";
    public static final String KEY_CREATED_AT ="created_at";


    public static final String TAG = "DBAdapter";


    private static final String DATABASE_NAME = "IncrudibleDB";

    private static final String DATABASE_TABLE = "orders";

    private static final int DATABASE_VERSION = 2;


    private static final String DATABASE_CREATE =

            "create table orders(_id integer primary key autoincrement,"

                    + "name text not null,size text not null,topping1 text not null, topping2 text ,topping3 text, topping4 text, topping5 text, topping6 text, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL );";


    private final Context context;

    private DatabaseHelper DBHelper;

    private SQLiteDatabase db;


    public DBAdapter(Context ctx)

    {

        this.context = ctx;

        DBHelper = new DatabaseHelper(context);

    }


    private static class DatabaseHelper extends SQLiteOpenHelper

    {

        DatabaseHelper(Context context)

        {

            super(context,DATABASE_NAME,null,DATABASE_VERSION);

        }


        public void onCreate(SQLiteDatabase db)

        {

            try{

                db.execSQL(DATABASE_CREATE);

            }catch(SQLException e){

                e.printStackTrace();

            }

        }//end method onCreate


        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)

        {

            Log.w(TAG,"Upgrade database from version " + oldVersion + " to "

                    + newVersion + ", which will destroy all old data");

            db.execSQL("DROP TABLE IF EXISTS orders");

            onCreate(db);

        }//end method onUpgrade

    }


//open the database

    public DBAdapter open() throws SQLException

    {

        db = DBHelper.getWritableDatabase();

        return this;

    }


//close the database

    public void close()

    {

        DBHelper.close();

    }


//insert a contact into the database

    public long insertOrder(String name,String size, String topping1, String topping2, String topping3, String topping4, String topping5, String topping6)

    {

        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_SIZE, size);
        initialValues.put(KEY_TOPPING1, topping1);
        initialValues.put(KEY_TOPPING2, topping2);
        initialValues.put(KEY_TOPPING3, topping3);
        initialValues.put(KEY_TOPPING4, topping4);
        initialValues.put(KEY_TOPPING5, topping5);
        initialValues.put(KEY_TOPPING6, topping6);

        return db.insert(DATABASE_TABLE, null, initialValues);

    }


//delete a particular order

    public boolean deleteOrder(long rowId)

    {

        return db.delete(DATABASE_TABLE,KEY_ROWID + "=" + rowId,null) >0;

    }


//retrieve all the orders

    public Cursor getAllOrders()

    {

        return db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME,

                KEY_SIZE,KEY_TOPPING1,KEY_TOPPING2,KEY_TOPPING3,KEY_TOPPING4,KEY_TOPPING5,KEY_TOPPING6,KEY_CREATED_AT},null,null,null,null,null);

    }


//retrieve a single order

    public Cursor getOrder(long rowId) throws SQLException

    {

        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,

                KEY_NAME,KEY_SIZE,KEY_TOPPING1,KEY_TOPPING2,KEY_TOPPING3,KEY_TOPPING4,KEY_TOPPING5,KEY_TOPPING6,KEY_CREATED_AT},KEY_ROWID + "=" + rowId,null,null,null,null,null);

        if(mCursor != null)

        {

            mCursor.moveToFirst();

        }

        return mCursor;

    }


//updates a order

    public boolean updateOrder(long rowId,String name,String size, String topping1, String topping2, String topping3, String topping4, String topping5, String topping6)

    {

        ContentValues cval = new ContentValues();

        cval.put(KEY_NAME, name);
        cval.put(KEY_SIZE, size);
        cval.put(KEY_TOPPING1, topping1);
        cval.put(KEY_TOPPING2, topping2);
        cval.put(KEY_TOPPING3, topping3);
        cval.put(KEY_TOPPING4, topping4);
        cval.put(KEY_TOPPING5, topping5);
        cval.put(KEY_TOPPING6, topping6);

        return db.update(DATABASE_TABLE, cval, KEY_ROWID + "=" + rowId,null) >0;

    }


}//end class DBAdapter
