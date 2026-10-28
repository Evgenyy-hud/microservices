
package com.example.cart;
import com.example.cart.models.CartProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service",url="${tovar-service.url}")
public interface ProductClient {
    @GetMapping("/tovars/{id}")
    CartProductDTO getTovarDTO(@PathVariable("id") Long id);
}
