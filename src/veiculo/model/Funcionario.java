package veiculo.model;

import veiculo.dao.FuncionarioDAO;
import java.time.LocalDate;
import java.util.List;

public class Funcionario {
    
    private int codigo;
    private String nome;
    private long cpf;
    private double salario;
    private long matricula;
    private String funcao;
    private LocalDate data_nascimento;

    public Funcionario(int codigo, String nome, long cpf, double salario, long matricula, String funcao, LocalDate data_nascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.matricula = matricula;
        this.funcao = funcao;
        this.data_nascimento = data_nascimento;
    }
    
    public Funcionario(){
    }

    FuncionarioDAO dao = new FuncionarioDAO();
    
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

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
        
    public boolean verificarExistenciaDeFuncionario(Funcionario a) {
        List<Funcionario> lista = dao.getAll();
        
        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    
    }
    
}

