package view;

import controller.UsuarioDAO;
import java.awt.HeadlessException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import util.Constantes;
import util.HashMD5;

public class FrameUsuario extends javax.swing.JInternalFrame {

    private List<Usuario> usuarios;
    private Constantes modo;

    public FrameUsuario() {
        initComponents();
    }

    private void listar() {
        try {
            usuarios = new UsuarioDAO().consultarUsuarios();
            DefaultTableModel table = (DefaultTableModel) tableUsuarios.getModel();
            table.setNumRows(0);
            for (Usuario usuario : usuarios) {
                table.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getPerfi()
                });
            }
        } catch (Exception e) {
        }
    }

    private void listar(String nome) {
        try {
            usuarios = new UsuarioDAO().consultarUsuarios(nome);
            DefaultTableModel table = (DefaultTableModel) tableUsuarios.getModel();
            table.setNumRows(0);
            for (Usuario usuario : usuarios) {
                table.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getPerfi()
                });
            }
        } catch (Exception e) {
        }
    }

    private void inserir() {
        try {
            if (!textFieldNome.getText().isBlank()) {
                String senha = new HashMD5().gerarHash(Arrays.toString(textPasswordSenha.getPassword()));
                if (senha.length() > 16) {
                    senha = senha.substring(0, 16);
                }
                Usuario usuario;
                usuario = new Usuario(
                        textFieldNome.getText().strip(),
                        textFieldLogin.getText().strip(),
                        senha,
                        textFieldEmail.getText().strip(),
                        comboBoxPerfil.getSelectedItem().toString()
                );

                if (new UsuarioDAO().inserirUsuario(usuario)) {
                    JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
                    listar();
                    limparCampos();
                    desabilitarCampos();
                    desabilitarBotoes();
                } else {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao cadastrar o usuário!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Informe o nome do usuário!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
        }
    }

    private void alterar() {
        try {
            if (!textFieldNome.getText().isBlank()) {
                String senha = new HashMD5().gerarHash(Arrays.toString(textPasswordSenha.getPassword()));
                if (senha.length() > 16) {
                    senha = senha.substring(0, 16);
                }
                Usuario usuario;
                usuario = new Usuario(
                        usuarios.get(tableUsuarios.getSelectedRow()).getId(),
                        textFieldNome.getText().strip(),
                        textFieldLogin.getText().strip(),
                        senha,
                        textFieldEmail.getText().strip(),
                        comboBoxPerfil.getSelectedItem().toString()
                );

                if (new UsuarioDAO().alterarUsuario(usuario)) {
                    JOptionPane.showMessageDialog(this, "Usuário alterado com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
                    listar();
                    limparCampos();
                    desabilitarCampos();
                    desabilitarBotoes();
                } else {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao alterar o usuário!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException e) {
        }
    }

    private void excluir() {
        try {
            if (new UsuarioDAO().excluirUsuario(usuarios.get(tableUsuarios.getSelectedRow()))) {
                JOptionPane.showMessageDialog(this, "Usuario excluido com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                limparCampos();
                desabilitarCampos();
                desabilitarBotoes();
            } else {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir o usuario!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
        }
    }

    private void mostrar(int i) {
        textFieldNome.setText(usuarios.get(i).getNome());
        textFieldLogin.setText(usuarios.get(i).getLogin());
        textFieldEmail.setText(usuarios.get(i).getEmail());
        comboBoxPerfil.setSelectedItem(usuarios.get(i).getPerfi());
    }

    private void habilitarCampos() {
        textFieldNome.setEnabled(true);
        textFieldLogin.setEnabled(true);
        textPasswordSenha.setEnabled(true);
        textFieldEmail.setEnabled(true);
        comboBoxPerfil.setEnabled(true);
    }

    private void desabilitarCampos() {
        textFieldNome.setEnabled(false);
        textFieldLogin.setEnabled(false);
        textPasswordSenha.setEnabled(false);
        textFieldEmail.setEnabled(false);
        comboBoxPerfil.setEnabled(false);
    }

    private void limparCampos() {
        textFieldNome.setText("");
        textFieldLogin.setText("");
        textPasswordSenha.setText("");
        textFieldEmail.setText("");
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
        labelRegistrarVendas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelFiltro = new javax.swing.JLabel();
        textFieldFiltro = new javax.swing.JTextField();
        buttonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        labelLogin = new javax.swing.JLabel();
        textFieldLogin = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        textPasswordSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JTextField();
        labelPerfil = new javax.swing.JLabel();
        comboBoxPerfil = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        buttonNovo = new javax.swing.JButton();
        buttonAlterar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        buttonSalvar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastrar Usuário - Projeto, TADS");
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
        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout3.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout3);

        labelRegistrarVendas.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelRegistrarVendas.setForeground(new java.awt.Color(255, 255, 255));
        labelRegistrarVendas.setText("Usuário");
        jPanel1.add(labelRegistrarVendas);

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

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Perfil"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableUsuarios);

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldNome, gridBagConstraints);

        labelLogin.setText("Login:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelLogin, gridBagConstraints);

        textFieldLogin.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldLogin, gridBagConstraints);

        labelSenha.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(labelSenha, gridBagConstraints);

        textPasswordSenha.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textPasswordSenha, gridBagConstraints);

        jLabel4.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        textFieldEmail.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(textFieldEmail, gridBagConstraints);

        labelPerfil.setText("Perfil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel3.add(labelPerfil, gridBagConstraints);

        comboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Administrador" }));
        comboBoxPerfil.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(comboBoxPerfil, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

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
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
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

    private void tableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUsuariosMouseClicked
        if (tableUsuarios.getSelectedRow() != -1) {
            mostrar(tableUsuarios.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum usuário!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tableUsuariosMouseClicked

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        limparCampos();
        habilitarCampos();
        habilitarBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarActionPerformed
        if (tableUsuarios.getSelectedRow() != -1) {
            habilitarCampos();
            habilitarBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum usuário!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonAlterarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if (tableUsuarios.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o usuário?", "Mensagem de confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluir();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum usuário!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            inserir();
        } else if (modo == Constantes.EDIT_MODE) {
            alterar();
        }
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        limparCampos();
        desabilitarCampos();
        desabilitarBotoes();
    }//GEN-LAST:event_buttonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAlterar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JComboBox<String> comboBoxPerfil;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFiltro;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPerfil;
    private javax.swing.JLabel labelRegistrarVendas;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JTable tableUsuarios;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldFiltro;
    private javax.swing.JTextField textFieldLogin;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JPasswordField textPasswordSenha;
    // End of variables declaration//GEN-END:variables
}
