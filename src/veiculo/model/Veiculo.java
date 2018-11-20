package veiculo.model;

import java.util.List;
import veiculo.dao.VeiculoDAO;

public class Veiculo {

    private int codigo;
    private String nome;
    private Marca marca;
    private String modelo;
    private String cor;
    private int ano;

    public Veiculo(int codigo, String nome, Marca marca, String modelo, String cor, int ano) {
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
    }
    
    public Veiculo() {
    }

    VeiculoDAO dao = new VeiculoDAO();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public VeiculoDAO getDao() {
        return dao;
    }

    public void setDao(VeiculoDAO dao) {
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
        final Veiculo other = (Veiculo) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    public boolean verificarExistenciaDeVeiculo(Veiculo a) {
        List<Veiculo> lista = dao.getAll();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
}

