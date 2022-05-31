package com.github.zaolahma.webapp.imagerepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

	public void update(InputStream imageData) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		byte[] tmpBuf = new byte[4096];
		int readBytes = 0;
		while ((readBytes = imageData.read(tmpBuf, 0, tmpBuf.length)) != -1) {
			bos.write(tmpBuf, 0, readBytes);
		}
		
		ImageStorage.getApi().updateImage(bos.toByteArray());
	}
}
