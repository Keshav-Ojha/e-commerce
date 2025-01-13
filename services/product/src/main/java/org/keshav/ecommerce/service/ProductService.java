package org.keshav.ecommerce.service;

import jakarta.validation.Valid;
import org.keshav.ecommerce.product.ProductPurchaseResponse;
import org.keshav.ecommerce.product.ProductPurchaseRequest;
import org.keshav.ecommerce.product.ProductRequest;
import org.keshav.ecommerce.product.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer addProduct(@Valid ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
