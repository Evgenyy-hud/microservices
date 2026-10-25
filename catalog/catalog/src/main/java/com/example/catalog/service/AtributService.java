package com.example.catalog.service;

import com.example.catalog.models.Atribut;
import com.example.catalog.models.Product;
import com.example.catalog.repo.AtributRepository;
import com.example.catalog.repo.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class AtributService {
    private final AtributRepository atributRepository;
    private final ProductRepository productRepository;

    public AtributService(AtributRepository atributRepository, ProductRepository productRepository) {
        this.atributRepository = atributRepository;
        this.productRepository = productRepository;
    }

    public List<Atribut> getAllAtributs() {
        return atributRepository.findAll();
    }

    public Optional<Atribut> getAtributById(Long id) {
        return atributRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<List<Product>> getTovarsByAtribut(Long id) {
        return atributRepository.findById(id)
                .map(Atribut::getProducts);
    }

    @Transactional
    public Optional<Atribut> createAtributForProduct(Long tovarId, Atribut atribut) {
        Optional<Product> optionalProduct = productRepository.findById(tovarId);
        if (optionalProduct.isEmpty()) {
            return Optional.empty();
        }
        Product product = optionalProduct.get();
        atribut.getProducts().add(product);
        return Optional.of(atributRepository.save(atribut));
    }

    @Transactional
    public Optional<Atribut> updateAtribut(Long id, Atribut request) {
        return atributRepository.findById(id).map(existingAtribut -> {
            existingAtribut.setColor(request.getColor());
            existingAtribut.setMemory(request.getMemory());
            existingAtribut.setRam(request.getRam());
            return atributRepository.save(existingAtribut);
        });
    }
    @Transactional
    public Optional<Atribut> deleteAtribut(Long id) {
        return atributRepository.findById(id).map(atribut -> {
            atributRepository.deleteById(id);
            return atribut;
        });
    }
}
