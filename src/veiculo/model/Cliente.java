package veiculo.model;

import veiculo.dao.ClienteDAO;
import java.time.LocalDate;
import java.util.List;

public class Cliente {
    
    private int codigo;
    private String nome;
    private long cpf;
    private Sexo sexo;
    private Naturalidade naturalidade;
    private String endereco;
    private LocalDate data_nascimento;

    public Cliente(int codigo, String nome, long cpf, Sexo sexo, Naturalidade naturalidade, String endereco, LocalDate data_nascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.naturalidade = naturalidade;
        this.endereco = endereco;
        this.data_nascimento = data_nascimento;
    }

    public Cliente() {
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

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Naturalidade getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(Naturalidade naturalidade) {
        this.naturalidade = naturalidade;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String toString() {
        return nome;
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
        final Cliente other = (Cliente) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    
    public boolean verificarExistenciaDeCliente(Cliente a) {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.getAll();
        
        for (int i = 0; i < lista.size(); i++) {
            if (a.equals(lista.get(i))) {
                return true;
            }
        }
        return false;
    }
}
