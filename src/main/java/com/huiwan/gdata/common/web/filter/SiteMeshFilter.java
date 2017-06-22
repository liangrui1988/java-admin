package com.huiwan.gdata.common.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", "/views/body.htm")
				.addExcludedPath("/views/login.*")
				.addExcludedPath("/views/body.jsp");
		// .addDecoratorPath("/admin/*", "/admin/decorator.html");
	}
}