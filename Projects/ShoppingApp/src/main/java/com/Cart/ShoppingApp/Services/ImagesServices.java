package com.Cart.ShoppingApp.Services;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Cart.ShoppingApp.Exception.ResourceNotFound;
import com.Cart.ShoppingApp.Repository.ImageRepository;
import com.Cart.ShoppingApp.model.Images;

@Service
public class ImagesServices {
	@Autowired
	ImageRepository imageRepository;
	
	public Images getImageById(Long id){
		return imageRepository.findById(id).orElseThrow(()->new ResourceNotFound("Image not Found with id: "+id));
	}
	public void deleteImage(Long id) {
		imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, ()->new ResourceNotFound("Image not Found with id: "+id));
	}
	public void updateImage(MultipartFile file,Long imageId) throws Exception {
		Images image=getImageById(imageId);
		try {
			image.setfileName(file.getOriginalFilename());
			image.setImage(new SerialBlob(file.getBytes()));
			imageRepository.save(image);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
}
