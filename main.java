/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes;

import id.ac.unpas.Tubes.controller.AnggotaController;
import id.ac.unpas.Tubes.controller.BukuController;
import id.ac.unpas.Tubes.controller.PeminjamanController;
import id.ac.unpas.Tubes.view.PanelAnggota;
import id.ac.unpas.Tubes.view.PanelBuku;
import id.ac.unpas.Tubes.view.PanelPeminjaman;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Arya
 */
public class main extends JFrame {
    public main() {
        setTitle("Sistem Informasi Perpustakaan");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MENU
        JButton btnAnggota = new JButton("Data Anggota");
        JButton btnBuku = new JButton("Data Buku");
        JButton btnPeminjaman = new JButton("Peminjaman");

        JPanel panelMenu = new JPanel(new GridLayout(3, 1, 5, 5));
        panelMenu.add(btnAnggota);
        panelMenu.add(btnBuku);
        panelMenu.add(btnPeminjaman);

        // CONTENT
        CardLayout cardLayout = new CardLayout();
        JPanel panelContent = new JPanel(cardLayout);

        PanelAnggota panelAnggota = new PanelAnggota();
        PanelBuku panelBuku = new PanelBuku();
        PanelPeminjaman panelPeminjaman = new PanelPeminjaman();

        // CONTROLLER
        new AnggotaController(panelAnggota);
        new BukuController(panelBuku);
        PeminjamanController peminjamanController = new PeminjamanController(panelPeminjaman);

        panelContent.add(panelAnggota, "anggota");
        panelContent.add(panelBuku, "buku");
        panelContent.add(panelPeminjaman, "peminjaman");

        // ACTION MENU
        btnAnggota.addActionListener(e -> cardLayout.show(panelContent, "anggota"));
        btnBuku.addActionListener(e -> cardLayout.show(panelContent, "buku"));
        btnPeminjaman.addActionListener(e -> { 
            peminjamanController.refreshCombo();
            cardLayout.show(panelContent, "peminjaman");
        });

        setLayout(new BorderLayout());
        add(panelMenu, BorderLayout.WEST);
        add(panelContent, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new main().setVisible(true);
        });
    }
}
