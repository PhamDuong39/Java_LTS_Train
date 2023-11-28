package com.example.java_train.Controller;

import com.example.java_train.Models.CommonModel.Pagination;
import com.example.java_train.Models.ProductModel.GetProductModel;
import com.example.java_train.Services.ProductService;
import com.example.java_train.ServicesImp.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get-products")
    public ResponseEntity<?> getAllProduct(@RequestParam(value = "PageSize", required = false) Integer PageSize,
                                           @RequestParam(value = "PageNumber", required = false) Integer PageNumber) {
        Pagination pagination = new Pagination(PageSize, PageNumber);

        return productService.GetProducts(pagination);
    }
}
