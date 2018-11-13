package veiculo.model;

import veiculo.dao.VendaDAO;
import java.time.LocalDate;
import java.util.List;

public class Venda {
    
    private int codigo;
    private int cod_veiculo;
    private int cod_funcionario;
    private int cod_cliente;
    private float preco;
    private int quantidade;
    private LocalDate data_compra;

    public Venda(int codigo, int cod_veiculo, int cod_funcionario, int cod_cliente, float preco, int quantidade, LocalDate data_compra) {
        this.codigo = codigo;
        this.cod_veiculo = cod_veiculo;
        this.cod_funcionario  = cod_funcionario;
        this.cod_cliente  = cod_cliente;
        this.preco  = preco;
        this.quantidade  = quantidade;
        this.data_compra  = data_compra;
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

    public int getCod_veiculo() {
        return cod_veiculo;
    }

    public void setCod_veiculo(int cod_veiculo) {
        this.cod_veiculo = cod_veiculo;
    }

    public int getCod_funcionario() {
        return cod_funcionario;
    }

    public void setCod_funcionario(int cod_funcionario) {
        this.cod_funcionario = cod_funcionario;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
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
        List<Venda> lista = dao.list();
        
        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
}
