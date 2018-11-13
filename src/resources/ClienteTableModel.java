package resources;

import veiculo.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteTableModel extends GenericTableModel<Cliente> {

    private final String[] colunas = {"Codigo", "Nome", "CPF", "Sexo",
       "Naturalidade", "Endereço", "Data nascimento"};
    
    private List<Cliente> list;

    private ClienteTableModel() {
        list = new ArrayList<>();
    }

    public ClienteTableModel(List<Cliente> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Cliente entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Cliente entity) {
        return list.contains(entity);
    }

    @Override
    public Cliente getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Cliente entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Cliente> list() {
        return list;
    }

    @Override
    public void remove(Cliente entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Cliente> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Cliente entity) {
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
        Cliente cliente = list.get(linha);
        switch (coluna) {
            case 0:
                return cliente.getCodigo();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getCpf();
            case 3:
                return cliente.getSexo();
            case 4:
                return cliente.getNaturalidade();
            case 5:
                return cliente.getEndereco();
            case 6:
                return cliente.getData_nascimento();
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