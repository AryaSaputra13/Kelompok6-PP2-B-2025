/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Tubes.model;

/**
 *
 * @author Arya
 */
public class Buku {
    private int idBuku;
    private String judul;
    private String penulis;
    private int stok;

    public Buku() {
    }

    public Buku(int idBuku, String judul, String penulis, int stok) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.stok = stok;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
