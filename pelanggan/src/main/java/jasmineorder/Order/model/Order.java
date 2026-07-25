package jasmineorder.Order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double harga;
    private int jumlah;
    private double total;

    public Order() {
    }

    public Order(Long id, double harga, int jumlah) {
        this.id = id;
        this.harga = harga;
        this.jumlah = jumlah;
        this.total = harga * jumlah;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
        hitungTotal();
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
        hitungTotal();
    }

    public double getTotal() {
        return total;
    }

    public void hitungTotal() {
        this.total = this.harga * this.jumlah;
    }
}