package com.github.zaolahma.webapp.imagerepository;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImageUploadController {
	
	private final ImageService mImageService;
	
	@Autowired
	public ImageUploadController(ImageService imageService) {
		mImageService = imageService;
	}
	
	@PostMapping("/upload")
    public ResponseEntity<Void> upload(HttpServletRequest request) throws Exception {
		System.out.println("Receiving image data");
		Long startTime = System.currentTimeMillis();
		mImageService.update(request.getInputStream());
		System.out.println("Elapsed: " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.ok().build();
	}
}
