package com.example.l11el12crudtime.persistance;

import java.sql.SQLException;

public interface ITimeDao {
    public TimeDao open() throws SQLException;
    public void close();
}
