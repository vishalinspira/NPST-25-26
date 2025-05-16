package Compliance_certificate_at_quarterly_interval.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import Compliance_certificate_at_quarterly_interval.constants.Compliance_certificate_at_quarterly_intervalPortletKeys;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=false",
			"com.liferay.portlet.requires-namespaced-parameters=false",
			"javax.portlet.display-name=Compliance_certificate_at_quarterly_interval_scrutiny",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/scrutinyview.jsp",
			"javax.portlet.name=" + Compliance_certificate_at_quarterly_intervalPortletKeys.COMPLIANCE_CERTIFICATE_AT_QUARTERLY_INTERVAL_SCR,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class ComplianceCertificateAtQuarterlyintervalScrutinyPortlet  extends MVCPortlet {

}