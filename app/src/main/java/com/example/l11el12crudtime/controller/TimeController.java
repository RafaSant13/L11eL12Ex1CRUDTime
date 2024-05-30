package com.example.l11el12crudtime.controller;

import com.example.l11el12crudtime.model.Time;
import com.example.l11el12crudtime.persistance.TimeDao;

import java.sql.SQLException;
import java.util.List;

public class TimeController implements IController<Time> {

    private final TimeDao tDao;

    public TimeController(TimeDao tDao){
        this.tDao = tDao;
    }
    @Override
    public void insert(Time time) throws SQLException {
        if (tDao.open()==null){
            tDao.open();
        }
        tDao.insert(time);
        tDao.close();
    }

    @Override
    public void update(Time time) throws SQLException {
        if (tDao.open()==null){
            tDao.open();
        }
        tDao.update(time);
        tDao.close();
    }

    @Override
    public void delete(Time time) throws SQLException {
        if (tDao.open()==null){
            tDao.open();
        }
        tDao.delete(time);
        tDao.close();
    }

    @Override
    public Time findOne(Time time) throws SQLException {
        if (tDao.open()==null){
            tDao.open();
        }
        return tDao.findOne(time);
    }

    @Override
    public List<Time> findAll() throws SQLException {
        if (tDao.open()==null){
            tDao.open();
        }
        return tDao.findAll();
    }

}
