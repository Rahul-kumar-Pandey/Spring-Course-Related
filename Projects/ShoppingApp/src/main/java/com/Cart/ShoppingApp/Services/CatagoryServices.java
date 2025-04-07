package com.Cart.ShoppingApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import com.Cart.ShoppingApp.Exception.AlreadyExistsException;
import com.Cart.ShoppingApp.Exception.ApiException;
import com.Cart.ShoppingApp.Exception.ResourceNotFound;
import com.Cart.ShoppingApp.Exception.ResourceNotFoundException;
import com.Cart.ShoppingApp.Repository.CatagoryRepository;
import com.Cart.ShoppingApp.model.Catagory;
import com.Cart.ShoppingApp.payload.CatagoryDto;
import com.Cart.ShoppingApp.payload.CatagoryResponse;

@Service
public class CatagoryServices {
	@Autowired
	CatagoryRepository catagoryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public CatagoryResponse getAllCatagory(Integer pageNumber ,Integer pageSize){
		//Implementing pagination
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Catagory>catPaged=catagoryRepository.findAll(pageable);
		
		List<Catagory>catList=catPaged.getContent();
		List<CatagoryDto>catagoryDtos= catList.stream().map(catagory->modelMapper.map(catagory, CatagoryDto.class)).toList();
		if(catList.isEmpty()) {
			throw new ApiException("Not Catagory Created Till now");
		}
		
		CatagoryResponse catagoryResponse=new CatagoryResponse();
		catagoryResponse.setContent(catagoryDtos);
		catagoryResponse.setPageNumber(catPaged.getNumber());
		catagoryResponse.setPageSize(catPaged.getSize());
		catagoryResponse.setTotalElements(catPaged.getTotalElements());
		catagoryResponse.setTotalPages(catPaged.getTotalPages());
		catagoryResponse.setLastPage(catPaged.isLast());
		
		return catagoryResponse;
	}
	public CatagoryDto createCatagory(CatagoryDto catagoryDto) {
		Catagory catagory=modelMapper.map(catagoryDto, Catagory.class);
		
		if(catagoryRepository.findByName(catagory.getName())!=null) {
			throw new ApiException("catagory with "+catagoryDto.getName()+" already Exists");
		}
		Catagory savedCatagory=catagoryRepository.save(catagory);
		return modelMapper.map(savedCatagory,CatagoryDto.class);
	}
	public CatagoryDto deleteCatagory(Long catId) {
		Catagory existingCat=catagoryRepository.findById(catId)
		.orElseThrow(()->new ResourceNotFoundException("Catagory","catagoryId",catId));
		
		catagoryRepository.delete(existingCat);
		
		CatagoryDto catagoryDto=modelMapper.map(existingCat, CatagoryDto.class);
		return catagoryDto;
	}
	public CatagoryDto updateCatagory(CatagoryDto catagoryDto,Long catId){
		
		Catagory existingCat=catagoryRepository.findById(catId)
		.orElseThrow(()->new ResourceNotFoundException("Catagory","catagoryId",catId));
		
		Catagory catagory=modelMapper.map(catagoryDto, Catagory.class);
		catagory.setCatId(catId);
		Catagory savedCatagory=catagoryRepository.save(catagory);
		return modelMapper.map(savedCatagory,CatagoryDto.class);
	}
	/////////////////////////////////////////////////////////////
	Catagory getCatagoryById(Long Id) {
		return catagoryRepository.findById(Id).orElseThrow(()->new ResourceNotFound("catagory not found!"));
	}
	Catagory getCatagoryByName(String name){
		return catagoryRepository.findByName(name);
	}
//	List<Catagory>getAllCatagory(){
//		return catagoryRepository.findAll();
//	}
//	Catagory addCatagory(Catagory catagory) {
//		Catagory catagoryData=catagoryRepository.findByName(catagory.getName());
//		if(null!=catagoryData) {
//			throw new AlreadyExistsException("Catagory Already Found!"); 
//		}
//		return catagoryRepository.save(catagory);
//	}
//	Catagory updateCatagory(Catagory catagory,Long id) {
//		return Optional.ofNullable(getCatagoryById(id)).map(oldCatagory->{
//			oldCatagory.setName(catagory.getName());
//			return catagoryRepository.save(oldCatagory);
//		}).orElseThrow(()->new ResourceNotFound("catagory not found!"));
//	}
//	void deleteCatagory(Long id) {
//		catagoryRepository.findById(id).ifPresentOrElse(catagoryRepository:: delete, ()->new ResourceNotFound("catagory not found!"));
//	}
}
