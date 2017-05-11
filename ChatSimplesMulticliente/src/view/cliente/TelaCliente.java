/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cliente;

import cliente.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Mensagem;

/**
 *
 * @author Nexus_Kayros
 */
public class TelaCliente extends javax.swing.JFrame {

    // Atributos adicionais
    private Cliente refCliente;
    private String nomeCliente;
    
    public TelaCliente() {
        initComponents();
    }

    public TelaCliente(Cliente refCliente, String nomeCliente) {
        this();
        this.refCliente = refCliente;
        this.nomeCliente = nomeCliente;
        lblNomeCliente.setText(nomeCliente);
    }

    // Atualização do chat
    public void iniciarCaixaMensagem(){
     
        // Iniciar Thread de tratamento da caixa de mensagem
        new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        // Receber dados da mensagem
                        Mensagem mensagem = (Mensagem) refCliente.getEntradaDados().readObject();
                        
                        // Enviar mensagem à caixa de mensagens
                        atualizarCaixaMensagem((mensagem.getRemetente() + ": " + mensagem.getTexto()));
                        
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }           
        }.start();
        System.out.println("Chat iniciado!"); 
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtaMensagens = new javax.swing.JTextArea();
        btnSair = new javax.swing.JButton();
        lblAuxConectadoComo = new javax.swing.JLabel();
        lblNomeCliente = new javax.swing.JLabel();
        jtfMensagem = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtaMensagens.setColumns(20);
        jtaMensagens.setRows(5);
        jScrollPane1.setViewportView(jtaMensagens);

        btnSair.setText("Conectar");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        lblAuxConectadoComo.setText("Conectado como: ");

        lblNomeCliente.setText("Fulano De Tal");

        jtfMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfMensagemKeyPressed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEnviarMousePressed(evt);
            }
        });
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAuxConectadoComo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomeCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSair))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtfMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair)
                    .addComponent(lblAuxConectadoComo)
                    .addComponent(lblNomeCliente))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfMensagem)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfMensagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfMensagemKeyPressed

    }//GEN-LAST:event_jtfMensagemKeyPressed

    private void btnEnviarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnviarMousePressed
        
    }//GEN-LAST:event_btnEnviarMousePressed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviarMensagem(jtfMensagem.getText());     
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        iniciarCaixaMensagem();
    }//GEN-LAST:event_btnSairActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente().setVisible(true);               
            }
        });
    }
    
    // Enviar mensagem
    public void enviarMensagem(String texto){
        this.refCliente.enviarMensagem(texto);      
    }
    
    // Atualizar tela de mensagens
    public void atualizarCaixaMensagem(String msg){
        this.jtaMensagens.setText(jtaMensagens.getText() + "\n" + msg);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnSair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtaMensagens;
    private javax.swing.JTextField jtfMensagem;
    private javax.swing.JLabel lblAuxConectadoComo;
    private javax.swing.JLabel lblNomeCliente;
    // End of variables declaration//GEN-END:variables
}
