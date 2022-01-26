package com.github.zaolahma.network.endpoint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEndpoint implements Endpoint {
	protected static final int DEFAULT_PEER_TIMEOUT_MS = 200;
	
	protected boolean mActive;
	protected List<Socket> mPeers = new ArrayList<Socket>();

	@Override
	public Serializable getNextMessage() {
		Serializable retVal = null;
		
		while (mActive) {
			for (Socket peer : mPeers) {
				try {
					ObjectInputStream is = new ObjectInputStream(peer.getInputStream());
					retVal = (Serializable) is.readObject();
					break;
				} catch (IOException | ClassNotFoundException e) {
					continue;
				}
			}
			
			if (null != retVal) {
				break;
			}
		}
		
		return retVal;
	}

	@Override
	public void sendMessage(Serializable msg) {
		for (Socket peer : mPeers) {
			ObjectOutputStream os;
			try {
				os = new ObjectOutputStream(peer.getOutputStream());
				os.writeObject(msg);
			} catch (IOException e) {
				continue;
			}
		}
	}
}
