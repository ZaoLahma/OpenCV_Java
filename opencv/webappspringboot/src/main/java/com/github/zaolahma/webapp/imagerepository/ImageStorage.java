package com.github.zaolahma.webapp.imagerepository;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import com.github.zaolahma.webapp.page.camera.CameraEndpoint;

public class ImageStorage {
	private static ImageStorage INSTANCE = null;
	private static Object mSyncObject = new Object();

	private byte[] mImageData;

	protected List<CameraEndpoint> mHandlers = new CopyOnWriteArrayList<CameraEndpoint>();

	private ImageStorage() {
	}

	public static ImageStorage getApi() {
		synchronized (mSyncObject) {
			if (null == INSTANCE) {
				INSTANCE = new ImageStorage();
			}
		}

		return INSTANCE;
	}

	public void updateImage(byte[] newImage) {
		synchronized (mSyncObject) {
			final int X_SIZE = 200;
			final int Y_SIZE = 100;

			BufferedImage img = new BufferedImage(X_SIZE, Y_SIZE, BufferedImage.TYPE_INT_RGB);
			int x = 0;
			int y = 0;
			for (int byteIndex = 0; byteIndex < newImage.length; ++byteIndex) {
				final int r = Byte.toUnsignedInt(newImage[byteIndex]);
				final int g = Byte.toUnsignedInt(newImage[byteIndex]);
				final int b = Byte.toUnsignedInt(newImage[byteIndex]);
				int pixel = 0;
				pixel = (r << 16) | (g << 8) | b;

				img.setRGB(x, y, pixel);
				x += 1;
				if (x == X_SIZE) {
					x = 0;
					y += 1;
					if (y == Y_SIZE) {
						break;
					}
				}
			}

			ByteArrayOutputStream os = null;
			try {
				os = new ByteArrayOutputStream();
				if (!ImageIO.write(img, "png", os)) {
					System.out.println("Well, that didn't work now did it?");
				}
				os.flush();
			} catch (IOException e) {
				System.out.println("Exception! " + e.getMessage());
			} finally {
				if (null != os) {
					try {
						os.close();
					} catch (IOException e) {
					}
				}
			}

			mImageData = os.toByteArray();

			mHandlers.forEach(handler -> {
				handler.handleFrame();
			});
		}
	}

	public byte[] getImageData() {
		byte[] retVal;

		synchronized (mSyncObject) {
			retVal = mImageData;
		}

		return retVal;
	}

	public void registerFrameHandler(CameraEndpoint handler) {
		mHandlers.add(handler);
	}

	public void deregisterFrameHandler(CameraEndpoint handler) {
		mHandlers.remove(handler);
	}

}
