package com.github.zaolahma.network.endpoint;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AbstractEndpoint {
	public void connect(final String hostName, final int portNo) throws 
		UnknownHostException, IOException {
		Socket peer = new Socket(hostName, portNo);
		peer.setSoTimeout(DEFAULT_PEER_TIMEOUT_MS);
		mPeers.add(peer);
	}
}
