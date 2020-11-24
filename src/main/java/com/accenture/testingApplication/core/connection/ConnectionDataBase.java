package com.accenture.testingApplication.core.connection;

import com.accenture.testingApplication.core.сonstant.DialogueConstant;
import com.accenture.testingApplication.core.сonstant.StructureDataBaseConstant;
import com.accenture.testingApplication.core.сonstant.ConfigDataBaseConstant;
import com.accenture.testingApplication.core.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConnectionDataBase {

    Connection dbConnection;

    public Connection getDataBaseConnection()
            throws ClassCastException, SQLException {
        String ConnectionString = "jdbc:h2:" + ConfigDataBaseConstant.Path + "/" + ConfigDataBaseConstant.Name;
        try {
            Class.forName(ConfigDataBaseConstant.ClassDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbConnection = DriverManager.getConnection(ConnectionString, ConfigDataBaseConstant.User, ConfigDataBaseConstant.Pass);
        return dbConnection;
    }
}

