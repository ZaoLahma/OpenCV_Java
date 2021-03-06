package com.github.zaolahma.page.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import com.github.zaolahma.page.control.ControlPage;
import com.github.zaolahma.page.start.StartPage;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public BasePage() {
		add(new BookmarkablePageLink<Void>("Start", StartPage.class));
		add(new BookmarkablePageLink<Void>("Control", ControlPage.class));
		add(new Label("footer", "This is in the footer"));
	}
}
