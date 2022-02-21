package com.github.zaolahma.page.start;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.github.zaolahma.page.base.BasePage;

public class StartPage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	public StartPage(final PageParameters parameters) {
		super();
		add(new Label("testLabel", "Start page"));
	}
}
