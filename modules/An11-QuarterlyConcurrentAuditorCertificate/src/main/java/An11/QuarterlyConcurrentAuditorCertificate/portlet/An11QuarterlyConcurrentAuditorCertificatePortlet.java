package An11.QuarterlyConcurrentAuditorCertificate.portlet;

import An11.QuarterlyConcurrentAuditorCertificate.constants.An11QuarterlyConcurrentAuditorCertificatePortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author deepak
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=An11QuarterlyConcurrentAuditorCertificate",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + An11QuarterlyConcurrentAuditorCertificatePortletKeys.AN11QUARTERLYCONCURRENTAUDITORCERTIFICATE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class An11QuarterlyConcurrentAuditorCertificatePortlet extends MVCPortlet {
}