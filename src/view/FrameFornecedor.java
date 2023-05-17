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
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        labelFornecedor1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        labelFiltro1 = new javax.swing.JLabel();
        textFieldFiltro1 = new javax.swing.JTextField();
        buttonPesquisar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFornecedores1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        labelNome1 = new javax.swing.JLabel();
        textFieldNome1 = new javax.swing.JTextField();
        labelEmail1 = new javax.swing.JLabel();
        textFieldEmail1 = new javax.swing.JTextField();
        labelEndereco1 = new javax.swing.JLabel();
        textFieldEndereco1 = new javax.swing.JTextField();
        labelBairro1 = new javax.swing.JLabel();
        textFieldBairro1 = new javax.swing.JTextField();
        labelCidade1 = new javax.swing.JLabel();
        textFieldCidade1 = new javax.swing.JTextField();
        labelUF1 = new javax.swing.JLabel();
        comboBoxUF1 = new javax.swing.JComboBox<>();
        labelCEP1 = new javax.swing.JLabel();
        textFieldCEP1 = new javax.swing.JFormattedTextField();
        labelTelefone1 = new javax.swing.JLabel();
        textFieldTelefone1 = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        buttonSelecionarFornecedor1 = new javax.swing.JButton();
        buttonNovo1 = new javax.swing.JButton();
        buttonAlterar1 = new javax.swing.JButton();
        buttonExcluir1 = new javax.swing.JButton();
        buttonSalvar1 = new javax.swing.JButton();
        buttonCancelar1 = new javax.swing.JButton();

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

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setTitle("Fornecedor - Projeto, TADS");
        jInternalFrame1.setToolTipText("");
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(784, 539));
        jInternalFrame1.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
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
                jInternalFrame1formInternalFrameOpened(evt);
            }
        });
        jInternalFrame1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel5.setBackground(java.awt.SystemColor.activeCaption);
        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout3.setAlignOnBaseline(true);
        jPanel5.setLayout(flowLayout3);

        labelFornecedor1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelFornecedor1.setForeground(new java.awt.Color(255, 255, 255));
        labelFornecedor1.setText("Fornecedor");
        jPanel5.add(labelFornecedor1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jInternalFrame1.getContentPane().add(jPanel5, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        labelFiltro1.setText("Filtro p/ Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(labelFiltro1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(textFieldFiltro1, gridBagConstraints);

        buttonPesquisar1.setText("Pesquisar");
        buttonPesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPesquisar1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(buttonPesquisar1, gridBagConstraints);

        tableFornecedores1.setModel(new javax.swing.table.DefaultTableModel(
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
        tableFornecedores1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFornecedores1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableFornecedores1);
        if (tableFornecedores1.getColumnModel().getColumnCount() > 0) {
            tableFornecedores1.getColumnModel().getColumn(0).setMinWidth(1);
            tableFornecedores1.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jInternalFrame1.getContentPane().add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        labelNome1.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(labelNome1, gridBagConstraints);

        textFieldNome1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(textFieldNome1, gridBagConstraints);

        labelEmail1.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(labelEmail1, gridBagConstraints);

        textFieldEmail1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(textFieldEmail1, gridBagConstraints);

        labelEndereco1.setText("Endereço:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(labelEndereco1, gridBagConstraints);

        textFieldEndereco1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(textFieldEndereco1, gridBagConstraints);

        labelBairro1.setText("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(labelBairro1, gridBagConstraints);

        textFieldBairro1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(textFieldBairro1, gridBagConstraints);

        labelCidade1.setText("Cidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(labelCidade1, gridBagConstraints);

        textFieldCidade1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(textFieldCidade1, gridBagConstraints);

        labelUF1.setText("UF:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(labelUF1, gridBagConstraints);

        comboBoxUF1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MT", "RS", "MG" }));
        comboBoxUF1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(comboBoxUF1, gridBagConstraints);

        labelCEP1.setText("CEP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(labelCEP1, gridBagConstraints);

        try {
            textFieldCEP1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textFieldCEP1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(textFieldCEP1, gridBagConstraints);

        labelTelefone1.setText("Telefone:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(labelTelefone1, gridBagConstraints);

        try {
            textFieldTelefone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textFieldTelefone1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(textFieldTelefone1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jInternalFrame1.getContentPane().add(jPanel7, gridBagConstraints);

        java.awt.FlowLayout flowLayout4 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout4.setAlignOnBaseline(true);
        jPanel8.setLayout(flowLayout4);

        buttonSelecionarFornecedor1.setText("Selecionar Fornecedor");
        buttonSelecionarFornecedor.setVisible(false);
        buttonSelecionarFornecedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelecionarFornecedor1ActionPerformed(evt);
            }
        });
        jPanel8.add(buttonSelecionarFornecedor1);

        buttonNovo1.setText("Novo");
        buttonNovo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovo1ActionPerformed(evt);
            }
        });
        jPanel8.add(buttonNovo1);

        buttonAlterar1.setText("Alterar");
        buttonAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlterar1ActionPerformed(evt);
            }
        });
        jPanel8.add(buttonAlterar1);

        buttonExcluir1.setText("Excluir");
        buttonExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluir1ActionPerformed(evt);
            }
        });
        jPanel8.add(buttonExcluir1);

        buttonSalvar1.setText("Salvar");
        buttonSalvar1.setEnabled(false);
        buttonSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvar1ActionPerformed(evt);
            }
        });
        jPanel8.add(buttonSalvar1);

        buttonCancelar1.setText("Cancelar");
        buttonCancelar1.setEnabled(false);
        buttonCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelar1ActionPerformed(evt);
            }
        });
        jPanel8.add(buttonCancelar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jInternalFrame1.getContentPane().add(jPanel8, gridBagConstraints);

        getContentPane().add(jInternalFrame1, new java.awt.GridBagConstraints());

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

    private void buttonPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPesquisar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPesquisar1ActionPerformed

    private void tableFornecedores1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFornecedores1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableFornecedores1MouseClicked

    private void buttonSelecionarFornecedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarFornecedor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSelecionarFornecedor1ActionPerformed

    private void buttonNovo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonNovo1ActionPerformed

    private void buttonAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAlterar1ActionPerformed

    private void buttonExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExcluir1ActionPerformed

    private void buttonSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSalvar1ActionPerformed

    private void buttonCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelar1ActionPerformed

    private void jInternalFrame1formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jInternalFrame1formInternalFrameOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame1formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAlterar;
    private javax.swing.JButton buttonAlterar1;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonCancelar1;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonExcluir1;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonNovo1;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JButton buttonPesquisar1;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JButton buttonSalvar1;
    private javax.swing.JButton buttonSelecionarFornecedor;
    private javax.swing.JButton buttonSelecionarFornecedor1;
    private javax.swing.JComboBox<String> comboBoxUF;
    private javax.swing.JComboBox<String> comboBoxUF1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelBairro1;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCEP1;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelCidade1;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEmail1;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelEndereco1;
    private javax.swing.JLabel labelFiltro;
    private javax.swing.JLabel labelFiltro1;
    private javax.swing.JLabel labelFornecedor;
    private javax.swing.JLabel labelFornecedor1;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelTelefone1;
    private javax.swing.JLabel labelUF;
    private javax.swing.JLabel labelUF1;
    private javax.swing.JTable tableFornecedores;
    private javax.swing.JTable tableFornecedores1;
    private javax.swing.JTextField textFieldBairro;
    private javax.swing.JTextField textFieldBairro1;
    private javax.swing.JFormattedTextField textFieldCEP;
    private javax.swing.JFormattedTextField textFieldCEP1;
    private javax.swing.JTextField textFieldCidade;
    private javax.swing.JTextField textFieldCidade1;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldEmail1;
    private javax.swing.JTextField textFieldEndereco;
    private javax.swing.JTextField textFieldEndereco1;
    private javax.swing.JTextField textFieldFiltro;
    private javax.swing.JTextField textFieldFiltro1;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldNome1;
    private javax.swing.JFormattedTextField textFieldTelefone;
    private javax.swing.JFormattedTextField textFieldTelefone1;
    // End of variables declaration//GEN-END:variables
}
