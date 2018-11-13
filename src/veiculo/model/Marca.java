/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiculo.model;

/**
 *
 * @author gabriel.zanghelini
 */
public class Marca {
    private int codigo;
    private String nome;
    private int ano_criacao;

    public Marca(int codigo, String nome, int ano_criacao) {
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

    public int getAno_criacao() {
        return ano_criacao;
    }

    public void setAno_criacao(int ano_criacao) {
        this.ano_criacao = ano_criacao;
    }
}
