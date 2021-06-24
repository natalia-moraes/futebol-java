/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futebol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author naty
 */
public class DB {

    static final String banco = "jdbc:mysql://localhost:3306/futebol";
    Connection conexao = null;

    public Connection Conecta() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(banco, "root", "");
            return conexao;
        } catch (SQLDataException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar o banco de dados");
        }
        return conexao;
    }

}
