package com.github.zaolahma.webapp.page.camera;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;

import com.github.zaolahma.webapp.page.base.BasePage;

public class CameraTextPage extends BasePage {
	public CameraTextPage(PageParameters params) {
        super(params);
	}

	private static final long serialVersionUID = 1L;

	@Override
    public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(getClass(),
                "image_text_client.js")));
	}
}
