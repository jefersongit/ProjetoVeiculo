package veiculo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import veiculo.model.Cliente;
import veiculo.model.Funcionario;

public class FuncionarioDAO implements Dao<Funcionario> {

    private static final String GET_BY_ID = "SELECT * FROM funcionario where codigo = ? ";
    private static final String GET_ALL = "SELECT * FROM funcionario order by codigo asc";
    private static final String INSERT = "Insert into funcionario(nome, cpf, salario, matricula, funcao, data_nascimento) values(?,?,?,?,?,?)";
    private static final String UPDATE = "update funcionario set nome = ?, cpf = ?, salario = ?, matricula = ?, funcao = ?, data_nascimento = ? where codigo = ?";
    private static final String DELETE = "delete from funcionario where codigo = ?";

    public FuncionarioDAO() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
            // e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "create table if not exists funcionario("
                + "codigo           integer,"
                + "nome             varchar2(50),"
                + "cpf              long,"
                + "salario          float,"
                + "matricula        varchar2(50),"
                + "funcao           varchar2(50),"
                + "data_nascimento  date,"
                + "primary key(codigo));";

        Connection conn = DbConnection.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

        DbConnection.close(conn, stmt, null);
    }

    @Override
    public void insert(Funcionario funcionario) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getNome());
            stmt.setLong(2, funcionario.getCpf());
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setLong(4, funcionario.getMatricula());
            stmt.setString(5, funcionario.getFuncao());
            stmt.setDate(6, Date.valueOf(funcionario.getData_nascimento()));
            
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                funcionario.setCodigo(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir funcionario.", e);
        } finally {
            DbConnection.close(conn, stmt, rs);
        }
    }

    private Funcionario getFuncionarioFromRS(ResultSet res) throws SQLException {
        Funcionario funcionario = new Funcionario();

        funcionario.setCodigo(res.getInt("codigo"));
        funcionario.setNome(res.getString("nome"));
        funcionario.setCpf(res.getInt("cpf"));
        funcionario.setSalario(res.getDouble("salario"));
        funcionario.setMatricula(res.getLong("matricula"));
        funcionario.setFuncao(res.getString("funcao"));
        funcionario.setData_nascimento(res.getDate("data_nascimento").toLocalDate());

        return funcionario;
    }

    @Override
    public Funcionario getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Funcionario funcionario = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = getFuncionarioFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter cliente pela chave.", e);
        } finally {
            DbConnection.close(conn, stmt, rs);
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Funcionario> funcionarios = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                funcionarios.add(getFuncionarioFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos os funcionarios.", e);
        } finally {
            DbConnection.close(conn, stmt, rs);
        }

        return funcionarios;
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
            throw new RuntimeException("Erro ao remover funcionario.", e);
        } finally {
            DbConnection.close(conn, stmt, null);
        }
    }

    @Override
    public void update(Funcionario t) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

            //setar os parâmetros
            stmt.setString(1, t.getNome());
            stmt.setLong(2, t.getCpf());
            stmt.setDouble(3, t.getSalario());
            stmt.setLong(4, t.getMatricula());
            stmt.setString(5, t.getFuncao());
            stmt.setDate(6, Date.valueOf(t.getData_nascimento()));
            stmt.setInt(7, t.getCodigo());
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionario.", e);
        } finally {
            DbConnection.close(conn, stmt, null);

        }
    }
}
