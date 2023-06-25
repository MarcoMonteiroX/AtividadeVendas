package view;

import javax.swing.ImageIcon;

public class FrameTelaPrincipal extends javax.swing.JFrame {

    boolean administrador = false;

    public FrameTelaPrincipal() {
        initComponents();
    }

    public FrameTelaPrincipal(boolean administrador) {
        initComponents();
        if (!administrador) {
            menuItemUsuario.setEnabled(false);
            menuItemUsuario.setVisible(false);
            menuItemConsultarVendas.setEnabled(false);
            menuItemConsultarVendas.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        menuSistema = new javax.swing.JMenu();
        menuItemSair = new javax.swing.JMenuItem();
        menuCadastro = new javax.swing.JMenu();
        menuItemCliente = new javax.swing.JMenuItem();
        menuItemFornecedor = new javax.swing.JMenuItem();
        menuItemProduto = new javax.swing.JMenuItem();
        menuItemUsuario = new javax.swing.JMenuItem();
        menuVendas = new javax.swing.JMenu();
        menuItemRegistrarVendas = new javax.swing.JMenuItem();
        menuItemConsultarVendas = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        menuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal - Projeto, TADS");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon(getClass().getResource("/images/LOGO.png")).getImage());

        javax.swing.GroupLayout desktopPanelLayout = new javax.swing.GroupLayout(desktopPanel);
        desktopPanel.setLayout(desktopPanelLayout);
        desktopPanelLayout.setHorizontalGroup(
            desktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        desktopPanelLayout.setVerticalGroup(
            desktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );

        menuSistema.setText("Sistema");

        menuItemSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuItemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SAIR-LOGO.png"))); // NOI18N
        menuItemSair.setText("Sair");
        menuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSairActionPerformed(evt);
            }
        });
        menuSistema.add(menuItemSair);

        menuBar.add(menuSistema);

        menuCadastro.setText("Cadastro");

        menuItemCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuItemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CLIENTE-LOGO.png"))); // NOI18N
        menuItemCliente.setText("Cliente");
        menuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemClienteActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemCliente);

        menuItemFornecedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuItemFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FORNECEDOR-LOGO.png"))); // NOI18N
        menuItemFornecedor.setText("Fornecedor");
        menuItemFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemFornecedorActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemFornecedor);

        menuItemProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuItemProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PRODUTO-LOGO.png"))); // NOI18N
        menuItemProduto.setText("Produto");
        menuItemProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemProdutoActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemProduto);

        menuItemUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuItemUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CLIENTE-LOGO.png"))); // NOI18N
        menuItemUsuario.setText("Usuario");
        menuItemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemUsuarioActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemUsuario);

        menuBar.add(menuCadastro);

        menuVendas.setText("Vendas");

        menuItemRegistrarVendas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuItemRegistrarVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/REGISTRAR-LOGO.png"))); // NOI18N
        menuItemRegistrarVendas.setText("Registrar Vendas");
        menuItemRegistrarVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRegistrarVendasActionPerformed(evt);
            }
        });
        menuVendas.add(menuItemRegistrarVendas);

        menuItemConsultarVendas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuItemConsultarVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/REGISTRAR-LOGO.png"))); // NOI18N
        menuItemConsultarVendas.setText("Consultar Vendas");
        menuItemConsultarVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultarVendasActionPerformed(evt);
            }
        });
        menuVendas.add(menuItemConsultarVendas);

        menuBar.add(menuVendas);

        menuAjuda.setText("Ajuda");

        menuItemSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuItemSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SOBRE-LOGO.png"))); // NOI18N
        menuItemSobre.setText("Sobre");
        menuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSobreActionPerformed(evt);
            }
        });
        menuAjuda.add(menuItemSobre);

        menuBar.add(menuAjuda);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setSize(new java.awt.Dimension(1296, 728));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSairActionPerformed

    private void menuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemClienteActionPerformed
        FrameCliente tela = new FrameCliente();
        tela.setVisible(true);
        desktopPanel.add(tela);

    }//GEN-LAST:event_menuItemClienteActionPerformed

    private void menuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSobreActionPerformed
        FrameSobre tela = new FrameSobre();
        tela.setVisible(true);
        desktopPanel.add(tela);
    }//GEN-LAST:event_menuItemSobreActionPerformed

    private void menuItemFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemFornecedorActionPerformed
        FrameFornecedor tela = new FrameFornecedor();
        tela.setVisible(true);
        desktopPanel.add(tela);
    }//GEN-LAST:event_menuItemFornecedorActionPerformed

    private void menuItemProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemProdutoActionPerformed
        FrameProduto tela = new FrameProduto(this.desktopPanel);
        tela.setVisible(true);
        desktopPanel.add(tela);
    }//GEN-LAST:event_menuItemProdutoActionPerformed

    private void menuItemRegistrarVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRegistrarVendasActionPerformed
        FrameRegistrarVendas tela = new FrameRegistrarVendas(this.desktopPanel);
        tela.setVisible(true);
        desktopPanel.add(tela);
    }//GEN-LAST:event_menuItemRegistrarVendasActionPerformed

    private void menuItemConsultarVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultarVendasActionPerformed
        FrameConsultarVendas tela = new FrameConsultarVendas();
        tela.setVisible(true);
        desktopPanel.add(tela);
    }//GEN-LAST:event_menuItemConsultarVendasActionPerformed

    private void menuItemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUsuarioActionPerformed
        FrameUsuario tela = new FrameUsuario();
        tela.setVisible(true);
        desktopPanel.add(tela);
    }//GEN-LAST:event_menuItemUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(FrameTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameTelaPrincipal().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPanel;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuItemCliente;
    private javax.swing.JMenuItem menuItemConsultarVendas;
    private javax.swing.JMenuItem menuItemFornecedor;
    private javax.swing.JMenuItem menuItemProduto;
    private javax.swing.JMenuItem menuItemRegistrarVendas;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JMenuItem menuItemUsuario;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JMenu menuVendas;
    // End of variables declaration//GEN-END:variables
}
