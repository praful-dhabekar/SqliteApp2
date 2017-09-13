package com.praful.sqliteapp2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.praful.sqliteapp2.R.id.sID;
import static com.praful.sqliteapp2.R.id.sMarks;
import static com.praful.sqliteapp2.R.id.sName;

public class MainActivity extends AppCompatActivity {

    EditText editText_name,editText_ID,editText_Marks;
    TextView textView_name, textView_id, textView_marks;
    StudentDbHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new StudentDbHelper(this);
        db = dbHelper.getWritableDatabase();

        editText_ID = (EditText)findViewById(sID);
        editText_name = (EditText)findViewById(sName);
        editText_Marks = (EditText)findViewById(sMarks);

        textView_id = (TextView)findViewById(R.id.text_view_id);
        textView_name = (TextView)findViewById(R.id.text_view_name);
        textView_marks = (TextView)findViewById(R.id.text_view_marks);


        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sID= editText_ID.getText().toString();
                String sName = editText_name.getText().toString();
                String sMarks = editText_Marks.getText().toString();

                ContentValues values = new ContentValues();
                values.put("sid", sID);
                values.put("sname", sName);
                values.put("marks", sMarks);

                db.insert("student",null, values);
                //Log.d("Student info " ,"Table row: "+row+" is inserted");

                db = dbHelper.getReadableDatabase();

                String projection[] = {"sid","sname","marks"};

                Cursor cursor = db.query("student",projection,null,null,null,null,null);

                cursor.moveToPosition(0);
                //System.out.println("Name is: "+cursor.getString(0));

                textView_id.setText(cursor.getString(0));
                textView_name.setText(cursor.getString(1));
                textView_marks.setText(cursor.getString(2));
            }
        });

    }
}
