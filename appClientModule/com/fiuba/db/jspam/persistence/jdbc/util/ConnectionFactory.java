package com.fiuba.db.jspam.persistence.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Demian
 */
public class ConnectionFactory {
    private static ConnectionFactory factory= new ConnectionFactory();
 
    private ConnectionFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener la conexion",ex);
        }
    }
    
    public static ConnectionFactory getInstance(){
        return factory;
    }
    
    public Connection getConnection(){
        try {            
            return DriverManager.getConnection("jdbc:mysql://localhost/jspam?user=jspamMySql&password=jspam");
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener la conexion",ex);
        }
    }
    
    public void closeStatement(PreparedStatement stm){
        if (stm != null){
            try {
                stm.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Error al cerrar el PreparedStatement",ex);
            }
        }
    }
    
    public void closeResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Error al cerrar el ResultSet",ex);
            }
        }
    }
    
    public void closeConnection(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Error cerrar la conexion",ex);
            }
        }
    }

}
