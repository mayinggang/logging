package chat.signa.net.pingfang.logging.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/1/22.
 */
public class AppDbHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HaleApp.db";

    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String NOT_NULL = " NOT NULL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";
    private static final String DATETIME_TYPE = "DATETIME DEFAULT CURRENT_TIMESTAMP";
    private static final String UNIQUE = " UNIQUE";
    private static final String COMMA_SEP = ",";

    public static final String  SQL_ENTITY_USER=
            "CREATE TABLE "+AppContract.UserEntity.USER_ID+INTEGER_TYPE+PRIMARY_KEY+COMMA_SEP+
                    AppContract.UserEntity.USER_NAME+TEXT_TYPE+NOT_NULL+COMMA_SEP+
                    AppContract.UserEntity.USER_AGE+INTEGER_TYPE+NOT_NULL+COMMA_SEP+
                    AppContract.UserEntity.USER_SEX+TEXT_TYPE+NOT_NULL+COMMA_SEP+
                    AppContract.UserEntity.USER_ADDRESS+TEXT_TYPE+NOT_NULL+COMMA_SEP+
                    AppContract.UserEntity.USER_PASSWORD+TEXT_TYPE+NOT_NULL  ;

    private static final String SQL_DELETE_ENTRY_USER=
            "DROP TABLE IF EXISTS "+AppContract.UserEntity.TABLE_NAME;

    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_ENTITY_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRY_USER);

        onCreate(db);
    }
}
