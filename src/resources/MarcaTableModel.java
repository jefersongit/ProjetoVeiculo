package resources;

import veiculo.model.Marca;
import java.util.ArrayList;
import java.util.List;

public class MarcaTableModel extends GenericTableModel<Marca> {

    private final String[] colunas = {"Codigo", "Nome", "Ano de Criação"};
    
    private List<Marca> list;

    private MarcaTableModel() {
        list = new ArrayList<>();
    }

    public MarcaTableModel(List<Marca> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Marca entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Marca entity) {
        return list.contains(entity);
    }

    @Override
    public Marca getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Marca entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Marca> list() {
        return list;
    }

    @Override
    public void remove(Marca entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Marca> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Marca entity) {
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
        Marca marca = list.get(linha);
        switch (coluna) {
            case 0:
                return marca.getCodigo();
            case 1:
                return marca.getNome();
            case 2:
                return marca.getAno_criacao();
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