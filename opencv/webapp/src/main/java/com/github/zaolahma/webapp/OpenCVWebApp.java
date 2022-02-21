package com.github.zaolahma.webapp;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import com.github.zaolahma.page.start.StartPage;

public class OpenCVWebApp extends WebApplication
{
	@Override
	protected void init() {
		super.init();
		getCspSettings().blocking().disabled();
	}
	
	@Override
	public Class<? extends Page> getHomePage() {
		return StartPage.class;
	}
}
