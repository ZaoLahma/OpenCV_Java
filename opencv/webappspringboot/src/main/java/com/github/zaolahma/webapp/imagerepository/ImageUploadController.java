package com.github.zaolahma.webapp.imagerepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {
	
	private final ImageService mImageService;
	
	@Autowired
	public ImageUploadController(ImageService imageService) {
		mImageService = imageService;
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		System.out.println("Receiving image data");
		Long startTime = System.currentTimeMillis();
		try {
			mImageService.update(file.getInputStream());
			System.out.println("Elapsed: " + (System.currentTimeMillis() - startTime));
			return "redirect:/";
		} catch (Exception e) {
			return "nope!";
		}
	}
}
