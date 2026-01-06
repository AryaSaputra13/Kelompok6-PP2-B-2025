/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes.controller;

import id.ac.unpas.Tubes.connection.connectionDB;
import id.ac.unpas.Tubes.view.PanelPeminjaman;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 *
 * @author Arya
 */
public class PeminjamanController {
   private PanelPeminjaman view;

    public PeminjamanController(PanelPeminjaman view) {
        this.view = view;
        loadCombo();
        loadTable();
        actionButton();
    }

    private void actionButton() {
        view.btnSimpan.addActionListener(e -> insert());
        view.btnUbah.addActionListener(e -> update());
        view.btnHapus.addActionListener(e -> delete());
        view.btnReset.addActionListener(e -> reset());

        view.tablePeminjaman.getSelectionModel().addListSelectionListener(e -> {
            int row = view.tablePeminjaman.getSelectedRow();
            if (row != -1) {
                view.txtId.setText(view.tableModel.getValueAt(row, 0).toString());
                view.txtTanggal.setText(view.tableModel.getValueAt(row, 3).toString());

                view.cbAnggota.setSelectedItem(
                    view.tableModel.getValueAt(row, 1).toString()
                );
                view.cbBuku.setSelectedItem(
                    view.tableModel.getValueAt(row, 2).toString()
                );
            }
        });
    }

    private void loadCombo() {
        try {
            Statement st = connectionDB.configDB().createStatement();
            ResultSet rsA = st.executeQuery("SELECT * FROM anggota");
            while (rsA.next()) {
                view.cbAnggota.addItem(rsA.getInt("id_anggota") + " - " + rsA.getString("nama"));
            }

            ResultSet rsB = st.executeQuery("SELECT * FROM buku WHERE stok > 0");
            while (rsB.next()) {
                view.cbBuku.addItem(rsB.getInt("id_buku") + " - " + rsB.getString("judul"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void refreshCombo() {
        view.cbAnggota.removeAllItems();
        view.cbBuku.removeAllItems();

        try {
            Statement st = connectionDB.configDB().createStatement();

            ResultSet rsAnggota = st.executeQuery("SELECT * FROM anggota");
            while (rsAnggota.next()) {
                view.cbAnggota.addItem(
                    rsAnggota.getInt("id_anggota") + " - " +
                    rsAnggota.getString("nama")
                );
            }

            ResultSet rsBuku = st.executeQuery("SELECT * FROM buku WHERE stok > 0");
            while (rsBuku.next()) {
                view.cbBuku.addItem(
                    rsBuku.getInt("id_buku") + " - " +
                    rsBuku.getString("judul")
                );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void insert() {
        try {
            int idAnggota = Integer.parseInt(view.cbAnggota.getSelectedItem().toString().split(" - ")[0]);
            int idBuku = Integer.parseInt(view.cbBuku.getSelectedItem().toString().split(" - ")[0]);

            String sql = "INSERT INTO peminjaman (id_anggota, id_buku, tanggal_pinjam) VALUES (?,?,?)";
            PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
            ps.setInt(1, idAnggota);
            ps.setInt(2, idBuku);
            ps.setDate(3, Date.valueOf(view.txtTanggal.getText()));
            ps.executeUpdate();

            loadTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Input tidak valid");
        }
    }
    
    private void update() {
        if (view.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan diubah");
            return;
        }

        try {
            int idAnggota = Integer.parseInt(
                view.cbAnggota.getSelectedItem().toString().split(" - ")[0]
            );
            int idBuku = Integer.parseInt(
                view.cbBuku.getSelectedItem().toString().split(" - ")[0]
            );

            String sql = "UPDATE peminjaman SET id_anggota=?, id_buku=?, tanggal_pinjam=? WHERE id_peminjaman=?";
            PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
            ps.setInt(1, idAnggota);
            ps.setInt(2, idBuku);
            ps.setDate(3, Date.valueOf(view.txtTanggal.getText()));
            ps.setInt(4, Integer.parseInt(view.txtId.getText()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(view, "Data berhasil diubah");
            loadTable();
            reset();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Input tidak valid");
        }
    }
    
    private void reset() {
        view.txtId.setText("");
        view.txtTanggal.setText("");
        view.cbAnggota.setSelectedIndex(-1);
        view.cbBuku.setSelectedIndex(-1);
    }

    private void delete() {
        try {
            String sql = "DELETE FROM peminjaman WHERE id_peminjaman=?";
            PreparedStatement ps = connectionDB.configDB().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(view.txtId.getText()));
            ps.executeUpdate();
            loadTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void loadTable() {
        DefaultTableModel model = view.tableModel;
        model.setRowCount(0);

        try {
            Statement st = connectionDB.configDB().createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT p.id_peminjaman, a.nama, b.judul, p.tanggal_pinjam " +
                    "FROM peminjaman p JOIN anggota a ON p.id_anggota=a.id_anggota " +
                    "JOIN buku b ON p.id_buku=b.id_buku");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4)
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
