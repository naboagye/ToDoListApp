package com.example.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class dbhelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ToDoApp";
    private  static final String DB_TABLE = "Task";
    private static final String DB_COLUMN= "TaskName";
    //private static final String DB_DATE = "Taskdate";
    private static final String DB_TIME = "Timetask";



    public dbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL" + DB_TABLE + DB_COLUMN + " TEXT );");
        String query = "CREATE TABLE " + DB_TABLE +"("
                + DB_COLUMN + " Text ,"
                + DB_TIME + "Text"
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        String query = String.format("DROP TABLE IF EXISTS %s", DB_TABLE);

        db.execSQL(query);

        // Create tables again
        onCreate(db);
    }

    public void insertNewtask(String task){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN, task);
        values.put(DB_TIME,task);
        db.insertWithOnConflict(DB_TABLE, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE, DB_COLUMN + "=? ", new String[]{task});
        db.close();
    }

    public ArrayList<String> getTaskList(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN}, null, null, null, null, null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_COLUMN);
            taskList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }
}