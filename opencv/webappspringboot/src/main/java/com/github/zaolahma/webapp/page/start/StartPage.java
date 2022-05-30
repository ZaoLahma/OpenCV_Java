package com.github.zaolahma.webapp.page.start;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.github.zaolahma.webapp.page.base.BasePage;

@WicketHomePage
public class StartPage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	public StartPage(PageParameters parameters) {
		super(parameters);
		add(new Label("testLabel", "Start page"));
	}
}
