package veiculo.ui;

import veiculo.dao.ClienteDAO;
import veiculo.dao.FuncionarioDAO;
import veiculo.dao.VendaDAO;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import veiculo.model.Cliente;
import veiculo.model.Funcionario;
import veiculo.model.Venda;
import resources.VendaTableModel;

public class JanelaVenda extends javax.swing.JFrame {

    /**
     * Creates new form JanelaVenda
     */
    public JanelaVenda() {
        initComponents();
        desabilitarComponentes();
        atualizarTabelaProduto();
        atualizarTabelaVenda();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btNovo = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProduto = new javax.swing.JTable();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaVenda = new javax.swing.JTable();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        txCodigo = new javax.swing.JTextField();
        cbFuncionario = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txQuantidade = new javax.swing.JTextField();
        txData = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        btNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Imagens/novo.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        jPanel1.add(btNovo);

        btCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Imagens/cancelar.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.setEnabled(false);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btCancelar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        btSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Imagens/Salvar.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setEnabled(false);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        jPanel2.add(btSalvar);

        btSair.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Imagens/Voltar.png"))); // NOI18N
        btSair.setText("Voltar");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        jPanel2.add(btSair);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        tabelaProduto.setModel(carregarTabelaProduto());
        tabelaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaProduto);

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        tabelaVenda.setModel(carregarTabelaVenda());
        jScrollPane2.setViewportView(tabelaVenda);

        jLayeredPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nova Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setText("Funcionário:");

        CarregaFuncionario();

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Total:");

        jLabel4.setText("Quantidade:");

        txQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txQuantidadeFocusLost(evt);
            }
        });

        jLabel1.setText("Cliente:");

        CarregaCliente();

        jLabel5.setText("Data:");

        jLabel3.setText("Código:");

        txTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txTotal.setEnabled(false);

        jLayeredPane3.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txCodigo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(cbFuncionario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txQuantidade, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txData, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(cbCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txTotal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(23, 23, 23)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbFuncionario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txData, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel6)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addComponent(jLayeredPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoMouseClicked

    }//GEN-LAST:event_tabelaProdutoMouseClicked

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        desabilitarComponentes();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        habilitarComponentes();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
            Venda venda = getVenda();

            if (!venda.verificarExistenciaDeVenda(venda)) {
//                daoVenda.save(getVenda());
            } else {
                daoVenda.update(getVenda());
            }

            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso");

            atualizarTabelaProduto();
            atualizarTabelaVenda();
            limparCampos();
            desabilitarComponentes();       
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void txQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txQuantidadeFocusLost
//        double preco = 0;
//
//        Produto produto = ptm.getValueAT(tabelaProduto.getSelectedRow());
//        preco = produto.getPreco();
//
//        int quantidade = Integer.parseInt(txQuantidade.getText());
//        double valorFinal = preco * quantidade;
//        txTotal.setText(String.valueOf(valorFinal));

    }//GEN-LAST:event_txQuantidadeFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbFuncionario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaProduto;
    private javax.swing.JTable tabelaVenda;
    private javax.swing.JTextField txCodigo;
    private javax.swing.JTextField txData;
    private javax.swing.JTextField txQuantidade;
    private javax.swing.JTextField txTotal;
    // End of variables declaration//GEN-END:variables

    VendaDAO daoVenda = new VendaDAO();
    ClienteDAO daoCliente = new ClienteDAO();
    FuncionarioDAO daoFuncionario = new FuncionarioDAO();
//    ProdutoDAO daoProduto = new ProdutoDAO();
//
//    private ProdutoTableModel ptm;
//    private VendaTableModel vtm;

    private TableModel carregarTabelaVenda() {
//        List<Venda> listaVenda = daoVenda.list();
//        vtm = new VendaTableModel(listaVenda);
//        return vtm;
        return null;
    }

    private void atualizarTabelaVenda() {
        tabelaVenda.setModel(carregarTabelaVenda());
    }

    private TableModel carregarTabelaProduto() {
//        List<Produto> listaProduto = daoProduto.list();
//        ptm = new ProdutoTableModel(listaProduto);
//        return ptm;
        return null;
    }

    private void atualizarTabelaProduto() {
        tabelaProduto.setModel(carregarTabelaProduto());
    }

    private void habilitarComponentes() {
        limparCampos();
        cbCliente.setEnabled(true);
        cbFuncionario.setEnabled(true);
        txQuantidade.setEnabled(true);
        txCodigo.setEnabled(true);
        txData.setEnabled(true);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
    }

    private void desabilitarComponentes() {
        limparCampos();
        cbCliente.setEnabled(false);
        cbCliente.setSelectedIndex(-1);
        cbFuncionario.setEnabled(false);
        cbFuncionario.setSelectedIndex(-1);
        txCodigo.setEnabled(false);
        txData.setEnabled(false);
        txQuantidade.setEnabled(false);
        btSalvar.setEnabled(false);
        btCancelar.setEnabled(false);
    }

    private void limparCampos() {
        cbCliente.removeAll();
        cbFuncionario.removeAll();
        txQuantidade.setText("");
        txData.setText("");
        txCodigo.setText("");
    }

    private Venda getVenda() {
        ClienteDAO clienteDao = new ClienteDAO();
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario();
//        Produto produto = new Produto();
        Cliente cliente = new Cliente();

//        int cod_produto;
//        Produto p = ptm.getValueAT(tabelaProduto.getSelectedRow());
//        cod_produto = p.getCodigo();

        List<Cliente> listaCliente = clienteDao.getAll();
        List<Funcionario> listaFuncionario = funcionarioDao.getAll();

        for (int i = 0; i < listaCliente.size(); i++) {
            if (cbCliente.getSelectedItem().equals(listaCliente.get(i).toString())) {
                cliente.setCodigo(listaCliente.get(i).getCodigo());
            }
        }

        for (int i = 0; i < listaFuncionario.size(); i++) {
            if (cbFuncionario.getSelectedItem().equals(listaFuncionario.get(i).toString())) {
                funcionario.setCodigo(listaFuncionario.get(i).getCodigo());
            }
        }

        //Cliente cliente = (Cliente) cbCliente.getSelectedItem();
        //Funcionario funcionario = (Funcionario) cbFuncionario.getSelectedItem();
        //Produto produto = ad.getValueAT(tabelaProduto.getSelectedRow());
        int quantidade = Integer.parseInt(txQuantidade.getText());
        int codigo = Integer.parseInt(txCodigo.getText());
//        produto.setCodigo(cod_produto);
        double total = Double.parseDouble(txTotal.getText());
        LocalDate data_compra = LocalDate.parse(txData.getText());

//        return new Venda(codigo, produto, funcionario, cliente, total, quantidade, data_compra);
return null;
    }

    private void CarregaCliente() {

        List<Cliente> listaCliente = daoCliente.getAll();

        for (int i = 0; i < listaCliente.size(); i++) {
            cbCliente.addItem(listaCliente.get(i).toString());

        }
    }

    private void CarregaFuncionario() {

        List<Funcionario> listaFuncionario = daoFuncionario.getAll();

        for (int i = 0; i < listaFuncionario.size(); i++) {
            cbFuncionario.addItem(listaFuncionario.get(i).toString());
        }
    }
}
