/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes.controller;

import id.ac.unpas.Tubes.connection.connectionDB;
import id.ac.unpas.Tubes.view.PanelAnggota;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arya
 */
public class AnggotaController {
    private PanelAnggota view;

    public AnggotaController(PanelAnggota view) {
        this.view = view;
        loadTable();
        actionButton();
        actionTable();
    }

    private void actionButton() {
        view.btnSimpan.addActionListener(e -> insert());
        view.btnUbah.addActionListener(e -> update());
        view.btnHapus.addActionListener(e -> delete());
        view.btnReset.addActionListener(e -> reset());
    }

    private void actionTable() {
        view.tableAnggota.getSelectionModel().addListSelectionListener(e -> {
            int row = view.tableAnggota.getSelectedRow();
            if (row >= 0) {
                view.txtId.setText(view.tableModel.getValueAt(row, 0).toString());
                view.txtNama.setText(view.tableModel.getValueAt(row, 1).toString());
                view.txtAlamat.setText(view.tableModel.getValueAt(row, 2).toString());
                view.txtNoTelp.setText(view.tableModel.getValueAt(row, 3).toString());
            }
        });
    }

    private void insert() {
        if (view.txtNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nama tidak boleh kosong");
            return;
        }

        try {
            String sql = "INSERT INTO anggota (nama, alamat, no_telp) VALUES (?,?,?)";
            PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
            ps.setString(1, view.txtNama.getText());
            ps.setString(2, view.txtAlamat.getText());
            ps.setString(3, view.txtNoTelp.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(view, "Data berhasil disimpan");
            loadTable();
            reset();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    private void update() {
        if (view.txtId.getText().isEmpty()) return;

        try {
            String sql = "UPDATE anggota SET nama=?, alamat=?, no_telp=? WHERE id_anggota=?";
            PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
            ps.setString(1, view.txtNama.getText());
            ps.setString(2, view.txtAlamat.getText());
            ps.setString(3, view.txtNoTelp.getText());
            ps.setInt(4, Integer.parseInt(view.txtId.getText()));
            ps.executeUpdate();

            loadTable();
            reset();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    private void delete() {
        if (view.txtId.getText().isEmpty()) return;

        int confirm = JOptionPane.showConfirmDialog(view, "Hapus data?");
        if (confirm == 0) {
            try {
                String sql = "DELETE FROM anggota WHERE id_anggota=?";
                PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(view.txtId.getText()));
                ps.executeUpdate();

                loadTable();
                reset();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
    }

    private void loadTable() {
        DefaultTableModel model = view.tableModel;
        model.setRowCount(0);

        try {
            Statement st = connectionDB.configDB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM anggota");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_anggota"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("no_telp")
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reset() {
        view.txtId.setText("");
        view.txtNama.setText("");
        view.txtAlamat.setText("");
        view.txtNoTelp.setText("");
    } 
}
