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
import veiculo.model.Cliente;
import veiculo.model.Funcionario;
import veiculo.model.Marca;
import veiculo.model.Veiculo;
import veiculo.model.Venda;

public class VendaDAO implements Dao<Venda> {

    private static final String GET_BY_ID = "SELECT * FROM venda where codigo = ? ";
//            + "JOIN cliente c on v.cod_cliente = c.codigo "
//            + "JOIN funcionario f on v.cod_funcionario = f.codigo "
//            + "JOIN veiculo vc on v.cod_veiculo = vc.codigo "
    private static final String GET_ALL = "SELECT * FROM venda order by codigo asc";
    private static final String INSERT = "Insert into venda(cod_veiculo , cod_funcionario , cod_cliente , preco , quantidade, data_compra) values(?,?,?,?,?,?)";
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
                + "codigo               integer,"
                + "cod_veiculo          integer,"
                + "cod_funcionario      integer,"
                + "cod_cliente          integer,"
                + "preco                float,"
                + "quantidade           integer,"
                + "data_compra          date,"
                + "primary key(codigo),"
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

    private Venda getVendaFromRS(ResultSet rs) throws SQLException {
        Venda venda = new Venda();

        venda.setCodigo(rs.getInt("codigo"));

        VeiculoDAO vDao = new VeiculoDAO();
        Veiculo veiculo = vDao.getByKey(rs.getInt("cod_veiculo"));
        venda.setVeiculo(veiculo);

        FuncionarioDAO fDao = new FuncionarioDAO();
        Funcionario funcionario = fDao.getByKey(rs.getInt("cod_funcionario"));
        venda.setFuncionario(funcionario);

        ClienteDAO cDao = new ClienteDAO();
        Cliente cliente = cDao.getByKey(rs.getInt("cod_cliente"));
        venda.setCliente(cliente);

        venda.setPreco(rs.getFloat("preco"));
        venda.setQuantidade(rs.getInt("quantidade"));
        venda.setData_compra(rs.getDate("data_compra").toLocalDate());

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
            stmt.setInt(1, t.getVeiculo().getCodigo());
            stmt.setInt(2, t.getFuncionario().getCodigo());
            stmt.setInt(3, t.getCliente().getCodigo());
            stmt.setFloat(4, t.getPreco());
            stmt.setInt(5, t.getQuantidade());
            stmt.setDate(6, Date.valueOf(t.getData_compra()));
            stmt.setInt(7, t.getCodigo());
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda.", e);
        } finally {
            DbConnection.close(conn, stmt, null);

        }
    }
}
