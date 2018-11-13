package resources;

import veiculo.model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioTableModel extends GenericTableModel<Funcionario> {

    private final String[] colunas = {"Codigo", "Nome", "CPF", "Salario", 
        "Matricula", "Funcao", "data_nascimento"};
    
    private List<Funcionario> list;

    private FuncionarioTableModel() {
        list = new ArrayList<>();
    }

    public FuncionarioTableModel(List<Funcionario> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Funcionario entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Funcionario entity) {
        return list.contains(entity);
    }

    @Override
    public Funcionario getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Funcionario entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Funcionario> list() {
        return list;
    }

    @Override
    public void remove(Funcionario entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Funcionario> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Funcionario entity) {
        list.set(idx, entity);
        super.fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Funcionario funcionario = list.get(linha);
        switch (coluna) {
            case 0:
                return funcionario.getCodigo();
            case 1:
                return funcionario.getNome();
            case 2:
                return funcionario.getCpf();
            case 3:
                return funcionario.getSalario();
            case 4:
                return funcionario.getMatricula();
            case 5:
                return funcionario.getFuncao();
            case 6:
                return funcionario.getData_nascimento();
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return colunas[col];
    }

    public String[] getColumnNames() {
        return colunas;
    }
}
    
    
    

