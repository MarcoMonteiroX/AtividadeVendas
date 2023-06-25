package view;

import controller.UsuarioDAO;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.HashMD5;

public class FrameLogin extends javax.swing.JFrame {

    public FrameLogin() {
        initComponents();
    }

    private void limparCampos() {
        textFieldLogin.setText("");
        textFieldSenha.setText("");
    }

    private String criptografarSenha(char[] senha) {
        String retornar = new HashMD5().gerarHash(Arrays.toString(senha));
        return (retornar.length() > 16) ? retornar.substring(0, 16) : retornar;
    }

    private void autenticar(String login, String senha) {
        if (new UsuarioDAO().autenticarUsuario(login, senha)) {
            JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
            verificarPrivilegios(login);
        } else {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao efetuar o login!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            limparCampos();
        }
    }

    private void verificarPrivilegios(String login) {
        String perfil = new UsuarioDAO().administrador(login);
        switch (perfil) {
            case "Administrador":
                new FrameTelaPrincipal(true).setVisible(true);
                this.dispose();
                break;
            case "Usuario":
                new FrameTelaPrincipal(false).setVisible(true);
                this.dispose();
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelTitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        panelLogin = new javax.swing.JPanel();
        labelLogin = new javax.swing.JLabel();
        textFieldLogin = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        textFieldSenha = new javax.swing.JPasswordField();
        panelLogar = new javax.swing.JPanel();
        buttonLogar = new javax.swing.JButton();
        buttonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Projeto, TADS");
        setIconImage(new ImageIcon(getClass().getResource("/images/LOGO.png")).getImage());
        setMinimumSize(new java.awt.Dimension(600, 300));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panelTitulo.setBackground(java.awt.SystemColor.activeCaption);
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout2.setAlignOnBaseline(true);
        panelTitulo.setLayout(flowLayout2);

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setText("Login");
        panelTitulo.add(labelTitulo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(panelTitulo, gridBagConstraints);

        panelLogin.setLayout(new java.awt.GridBagLayout());

        labelLogin.setText("Login:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelLogin.add(labelLogin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelLogin.add(textFieldLogin, gridBagConstraints);

        labelSenha.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelLogin.add(labelSenha, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelLogin.add(textFieldSenha, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(panelLogin, gridBagConstraints);

        panelLogar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        buttonLogar.setText("Logar");
        buttonLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogarActionPerformed(evt);
            }
        });
        panelLogar.add(buttonLogar);

        buttonSair.setText("Sair");
        buttonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSairActionPerformed(evt);
            }
        });
        panelLogar.add(buttonSair);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(panelLogar, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogarActionPerformed
        if (textFieldLogin.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Informe o login do usuário!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            limparCampos();
            return;
        } else if (String.valueOf(textFieldSenha.getPassword()).isBlank()) {
            JOptionPane.showMessageDialog(this, "Informe a senha do usuário!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            limparCampos();
            return;
        }
        String login = textFieldLogin.getText();
        String senha = criptografarSenha(textFieldSenha.getPassword());
        autenticar(login, senha);
    }//GEN-LAST:event_buttonLogarActionPerformed

    private void buttonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonSairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogar;
    private javax.swing.JButton buttonSair;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelLogar;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTextField textFieldLogin;
    private javax.swing.JPasswordField textFieldSenha;
    // End of variables declaration//GEN-END:variables
}
