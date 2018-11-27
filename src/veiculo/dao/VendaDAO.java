package veiculo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import veiculo.dao.DbConnection;
import veiculo.model.Venda;

public class VendaDAO implements Dao<Venda> {

    private static final String GET_BY_ID = "SELECT * FROM venda where codigo = ? ";
    private static final String GET_ALL = "SELECT * FROM venda order by codigo asc";
    private static final String INSERT = "Insert into venda(cod_veiculo , cod_funcionario , cod_cliente , preco , quantidade , data_compra) values(?,?,?,?,?,?)";
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

        DbConnection.close(conn, stmt, null);
    }

    @Override
    public void insert(Venda venda) {
        Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, venda.getVeiculo().getCodigo());
            stmt.setInt(2, venda.getFuncionario().getCodigo());
            stmt.setInt(3, venda.getCliente().getCodigo());
            stmt.setFloat(4, venda.getPreco());
            stmt.setInt(5, venda.getQuantidade());
            stmt.setDate(6, Date.valueOf(venda.getData_compra()));
            
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                venda.setCodigo(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir venda.", e);
        } finally {
            DbConnection.close(conn, stmt, rs);
        }
    }

    private Venda getVendaFromRS(ResultSet res) throws SQLException {
        Venda venda = new Venda();

        venda.setCodigo(res.getInt("codigo"));
//        venda.setVeiculo(res.getInt("cod_veiculo"));
//        venda.setFuncionario(res.getInt("cod_funcionario"));
//        venda.setCliente(res.getInt("cod_cliente"));
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
            DbConnection.close(conn, stmt, rs);
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
            DbConnection.close(conn, stmt, rs);
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
            DbConnection.close(conn, stmt, null);
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
            stmt.setInt(2, t.getVeiculo().getCodigo());
            stmt.setInt(3, t.getFuncionario().getCodigo());
            stmt.setInt(4, t.getCliente().getCodigo());
            stmt.setFloat(5, t.getPreco());
            stmt.setInt(6, t.getQuantidade());
            stmt.setDate(7, Date.valueOf(t.getData_compra()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda.", e);
        } finally {
            DbConnection.close(conn, stmt, null);

        }
    }
}

