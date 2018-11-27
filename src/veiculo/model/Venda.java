package veiculo.model;

import veiculo.dao.VendaDAO;
import java.time.LocalDate;
import java.util.List;

public class Venda {

    private int codigo;
    private Veiculo veiculo;
    private Funcionario funcionario;
    private Cliente cliente;
    private float preco;
    private int quantidade;
    private LocalDate data_compra;

    public Venda(int codigo, Veiculo veiculo, Funcionario funcionario, Cliente cliente, float preco, LocalDate data_compra) {
        this.codigo = codigo;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.preco = preco;
        this.data_compra = data_compra;
    }

    public Venda() {
    }

    VendaDAO dao = new VendaDAO();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData_compra() {
        return data_compra;
    }

    public void setData_compra(LocalDate data_compra) {
        this.data_compra = data_compra;
    }

    public VendaDAO getDao() {
        return dao;
    }

    public void setDao(VendaDAO dao) {
        this.dao = dao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    public boolean verificarExistenciaDeVenda(Venda a) {
        List<Venda> lista = dao.getAll();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
}
