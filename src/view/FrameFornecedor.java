package view;

import controller.FornecedorDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Fornecedor;
import util.Constantes;

public class FrameFornecedor extends javax.swing.JInternalFrame {

    private Constantes modo;
    private List<Fornecedor> fornecedores;
    private Fornecedor fornecedor;
    private FrameProduto produto;

    public FrameFornecedor() {
        initComponents();
    }

    public FrameFornecedor(FrameProduto produto, boolean botaoVisivel) {
        this.produto = produto;
        initComponents();
        buttonSelecionarFornecedor.setVisible(botaoVisivel);
    }

    private void listar() {
        fornecedores = new FornecedorDAO().consultarFornecedores();
        DefaultTableModel dados = (DefaultTableModel) tableFornecedores.getModel();
        dados.setNumRows(0);

        for (Fornecedor fornecedor : fornecedores) {
            dados.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome()
            });
        }
    }

    private void listar(String nome) {
        fornecedores = new FornecedorDAO().consultarFornecedores(nome);
        DefaultTableModel dados = (DefaultTableModel) tableFornecedores.getModel();
        dados.setNumRows(0);

        for (Fornecedor fornecedor : fornecedores) {
            dados.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome()
            });
        }
    }

    private void incluir() {
        if (!textFieldNome.getText().isBlank()) {
            Fornecedor fornecedor = new Fornecedor(
                    textFieldNome.getText().strip(),
                    textFieldEmail.getText().strip(),
                    textFieldEndereco.getText().strip(),
                    textFieldBairro.getText().strip(),
                    textFieldCidade.getText().strip(),
                    comboBoxUF.getSelectedItem().toString().strip(),
                    ((String) textFieldCEP.getValue()).replace(".", "").replace("-", ""),
                    (String) textFieldTelefone.getValue()
            );

            if (new FornecedorDAO().incluirFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                limparCampos();
                desabilitarCampos();
                desabilitarBotoes();
            } else {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao cadastrar o fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Informe o nome do fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterar() {
        if (!textFieldNome.getText().isBlank()) {
            Fornecedor fornecedor = new Fornecedor(
                    fornecedores.get(tableFornecedores.getSelectedRow()).getId(),
                    textFieldNome.getText().strip(),
                    textFieldEmail.getText().strip(),
                    textFieldEndereco.getText().strip(),
                    textFieldBairro.getText().strip(),
                    textFieldCidade.getText().strip(),
                    comboBoxUF.getSelectedItem().toString().strip(),
                    ((String) textFieldCEP.getValue()).replace(".", "").replace("-", ""),
                    (String) textFieldTelefone.getValue()
            );

            if (new FornecedorDAO().alterarFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Fornecedor alterado com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                limparCampos();
                desabilitarCampos();
                desabilitarBotoes();
            } else {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar o fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Informe o nome do fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        if (new FornecedorDAO().excluirFornecedor(fornecedores.get(tableFornecedores.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Fornecedor excluido com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
            listar();
            limparCampos();
            desabilitarCampos();
            desabilitarBotoes();
        } else {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir o fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrar(int i) {
        textFieldNome.setText(fornecedores.get(i).getNome());
        textFieldEmail.setText(fornecedores.get(i).getEmail());
        textFieldEndereco.setText(fornecedores.get(i).getEndereco());
        textFieldBairro.setText(fornecedores.get(i).getBairro());
        textFieldCidade.setText(fornecedores.get(i).getCidade());
        comboBoxUF.setSelectedItem(fornecedores.get(i).getUf());
        textFieldCEP.setText(fornecedores.get(i).getCep());
        textFieldTelefone.setText(fornecedores.get(i).getTelefone());
    }

    private void habilitarCampos() {
        textFieldNome.setEnabled(true);
        textFieldEmail.setEnabled(true);
        textFieldEndereco.setEnabled(true);
        textFieldBairro.setEnabled(true);
        textFieldCidade.setEnabled(true);
        comboBoxUF.setEnabled(true);
        textFieldCEP.setEnabled(true);
        textFieldTelefone.setEnabled(true);
    }

    private void desabilitarCampos() {
        textFieldNome.setEnabled(false);
        textFieldEmail.setEnabled(false);
        textFieldEndereco.setEnabled(false);
        textFieldBairro.setEnabled(false);
        textFieldCidade.setEnabled(false);
        comboBoxUF.setEnabled(false);
        textFieldCEP.setEnabled(false);
        textFieldTelefone.setEnabled(false);
    }

    private void limparCampos() {
        textFieldNome.setText("");
        textFieldEmail.setText("");
        textFieldEndereco.setText("");
        textFieldBairro.setText("");
        textFieldCidade.setText("");
        comboBoxUF.setSelectedIndex(0);
        textFieldCEP.setText("");
        textFieldTelefone.setText("");
    }

    private void habilitarBotoes() {
        buttonNovo.setEnabled(false);
        buttonAlterar.setEnabled(false);
        buttonExcluir.setEnabled(false);
        buttonSalvar.setEnabled(true);
        buttonCancelar.setEnabled(true);
    }

    private void desabilitarBotoes() {
        buttonNovo.setEnabled(true);
        buttonAlterar.setEnabled(true);
        buttonExcluir.setEnabled(true);
        buttonSalvar.setEnabled(false);
        buttonCancelar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        labelFornecedor = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelFiltro = new javax.swing.JLabel();
        textFieldFiltro = new javax.swing.JTextField();
        buttonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableFornecedores = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JTextField();
        labelEndereco = new javax.swing.JLabel();
        textFieldEndereco = new javax.swing.JTextField();
        labelBairro = new javax.swing.JLabel();
        textFieldBairro = new javax.swing.JTextField();
        labelCidade = new javax.swing.JLabel();
        textFieldCidade = new javax.swing.JTextField();
        labelUF = new javax.swing.JLabel();
        comboBoxUF = new javax.swing.JComboBox<>();
        labelCEP = new javax.swing.JLabel();
        textFieldCEP = new javax.swing.JFormattedTextField();
        labelTelefone = new javax.swing.JLabel();
        textFieldTelefone = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        buttonSelecionarFornecedor = new javax.swing.JButton();
        buttonNovo = new javax.swing.JButton();
        buttonAlterar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        buttonSalvar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Fornecedor - Projeto, TADS");
        setToolTipText("");
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

        labelFornecedor.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelFornecedor.setForeground(new java.awt.Color(255, 255, 255));
        labelFornecedor.setText("Fornecedor");
        jPanel1.add(labelFornecedor);

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

        tableFornecedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tableFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableFornecedores);
        if (tableFornecedores.getColumnModel().getColumnCount() > 0) {
            tableFornecedores.getColumnModel().getColumn(0).setMinWidth(1);
            tableFornecedores.getColumnModel().getColumn(0).setMaxWidth(200);
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

        labelEmail.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelEmail, gridBagConstraints);

        textFieldEmail.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldEmail, gridBagConstraints);

        labelEndereco.setText("Endereço:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelEndereco, gridBagConstraints);

        textFieldEndereco.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldEndereco, gridBagConstraints);

        labelBairro.setText("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelBairro, gridBagConstraints);

        textFieldBairro.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldBairro, gridBagConstraints);

        labelCidade.setText("Cidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelCidade, gridBagConstraints);

        textFieldCidade.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldCidade, gridBagConstraints);

        labelUF.setText("UF:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelUF, gridBagConstraints);

        comboBoxUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MT", "RS", "MG" }));
        comboBoxUF.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(comboBoxUF, gridBagConstraints);

        labelCEP.setText("CEP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelCEP, gridBagConstraints);

        try {
            textFieldCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textFieldCEP.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldCEP, gridBagConstraints);

        labelTelefone.setText("Telefone:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelTelefone, gridBagConstraints);

        try {
            textFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textFieldTelefone.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldTelefone, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel3, gridBagConstraints);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout2.setAlignOnBaseline(true);
        jPanel4.setLayout(flowLayout2);

        buttonSelecionarFornecedor.setText("Selecionar Fornecedor");
        buttonSelecionarFornecedor.setVisible(false);
        buttonSelecionarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelecionarFornecedorActionPerformed(evt);
            }
        });
        jPanel4.add(buttonSelecionarFornecedor);

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

    private void tableFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFornecedoresMouseClicked
        if (tableFornecedores.getSelectedRow() != -1) {
            mostrar(tableFornecedores.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableFornecedoresMouseClicked

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        limparCampos();
        habilitarCampos();
        habilitarBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarActionPerformed
        if (tableFornecedores.getSelectedRow() != -1) {
            habilitarCampos();
            habilitarBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonAlterarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if (tableFornecedores.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o fornecedor?", "Mensagem de confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluir();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
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

    private void buttonSelecionarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarFornecedorActionPerformed
        if (tableFornecedores.getSelectedRow() != -1) {
            fornecedor = fornecedores.get(tableFornecedores.getSelectedRow());
            produto.addFornecedor(fornecedor);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum fornecedor!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSelecionarFornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAlterar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JButton buttonSelecionarFornecedor;
    private javax.swing.JComboBox<String> comboBoxUF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelFiltro;
    private javax.swing.JLabel labelFornecedor;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelUF;
    private javax.swing.JTable tableFornecedores;
    private javax.swing.JTextField textFieldBairro;
    private javax.swing.JFormattedTextField textFieldCEP;
    private javax.swing.JTextField textFieldCidade;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldEndereco;
    private javax.swing.JTextField textFieldFiltro;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JFormattedTextField textFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
