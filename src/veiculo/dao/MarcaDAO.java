/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiculo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import veiculo.model.Marca;

/**
 *
 * @author gabriel.zanghelini
 */
public class MarcaDAO implements Dao<Marca> {
    private static final String GET_BY_ID = "SELECT * FROM marca where codigo = ?";
    private static final String GET_ALL = "SELECT * FROM marca order by codigo asc";
    private static final String INSERT = "Insert into marca(codigo,nome,ano_criacao) values(?,?,?)";
    private static final String UPDATE = "update marca set nome = ?, ano_criacao = ? where codigo = ?";
    private static final String DELETE = "delete from marca where codigo = ?";

    public MarcaDAO() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
            // e.printStackTrace();
        }
    }
    
    private Marca getMarcaFromRS(ResultSet rs) throws SQLException {
        Marca marca = new Marca();

        marca.setCodigo(rs.getInt("codigo"));
        marca.setNome(rs.getString("nome"));
        marca.setAno_criacao(rs.getInt("ano_criacao"));

        return marca;
    }
    
    private void createTable() throws SQLException {
        String sqlCreate = "create table if not exists marca("
                + "codigo           int,"
                + "nome             varchar2(50),"
                + "ano_criacao      int"
                + "primary key(codigo));";

        Connection conn = DbConnection.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

        close(conn, stmt, null);
    }
    
    @Override
    public Marca getByKey(int id) {
        Connection conn = DbConnection.getConnection();
        
        Marca marca = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                marca = getMarcaFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter marca pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }
        return marca;
    }

    @Override
    public List<Marca> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Marca> marcas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                marcas.add(getMarcaFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os marcas.", e);
        } finally {
            close(conn, stmt, rs);
        }
        
        return marcas;
    }

    @Override
    public void insert(Marca t) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir marca.", e);
        } finally {
            close(conn, stmt, rs);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(DELETE);

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover marca.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Marca t) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(UPDATE);

            //setar os parâmetros
            stmt.setString(1, t.getNome());
            stmt.setInt(2, t.getAno_criacao());
            stmt.setInt(3, t.getCodigo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        } finally {
            close(conn, stmt, null);
        }
    }
    
    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar recursos.", e);
        }

    }
    
}
