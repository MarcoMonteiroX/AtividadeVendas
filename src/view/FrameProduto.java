package view;

import controller.FornecedorDAO;
import controller.ProdutoDAO;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Fornecedor;
import model.Produto;
import util.Constantes;

public class FrameProduto extends javax.swing.JInternalFrame {

    private Constantes modo;
    private List<Produto> produtos;
    private List<Fornecedor> fornecedores;
    private Produto produto;
    private Fornecedor fornecedor;
    private JDesktopPane panel;
    private FrameRegistrarVendas registrarVendas;

    public FrameProduto() {
        initComponents();
    }

    public FrameProduto(JDesktopPane panel) {
        this.panel = panel;
        initComponents();
    }

    public FrameProduto(FrameRegistrarVendas registrarVendas, boolean botaoVisibilidade) {
        this.registrarVendas = registrarVendas;
        initComponents();
        buttonSelecionarProduto.setVisible(botaoVisibilidade);
    }

    private void listar() {
        produtos = new ProdutoDAO().consultarProdutos();
        DefaultTableModel dados = (DefaultTableModel) tableProdutos.getModel();
        dados.setNumRows(0);
        for (Produto produto : produtos) {
            dados.addRow(new Object[]{
                produto.getId(),
                produto.getNome()
            });
        }
    }

    private void listar(String nome) {
        produtos = new ProdutoDAO().consultarProdutos(nome);
        DefaultTableModel dados = (DefaultTableModel) tableProdutos.getModel();
        dados.setNumRows(0);
        for (Produto produto : produtos) {
            dados.addRow(new Object[]{
                produto.getId(),
                produto.getNome()
            });
        }
    }

