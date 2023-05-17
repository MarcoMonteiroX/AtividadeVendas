package view;

public class FrameSobre extends javax.swing.JInternalFrame {

    public FrameSobre() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelIFMT = new javax.swing.JLabel();
        labelImagem = new javax.swing.JLabel();
        labelTADS = new javax.swing.JLabel();
        labelNomeSistema = new javax.swing.JLabel();
        labelVersaoSistema = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Sobre - Projeto, TADS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(784, 539));
        getContentPane().setLayout(new java.awt.GridLayout(5, 1));

        labelIFMT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelIFMT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIFMT.setText("Instituto Federal de Educação, Ciência e Tecnologia");
        getContentPane().add(labelIFMT);

        labelImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IFMT-LOGO.png"))); // NOI18N
        labelImagem.setPreferredSize(new java.awt.Dimension(100, 100));
        getContentPane().add(labelImagem);

        labelTADS.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelTADS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTADS.setText("TADS - 2023 - 3º Semestre");
        getContentPane().add(labelTADS);

        labelNomeSistema.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNomeSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomeSistema.setText("Sistema de Controle de Vendas");
        getContentPane().add(labelNomeSistema);

        labelVersaoSistema.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelVersaoSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelVersaoSistema.setText("Versão 1.0");
        getContentPane().add(labelVersaoSistema);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelIFMT;
    private javax.swing.JLabel labelImagem;
    private javax.swing.JLabel labelNomeSistema;
    private javax.swing.JLabel labelTADS;
    private javax.swing.JLabel labelVersaoSistema;
    // End of variables declaration//GEN-END:variables
}
