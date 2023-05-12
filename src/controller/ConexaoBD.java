package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoBD {

    public static Connection conectar() {
        Connection conexao;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/cvendas"; // ?useTimeZone=true&serverTimezone=UTC
        String user = "root";
        String password = "4004";
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void desconectar(Connection conexao) {
        try {
            if (!conexao.isClosed() && conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
