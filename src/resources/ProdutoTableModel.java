package resources;

import java.util.ArrayList;
import java.util.List;
import veiculo.model.Produto;

public class ProdutoTableModel extends GenericTableModel<Produto> {

    private final String[] colunas = {"Codigo", "Nome", "Modelo", 
        "Cor", "Ano", "Marca", "Preco"};
    
    private List<Produto> list;

    private ProdutoTableModel() {
        list = new ArrayList<>();
    }

    public ProdutoTableModel(List<Produto> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Produto entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Produto entity) {
        return list.contains(entity);
    }

    @Override
    public Produto getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Produto entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Produto> list() {
        return list;
    }

    @Override
    public void remove(Produto entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Produto> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Produto entity) {
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
        Produto produto = list.get(linha);
        switch (coluna) {
            case 0:
                return produto.getCodigo();
            case 1:
                return produto.getNome();
            case 2:
                return produto.getModelo();
            case 3:
                return produto.getCor();
            case 4:
                return produto.getAno();
            case 5:
                return produto.getMarca(); 
            case 6:
                return produto.getPreco();
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
