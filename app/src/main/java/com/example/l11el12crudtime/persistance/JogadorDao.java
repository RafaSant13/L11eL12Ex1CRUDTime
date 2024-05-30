package com.example.l11el12crudtime.persistance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.l11el12crudtime.model.Jogador;
import com.example.l11el12crudtime.model.Time;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JogadorDao implements IJogadorDao, ICRUDDao<Jogador> {

    private Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public JogadorDao (Context context){
        this.context = context;
    }

    @Override
    public JogadorDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;

    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Jogador j) throws SQLException {
        ContentValues cv = getContentValues(j);
        db.insert("jogador", null, cv);
    }

    @Override
    public int update(Jogador j) throws SQLException {
        ContentValues cv = getContentValues(j);
        int ret = db.update("jogador", cv,
                "id = " + j.getId(), null);
        return ret;
    }

    @Override
    public void delete(Jogador j) throws SQLException {
        ContentValues cv = getContentValues(j);
        db.delete("jogador", "codigo = " + j.getId(),
                null);
    }

    @SuppressLint("Range")
    @Override
    public Jogador findOne(Jogador j) throws SQLException {
        String sql = "SELECT j.id AS id, j.nome AS nome," +
                " j.data_nasc AS data_nasc," +
                " j.altura AS altura, j.peso AS peso," +
                " t.codigo AS timeCodigo, t.nome AS timeNome," +
                " t.cidade AS timeCidade from jogador j, time t" +
                " WHERE j.codigo_time = t.codigo AND j.id = "+j.getId();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()){
            Time t = new Time();
            t.setCodigo(cursor.getInt(cursor.getColumnIndex("timeCodigo")));
            t.setNome(cursor.getString(cursor.getColumnIndex("timeNome")));
            t.setCidade(cursor.getString(cursor.getColumnIndex("timeCidade")));

            j.setId(cursor.getInt(cursor.getColumnIndex("id")));
            j.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            j.setDataNasc(LocalDate.parse(cursor.getString(cursor.getColumnIndex("data_nasc"))));
            j.setAltura(cursor.getFloat(cursor.getColumnIndex("altura")));
            j.setPeso(cursor.getFloat(cursor.getColumnIndex("peso")));
            j.setTime(t);
        }
        cursor.close();
        return j;
    }

    @SuppressLint("Range")
    @Override
    public List<Jogador> findAll() throws SQLException {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT j.id, j.nome, j.data_nasc, j.altura, j.peso," +
                " t.codigo AS timeCodigo, t.nome AS timeNome," +
                " t.cidade AS timeCidade from jogador j, time t" +
                " WHERE j.codigo_time = t.codigo";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){

            Time t = new Time();
            t.setCodigo(cursor.getInt(cursor.getColumnIndex("timeCodigo")));
            t.setNome(cursor.getString(cursor.getColumnIndex("timeNome")));
            t.setCidade(cursor.getString(cursor.getColumnIndex("timeCidade")));

            Jogador j =  new Jogador();
            j.setId(cursor.getInt(cursor.getColumnIndex("id")));
            j.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            j.setDataNasc(LocalDate.parse(cursor.getString(cursor.getColumnIndex("data_nasc"))));
            j.setAltura(cursor.getFloat(cursor.getColumnIndex("altura")));
            j.setPeso(cursor.getFloat(cursor.getColumnIndex("peso")));
            j.setTime(t);

            jogadores.add(j);
            cursor.moveToNext();
        }
        cursor.close();
        return jogadores;
    }

    private static ContentValues getContentValues (Jogador j){
        ContentValues cv = new ContentValues();
        cv.put("id", j.getId());
        cv.put("nome", j.getNome());
        cv.put("data_nasc", j.getDataNasc().toString());
        cv.put("altura", j.getAltura());
        cv.put("peso", j.getPeso());
        cv.put("codigo_time", j.getTime().getCodigo());
        return cv;
    }
}
