package com.example.catalog.controllers;

import com.example.catalog.models.Atribut;
import com.example.catalog.models.Product;
import com.example.catalog.service.AtributService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AtributControllers {

    private final AtributService atributService;

    public AtributControllers(AtributService atributService) {
        this.atributService = atributService;
    }
    @GetMapping
    public List<Atribut> getAllAtributs() {
        return atributService.getAllAtributs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atribut> getAtributById(@PathVariable Long id) {
        return atributService.getAtributById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tovars")
    public ResponseEntity<List<Product>> getTovarsByAtribut(@PathVariable Long id) {
        return atributService.getTovarsByAtribut(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/tovar/{tovarId}")
    public ResponseEntity<Atribut> createAtributForProducts(@PathVariable Long tovarId, @RequestBody Atribut atribut) {
        return atributService.createAtributForProduct(tovarId, atribut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404, если товар не найден
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atribut> updateAtribut(@PathVariable Long id, @RequestBody Atribut request) {
        return atributService.updateAtribut(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404, если атрибут не найден
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Atribut> deleteAtribut(@PathVariable Long id) {
        return atributService.deleteAtribut(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404, если нечего удалять
    }

}
