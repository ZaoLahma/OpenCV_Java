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
			final int X_SIZE = 320;
			final int Y_SIZE = 180;

			// System.out.println("newImage.length: " + newImage.length);

			BufferedImage img = new BufferedImage(X_SIZE, Y_SIZE, BufferedImage.TYPE_INT_RGB);
			int x = 0;
			int y = 0;
			for (int byteIndex = 0; byteIndex < newImage.length; byteIndex += 3) {
				final int r = Byte.toUnsignedInt(newImage[byteIndex]);
				final int g = Byte.toUnsignedInt(newImage[byteIndex + 1]);
				final int b = Byte.toUnsignedInt(newImage[byteIndex + 2]);

				// System.out.println("r,g,b: " + r + ", " + g + ", " + b);

				int pixel = 0;
				pixel = (r << 16) | (g << 8) | b;

				// System.out.println("pixel: " + pixel);

				// System.out.println("x, y: " + x + ", " + y);

				img.setRGB(x, y, pixel);
				x += 1;
				if (x == X_SIZE) {
					x = 0;
					y += 1;
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
