package com.github.zaolahma.webapp.page.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.github.zaolahma.webapp.page.start.StartPage;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	protected PageParameters mPageParameters;
	
	public BasePage(PageParameters params) {
		mPageParameters = params;
		add(new BookmarkablePageLink<Void>("Start", StartPage.class));
		add(new Label("footer", "This is in the footer"));
	}
}
