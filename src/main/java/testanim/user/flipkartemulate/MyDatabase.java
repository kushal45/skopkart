package testanim.user.flipkartemulate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 26-Dec-15.
 */
public class MyDatabase
{

    MyDatabaseOpenHelper helper;
    public MyDatabase(Context context){
        helper = new MyDatabaseOpenHelper(context);
    }

    /**Inserta la data a la base de datos. 
     * @param name

     * @return
     */
    public long insertData(String name, String photouri){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //content values basically
        contentValues.put(MyDatabaseOpenHelper.COLUMN_NAME_NAME, name);
        contentValues.put(MyDatabaseOpenHelper.COLUMN_NAME_PHOTOURI, photouri);
        long id = db.insert(MyDatabaseOpenHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    /**Obtiene todos los valores de la base de datos. 
     * @return
     */
    public String getAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();
		/*Sencia SQL a ejecutar 
		 *SELECT id,name,password FROM USER  
		 */
        String[] columns = {helper.COLUMN_NAME_ID,helper.COLUMN_NAME_NAME,helper.COLUMN_NAME_PHOTOURI};
        Cursor cursor = db.query(helper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(0);
            String name = cursor.getString(1);
            String password = cursor.getString(2);
            buffer.append(cid + " " + name + " " + password + "\n");
        }
        return buffer.toString();
    }


    /**Obtiene los valores nombre y contraseña, de un usuario especifico. 
     * @param name
     * @return
     */
    public String getData(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
		/*Sentencia SQL a ejecutar 
		 * SELECT name,password from user where name="name"; 
		 */
        String[] columns = {helper.COLUMN_NAME_NAME,helper.COLUMN_NAME_PHOTOURI};
        //Estableciendo la consulta SQL 
        Cursor cursor = db.query(helper.TABLE_NAME, columns, helper.COLUMN_NAME_NAME +" = '"+ name +"'", null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            //Obteniendo los valores de los indices de las columnas. 
            int index1 = cursor.getColumnIndex(helper.COLUMN_NAME_NAME);
            int index2 = cursor.getColumnIndex(helper.COLUMN_NAME_PHOTOURI);
            String nameCol = cursor.getString(index1);
            String passCol = cursor.getString(index2);
            buffer.append(nameCol + " " + passCol +"\n");
        }
        return buffer.toString();

    }

}


 class MyDatabaseOpenHelper  extends SQLiteOpenHelper
{
    private static final String TAG_SQL = "EJEMPLO-SQLITE";
    private static final String DATABASE_NAME = "slidenerd.sqlite";
    private static final int DATABASE_VERSION = 2;
    static final String TABLE_NAME = "user";
     static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_NAME = "name";
   static final String COLUMN_NAME_PHOTOURI= "photoUri";
    private static final String SQL_CREATE_TABLE_USER="CREATE TABLE"+TABLE_NAME+"("+COLUMN_NAME_ID+" INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT"
            + COLUMN_NAME_NAME + " TEXT,"+ COLUMN_NAME_PHOTOURI +" TEXT);";
    public MyDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
