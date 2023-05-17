
package view;

import controller.VendaDAO;
import controller.ItensVendaDAO;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Produto;
import model.Venda;
import model.ItensVenda;

public class FrameRegistrarVendas extends javax.swing.JInternalFrame {

    private JDesktopPane panel;
    private Cliente cliente;
    private Produto produto;

    private List<ItensVenda> itensVendas;

    public FrameRegistrarVendas() {
        initComponents();
    }

    public FrameRegistrarVendas(JDesktopPane panel) {
        initComponents();
        this.panel = panel;
    }

    public void addCliente(Cliente cliente) {
        this.cliente = cliente;
        this.clienteSetText(this.cliente.getNome());
    }

    public void addProduto(Produto produto) {
        this.produto = produto;
        this.produtoSetText(this.produto.getNome());
    }

    private void clienteSetText(String texto) {
        this.textFieldCliente.setText(texto);
    }

    private void produtoSetText(String texto) {
        this.textFieldProduto.setText(texto);
    }

    private void listar() {
        itensVendas = new ItensVendaDAO().consultarItensVenda();
        DefaultTableModel dados = (DefaultTableModel) tableVendas.getModel();
        dados.setNumRows(0);
        for (ItensVenda itemVenda : itensVendas) {
            dados.addRow(new Object[]{
                new ItensVendaDAO().consultarNomeProduto(itemVenda.getProdutoId()),
                itemVenda.getQuantidade(),
                itemVenda.getValor(),
                itemVenda.getValorTotal()
            });
        }

    }

    private void inserir() {
        if (!textFieldCliente.getText().isBlank() && !textFieldProduto.getText().isBlank() && !textFieldQuantidade.getText().isBlank()) {
            LocalDate dataVenda = LocalDate.now();
            Venda venda = new Venda(cliente.getId(), Date.valueOf(dataVenda));
            if (new VendaDAO().incluirVenda(venda)) {
                System.out.println("Entrou 1 if");
                List<Venda> vendas = new VendaDAO().consultarVendas();
                venda.setId(vendas.get(vendas.size() - 1).getId());
                ItensVenda itemVenda = new ItensVenda(
                        produto.getId(),
                        venda.getId(),
                        Integer.valueOf(textFieldQuantidade.getText()),
                        produto.getValor()
                );
                if (new ItensVendaDAO().incluirItensVenda(itemVenda)) {
                    JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
                    listar();
                } else {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao cadastrar a venda!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {

            }
        }
    }

    private void mostrar(int i) {
        textFieldValorTotal.setText(itensVendas.get(i).getValorTotal().toString());
    }

    private void limparCampos() {
        textFieldQuantidade.setText("");
    }

    private void excluir() {
        if (new ItensVendaDAO().excluirItemVenda(itensVendas.get(tableVendas.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Venda excluida com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            listar();
        } else {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir a venda!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        labelRegistrarVendas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelCliente = new javax.swing.JLabel();
        labelProduto = new javax.swing.JLabel();
        textFieldProduto = new javax.swing.JTextField();
        buttonConsultarProduto = new javax.swing.JButton();
        labelQuantidade = new javax.swing.JLabel();
        textFieldQuantidade = new javax.swing.JTextField();
        buttonIncluir = new javax.swing.JButton();
        buttonConsultarCliente = new javax.swing.JButton();
        textFieldCliente = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVendas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        labelValorTotal = new javax.swing.JLabel();
        textFieldValorTotal = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        buttonExcluirItem = new javax.swing.JButton();
        buttonSalvarItem = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registrar Vendas - Projeto, TADS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(784, 539));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        labelRegistrarVendas.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelRegistrarVendas.setForeground(new java.awt.Color(255, 255, 255));
        labelRegistrarVendas.setText("Registrar Venda");
        jPanel1.add(labelRegistrarVendas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelCliente.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(labelCliente, gridBagConstraints);

        labelProduto.setText("Produto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(labelProduto, gridBagConstraints);

        textFieldProduto.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(textFieldProduto, gridBagConstraints);

        buttonConsultarProduto.setText("...");
        buttonConsultarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConsultarProdutoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(buttonConsultarProduto, gridBagConstraints);

        labelQuantidade.setText("QTDE:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(labelQuantidade, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(textFieldQuantidade, gridBagConstraints);

        buttonIncluir.setText("Incluir");
        buttonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(buttonIncluir, gridBagConstraints);

        buttonConsultarCliente.setText("...");
        buttonConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConsultarClienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(buttonConsultarCliente, gridBagConstraints);

        textFieldCliente.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(textFieldCliente, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        tableVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "QTDE", "Vlr. Unit.", "Vlr. Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableVendas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout2.setAlignOnBaseline(true);
        jPanel4.setLayout(flowLayout2);

        labelValorTotal.setText("Valor Total:");
        jPanel4.add(labelValorTotal);

        textFieldValorTotal.setText("                      ");
        textFieldValorTotal.setEnabled(false);
        jPanel4.add(textFieldValorTotal);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        buttonExcluirItem.setText("Excluir Item");
        buttonExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirItemActionPerformed(evt);
            }
        });
        jPanel5.add(buttonExcluirItem);

        buttonSalvarItem.setText("Salvar");
        jPanel5.add(buttonSalvarItem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConsultarClienteActionPerformed
        FrameCliente frameCliente = new FrameCliente(this, true);
        panel.add(frameCliente);
        frameCliente.setVisible(true);
    }//GEN-LAST:event_buttonConsultarClienteActionPerformed

    private void buttonConsultarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConsultarProdutoActionPerformed
        FrameProduto frameProduto = new FrameProduto(this, true);
        panel.add(frameProduto);
        frameProduto.setVisible(true);
    }//GEN-LAST:event_buttonConsultarProdutoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        listar();
    }//GEN-LAST:event_formInternalFrameOpened

    private void tableVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVendasMouseClicked
        if (tableVendas.getSelectedRow() != -1) {
            mostrar(tableVendas.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione alguma venda!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableVendasMouseClicked

    private void buttonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncluirActionPerformed
        inserir();
    }//GEN-LAST:event_buttonIncluirActionPerformed

    private void buttonExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirItemActionPerformed
        if (tableVendas.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir a venda?", "Mensagem de confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluir();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione alguma venda!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonConsultarCliente;
    private javax.swing.JButton buttonConsultarProduto;
    private javax.swing.JButton buttonExcluirItem;
    private javax.swing.JButton buttonIncluir;
    private javax.swing.JButton buttonSalvarItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelProduto;
    private javax.swing.JLabel labelQuantidade;
    private javax.swing.JLabel labelRegistrarVendas;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JTable tableVendas;
    private javax.swing.JTextField textFieldCliente;
    private javax.swing.JTextField textFieldProduto;
    private javax.swing.JTextField textFieldQuantidade;
    private javax.swing.JTextField textFieldValorTotal;
    // End of variables declaration//GEN-END:variables
}
