package veiculo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    public static final String URL = "jdbc:sqlite:banco.db";

    private static Connection conn = null;

    /**
     * ObtÃ©m uma conexÃ£o com o banco
     *
     * @return objeto Connection
     */
    public static Connection getConnection() {

        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro Conectando ao banco de dados", ex);
        }

        return conn;
    }

    public static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                //myConn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar recursos.", e);
        }

    }

}
