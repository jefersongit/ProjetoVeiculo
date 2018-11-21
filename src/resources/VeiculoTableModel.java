/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.ArrayList;
import java.util.List;
import veiculo.model.Veiculo;

public class VeiculoTableModel extends GenericTableModel<Veiculo> {

    private final String[] colunas = {"Codigo", "Nome", "Modelo",
        "Cor", "Ano", "Marca"};

    private List<Veiculo> list;

    private VeiculoTableModel() {
        list = new ArrayList<>();
    }

    public VeiculoTableModel(List<Veiculo> list) {
        this();
        setData(list);
    }

    @Override
    public void add(Veiculo entity) {
        list.add(0, entity);
        super.fireTableDataChanged();
    }

    @Override
    public void clear() {
        this.list.clear();
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Veiculo entity) {
        return list.contains(entity);
    }

    @Override
    public Veiculo getValueAT(int row) {
        return list.get(row);
    }

    @Override
    public int indexOf(Veiculo entity) {
        return list.indexOf(entity);
    }

    @Override
    public List<Veiculo> list() {
        return list;
    }

    @Override
    public void remove(Veiculo entity) {
        list.remove(entity);
        super.fireTableDataChanged();
    }

    @Override
    public void setData(List<Veiculo> list) {
        this.list.clear();
        this.list.addAll(list);
        super.fireTableDataChanged();
    }

    @Override
    public void updateItem(int idx, Veiculo entity) {
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
        Veiculo veiculo = list.get(linha);
        switch (coluna) {
            case 0:
                return veiculo.getCodigo();
            case 1:
                return veiculo.getNome();
            case 2:
                return veiculo.getModelo();
            case 3:
                return veiculo.getCor();
            case 4:
                return veiculo.getAno();
            case 5:
                return veiculo.getMarca();
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
