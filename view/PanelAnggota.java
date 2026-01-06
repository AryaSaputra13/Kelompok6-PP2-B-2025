/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author Flavio Boniperti
 */
public class PanelAnggota extends JPanel {
    public JTextField txtId, txtNama, txtAlamat, txtNoTelp;
    public JButton btnSimpan, btnUbah, btnHapus, btnReset;

    public JTable tableAnggota;
    public DefaultTableModel tableModel;

    public PanelAnggota() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {

        // txtId tetap ada tapi disembunyikan
        txtId = new JTextField();
        txtId.setVisible(false);

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));

        panelInput.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Alamat"));
        txtAlamat = new JTextField();
        panelInput.add(txtAlamat);

        panelInput.add(new JLabel("No Telp"));
        txtNoTelp = new JTextField();
        panelInput.add(txtNoTelp);

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

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelButton, BorderLayout.SOUTH);

        // Table
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Nama", "Alamat", "No Telp"}, 0
        );
        tableAnggota = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableAnggota);

        setLayout(new BorderLayout());
        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
