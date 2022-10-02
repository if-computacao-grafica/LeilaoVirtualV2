package com.mycompany.leilaoservidormulticast;

import com.mycompany.leilaoservidormulticast.compartilhado.domain.Leilao;
import com.mycompany.leilaoservidormulticast.utils.Temporizador;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skmat
 */
public class ServerForm extends javax.swing.JFrame {
    
    private LeilaoServidor server = new LeilaoServidor();
    private Leilao leilaoSelecionado;
    
    /**
     * Creates new form Server
     */
    public ServerForm() throws NoSuchAlgorithmException {
        initComponents();
        server.iniciarServer();
        normalMode();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_auction = new javax.swing.JTable();
        btn_finish = new java.awt.Button();
        btn_create = new java.awt.Button();
        btn_start = new java.awt.Button();
        btn_cancel = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_auction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Price", "Address", "Port", "Last Bid", "Last Bidder", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_auction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_auctionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_auction);

        btn_finish.setActionCommand("buttonCreate");
        btn_finish.setLabel("Finalizar");
        btn_finish.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_finishMouseClicked(evt);
            }
        });

        btn_create.setActionCommand("buttonCreate");
        btn_create.setLabel("Criar Ação");
        btn_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createActionPerformed(evt);
            }
        });

        btn_start.setActionCommand("buttonCreate");
        btn_start.setLabel("Iniciar");
        btn_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_startMouseClicked(evt);
            }
        });
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        btn_cancel.setActionCommand("ButtonPrint");
        btn_cancel.setLabel("Cancelar");
        btn_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_create, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_start, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_finish, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 294, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_finish, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_create, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_start, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_finish.getAccessibleContext().setAccessibleName("buttonCreate");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void normalMode() {
        tbl_auction.clearSelection();
        btn_start.setEnabled(false);
        btn_finish.setEnabled(false);
        btn_cancel.setEnabled(false);
    }
    
    private void editMode() {
        btn_start.setEnabled(leilaoSelecionado.getStatus() == Leilao.NAO_INICIADO);
        btn_finish.setEnabled(leilaoSelecionado.getStatus() == Leilao.INICIADO);
        btn_cancel.setEnabled(true);
    }
    
    public void addAuction(Leilao auction) {
        server.addAuction(auction);
        refreshTable();
    }
    
    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_auction.getModel();
        model.setRowCount(0);
        server.getAuctions().forEach((auction) -> {
            int status = auction.getStatus();
            
            model.addRow(new Object[]{
                auction.getProduto().getNome(),
                auction.getProduto().getPreco(),
                auction.getEndereco().toString(),
                auction.getPorta(),
                auction.getUltimoLance().getPreco(),
                auction.getUltimoLance().getNomeParticipante(),
                status == Leilao.NAO_INICIADO ? "not started" : status == Leilao.INICIADO ? "started" : "finished"
            });
        });
    }
    
    private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed
        // TODO add your handling code here:
        new CreateAuctionForm(this).setVisible(true);
    }//GEN-LAST:event_btn_createActionPerformed

    private void tbl_auctionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_auctionMouseClicked
        // TODO add your handling code here:
        int index = tbl_auction.getSelectedRow();
        leilaoSelecionado = server.getAuctions().get(index);
        editMode();
    }//GEN-LAST:event_tbl_auctionMouseClicked

    private void btn_cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelMouseClicked
        // TODO add your handling code here:
        normalMode();
        leilaoSelecionado = null;
    }//GEN-LAST:event_btn_cancelMouseClicked

    private void btn_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_startMouseClicked
        // TODO add your handling code here:
        leilaoSelecionado.iniciarAuction(() -> {
            refreshTable();
        });
        refreshTable();
        normalMode();
    }//GEN-LAST:event_btn_startMouseClicked
  
    private void btn_finishMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_finishMouseClicked
        // TODO add your handling code here:
        leilaoSelecionado.pararAuction();
        refreshTable();
        normalMode();
    }//GEN-LAST:event_btn_finishMouseClicked

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        Temporizador temp = new Temporizador();
        
        temp.timer.scheduleAtFixedRate(temp.task, 100, ABORT);
        
        if(temp.estaRodando == false){
            System.out.println("fechar programa");
        }
        
      
        
    }//GEN-LAST:event_btn_startActionPerformed
    
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ServerForm().setVisible(true);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btn_cancel;
    private java.awt.Button btn_create;
    private java.awt.Button btn_finish;
    private java.awt.Button btn_start;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_auction;
    // End of variables declaration//GEN-END:variables
}
