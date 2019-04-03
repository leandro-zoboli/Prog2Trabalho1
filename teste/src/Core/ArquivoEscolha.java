package Core;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ArquivoEscolha extends javax.swing.JFrame {

    private File arquivo;

    public ArquivoEscolha() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Salvar arquivos .mp3");
        arquivo = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEditarArquivo = new javax.swing.JToggleButton();
        nomeArquivo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarArquivo = new javax.swing.JButton();
        btnPronto = new javax.swing.JButton();

        btnEditarArquivo.setText("Editar arquivo");
        btnEditarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarArquivoActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Nome arquivo:");

        btnBuscarArquivo.setText("Buscar...");
        btnBuscarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArquivoActionPerformed(evt);
            }
        });

        btnPronto.setText("Pronto");
        btnPronto.setEnabled(false);
        btnPronto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProntoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBuscarArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPronto)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomeArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarArquivo)
                    .addComponent(btnPronto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArquivoActionPerformed
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //j.showSaveDialog(null);
        int retorno = j.showOpenDialog(this);
        arquivo = j.getSelectedFile();

        if (retorno == JFileChooser.APPROVE_OPTION) {
            nomeArquivo.setText(arquivo.getName());
        } else if (retorno == JFileChooser.CANCEL_OPTION) {
            nomeArquivo.setText("");
        }

        //btnPronto.setEnabled(nomeArquivo.getText() != "");
        //veriricar se arquivo é mp3 (3 primeiros bytes formam 'TAG')
        //if (nomeArquivo.getText().split(".")[nomeArquivo.getText().split(".").length] == "mp3") {
        //  int[] primeirosBytes = UtilsIO.ReadBytes(arquivo, 3);
        //}
        btnPronto.setEnabled(ArquivoEhMp3(nomeArquivo.getText()));
    }//GEN-LAST:event_btnBuscarArquivoActionPerformed

    private boolean ArquivoEhMp3(String arquivo) {
        if (!arquivo.equals("")) {
            return arquivo.endsWith(".mp3");
        }
        return false;
    }

    private void btnEditarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarArquivoActionPerformed
        btnEditarArquivo.setEnabled(nomeArquivo.getText() != "");
    }//GEN-LAST:event_btnEditarArquivoActionPerformed

    private void btnProntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProntoActionPerformed
        UtilsIO.setCursorPosition(0);
        if (!UtilsIO.ReadData(arquivo, 3).equals("TAG")) {
            JOptionPane.showMessageDialog(this, "O arquivo escolhido não possui o formato \"ID3v1.1\"", "Atenção:", JOptionPane.WARNING_MESSAGE);
        } else {
            ArquivoAcoes acoes = new ArquivoAcoes(arquivo);
            acoes.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnProntoActionPerformed

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
            java.util.logging.Logger.getLogger(ArquivoEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArquivoEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArquivoEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArquivoEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArquivoEscolha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarArquivo;
    private javax.swing.JToggleButton btnEditarArquivo;
    private javax.swing.JButton btnPronto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel nomeArquivo;
    // End of variables declaration//GEN-END:variables
}
