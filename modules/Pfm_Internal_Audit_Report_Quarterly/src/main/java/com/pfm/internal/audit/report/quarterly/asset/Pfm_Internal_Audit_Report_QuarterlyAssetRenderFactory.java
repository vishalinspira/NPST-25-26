package com.pfm.internal.audit.report.quarterly.asset;

import com.daily.average.service.model.Pfm_Qr_Internal_Audit_Report;
import com.daily.average.service.service.Pfm_Qr_Internal_Audit_ReportLocalService;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.pfm.internal.audit.report.quarterly.constants.pfm_Internal_Audit_Report_QuarterlyPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {"javax.portlet.name=" + pfm_Internal_Audit_Report_QuarterlyPortletKeys.PFM_INTERNAL_AUDIT_REPORT_QUARTERLY},
        service = AssetRendererFactory.class
		)

public class Pfm_Internal_Audit_Report_QuarterlyAssetRenderFactory extends BaseAssetRendererFactory<Pfm_Qr_Internal_Audit_Report>{
	
	
	public Pfm_Internal_Audit_Report_QuarterlyAssetRenderFactory() {
		setClassName(Pfm_Qr_Internal_Audit_Report.class.getName());
		setCategorizable(true);
        setLinkable(true);
        setPortletId(pfm_Internal_Audit_Report_QuarterlyPortletKeys.PFM_INTERNAL_AUDIT_REPORT_QUARTERLY);
        setSearchable(true);
        setSelectable(true);
	}
	
	@Override
	public AssetRenderer<Pfm_Qr_Internal_Audit_Report> getAssetRenderer(long classPK, int type) throws PortalException {
		Pfm_Qr_Internal_Audit_Report pfm_Qr_Internal_Audit_Report = pfm_Qr_Internal_Audit_ReportLocalService.getPfm_Qr_Internal_Audit_Report(classPK);
		Pfm_Internal_Audit_Report_QuarterlyAssetRender assetRender = new Pfm_Internal_Audit_Report_QuarterlyAssetRender(pfm_Qr_Internal_Audit_Report);
		assetRender.setAssetRendererType(type);
		return assetRender;
	}
	
	@Override
	public String getType() {
		return "Pfm_Qr_Internal_Audit_Report";
	}
	
	@Override
    public String getClassName() {
		return Pfm_Qr_Internal_Audit_Report.class.getName();
	}
	
	@Reference
	Pfm_Qr_Internal_Audit_ReportLocalService pfm_Qr_Internal_Audit_ReportLocalService;

}
