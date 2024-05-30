package com.example.l11el12crudtime.persistance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.l11el12crudtime.model.Time;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeDao implements ITimeDao, ICRUDDao<Time> {

    private Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public TimeDao (Context context){
        this.context = context;
    }
    @Override
    public TimeDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Time t) throws SQLException {
        ContentValues cv = getContentValues(t);
        db.insert("time", null, cv);
    }

    @Override
    public int update(Time t) throws SQLException {
        ContentValues cv = getContentValues(t);
        int ret = db.update("time", cv,
                "codigo = " + t.getCodigo(), null);
        return ret;
    }

    @Override
    public void delete(Time t) throws SQLException {
        ContentValues cv = getContentValues(t);
        db.delete("time", "codigo = " + t.getCodigo(),
                null);
    }

    @SuppressLint("Range")
    @Override
    public Time findOne(Time t) throws SQLException {
        String sql = "SELECT codigo, nome, cidade FROM time WHERE codigo = "+t.getCodigo();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()){
            t.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            t.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            t.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
        }
        cursor.close();
        return t;
    }

    @SuppressLint("Range")
    @Override
    public List<Time> findAll() throws SQLException {
        List<Time> times = new ArrayList<>();
        String sql = "SELECT codigo, nome, cidade FROM time";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            Time t = new Time();
            t.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            t.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            t.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
            times.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return times;
    }

    private static ContentValues getContentValues (Time  t){
        ContentValues cv = new ContentValues();
        cv.put("codigo", t.getCodigo());
        cv.put("nome", t.getNome());
        cv.put("cidade", t.getCidade());
        return cv;
    }

}
