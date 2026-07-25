package com.jasmine.produk.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.jasmine.produk.model.produk;
import com.jasmine.produk.service.ProdukService;

@RestController
@RequestMapping("/api/produk")   
public class ProdukControler {

    @Autowired
    private ProdukService produkService;

    @GetMapping
    public List<produk> getAllProduk() {
        return produkService.getAllProduks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<produk> getProdukById(@PathVariable Long id){
        produk produk = produkService.getProdukbyId(id);
        return produk != null 
            ? ResponseEntity.ok(produk) 
            : ResponseEntity.notFound().build();
    }

    @PostMapping
    public produk createProduk(@RequestBody produk produk) {
        return produkService.createProduk(produk);
    }

    @DeleteMapping("/{id}")   // ← PERBAIKI INI
    public ResponseEntity<?> deleteProduk(@PathVariable Long id){
        produkService.deleteProduk(id);
        return ResponseEntity.ok().build();
    }
}