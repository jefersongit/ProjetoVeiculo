package veiculo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import veiculo.model.Veiculo;

public class VeiculoDAO implements Dao<Veiculo> {

    private static final String GET_BY_ID = "SELECT * FROM veiculo where codigo = ?";
    private static final String GET_ALL = "SELECT * FROM veiculo order by codigo asc";
    private static final String INSERT = "Insert into veiculo(codigo,nome,categoria,cod_marca,modelo,cor,ano) values(?,?,?,?,?,?,?)";
    private static final String UPDATE = "update veiculo set codigo = ?, nome = ?, categoria = ?, cod_marca = ?, modelo = ? , cor = ?, ano = ? where codigo = ?";
    private static final String DELETE = "delete from veiculo where codigo = ?";

    public VeiculoDAO() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
            // e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "create table if not exists veiculo("
                + "codigo           int,"
                + "nome             varchar2(50),"
                + "categoria        varchar2(50),"
                + "cod_marca        int,"
                + "modelo           varchar2(50),"
                + "cor              varchar2(50),"
                + "ano              int"
                + "foreign key(cod_marca) references marca(codigo))";

        Connection conn = DbConnection.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

        close(conn, stmt, null);
    }

    private Veiculo getVeiculoFromRS(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();

        veiculo.setCodigo(rs.getInt("codigo"));
        veiculo.setNome(rs.getString("nome"));
        veiculo.setCategoria(rs.getString("categoria"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setCor(rs.getString("cor"));
        veiculo.setAno(rs.getInt("ano"));

        return veiculo;
    }

    @Override
    public void insert(Veiculo veiculo) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, veiculo.getCodigo());
            stmt.setString(2, veiculo.getNome());
            stmt.setString(3, veiculo.getCategoria());
            stmt.setString(4, veiculo.getModelo());
            stmt.setString(5, veiculo.getCor());
            stmt.setInt(6, veiculo.getAno());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                veiculo.setCodigo(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir veiculo.", e);
        } finally {
            close(conn, stmt, rs);
        }
    }

    @Override
    public Veiculo getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Veiculo veiculo = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                veiculo = getVeiculoFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter veiculo pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }
        return veiculo;
    }

    @Override
    public List<Veiculo> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Veiculo> veiculos = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                veiculos.add(getVeiculoFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os veiculos.", e);
        } finally {
            close(conn, stmt, rs);
        }

//        finally {
//            conexao.fecharConexao();
//        }
        return veiculos;
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
            throw new RuntimeException("Erro ao remover veiculo.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Veiculo t) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

            //setar os parâmetros
            stmt.setInt(1, t.getCodigo());
            stmt.setString(2, t.getNome());
            stmt.setString(3, t.getCategoria());
            stmt.setString(4, t.getModelo());
            stmt.setString(5, t.getCor());
            stmt.setInt(6, t.getAno());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veiculo.", e);
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

    public List<Veiculo> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
