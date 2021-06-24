/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futebol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author naty
 */
public class Clubes extends javax.swing.JFrame {

    /**
     * Creates new form Clubes
     */
    DB conn = new DB();
    Connection connect;
    PreparedStatement mysql;
    Statement stmt;
    ResultSet set;
    Clube Clube = new Clube();
    int id;
    String name;
    String id_jogador;
    boolean isEditing = false;

    /**
     * Creates new form Clubes
     */
    public Clubes() throws SQLException, ClassNotFoundException {
        initComponents();
        this.connect = conn.Conecta();
        carregarJogadores();
        listClubes();
        jButton4.disable();
    }

    /** Utilizei tratamento com try catch para erros inesperados */
    public void listClubes() {
        try {
            /** Executando select para pesquisa */
            String query = "select id,name,name_jogador from time t INNER JOIN jogadores j on t.id_jogador = j.id_jogador";
            stmt = this.connect.createStatement();
            set = stmt.executeQuery(query);

            String[] columnNames = {"Id", "Nome Time", "Jogador"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            while (set.next()) {
                tableModel.addRow(new String[]{
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
                    set.getString("name_jogador")
                });
            }
            nameTime.setText(this.name);
            tableTime.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(Clubes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregarJogadores() {
        try {
            String query = "select * from jogadores";
            stmt = this.connect.createStatement();
            set = stmt.executeQuery(query);
            while (set.next()) {
                jogadores.addItem(String.valueOf(set.getInt("id_jogador")) + "-" + set.getString("name_jogador"));
            }
            set.close();
        } catch (SQLException ex) {

        }
    }

    private void pesquisarUmTime() {
        try {
            String query = "select id,name,name_jogador from time t INNER JOIN jogadores j on t.id_jogador = j.id_jogador where name like '%" + this.nameTime.getText() + "%'";
            stmt = this.connect.createStatement();
            set = stmt.executeQuery(query);

            String[] columnNames = {"Id", "Nome Time", "Jogador"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            while (set.next()) {
                tableModel.addRow(new String[]{
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
                    set.getString("name_jogador")
                });

            }
            tableTime.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(Clubes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removerClube() {
        try {
            String query = "delete from time where id=?";
            mysql = this.connect.prepareStatement(query);
            mysql.setInt(1, this.id);
            mysql.execute();
            JOptionPane.showMessageDialog(null, "Time removido com sucesso!");
            listClubes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover" + ex.getMessage());
        }
    }

    public void editarClube() {
        try {
            String query = "update time set name=? where id=?";
            mysql = this.connect.prepareStatement(query);
            mysql.setString(1, nameTime.getText());
            // mysql.setInt(2, Integer.valueOf(this.id_jogador));
            mysql.setInt(2, this.id);
            mysql.execute();
            JOptionPane.showMessageDialog(null, "Time atualizado com sucesso!");
            listClubes();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover" + ex.getMessage());
        }
    }

    private void criarTime() throws SQLException {
        try {
            if (nameTime.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o nome do time");
            } else {
                mysql = this.connect.prepareStatement("insert into time (name,id_jogador) values (?,?)");
                mysql.setString(1, nameTime.getText());
                mysql.setInt(2, Integer.valueOf(jogadores.getSelectedItem().toString().substring(0, 1)));
                mysql.execute();
                JOptionPane.showMessageDialog(null, "Time cadastrado com sucesso!");
                listClubes(); /** Exibe mensagem e lista todos os times com a chamada da função */
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro" + e.getMessage()); /** Exibindo caixa de mensagens */
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jogadores = new javax.swing.JComboBox<>();
        nameTime = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTime = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crud Time");
        setResizable(false);

        jLabel1.setText("Nome:");

        jLabel2.setText("Jogadores:");

        jogadores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jogadoresItemStateChanged(evt);
            }
        });

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tableTime.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Jogador"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tableTimeFocusGained(evt);
            }
        });
        tableTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTimeMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableTimeMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(tableTime);

        jButton2.setText("Jogadores");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Editar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(nameTime, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jogadores, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jogadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            criarTime();
        } catch (SQLException ex) {
            Logger.getLogger(Clubes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jogadoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jogadoresItemStateChanged
        this.jogadores.setSelectedItem(evt.getItem().toString().substring(0, 1));
    }//GEN-LAST:event_jogadoresItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new JogadoresList().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Clubes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clubes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        pesquisarUmTime();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        removerClube();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tableTimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTimeMouseClicked
        int column = 0;
        int row = tableTime.getSelectedRow();
        String id = tableTime.getModel().getValueAt(row, column).toString();
        String name = tableTime.getModel().getValueAt(row, 1).toString();
        String id_jogador = tableTime.getModel().getValueAt(row, 2).toString();
        this.name = name;
        this.id_jogador = id_jogador;
        this.id = Integer.parseInt(id);
        nameTime.setText(this.name);
        this.isEditing = true;
        jButton4.enable();
    }//GEN-LAST:event_tableTimeMouseClicked

    private void tableTimeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTimeMouseExited
        this.name = "";
        this.id_jogador = "";
        this.isEditing = false;
    }//GEN-LAST:event_tableTimeMouseExited

    private void tableTimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tableTimeFocusGained
        this.isEditing = true;
    }//GEN-LAST:event_tableTimeFocusGained

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        editarClube();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Clubes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clubes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clubes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clubes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Clubes().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Clubes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Clubes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jogadores;
    private javax.swing.JTextField nameTime;
    private javax.swing.JTable tableTime;
    // End of variables declaration//GEN-END:variables
}
