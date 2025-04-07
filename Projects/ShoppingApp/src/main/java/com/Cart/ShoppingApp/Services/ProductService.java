package com.Cart.ShoppingApp.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Cart.ShoppingApp.Exception.ResourceNotFoundException;
import com.Cart.ShoppingApp.Repository.CatagoryRepository;
import com.Cart.ShoppingApp.Repository.ProductRepository;
import com.Cart.ShoppingApp.model.Catagory;
import com.Cart.ShoppingApp.model.Product;
import com.Cart.ShoppingApp.payload.ProductDto;
import com.Cart.ShoppingApp.payload.ProductResponse;


@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CatagoryRepository catagoryRepository;
	@Autowired
	ModelMapper modelMapper;
	public ProductDto addProduct(Product product, Long catId) {
		
		//Product product=modelMapper.map(productDto, Product.class);
		
		Catagory catagory=catagoryRepository.findById(catId).orElseThrow(()->
			new ResourceNotFoundException("Catagory","catagoryId",catId));
		
		product.setCatagory(catagory);
		product.setProductImage("default.png");
		
		return modelMapper.map(productRepository.save(product), ProductDto.class);
	}
	public ProductResponse getAllProducts() {
		
		List<Product>products=productRepository.findAll();
		List<ProductDto>productDtos=products.stream().map(product->modelMapper.map(product, ProductDto.class)).toList();
		ProductResponse productResponse=new ProductResponse();
		
		productResponse.setContents(productDtos);
		return productResponse;
	}
	public ProductResponse getProductByCatagory(Long catId) {
		Catagory catagory=catagoryRepository.findById(catId).orElseThrow(()->
		new ResourceNotFoundException("Catagory","catagoryId",catId));
		
		
		List<ProductDto>productDtos=productRepository.findByCatagory(catagory).stream()
				.map(product->modelMapper.map(product, ProductDto.class)).toList();
		
		ProductResponse productResponse=new ProductResponse();
		
		productResponse.setContents(productDtos);
		return productResponse;	
	}
	public ProductResponse getProductByKeyword(String keyword) {
		List<ProductDto>productDtos=productRepository.findByProductNameLikeIgnoreCase('%'+keyword+'%').stream()
				.map(product->modelMapper.map(product, ProductDto.class)).toList();
		
		ProductResponse productResponse=new ProductResponse();
		
		productResponse.setContents(productDtos);
		return productResponse;	
	}
	public ProductDto updateProduct(Product product, Long productId) {
		//get existing product
		Product existingProduct=productRepository.findById(productId)
				.orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));
		//update product
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductDesc(product.getProductDesc());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setSpecialPrice(product.getSpecialPrice());
		
		Product savedProduct=productRepository.save(existingProduct);
		return modelMapper.map(savedProduct, ProductDto.class);
	}
	public ProductDto deleteProduct(Long productId) {
		Product existingProduct=productRepository.findById(productId)
				.orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));
		
		productRepository.delete(existingProduct);
		return modelMapper.map(existingProduct, ProductDto.class);
	}
	public ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException {
		//get product from DB
		Product productDB=productRepository.findById(productId)
				.orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));
		//upload image to server
		//Get the fileName of the uploaded image
		String path="images/";
		String fileName=uploadImage(path,image);
		
		//Updating the new fileName to the product
		productDB.setProductImage(fileName);
		
		//Save Update Product
		Product updatedProduct=productRepository.save(productDB);
		
		//return DTO after mapping product to DTO
		return modelMapper.map(updatedProduct,ProductDto.class);
	}
	private String uploadImage(String path, MultipartFile file) throws IOException {
		//file Names of original image
		String originalFileName=file.getOriginalFilename();
		
		//Generate a unique file Name
		String randomId=UUID.randomUUID().toString();
		
		String fileName=randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
		String filePath=path+File.separator+fileName;
		
		//check if path exist and create
		File folder=new File(path);
		if(!folder.exists()) {
			folder.mkdir();
		}
		//upload to server
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		// returing file Name
		return fileName;
	}
	
	
}
