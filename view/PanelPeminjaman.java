/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Arya
 */
public class PanelPeminjaman extends JPanel {
    public JTextField txtId, txtTanggal;
    public JComboBox<String> cbAnggota, cbBuku;
    public JButton btnSimpan, btnUbah, btnHapus, btnReset;

    public JTable tablePeminjaman;
    public DefaultTableModel tableModel;

    public PanelPeminjaman() {
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {

        // ID peminjaman disimpan tapi tidak ditampilkan
        txtId = new JTextField();
        txtId.setVisible(false);

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));

        panelInput.add(new JLabel("Anggota"));
        cbAnggota = new JComboBox<>();
        panelInput.add(cbAnggota);

        panelInput.add(new JLabel("Buku"));
        cbBuku = new JComboBox<>();
        panelInput.add(cbBuku);

        panelInput.add(new JLabel("Tanggal Pinjam (yyyy-mm-dd)"));
        txtTanggal = new JTextField();
        panelInput.add(txtTanggal);

        // Panel Button
        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("Reset");

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(btnSimpan);
        panelButton.add(btnUbah);
        panelButton.add(btnHapus);
        panelButton.add(btnReset);

        // Panel Atas
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelButton, BorderLayout.SOUTH);

        // Table
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Anggota", "Buku", "Tanggal"}, 0
        );
        tablePeminjaman = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePeminjaman);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
