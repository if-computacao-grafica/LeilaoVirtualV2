package com.mycompany.leilaocliente;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;


public class MulticastForm extends javax.swing.JFrame {

    private ClienteLicitante clienteLicitante;
    private ClienteLicitanteConcorrente clienteLicitanteConcorrente = new ClienteLicitanteConcorrente();

   
    public MulticastForm() {
        initComponents();
        clienteLicitanteConcorrente.enviarListaRequisicoesDoLeilao();
        clienteLicitanteConcorrente.listenListaLeilao(() -> {
            setListaLeilao();
        });
        normalMode();
        autoRefresh();
    }

    private void normalMode() {
        txt_chavePublica.setEnabled(true);
        cmb_auction_list.setEnabled(true);
        btn_refresh.setEnabled(true);
        btn_join.setEnabled(true);

        txt_product_name.setVisible(false);
        txt_last_bid.setVisible(false);
        txt_last_bidder.setVisible(false);
        txt_price.setVisible(false);

        btn_make_bid.setVisible(false);

        lbl_last_bid.setVisible(false);
        lbl_last_bidder.setVisible(false);
    }

    private void auctionMode() {
        txt_chavePublica.setEnabled(false);
        cmb_auction_list.setEnabled(false);
        btn_refresh.setEnabled(false);
        btn_join.setEnabled(false);

        txt_product_name.setVisible(true);
        txt_last_bid.setVisible(true);
        txt_last_bidder.setVisible(true);
        txt_price.setVisible(true);

        btn_make_bid.setVisible(true);

        lbl_last_bid.setVisible(true);
        lbl_last_bidder.setVisible(true);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmb_auction_list = new javax.swing.JComboBox<>();
        btn_refresh = new javax.swing.JButton();
        btn_join = new javax.swing.JButton();
        txt_chavePublica = new javax.swing.JTextField();
        panel_auction = new java.awt.Panel();
        txt_product_name = new javax.swing.JLabel();
        txt_last_bid = new javax.swing.JLabel();
        lbl_last_bid = new javax.swing.JLabel();
        btn_make_bid = new javax.swing.JButton();
        lbl_last_bidder = new javax.swing.JLabel();
        txt_last_bidder = new javax.swing.JLabel();
        txt_price = new javax.swing.JFormattedTextField();
        txt_id_cliente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmb_auction_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_refresh.setText("Atualizar");
        btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_refreshMouseClicked(evt);
            }
        });

        btn_join.setText("Entrar");
        btn_join.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_joinMouseClicked(evt);
            }
        });

        txt_product_name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_product_name.setText("Nome Produto");

        txt_last_bid.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_last_bid.setText("Brl. 5000");

        lbl_last_bid.setText("Ultimo Lance");

        btn_make_bid.setText("Fazer Lance");
        btn_make_bid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_make_bidMouseClicked(evt);
            }
        });

        lbl_last_bidder.setText("Licitante");

        txt_last_bidder.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_last_bidder.setText("Nome");

        txt_price.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txt_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_priceKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_priceKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel_auctionLayout = new javax.swing.GroupLayout(panel_auction);
        panel_auction.setLayout(panel_auctionLayout);
        panel_auctionLayout.setHorizontalGroup(
            panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_auctionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_auctionLayout.createSequentialGroup()
                        .addComponent(txt_product_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(86, 86, 86))
                    .addGroup(panel_auctionLayout.createSequentialGroup()
                        .addGroup(panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_auctionLayout.createSequentialGroup()
                                .addComponent(txt_last_bid, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_last_bidder))
                            .addGroup(panel_auctionLayout.createSequentialGroup()
                                .addGroup(panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_last_bid)
                                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_last_bidder)
                                    .addComponent(btn_make_bid))))
                        .addGap(0, 18, Short.MAX_VALUE))))
        );
        panel_auctionLayout.setVerticalGroup(
            panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_auctionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_product_name)
                .addGap(18, 18, 18)
                .addGroup(panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_last_bid)
                    .addComponent(lbl_last_bidder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_last_bid)
                    .addComponent(txt_last_bidder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_auctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_make_bid)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_join)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_refresh))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_auction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_auction_list, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_chavePublica)
                    .addComponent(txt_id_cliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(txt_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_chavePublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_auction_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_join)
                    .addComponent(btn_refresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_auction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void autoRefresh() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clienteLicitanteConcorrente.enviarListaRequisicoesDoLeilao();
            }
        }, 0, 1500);
    }
    
    private void btn_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseClicked
       
        clienteLicitanteConcorrente.enviarListaRequisicoesDoLeilao();
    }//GEN-LAST:event_btn_refreshMouseClicked

    private void btn_joinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_joinMouseClicked
     
        clienteLicitante = criarClienteLicitante();
        if (clienteLicitante.joinAuction()) {
            txt_product_name.setText(clienteLicitante.getAuction().getProduto().getNome());
            atualizarUltimoLance();

            clienteLicitante.listenBid(() -> {
                atualizarUltimoLance();
            }, () -> {
                clienteLicitanteConcorrente.enviarListaRequisicoesDoLeilao();
                normalMode();
            });
            auctionMode();
        } else {
            JOptionPane.showMessageDialog(this, "Auction not started or already finished");
        }
    }//GEN-LAST:event_btn_joinMouseClicked

    private ClienteLicitante criarClienteLicitante() {
        int index = cmb_auction_list.getSelectedIndex();
        return new ClienteLicitante(txt_chavePublica.getText(), clienteLicitanteConcorrente.getAuctions().get(index));
    }

    private void btn_make_bidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_make_bidMouseClicked

        int preco = Integer.parseInt(unformatPrice(txt_price.getText()));
        clienteLicitante.fazerLance(preco);
    }//GEN-LAST:event_btn_make_bidMouseClicked

    private void txt_priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_priceKeyTyped
       
    }//GEN-LAST:event_txt_priceKeyTyped

    private void txt_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_priceKeyReleased
        
        limitarTexto();
        int preco = Integer.parseInt(unformatPrice(txt_price.getText()));
        txt_price.setText(formatarPreco(preco));
    }//GEN-LAST:event_txt_priceKeyReleased

    private void limitarTexto() {
        if(txt_price.getText().length() >= 14)
            txt_price.setText(txt_price.getText().substring(0, 13));
    }
    
    private String unformatPrice(String price) {
        return price.replace(",", "");
    }
    
    private String formatarPreco(int price) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(price);
    }
    
    private void setListaLeilao() {
        System.out.println("set auction");
        cmb_auction_list.removeAllItems();
        clienteLicitanteConcorrente.getAuctions().forEach((leilao) -> {
            cmb_auction_list.addItem(leilao.getProduto().getNome());
        });
    }

    private void atualizarUltimoLance() {
        txt_last_bidder.setText(clienteLicitante.getAuction().getUltimoLance().getNomeParticipante());
        txt_last_bid.setText("Rp. " + String.valueOf(formatarPreco(clienteLicitante.getAuction().getUltimoLance().getPreco())));
    }

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MulticastForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MulticastForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_join;
    private javax.swing.JButton btn_make_bid;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JComboBox<String> cmb_auction_list;
    private javax.swing.JLabel lbl_last_bid;
    private javax.swing.JLabel lbl_last_bidder;
    private java.awt.Panel panel_auction;
    private javax.swing.JTextField txt_chavePublica;
    private javax.swing.JTextField txt_id_cliente;
    private javax.swing.JLabel txt_last_bid;
    private javax.swing.JLabel txt_last_bidder;
    private javax.swing.JFormattedTextField txt_price;
    private javax.swing.JLabel txt_product_name;
    // End of variables declaration//GEN-END:variables
}
