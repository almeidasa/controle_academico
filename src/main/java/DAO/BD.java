package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Alexandre Almeida
 */
public class BD {

    static String driver = "jdbc:postgresql://localhost:5432/";
    static String banco = "controle_academico";
    static String usuario = "postgres";
    static String senha = "root";

    public static Connection getConexao() throws Exception {
        Connection conn = DriverManager.getConnection(driver + banco, usuario, senha);
        return conn;
    }
}
