/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes.view;

/**
 *
 * @author Flavio 
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelBuku extends JPanel {
    public JTextField txtId, txtJudul, txtPenulis, txtStok;
    public JButton btnSimpan, btnUbah, btnHapus, btnReset;

    public JTable tableBuku;
    public DefaultTableModel tableModel;

    public PanelBuku() {
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {

        // ID disimpan tapi tidak ditampilkan
        txtId = new JTextField();
        txtId.setVisible(false);

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));

        panelInput.add(new JLabel("Judul"));
        txtJudul = new JTextField();
        panelInput.add(txtJudul);

        panelInput.add(new JLabel("Penulis"));
        txtPenulis = new JTextField();
        panelInput.add(txtPenulis);

        panelInput.add(new JLabel("Stok"));
        txtStok = new JTextField();
        panelInput.add(txtStok);

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
                new Object[]{"ID", "Judul", "Penulis", "Stok"}, 0
        );
        tableBuku = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableBuku);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
