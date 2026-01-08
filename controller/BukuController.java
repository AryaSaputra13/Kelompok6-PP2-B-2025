/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes.controller;

import id.ac.unpas.Tubes.connection.connectionDB;
import id.ac.unpas.Tubes.view.PanelBuku;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 *
 * @author Arya
 */
public class BukuController {
    private PanelBuku view;

    public BukuController(PanelBuku view) {
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
    
    private void actionTable(){
        view.tableBuku.getSelectionModel().addListSelectionListener(e -> {
            int row = view.tableBuku.getSelectedRow();
            if (row != -1) {
                view.txtId.setText(view.tableModel.getValueAt(row, 0).toString());
                view.txtJudul.setText(view.tableModel.getValueAt(row, 1).toString());
                view.txtPenulis.setText(view.tableModel.getValueAt(row, 2).toString());
                view.txtStok.setText(view.tableModel.getValueAt(row, 3).toString());
            }
        }); 
    }
    
    private boolean validasiInput() {
        String judul = view.txtJudul.getText().trim();
        String penulis = view.txtPenulis.getText().trim();
        String stokText = view.txtStok.getText().trim();

        if (judul.isEmpty() || penulis.isEmpty() || stokText.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field wajib diisi");
            return false;
        }

        if (!judul.matches("[a-zA-Z0-9\\s]+")) {
            JOptionPane.showMessageDialog(view, "Judul hanya boleh berisi huruf dan angka");
            return false;
        }

        if (!penulis.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(view, "Nama penulis hanya boleh berisi huruf");
            return false;
        }

        try {
            int stok = Integer.parseInt(stokText);
            if (stok < 0) {
                JOptionPane.showMessageDialog(view, "Stok tidak boleh negatif");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Stok harus berupa angka");
            return false;
        }

        return true;
    }

    private void insert() {
        if (!validasiInput()) return;

        try {
            String sql = "INSERT INTO buku (judul, penulis, stok) VALUES (?,?,?)";
            PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
            ps.setString(1, view.txtJudul.getText());
            ps.setString(2, view.txtPenulis.getText());
            ps.setInt(3, Integer.parseInt(view.txtStok.getText()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(view, "Data buku berhasil ditambahkan");
            loadTable();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void update() {
        if (view.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data buku terlebih dahulu");
            return;
        }

        if (!validasiInput()) return;

        try {
            String sql = "UPDATE buku SET judul=?, penulis=?, stok=? WHERE id_buku=?";
            PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
            ps.setString(1, view.txtJudul.getText());
            ps.setString(2, view.txtPenulis.getText());
            ps.setInt(3, Integer.parseInt(view.txtStok.getText()));
            ps.setInt(4, Integer.parseInt(view.txtId.getText()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(view, "Data buku berhasil diubah");
            loadTable();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void delete() {
        if (view.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data buku terlebih dahulu");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Apakah yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM buku WHERE id_buku=?";
                PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(view.txtId.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(view, "Data buku berhasil dihapus");
                loadTable();
                reset();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
            }
        }
    }

    private void loadTable() {
        DefaultTableModel model = view.tableModel;
        model.setRowCount(0);

        try {
            Statement st = connectionDB.configDB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM buku");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_buku"),
                        rs.getString("judul"),
                        rs.getString("penulis"),
                        rs.getInt("stok")
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reset() {
        view.txtId.setText("");
        view.txtJudul.setText("");
        view.txtPenulis.setText("");
        view.txtStok.setText("");
    }
}
