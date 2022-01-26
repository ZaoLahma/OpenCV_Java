package com.github.zaolahma.network.endpoint;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends AbstractEndpoint implements Runnable {
	
	protected int mPortNo;
	protected ServerSocket mSocket = null;

	public Server(final int portNo) throws IOException
	{
		mPortNo = portNo;
		
		mSocket = new ServerSocket(mPortNo);
		mSocket.setSoTimeout(1000);
	}
	
	@Override
	public void run()
	{
		mActive = true;
		
		while(mActive) {
			try {
				Socket peer = mSocket.accept();
				peer.setSoTimeout(DEFAULT_PEER_TIMEOUT_MS);
				System.out.println("Connected to by: " + peer.getRemoteSocketAddress().toString());
				mPeers.add(peer);
			} catch (IOException e) {
				continue;
			}
		}
	}
	

}
