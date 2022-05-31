package com.github.zaolahma.webapp.imagerepository;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {
	void update(InputStream fileStream) throws IOException;
}
