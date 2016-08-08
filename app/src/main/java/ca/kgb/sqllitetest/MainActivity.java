package ca.kgb.sqllitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadMagic(View view) {
        StudentsHelper studentsHelper = new StudentsHelper(this);
        SQLiteDatabase sqLiteDatabase = studentsHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + StudentsHelper.TABLE_NAME, null);
        cursor.moveToFirst();
        do{
            int id = cursor.getInt(cursor.getColumnIndex(StudentsHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(StudentsHelper.COLUMN_NAME));
            String age = cursor.getString(cursor.getColumnIndex(StudentsHelper.COLUMN_AGE));

            Log.d(TAG, "loadMagic: " + id + " " + name + " " + age);

        }while(cursor.moveToNext());
        cursor.close();
    }

    public void insertMagic(View view) {
        StudentsHelper studentsHelper = new StudentsHelper(this);
        SQLiteDatabase sqLiteDatabase = studentsHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StudentsHelper.COLUMN_NAME, "Karles");
        values.put(StudentsHelper.COLUMN_AGE, 29);

        sqLiteDatabase.insertOrThrow(StudentsHelper.TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }
}
