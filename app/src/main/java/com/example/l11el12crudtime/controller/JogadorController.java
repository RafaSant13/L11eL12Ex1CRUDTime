package com.example.l11el12crudtime.controller;

import com.example.l11el12crudtime.model.Jogador;
import com.example.l11el12crudtime.persistance.JogadorDao;

import java.sql.SQLException;
import java.util.List;

public class JogadorController implements  IController<Jogador> {

    private final JogadorDao jDao;

    public JogadorController(JogadorDao jDao){
        this.jDao = jDao;
    }
    @Override
    public void insert(Jogador jogador) throws SQLException {
        if (jDao.open()==null){
            jDao.open();
        }
        jDao.insert(jogador);
        jDao.close();
    }

    @Override
    public void update(Jogador jogador) throws SQLException {
        if (jDao.open()==null){
            jDao.open();
        }
        jDao.update(jogador);
        jDao.close();
    }

    @Override
    public void delete(Jogador jogador) throws SQLException {
        if (jDao.open()==null){
            jDao.open();
        }
        jDao.delete(jogador);
        jDao.close();
    }

    @Override
    public Jogador findOne(Jogador jogador) throws SQLException {
        if (jDao.open()==null){
            jDao.open();
        }
        return jDao.findOne(jogador);
    }

    @Override
    public List<Jogador> findAll() throws SQLException {
        if (jDao.open()==null){
            jDao.open();
        }
        return jDao.findAll();
    }
}
