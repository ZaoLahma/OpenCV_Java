package com.github.zaolahma.network.endpoint;

import java.io.Serializable;

public interface Endpoint {
	Serializable getNextMessage();
	void sendMessage(Serializable msg);
}
