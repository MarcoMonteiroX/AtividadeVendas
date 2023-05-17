package view;

import controller.ItensVendaDAO;
import controller.VendaDAO;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.ItensVenda;
import model.Venda;

public class FrameConsultarVendas extends javax.swing.JInternalFrame {

    private List<Venda> vendas;
    private List<ItensVenda> itensVendas;

    public FrameConsultarVendas() {
        initComponents();
    }

    private void listarVendas() {
        vendas = new VendaDAO().consultarVendas();
        DefaultTableModel dados = (DefaultTableModel) tableVendas.getModel();
        dados.setNumRows(0);
        for (Venda venda : vendas) {
            dados.addRow(new Object[]{
                venda.getId(),
                new VendaDAO().consultarNomeCliente(venda.getClienteId()),
                venda.getDataVenda()
            });
        }
    }

    private void listarItensVendas() {
        itensVendas = new ItensVendaDAO().consultarItensVenda();
        DefaultTableModel dados = (DefaultTableModel) tableItensVendas.getModel();
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

    private LocalDate formatarData(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private void listarEntreDatas(LocalDate dataInicial, LocalDate dataFinal) {
        vendas = new VendaDAO().consultarVendas(Date.valueOf(dataInicial), Date.valueOf(dataFinal));
        DefaultTableModel dados = (DefaultTableModel) tableVendas.getModel();
        dados.setNumRows(0);
        for (Venda venda : vendas) {
            dados.addRow(new Object[]{
                venda.getId(),
                new VendaDAO().consultarNomeCliente(venda.getClienteId()),
                venda.getDataVenda()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        labelConsultarVendas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelDataInicial = new javax.swing.JLabel();
        textFieldDataInicial = new javax.swing.JTextField();
        labelDataFinal = new javax.swing.JLabel();
        textFieldDataFinal = new javax.swing.JTextField();
        buttonPesquisar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVendas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        labelItensVenda = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableItensVendas = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Consultar Vendas - Projeto, TADS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.png"))); // NOI18N
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

        labelConsultarVendas.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelConsultarVendas.setForeground(new java.awt.Color(255, 255, 255));
        labelConsultarVendas.setText("Consultar Venda");
        jPanel1.add(labelConsultarVendas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelDataInicial.setText("Data Inicial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(labelDataInicial, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(textFieldDataInicial, gridBagConstraints);

        labelDataFinal.setText("Data Final:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(labelDataFinal, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(textFieldDataFinal, gridBagConstraints);

        buttonPesquisar.setText("Pesquisar");
        buttonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(buttonPesquisar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setMaximumSize(new java.awt.Dimension(462, 412));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMaximumSize(new java.awt.Dimension(Short.MAX_VALUE, 402));

        tableVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                " ID", "Cliente", "Data Venda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVendas.setMinimumSize(new java.awt.Dimension(16, 20));
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
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout2.setAlignOnBaseline(true);
        jPanel4.setLayout(flowLayout2);

        labelItensVenda.setText("Itens Venda");
        jPanel4.add(labelItensVenda);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel5.setMaximumSize(new java.awt.Dimension(462, 412));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMaximumSize(new java.awt.Dimension(Short.MAX_VALUE, 200));

        tableItensVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
        tableItensVendas.setMaximumSize(new java.awt.Dimension(225, 20));
        jScrollPane2.setViewportView(tableItensVendas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel6, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        listarVendas();
        listarItensVendas();
    }//GEN-LAST:event_formInternalFrameOpened

    private void buttonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPesquisarActionPerformed
        if (!textFieldDataInicial.getText().isBlank() && !textFieldDataFinal.getText().isBlank()) {
            listarEntreDatas(formatarData(textFieldDataInicial.getText()), formatarData(textFieldDataFinal.getText()));
        }
    }//GEN-LAST:event_buttonPesquisarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelConsultarVendas;
    private javax.swing.JLabel labelDataFinal;
    private javax.swing.JLabel labelDataInicial;
    private javax.swing.JLabel labelItensVenda;
    private javax.swing.JTable tableItensVendas;
    private javax.swing.JTable tableVendas;
    private javax.swing.JTextField textFieldDataFinal;
    private javax.swing.JTextField textFieldDataInicial;
    // End of variables declaration//GEN-END:variables
}
