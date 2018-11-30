package veiculo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import veiculo.model.Cliente;
import veiculo.model.Naturalidade;
import veiculo.model.Sexo;

public class ClienteDAO implements Dao<Cliente> {

    private static final String GET_BY_ID = "SELECT * FROM cliente where codigo = ?";
    private static final String GET_ALL = "SELECT * FROM cliente order by codigo asc";
    private static final String INSERT = "Insert into cliente(nome,cpf,sexo,naturalidade,endereco,data_nascimento) values(?,?,?,?,?,?)";
    private static final String UPDATE = "update cliente set nome = ?, cpf = ?, sexo = ?, naturalidade = ?, data_nascimento = ? , endereco = ? where codigo = ?";
    private static final String DELETE = "delete from cliente where codigo = ?";

    public ClienteDAO() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
            // e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "create table if not exists cliente("
                + "codigo           integer,"
                + "nome             varchar2(50),"
                + "cpf              long,"
                + "sexo             varchar2(50),"
                + "naturalidade     varchar2(50),"
                + "endereco         varchar2(50),"
                + "data_nascimento  date,"
                + "primary key(codigo));";

        Connection conn = DbConnection.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

        close(conn, stmt, null);
    }

    private Cliente getClienteFromRS(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();

        cliente.setCodigo(rs.getInt("codigo"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getLong("cpf"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
        cliente.setSexo(Sexo.valueOf(rs.getString("sexo")));
        cliente.setNaturalidade(Naturalidade.valueOf(rs.getString("naturalidade")));

        return cliente;
    }

    @Override
    public void insert(Cliente cliente) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setLong(2, cliente.getCpf());
            stmt.setString(3, String.valueOf(cliente.getSexo()));
            stmt.setString(4, String.valueOf(cliente.getNaturalidade()));
            stmt.setString(5, cliente.getEndereco());
            stmt.setDate(6, Date.valueOf(cliente.getData_nascimento()));

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                cliente.setCodigo(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente.", e);
        } finally {
            close(conn, stmt, rs);
        }
    }

    @Override
    public Cliente getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Cliente cliente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = getClienteFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter cliente pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }
        return cliente;
    }

    @Override
    public List<Cliente> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Cliente> clientes = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                clientes.add(getClienteFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os clientes.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return clientes;
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
            throw new RuntimeException("Erro ao remover cliente.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Cliente t) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

            //setar os parâmetros
            stmt.setString(1, t.getNome());
            stmt.setLong(2, t.getCpf());
            stmt.setString(3, String.valueOf(t.getSexo()));
            stmt.setString(4, String.valueOf(t.getNaturalidade()));
            stmt.setDate(5, Date.valueOf(t.getData_nascimento()));
            stmt.setString(6, t.getEndereco());
            stmt.setInt(7, t.getCodigo());

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
                //myConn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar recursos.", e);
        }

    }

}