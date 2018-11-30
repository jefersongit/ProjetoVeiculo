/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiculo.model;

import java.time.LocalDate;
import java.util.List;
import veiculo.dao.MarcaDAO;

/**
 *
 * @author gabriel.zanghelini
 */
public class Marca {

    private int codigo;
    private String nome;
    private LocalDate ano_criacao;

    public Marca(int codigo, String nome, LocalDate ano_criacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.ano_criacao = ano_criacao;
    }

    public Marca() {
    }

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

    public LocalDate getAno_criacao() {
        return ano_criacao;
    }

    public void setAno_criacao(LocalDate ano_criacao) {
        this.ano_criacao = ano_criacao;
    }

    public String toString() {
        return nome;
    }

    public boolean verificarExistenciaDeMarca(Marca a) {
        MarcaDAO dao = new MarcaDAO();
        List<Marca> lista = dao.getAll();

        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }

}
