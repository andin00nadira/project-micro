package com.jasmine.consumer;

public class Order {

    private String nama;
    private String menu;
    private int jumlah;
    private String email; // 🔥 TAMBAHAN

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getMenu() { return menu; }
    public void setMenu(String menu) { this.menu = menu; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}