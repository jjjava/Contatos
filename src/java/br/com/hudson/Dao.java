package br.com.hudson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hudson.sales
 * @verison 1.0.0
 */
public class Dao {

    private List<Contato> lista;

    public Dao() {
        lista = new ArrayList<>();
    }

    public List<Contato> getContatos(String param) {
        try {
            Connection conn = ConexaoMySql.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTATOS WHERE NOME LIKE '%" + param + "%'");
            while (rs.next()) {
                Contato c = new Contato();
                c.setId("" + rs.getInt("id"));
                c.setNome(HsStringUtil.simpleBold(rs.getString("nome"), param));
                c.setEmail(rs.getString("email"));
                c.setTel(rs.getString("tel"));

                lista.add(c);
            }
            rs.close();
            stmt.close();
            conn.close();
            // searchOnEmail(param);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return lista;
    }

    public List<Contato> getContatos(String param, int page) {
        int limit = 10;
        int rowset = 0;
        if(page!=1){
            rowset = (page*limit)-10;
        }
        try {
            Connection conn = ConexaoMySql.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTATOS WHERE NOME LIKE '%" + param + "%' LIMIT " + rowset + "," + limit);
            while (rs.next()) {
                Contato c = new Contato();
                c.setId("" + rs.getInt("id"));
                c.setNome(HsStringUtil.simpleBold(rs.getString("nome"), param));
                c.setEmail(rs.getString("email"));
                c.setTel(rs.getString("tel"));

                lista.add(c);
            }
            rs.close();
            stmt.close();
            conn.close();
            // searchOnEmail(param);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return lista;
    }

    public List<String> getAutoComplete(String param) {
        List<String> auto = new LinkedList<>();
        try {
            Connection conn = ConexaoMySql.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTATOS WHERE NOME LIKE '" + param + "%'");
            while (rs.next()) {
                auto.add(rs.getString("nome"));
            }
            rs.close();
            stmt.close();
            conn.close();
            // searchOnEmail(param);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return auto;
    }

    public int getNumberOfResults(String param) {
        int total = 0;
        try {
            Connection conn = ConexaoMySql.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(id) as total from contatos where NOME LIKE '%" + param + "%'");
            while (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        System.out.println("Dao:" + total);
        return total;
    }

    private void searchOnEmail(String param) {
        try {
            Connection conn = ConexaoMySql.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTATOS WHERE EMAIL LIKE '%" + param + "%'");

            while (rs.next()) {
                Contato c = new Contato();
                c.setId("" + rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setTel(rs.getString("tel"));

                lista.add(c);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
