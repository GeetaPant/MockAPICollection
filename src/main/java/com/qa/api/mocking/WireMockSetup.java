package com.qa.api.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WireMockSetup {
	
	private static WireMockServer server;
	
	public static void startWiremockServer() {
		server = new WireMockServer(8585);
		WireMock.configureFor("localhost", 8585);
		server.start();
	}
	public static void stopWiremockServer() {
		server.stop();
	}

}
