package ca.kgb.sqllitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText mEditTextAge;
    private EditText mEditTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextAge = (EditText) findViewById(R.id.editTextAge);
        mEditTextName = (EditText) findViewById(R.id.editTextName);
    }

    public void loadMagic(View view) {
        StudentsHelper studentsHelper = new StudentsHelper(this);
        SQLiteDatabase sqLiteDatabase = studentsHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + StudentsHelper.TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            int id = cursor.getInt(cursor.getColumnIndex(StudentsHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(StudentsHelper.COLUMN_NAME));
            String age = cursor.getString(cursor.getColumnIndex(StudentsHelper.COLUMN_AGE));

            Log.d(TAG, "loadMagic: " + id + " " + name + " " + age);

        } while (cursor.moveToNext());
        cursor.close();
    }

    public void insertMagic(View view) {
        StudentsHelper studentsHelper = new StudentsHelper(this);
        SQLiteDatabase sqLiteDatabase = studentsHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (mEditTextAge.getText().toString() != null) {
            values.put(StudentsHelper.COLUMN_AGE, mEditTextAge.getText().toString());
        } else
            values.put(StudentsHelper.COLUMN_AGE, 29);
        if (mEditTextName.getText().toString() != null) {
            values.put(StudentsHelper.COLUMN_NAME, mEditTextName.getText().toString());
        } else
            values.put(StudentsHelper.COLUMN_NAME, "Karles");

        sqLiteDatabase.insertOrThrow(StudentsHelper.TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public void loadNRMagic(View view) {
        StudentsHelper studentsHelper = new StudentsHelper(this);
        SQLiteDatabase sqLiteDatabase = studentsHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(StudentsHelper.TABLE_NAME, new String[]
                {StudentsHelper.COLUMN_ID, StudentsHelper.COLUMN_NAME, StudentsHelper.COLUMN_AGE},
                null, null, null, null, null);

        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + StudentsHelper.TABLE_NAME, null);
        cursor.moveToFirst();
        do{
            int id = cursor.getInt(cursor.getColumnIndex(StudentsHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(StudentsHelper.COLUMN_NAME));
            String age = cursor.getString(cursor.getColumnIndex(StudentsHelper.COLUMN_AGE));

            Log.d(TAG, "loadMagic: " + id + " " + name + " " + age);

        }while(cursor.moveToNext());
        cursor.close();
    }
}
