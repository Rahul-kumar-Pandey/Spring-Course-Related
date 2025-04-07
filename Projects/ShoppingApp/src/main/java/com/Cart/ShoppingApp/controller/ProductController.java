package com.Cart.ShoppingApp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Cart.ShoppingApp.Services.ProductService;
import com.Cart.ShoppingApp.model.Product;
import com.Cart.ShoppingApp.payload.ProductDto;
import com.Cart.ShoppingApp.payload.ProductResponse;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/admin/categories/{catId}/product")
	public ResponseEntity<ProductDto>addProduct(@RequestBody Product product,@PathVariable Long catId){
		ProductDto productDto=productService.addProduct(product,catId);
		return new ResponseEntity<>(productDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/public/products")
	public ResponseEntity<ProductResponse>getAllProducts(){
		ProductResponse productResponse=productService.getAllProducts();
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@GetMapping("/public/categories/{catId}/product")
	public ResponseEntity<ProductResponse>getProductByCatagory(@PathVariable Long catId){
		ProductResponse productResponse=productService.getProductByCatagory(catId);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@GetMapping("/public/products/keyword/{keyword}")
	public ResponseEntity<ProductResponse>getProductByCatagory(@PathVariable String keyword){
		ProductResponse productResponse=productService.getProductByKeyword(keyword);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@PostMapping("/admin/products/{productId}")
	public ResponseEntity<ProductDto>updateProduct(@RequestBody Product product,@PathVariable Long productId){
		ProductDto productDto=productService.updateProduct(product,productId);
		return new ResponseEntity<>(productDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/products/{productId}")
	public ResponseEntity<ProductDto>deleteProduct(@PathVariable Long productId){
		ProductDto productDto=productService.deleteProduct(productId);
		return new ResponseEntity<>(productDto,HttpStatus.OK);
	}
	
	@PutMapping("/products/{productId}/image")
	public ResponseEntity<ProductDto>updateProductImage(@PathVariable Long productId,@RequestParam("image")MultipartFile image) throws IOException{
		ProductDto productDto=productService.updateProductImage(productId,image);
		return new ResponseEntity<>(productDto,HttpStatus.OK);
	}
}