    private void incluir() {
        if (!textFieldNome.getText().isBlank()) {
            Produto produto = new Produto(
                    fornecedor.getId(),
                    textFieldNome.getText().strip(),
                    Integer.parseInt(textFieldEstoque.getText()),
                    Double.parseDouble(textFieldValor.getText())
            );

            if (new ProdutoDAO().incluirProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                limparCampos();
                desabilitarCampos();
                desabilitarBotoes();
            } else {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao cadastrar o produto!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void alterar() {
        if (!textFieldNome.getText().isBlank()) {
            Produto produto = new Produto(
                    produtos.get(tableProdutos.getSelectedRow()).getId(),
                    produtos.get(tableProdutos.getSelectedRow()).getFornecedorId(),
                    textFieldNome.getText().strip(),
                    Integer.parseInt(textFieldEstoque.getText()),
                    Double.parseDouble(textFieldValor.getText()));

            if (new ProdutoDAO().alterarProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                limparCampos();
                desabilitarCampos();
                desabilitarBotoes();
            } else {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar o produto!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        if (new ProdutoDAO().excluirProduto(produtos.get(tableProdutos.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Produto excluido com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
            listar();
            limparCampos();
            desabilitarCampos();
            desabilitarBotoes();
        } else {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir o produto!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrar(int i) {
        textFieldNome.setText(produtos.get(i).getNome());
        textFieldFornecedor.setText(new ProdutoDAO().consultarNomeFornecedor(produtos.get(i).getFornecedorId()));
        textFieldEstoque.setText(produtos.get(i).getQuantidadeEstoque().toString());
        textFieldValor.setText(produtos.get(i).getValor().toString());
    }

    private void habilitarCampos() {
        textFieldNome.setEnabled(true);
        textFieldEstoque.setEnabled(true);
        textFieldValor.setEnabled(true);
    }

    private void desabilitarCampos() {
        textFieldNome.setEnabled(false);
        textFieldEstoque.setEnabled(false);
        textFieldValor.setEnabled(false);
    }

    private void limparCampos() {
        textFieldNome.setText("");
        textFieldFornecedor.setText("");
        textFieldEstoque.setText("");
        textFieldValor.setText("");
    }

    private void habilitarBotoes() {
        buttonNovo.setEnabled(false);
        buttonAlterar.setEnabled(false);
        buttonExcluir.setEnabled(false);
        buttonSelecionarFornecedor.setEnabled(true);
        buttonSalvar.setEnabled(true);
        buttonCancelar.setEnabled(true);
    }

    private void desabilitarBotoes() {
        buttonNovo.setEnabled(true);
        buttonAlterar.setEnabled(true);
        buttonExcluir.setEnabled(true);
        buttonSelecionarFornecedor.setEnabled(false);
        buttonSalvar.setEnabled(false);
        buttonCancelar.setEnabled(false);
    }

    public void addFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        this.fornecedorSetText(this.fornecedor.getNome());
    }

    private void fornecedorSetText(String texto) {
        this.textFieldFornecedor.setText(texto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        labelProduto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelFiltro = new javax.swing.JLabel();
        textFieldFiltro = new javax.swing.JTextField();
        buttonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        labelFornecedor = new javax.swing.JLabel();
        textFieldFornecedor = new javax.swing.JTextField();
        buttonSelecionarFornecedor = new javax.swing.JButton();
        labelEstoque = new javax.swing.JLabel();
        textFieldEstoque = new javax.swing.JTextField();
        labelValor = new javax.swing.JLabel();
        textFieldValor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        buttonSelecionarProduto = new javax.swing.JButton();
        buttonNovo = new javax.swing.JButton();
        buttonAlterar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        buttonSalvar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Produto - Projeto, TADS");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(784, 539));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
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

        labelProduto.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelProduto.setForeground(new java.awt.Color(255, 255, 255));
        labelProduto.setText("Produtos");
        jPanel1.add(labelProduto);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelFiltro.setText("Filtro p/ Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(labelFiltro, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(textFieldFiltro, gridBagConstraints);

        buttonPesquisar.setText("Pesquisar");
        buttonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(buttonPesquisar, gridBagConstraints);

        tableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProdutos);
        if (tableProdutos.getColumnModel().getColumnCount() > 0) {
            tableProdutos.getColumnModel().getColumn(0).setMinWidth(1);
            tableProdutos.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        labelNome.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelNome, gridBagConstraints);

        textFieldNome.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldNome, gridBagConstraints);

        labelFornecedor.setText("Fornecedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelFornecedor, gridBagConstraints);

        textFieldFornecedor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldFornecedor, gridBagConstraints);

        buttonSelecionarFornecedor.setText("...");
        buttonSelecionarFornecedor.setEnabled(false);
        buttonSelecionarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelecionarFornecedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(buttonSelecionarFornecedor, gridBagConstraints);

        labelEstoque.setText("Estoque:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelEstoque, gridBagConstraints);

        textFieldEstoque.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldEstoque, gridBagConstraints);

        labelValor.setText("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelValor, gridBagConstraints);

        textFieldValor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldValor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout2.setAlignOnBaseline(true);
        jPanel4.setLayout(flowLayout2);

        buttonSelecionarProduto.setText("Selecionar Produto");
        buttonSelecionarProduto.setVisible(false);
        buttonSelecionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelecionarProdutoActionPerformed(evt);
            }
        });
        jPanel4.add(buttonSelecionarProduto);

        buttonNovo.setText("Novo");
        buttonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovoActionPerformed(evt);
            }
        });
        jPanel4.add(buttonNovo);

        buttonAlterar.setText("Alterar");
        buttonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlterarActionPerformed(evt);
            }
        });
        jPanel4.add(buttonAlterar);

        buttonExcluir.setText("Excluir");
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(buttonExcluir);

        buttonSalvar.setText("Salvar");
        buttonSalvar.setEnabled(false);
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(buttonSalvar);

        buttonCancelar.setText("Cancelar");
        buttonCancelar.setEnabled(false);
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(buttonCancelar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        listar();
    }//GEN-LAST:event_formInternalFrameOpened

    private void buttonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPesquisarActionPerformed
        if (!textFieldFiltro.getText().isBlank()) {
            listar("%" + textFieldFiltro.getText() + "%");
        } else {
            listar();
        }
        textFieldFiltro.setText("");
    }//GEN-LAST:event_buttonPesquisarActionPerformed

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        limparCampos();
        habilitarCampos();
        habilitarBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonSelecionarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarFornecedorActionPerformed
        FrameFornecedor frameFornecedor = new FrameFornecedor(this, true);
        panel.add(frameFornecedor);
        frameFornecedor.setVisible(true);
    }//GEN-LAST:event_buttonSelecionarFornecedorActionPerformed

    private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarActionPerformed
        if (tableProdutos.getSelectedRow() != -1) {
            habilitarCampos();
            habilitarBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum produto!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonAlterarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if (tableProdutos.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o produto?", "Mensagem de confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluir();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum produto!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            incluir();
        } else if (modo == Constantes.EDIT_MODE) {
            alterar();
        }
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        limparCampos();
        desabilitarCampos();
        desabilitarBotoes();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonSelecionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarProdutoActionPerformed
        if (tableProdutos.getSelectedRow() != 1) {
            produto = produtos.get(tableProdutos.getSelectedRow());
            registrarVendas.addProduto(produto);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum produto!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSelecionarProdutoActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        fornecedores = new FornecedorDAO().consultarFornecedores();
    }//GEN-LAST:event_formInternalFrameActivated

    private void tableProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdutosMouseClicked
        if (tableProdutos.getSelectedRow() != -1) {
            mostrar(tableProdutos.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum produto!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableProdutosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAlterar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JButton buttonSelecionarFornecedor;
    private javax.swing.JButton buttonSelecionarProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEstoque;
    private javax.swing.JLabel labelFiltro;
    private javax.swing.JLabel labelFornecedor;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelProduto;
    private javax.swing.JLabel labelValor;
    private javax.swing.JTable tableProdutos;
    private javax.swing.JTextField textFieldEstoque;
    private javax.swing.JTextField textFieldFiltro;
    private javax.swing.JTextField textFieldFornecedor;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldValor;
    // End of variables declaration//GEN-END:variables
}
