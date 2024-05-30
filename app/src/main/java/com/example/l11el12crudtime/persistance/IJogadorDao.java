package com.example.l11el12crudtime.persistance;

import java.sql.SQLException;

public interface IJogadorDao {

    public JogadorDao open() throws SQLException;
    public void close();
}
