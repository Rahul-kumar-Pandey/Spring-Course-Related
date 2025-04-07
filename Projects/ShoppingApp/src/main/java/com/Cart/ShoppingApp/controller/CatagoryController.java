package com.Cart.ShoppingApp.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Cart.ShoppingApp.Services.CatagoryServices;
import com.Cart.ShoppingApp.config.AppConstants;
import com.Cart.ShoppingApp.model.Catagory;
import com.Cart.ShoppingApp.payload.CatagoryDto;
import com.Cart.ShoppingApp.payload.CatagoryResponse;

import jakarta.validation.Valid;

@RestController
public class CatagoryController {

		@Autowired
		CatagoryServices catagoryServices;
		
		@GetMapping("/api/public/catagories")
		public ResponseEntity<CatagoryResponse>getAllCatagory(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
				@RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize){
			CatagoryResponse catagoryResponse= catagoryServices.getAllCatagory(pageNumber,pageSize);
			return new ResponseEntity<>(catagoryResponse,HttpStatus.OK);
		}
		
		@PostMapping("/api/admin/catagories")
		public ResponseEntity<CatagoryDto> createCatagory(@Valid @RequestBody CatagoryDto catagoryDto) {
			CatagoryDto savedCatagoryDto=catagoryServices.createCatagory(catagoryDto);
			return new ResponseEntity<>(savedCatagoryDto,HttpStatus.OK);
		}
		
		@DeleteMapping("/api/admin/catagories/{catId}")
		public ResponseEntity<CatagoryDto> deleteCatagory(@PathVariable Long catId) {
			CatagoryDto delCatagoryDto=catagoryServices.deleteCatagory(catId);
			return new ResponseEntity<>(delCatagoryDto,HttpStatus.OK);
		}
		@PutMapping("/api/admin/catagories/{catId}")
		public ResponseEntity<CatagoryDto>updateCatagory(@RequestBody CatagoryDto catagory,@PathVariable Long catId){
			CatagoryDto savedCatagoryDtos=catagoryServices.updateCatagory(catagory,catId);
			return new ResponseEntity<>(savedCatagoryDtos,HttpStatus.OK);
		}
}
