package com.github.zaolahma.camera;

public interface Camera {
	byte[] getImage();
	void setResolution(int x, int y);
}
