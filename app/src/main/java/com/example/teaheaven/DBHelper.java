package com.example.teaheaven;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.StringTokenizer;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CART_TABLE_NAME = "cart";
    public static final String CART_COLUMN_ID = "id";
    public static final String CART_COLUMN_NAME = "name";
    public static final String CART_COLUMN_GRAMS = "grams";
    public static final String CART_COLUMN_COUNT = "counts";
    public static final String CART_COLUMN_PRICE = "price";
    public static Context context;
//    public static final String CART_COLUMN_PHONE = "phone";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists cart" +
                "(id integer primary key autoincrement, username text, name text, grams integer, counts integer, price integer)"
        );
        db.execSQL(
                "create table if not exists allTea" +
                        "(id integer primary key autoincrement, name text, type text, region text, price integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertItemIntoCart(String username, String name, int grams, int counts, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean flag = isUserAlreadyInDB(db, username, name, grams, counts, price);
        if(!flag) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("name", name);
            contentValues.put("grams", grams);
            contentValues.put("counts", counts);
            contentValues.put("price", price*counts);
//            db.insert("cart",null,contentValues);
            long row = db.insert("cart", null, contentValues);
            if(row == -1) {
                Toast.makeText(context, "Not Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isUserAlreadyInDB(SQLiteDatabase db,String username, String name, int grams, int count, int price) {
        Cursor cursor = null;
        cursor = db.rawQuery("select id, name from cart where username = '"+username+"';",null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if(cursor.getString(1).equals(name)) {
//                update count
                    db.execSQL("UPDATE cart SET counts = counts + '"+count+"' WHERE id = " + cursor.getInt(0) + ";"
                    );
//                    get updated count value
                    Cursor countCursor = db.rawQuery(
                            "SELECT counts FROM cart WHERE id = " + cursor.getInt(0) + ";",
                            null
                    );
                    countCursor.moveToFirst();
                    count = countCursor.getInt(0);
                    countCursor.close();
//                    update grams
                    db.execSQL("UPDATE cart set grams = grams + " +
                            (count*grams) + " where id = " + cursor.getInt(0) + ";"
                    );
//                  update price
                    Log.v("Count is",count+"");
                    Log.v("price is",price+"");
                    Log.v("Product is",(count*price)+"");
                    db.execSQL("UPDATE cart SET price = " +
                            (count*price) + " WHERE id = " +cursor.getInt(0) + ";"
                    );
                    return true;
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return false;
    }

    public void updateCartData(String username, String teaName, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "UPDATE cart SET counts = '"+count+"' WHERE username = '"+username+"' AND name = '"+teaName+"';"
        );
    }
    public int getPrice(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery (
                "SELECT price FROM allTea WHERE name = '"+name+"';",
                null
        );
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

    public void updatePrice(int price, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        SharedPreferences preferences = context.getSharedPreferences("users",Context.MODE_PRIVATE);

        String username = preferences.getString("username","null");
        db.execSQL(
                "UPDATE cart SET price = '"+price+"' " +
                        "WHERE name = '"+name+"' AND username = '"+username+"';"
        );
    }
    public void deleteItemFromCart(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        SharedPreferences preferences = context.getSharedPreferences("users",Context.MODE_PRIVATE);

        String username = preferences.getString("username","null");
        db.execSQL(
                "DELETE FROM cart WHERE username = '"+username+"' AND name = '"+name+"';"
        );
    }
    public Cursor getDataByType(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res  = db.rawQuery("SELECT * FROM allTea WHERE type = '"+type+"';",null);
        return res;
    }

    public boolean checkIfUserNameExists(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS users " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT);"
        );
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE username = '"+username+"';",
                null
        );
        if(cursor.getCount() > 0)
            return true;
        return false;
    }

    public boolean checkPass(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS users " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT);"
        );
        Cursor cursor = db.rawQuery(
                "SELECT password FROM users WHERE username = '"+username+"';",
                null
        );
        cursor.moveToFirst();
        if(cursor.getCount() > 0 ){
            String dbPass = cursor.getString(0);
            if(dbPass.equals(password)){
                return true;
            }
        }
        return false;
    }

    public void insertUser(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS users " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT);"
        );
        db.execSQL(
                "INSERT INTO users (username, password, email)" +
                        "VALUES('"+username+"', '"+password+"', '"+email+"');"
        );
    }
    public int getCounts(String username, String teaName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("Username",username);
        Log.v("Tea name",teaName);
        Cursor cursor = db.rawQuery(
                "SELECT counts FROM cart WHERE username = '"+username.trim()+"' AND name = '"+teaName.trim()+"';",
                null);
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        return cursor.getInt(0);
        return 0;
    }

    public int getTotalPriceFormCart(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(price) FROM cart WHERE username = '"+username+"';",
                null
        );
        cursor.moveToFirst();
        if(cursor.getCount()>0) {
            int s = cursor.getInt(0);
            cursor.close();
            return s;
        }
        return 0;
    }

    public void loadDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        // name, type, region, price
        String stmts = "";
        String[] blackTeaNames = {
                "Assam",
                "Darjeeling",
                "Ceylon",
                "Chai Kee Mun",
                "English",
                "Earl Gray",
                "Lapsang Souchong",
                "Yunnan Red",
                "Kenyan"
        };
        String[] greenTeaNames = {
                "Matcha",
                "Sencha",
                "Hojicha",
                "Gyokuro",
                "Tencha",
                "Funmatsucha",
                "Agari",
                "Kabusecha",
                "Fukamushi cha",
                "Kukicha",
                "Kamairicha",
                "Genmaicha",
                "Bancha",
                "Shincha"
        };
        String[] oolongTeaNames = {
                "TI KUAN YIN",
                "DAN CONG",
                "DA HONG PAO",
                "JIN XUAN",
                "SI JI CHUN",
                "ALI SHAN"
        };
        String[] whiteTeaNames = {
                "Silver Needle",
                "White Peony",
                "Gong Mei",
                "Shou Mei",
                "Ceylon White",
                "African White"
        };
        for(String tea: blackTeaNames) {
            stmts += "INSERT INTO allTea (name, type, region, price) VALUES ('"+tea+"', 'Black', 'China', '28');";
        }
        for(String tea: greenTeaNames) {
            stmts += "INSERT INTO allTea (name, type, region, price) VALUES ('"+tea+"', 'Green', 'India', '9');";
        }
        for(String tea: oolongTeaNames) {
            stmts += "INSERT INTO allTea (name, type, region, price) VALUES ('"+tea+"', 'Oolong', 'China', '9');";
        }
        for(String tea: whiteTeaNames) {
            stmts += "INSERT INTO allTea (name, type, region, price) VALUES ('"+tea+"', 'White', 'Japan', '9');";
        }
        Log.v("Here is your data",stmts);
        execBatchSQL(stmts);
    }

    private void execBatchSQL(String stmts) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(
//                "Drop table allTea;"
//        );
        db.execSQL(
                "create table if not exists allTea" +
                        "(id integer primary key autoincrement, name text, type text, region text, price integer)"
        );
        Cursor cursor = db.rawQuery("SELECT * FROM allTea",null);
        if(cursor.getCount()>0){
            Log.v("Yes","YEeeeeesss");
            Log.v("yes",cursor.getCount()+"");
        } else {
            Log.v("NO","Nooooooo");
            StringTokenizer tokenizer = new StringTokenizer(stmts,";");
            while (tokenizer.hasMoreTokens()) {
                db.execSQL(tokenizer.nextToken());
            }
        }
    }

    public Cursor getCartData(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM cart WHERE username = '"+username+"';",null
        );
        return cursor;
    }

    public void deleteFromCart(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM cart WHERE username = '"+username+"';");
    }
    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from allTea;",null);
        if(cursor.getCount()>0) {
            Log.v("MSG","Table still exists");
        } else {
            Log.v("MSG","Table doesn't exists");
        }
//        db.execSQL("DROP TABLE allTea;");
//        Toast.makeText(context, "Table Dropped! ", Toast.LENGTH_SHORT).show();
    }
}
