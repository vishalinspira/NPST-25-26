package Compliance_certificate_at_quarterly_interval.asset;

import com.daily.average.service.model.InputQuarterlyInterval;
import com.daily.average.service.service.InputQuarterlyIntervalLocalService;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import Compliance_certificate_at_quarterly_interval.constants.Compliance_certificate_at_quarterly_intervalPortletKeys;

@Component(
        immediate = true,
        property = {"javax.portlet.name=" +Compliance_certificate_at_quarterly_intervalPortletKeys.COMPLIANCE_CERTIFICATE_AT_QUARTERLY_INTERVAL},
        service = AssetRendererFactory.class
		)


public class Compliance_certificate_at_quarterly_intervalAssetRenderFactory extends BaseAssetRendererFactory<InputQuarterlyInterval>{

	public Compliance_certificate_at_quarterly_intervalAssetRenderFactory() {
		setClassName(InputQuarterlyInterval.class.getName());
		setCategorizable(true);
        setLinkable(true);
        setPortletId(Compliance_certificate_at_quarterly_intervalPortletKeys.COMPLIANCE_CERTIFICATE_AT_QUARTERLY_INTERVAL);
        setSearchable(true);
        setSelectable(true);
	}
	
	@Override
	public AssetRenderer<InputQuarterlyInterval> getAssetRenderer(long classPK, int type) throws PortalException {
		InputQuarterlyInterval inputQuarterlyInterval = inputQuarterlyIntervalLocalService.getInputQuarterlyInterval(classPK);
		Compliance_certificate_at_quarterly_intervalAssetRender assetRender = new Compliance_certificate_at_quarterly_intervalAssetRender(inputQuarterlyInterval);
		assetRender.setAssetRendererType(type);
		return assetRender;
	}

	@Override
	public String getType() {
		return "InputQuarterlyInterval";
	}
	
	@Override
    public String getClassName() {
		return InputQuarterlyInterval.class.getName();
	}

	@Reference
	InputQuarterlyIntervalLocalService inputQuarterlyIntervalLocalService;
}