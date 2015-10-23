package br.com.hudson;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hudson Schumaker
 * @version 1.0.0
 * @since 4.0.0
 */
public class ConexaoMySql {

    public static Connection getConnection() {
        Connection c = null;
        try {
            Driver d = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL = "jdbc:mysql://172.16.188.128:3307/hefesto";
            //   String URL = HsFiles.readAlias();
            c = DriverManager.getConnection(URL, "root", "secreta");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            System.err.println("Erro:\n" + ex);
        }
        return c;
    }
}
