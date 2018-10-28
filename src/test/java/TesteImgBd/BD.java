package TesteImgBd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Autor Winder Rezende
 * @Data  27/10/2018
 */
public class BD {

    static String driver = "jdbc:postgresql://localhost:5432/";
    static String banco = "img";
    static String usuario = "postgres";
    static String senha = "root";

    public static Connection getConexao() throws Exception {
        DriverManager.registerDriver(new org.postgresql.Driver());
        Connection conn = DriverManager.getConnection(driver + banco, usuario, senha);
        return conn;    
    }
}
