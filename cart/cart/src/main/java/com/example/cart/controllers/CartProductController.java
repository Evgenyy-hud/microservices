package com.example.cart.controllers;


import com.example.cart.ProductClient;
import com.example.cart.models.CartProduct;
import com.example.cart.models.CartProductDTO;
import com.example.cart.models.CartRequest;
import com.example.cart.models.CartResponseDTO;
import com.example.cart.repo.CartRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartProductController {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public CartProductController(CartRepository cartRepository, ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.productClient = productClient;
    }

    @GetMapping
    public ResponseEntity<List<CartResponseDTO>> getCart() {
        List<CartResponseDTO> response = new ArrayList<>();
        for (CartProduct item : cartRepository.findAll()) {
            try {
                CartProductDTO tovar = productClient.getTovarDTO(item.getTovarId());
                response.add(new CartResponseDTO(
                        item.getId(),
                        item.getTovarId(),
                        tovar.getTitle(),
                        tovar.getDescription(),
                        tovar.getPrice(),
                        item.getCount(),
                        tovar.getAttributes()
                ));
            } catch (FeignException.NotFound e) {

            } catch (FeignException e) {

            }
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{tovarId}")
    public ResponseEntity<CartProduct> addToCart(@PathVariable Long tovarId,
                                                 @RequestBody CartRequest request) {
        try {
            productClient.getTovarDTO(tovarId);
        } catch (FeignException.NotFound ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }


        Optional<CartProduct> cartProductOpt = cartRepository.findByTovarId(tovarId);

        if (cartProductOpt.isEmpty()) {
            CartProduct newItem = new CartProduct(null, tovarId, request.getCount());
            return ResponseEntity.status(HttpStatus.CREATED).body(cartRepository.save(newItem));
        }
        CartProduct existingItem = cartProductOpt.get();
        existingItem.setCount(existingItem.getCount() + request.getCount());

        return ResponseEntity.ok(cartRepository.save(existingItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id){
        if(!cartRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        cartRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
