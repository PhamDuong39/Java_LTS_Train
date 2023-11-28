package com.example.java_train.ServicesImp;

import com.example.java_train.Entities.Product;
import com.example.java_train.Extensions.Mapper.ConvertProductToDTOs;
import com.example.java_train.Models.CommonModel.Pagination;
import com.example.java_train.Models.ProductModel.CreateProductModel;
import com.example.java_train.Models.ProductModel.EditProductModel;
import com.example.java_train.Models.ProductModel.GetProductModel;
import com.example.java_train.Repositories.ProductRepository;
import com.example.java_train.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository _productRepository;

    @Override
    public ResponseEntity<Page<GetProductModel>> GetProducts(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize());

        Page<GetProductModel> page = _productRepository.findALlProduct(pageable);

        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<CreateProductModel> Create(CreateProductModel createProductModel) {
        return null;
    }

    @Override
    public ResponseEntity<EditProductModel> Edit(EditProductModel editProductModel) {
        return null;
    }

    @Override
    public ResponseEntity<GetProductModel> GetById(int productId) {
        return null;
    }

    @Override
    public ResponseEntity<Product> Delete(int productId) {
        return null;
    }
}
