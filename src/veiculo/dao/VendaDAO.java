package veiculo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import veiculo.dao.Dao;
import veiculo.dao.DbConnection;
import veiculo.model.Venda;

public class VendaDAO implements Dao<Venda> {

    private static final String GET_BY_ID = "SELECT * FROM venda where codigo = ? ";
    private static final String GET_ALL = "SELECT * FROM venda order by codigo asc";
    private static final String INSERT = "Insert into venda(codigo, cod_veiculo , cod_funcionario , cod_cliente , preco , quantidade , data_compra) values(?,?,?,?,?,?,?)";
    private static final String UPDATE = "update venda set cod_veiculo = ?, cod_funcionario = ?, cod_cliente = ?, preco = ?, quantidade = ?, data_compra = ? where codigo = ?";
    private static final String DELETE = "delete from venda where codigo = ?";

    public VendaDAO() {
        try {
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela no banco.", e);
            // e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        String sqlCreate = "create table if not exists venda(" 
            + "codigo int not null primary key," 
            + "cod_veiculo          int," 
            + "cod_funcionario      int," 
            + "cod_cliente          int," 
            + "preco                float," 
            + "quantidade           int," 
            + "data_compra          date," 
            + "foreign key (cod_veiculo) references veiculo(codigo)," 
            + "foreign key (cod_funcionario) references funcionario(codigo)," 
            + "foreign key (cod_cliente) references cliente(codigo));";

        Connection conn = DbConnection.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

        close(conn, stmt, null);
    }

    @Override
    public void insert(Venda venda) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, venda.getCodigo());
            stmt.setInt(2, venda.getCod_veiculo());
            stmt.setInt(3, venda.getCod_funcionario());
            stmt.setInt(4, venda.getCod_cliente());
            stmt.setFloat(5, venda.getPreco());
            stmt.setInt(6, venda.getQuantidade());
            stmt.setDate(7, Date.valueOf(venda.getData_compra()));
            stmt.execute();
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                venda.setCodigo(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir venda.", e);
        } finally {
            close(conn, stmt, rs);
        }
    }

    private Venda getVendaFromRS(ResultSet res) throws SQLException {
        Venda venda = new Venda();

        venda.setCodigo(res.getInt("codigo"));
        venda.setCod_veiculo(res.getInt("cod_veiculo"));
        venda.setCod_funcionario(res.getInt("cod_funcionario"));
        venda.setCod_cliente(res.getInt("cod_cliente"));
        venda.setPreco(res.getFloat("preco"));
        venda.setQuantidade(res.getInt("quantidade"));
        venda.setData_compra(res.getDate("data_compra").toLocalDate());

        return venda;
    }

    @Override
    public Venda getByKey(int id) {
        Connection conn = DbConnection.getConnection();

        Venda venda = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                venda = getVendaFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter cliente pela chave.", e);
        } finally {
            close(conn, stmt, rs);
        }
        return venda;
    }

    @Override
    public List<Venda> getAll() {
        Connection conn = DbConnection.getConnection();

        List<Venda> vendas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(GET_ALL);

            while (rs.next()) {
                vendas.add(getVendaFromRS(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter todos as vendas.", e);
        } finally {
            close(conn, stmt, rs);
        }

        return vendas;
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
            throw new RuntimeException("Erro ao remover venda.", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    @Override
    public void update(Venda t) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(UPDATE);

            //setar os parâmetros
            stmt.setInt(1, t.getCodigo());
            stmt.setInt(2, t.getCod_veiculo());
            stmt.setInt(3, t.getCod_funcionario());
            stmt.setInt(4, t.getCod_cliente());
            stmt.setFloat(5, t.getPreco());
            stmt.setInt(6, t.getQuantidade());
            stmt.setDate(7, Date.valueOf(t.getData_compra()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda.", e);
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

    public List<Venda> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

