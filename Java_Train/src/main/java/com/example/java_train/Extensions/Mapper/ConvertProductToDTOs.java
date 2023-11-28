package com.example.java_train.Extensions.Mapper;

import com.example.java_train.Entities.Product;
import com.example.java_train.Models.ProductModel.GetProductModel;

import java.time.LocalDate;

public class ConvertProductToDTOs {
    public static GetProductModel ConvertToGetProductModel(Product product){
        GetProductModel getProductModel = new GetProductModel();
        getProductModel.setProductId(product.getId());
        getProductModel.setProductName(product.getProductName());
        getProductModel.setPrice(product.getPrice());
        getProductModel.setDescc(product.getDescc());
        getProductModel.setNgayHetHan(product.getNgayHetHan());

        getProductModel.setProductTypeId(product.getProductType().getId());
        getProductModel.setProductTypeName(product.getProductType().getTypeName());

        return getProductModel;
    }
}
