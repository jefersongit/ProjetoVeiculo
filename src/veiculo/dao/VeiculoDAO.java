package veiculo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import veiculo.dao.DbConnection;
import veiculo.model.Cor;
import veiculo.model.Marca;
import veiculo.model.Modelo;

import veiculo.model.Veiculo;

public class VeiculoDAO implements Dao<Veiculo> {

    private static final String GET_BY_ID = "SELECT * FROM veiculo JOIN marca on veiculo.codigo = marca.codigo where veiculo.codigo = ?";
    private static final String GET_ALL = "SELECT * FROM veiculo JOIN marca on veiculo.codigo = marca.codigo";
    private static final String INSERT = "Insert into veiculo(codigo,nome,cod_marca,modelo,cor,ano) values(?,?,?,?,?,?)";
    private static final String UPDATE = "update veiculo set codigo = ?, nome = ?, cod_marca = ?, modelo = ? , cor = ?, ano = ? where codigo = ?";
    private static final String DELETE = "delete from veiculo where codigo = ?";

    public VeiculoDAO() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "create table if not exists veiculo("
                + "codigo           int,"
                + "nome             varchar2(50),"
                + "cod_marca        int,"
                + "modelo           varchar2(50),"
                + "cor              varchar2(50),"
                + "ano              int,"
                + "foreign key(cod_marca) references marca(codigo))";

        Connection conn = DbConnection.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

        DbConnection.close(conn, stmt, null);
    }

    private Veiculo getVeiculoFromRS(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();

        veiculo.setCodigo(rs.getInt("codigo"));
        veiculo.setNome(rs.getString("nome"));
        veiculo.setModelo(Modelo.valueOf(rs.getString("modelo")));
        veiculo.setCor(Cor.valueOf(rs.getString("cor")));
        veiculo.setAno(rs.getInt("ano"));
        veiculo.setMarca( new Marca(rs.getInt("codigo"), rs.getString("nome"), rs.getDate("ano_criacao").toLocalDate()));
        
        return veiculo;
    }

    @Override
    public void insert(Veiculo veiculo) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, veiculo.getNome());
            stmt.setString(2, String.valueOf(veiculo.getModelo()));
            stmt.setString(3, String.valueOf(veiculo.getCor()));
            stmt.setInt(4, veiculo.getAno());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                veiculo.setCodigo(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir veiculo.", e);
        } finally {
            DbConnection.close(conn, stmt, rs);
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
            DbConnection.close(conn, stmt, rs);
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
            DbConnection.close(conn, stmt, rs);
        }

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
            DbConnection.close(conn, stmt, null);
        }
    }

    @Override
    public void update(Veiculo t) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

            //setar os parâmetros
            stmt.setString(1, t.getNome());
            stmt.setString(2, String.valueOf(t.getModelo()));
            stmt.setString(3, String.valueOf(t.getCor()));
            stmt.setInt(4, t.getAno());
            stmt.setInt(5, t.getCodigo());
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veiculo.", e);
        } finally {
            DbConnection.close(conn, stmt, null);
        }
    }
}