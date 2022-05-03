package com.github.zaolahma.page.control;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

import com.github.zaolahma.page.base.BasePage;

public class ControlPage extends BasePage {
	private static final long serialVersionUID = 1L;

	@Override
    public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(getClass(),
                "image_client.js")));
	}
}
