package Compliance_certificate_at_quarterly_interval.scrutiny;

import com.daily.average.service.model.InputQuarterlyInterval;
import com.daily.average.service.model.ReportUploadFileLog;
import com.daily.average.service.service.InputQuarterlyIntervalLocalServiceUtil;
import com.daily.average.service.service.ReportUploadFileLogLocalServiceUtil;
import com.daily.average.service.service.ScrutinyInputQuarterlyIntervalLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import Compliance_certificate_at_quarterly_interval.constants.Compliance_certificate_at_quarterly_intervalPortletKeys;
import Compliance_certificate_at_quarterly_interval.portlet.QuarterlyCompCertificateCreatePdfUtil;
import Compliance_certificate_at_quarterly_interval.resource.ComplianceQuarterlyMVCResource;
import Compliance_certificate_at_quarterly_interval.util.CompCerUtil;
import Compliance_certificate_at_quarterly_interval.util.ComplianceCertificateAtQuartelyIntervalUtil;

@Component(property = { 
		"javax.portlet.name=" + Compliance_certificate_at_quarterly_intervalPortletKeys.COMPLIANCE_CERTIFICATE_AT_QUARTERLY_INTERVAL,
		"javax.portlet.name=" + Compliance_certificate_at_quarterly_intervalPortletKeys.COMPLIANCE_CERTIFICATE_AT_QUARTERLY_INTERVAL_SCR,
		"mvc.command.name=" + Compliance_certificate_at_quarterly_intervalPortletKeys.input_quarterly_intervalPortletKeysScr, 
		}, 
service = MVCResourceCommand.class)
public class SaveCompliance_certificate_at_quarterly_intervalScrutiny implements MVCResourceCommand{
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		
		 ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		
		    
		    DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			Date formDate =ParamUtil.getDate(resourceRequest,"formDate",dateFormat); 
			
			Double version =ParamUtil.getDouble(resourceRequest, "version");
			
			Long reportUploadLogId = ParamUtil.getLong(resourceRequest, "reportUploadLogId");
			ComplianceCertificateAtQuartelyIntervalUtil complianceCertificateAtQuartelyIntervalUtil=new ComplianceCertificateAtQuartelyIntervalUtil();
			  Boolean isNonNPSUser = complianceCertificateAtQuartelyIntervalUtil.isNonNpsUser(themeDisplay.getUserId());
			  InputQuarterlyInterval inputQuarterlyInterval = InputQuarterlyIntervalLocalServiceUtil.fetchInputQuarterlyInterval(reportUploadLogId);
			
			String oneia = ParamUtil.getString(resourceRequest, "oneia");
			String oneib = ParamUtil.getString(resourceRequest, "oneib");
			String oneiia = ParamUtil.getString(resourceRequest, "oneiia");
			String oneiib = ParamUtil.getString(resourceRequest, "oneiib");
			String oneiic = ParamUtil.getString(resourceRequest, "oneiic");
			String oneiid = ParamUtil.getString(resourceRequest, "oneiid");
			String oneiiia = ParamUtil.getString(resourceRequest, "oneiiia");
			String oneiiib = ParamUtil.getString(resourceRequest, "oneiiib");
			String oneiva = ParamUtil.getString(resourceRequest, "oneiva");
			String onev = ParamUtil.getString(resourceRequest, "onev");
			String twoia = ParamUtil.getString(resourceRequest, "twoia");
			String twoib = ParamUtil.getString(resourceRequest, "twoib");
			String twoic = ParamUtil.getString(resourceRequest, "twoic");
			String twoiione = ParamUtil.getString(resourceRequest, "twoiione");
			String twoiitwo = ParamUtil.getString(resourceRequest, "twoiitwo");
			String twoiithree = ParamUtil.getString(resourceRequest, "twoiithree");
			String twoiifour = ParamUtil.getString(resourceRequest, "twoiifour");
			String twoiifive = ParamUtil.getString(resourceRequest, "twoiifive");
			String twoiisix = ParamUtil.getString(resourceRequest, "twoiisix");
			String twoiiseven = ParamUtil.getString(resourceRequest, "twoiiseven");
			String threea = ParamUtil.getString(resourceRequest, "threea");
			String threeb = ParamUtil.getString(resourceRequest, "threeb");
			String threec = ParamUtil.getString(resourceRequest, "threec");
			String threeci = ParamUtil.getString(resourceRequest, "threeci");
			String threecii = ParamUtil.getString(resourceRequest, "threecii");
			String threeciii = ParamUtil.getString(resourceRequest, "threeciii");
			String threeciv = ParamUtil.getString(resourceRequest, "threeciv");
			String foura = ParamUtil.getString(resourceRequest, "foura");
			String fourb = ParamUtil.getString(resourceRequest, "fourb");
			String fourc = ParamUtil.getString(resourceRequest, "fourc");
			String fivei = ParamUtil.getString(resourceRequest, "fivei");
			String fiveii = ParamUtil.getString(resourceRequest, "fiveii");
			String fiveiii = ParamUtil.getString(resourceRequest, "fiveiii");
			String fiveiv = ParamUtil.getString(resourceRequest, "fiveiv");
			String fiveva = ParamUtil.getString(resourceRequest, "fiveva");
			String fivevb = ParamUtil.getString(resourceRequest, "fivevb");
			String fivevc = ParamUtil.getString(resourceRequest, "fivevc");
			String fivevia = ParamUtil.getString(resourceRequest, "fivevia");
			String fivevib = ParamUtil.getString(resourceRequest, "fivevib");
			String sixi = ParamUtil.getString(resourceRequest, "sixi");
			String sixiia = ParamUtil.getString(resourceRequest, "sixiia");
			String sixiib = ParamUtil.getString(resourceRequest, "sixiib");
			String sixiic = ParamUtil.getString(resourceRequest, "sixiic");
			String sevenia = ParamUtil.getString(resourceRequest, "sevenia");
			String sevenib = ParamUtil.getString(resourceRequest, "sevenib");
			String sevenic = ParamUtil.getString(resourceRequest, "sevenic");
			String sevenid = ParamUtil.getString(resourceRequest, "sevenid");
			String sevenie = ParamUtil.getString(resourceRequest, "sevenie");
			String eightia = ParamUtil.getString(resourceRequest, "eightia");
			String eightib = ParamUtil.getString(resourceRequest, "eightib");
			String eightii = ParamUtil.getString(resourceRequest, "eightii");
			String eightiii = ParamUtil.getString(resourceRequest, "eightiii");
			String eightiv = ParamUtil.getString(resourceRequest, "eightiv");
			String eightv = ParamUtil.getString(resourceRequest, "eightv");
			String eightvia = ParamUtil.getString(resourceRequest, "eightvia");
			String eightvib = ParamUtil.getString(resourceRequest, "eightvib");
			String eightviia = ParamUtil.getString(resourceRequest, "eightviia");
			
			String eightviib = ParamUtil.getString(resourceRequest, "eightviib");
			String eightviii = ParamUtil.getString(resourceRequest, "eightviii");
			String eightix = ParamUtil.getString(resourceRequest, "eightix");
			String eightx = ParamUtil.getString(resourceRequest, "eightx");
			
			String ninea = ParamUtil.getString(resourceRequest, "ninea");
			String nineb = ParamUtil.getString(resourceRequest, "nineb");
			String ten = ParamUtil.getString(resourceRequest, "ten");
			String elevena = ParamUtil.getString(resourceRequest, "elevena");
			String elevenb = ParamUtil.getString(resourceRequest, "elevenb");
			String twelve = ParamUtil.getString(resourceRequest, "twelve");
			
			String oneia_rem = ParamUtil.getString(resourceRequest, "oneia_rem");
			String oneib_rem = ParamUtil.getString(resourceRequest, "oneib_rem");
			String oneiia_rem = ParamUtil.getString(resourceRequest, "oneiia_rem");
			String oneiib_rem = ParamUtil.getString(resourceRequest, "oneiib_rem");
			String oneiic_rem = ParamUtil.getString(resourceRequest, "oneiic_rem");
			String oneiid_rem = ParamUtil.getString(resourceRequest, "oneiid_rem");
			String oneiiia_rem = ParamUtil.getString(resourceRequest, "oneiiia_rem");
			String oneiiib_rem = ParamUtil.getString(resourceRequest, "oneiiib_rem");
			String oneiva_rem = ParamUtil.getString(resourceRequest, "oneiva_rem");
			String onev_rem = ParamUtil.getString(resourceRequest, "onev_rem");
			String twoia_rem = ParamUtil.getString(resourceRequest, "twoia_rem");
			String twoib_rem = ParamUtil.getString(resourceRequest, "twoib_rem");
			String twoic_rem = ParamUtil.getString(resourceRequest, "twoic_rem");
			String twoiione_rem = ParamUtil.getString(resourceRequest, "twoiione_rem");
			String twoiitwo_rem = ParamUtil.getString(resourceRequest, "twoiitwo_rem");
			String twoiithree_rem = ParamUtil.getString(resourceRequest, "twoiithree_rem");
			String twoiifour_rem = ParamUtil.getString(resourceRequest, "twoiifour_rem");
			String twoiifive_rem = ParamUtil.getString(resourceRequest, "twoiifive_rem");
			String twoiisix_rem = ParamUtil.getString(resourceRequest, "twoiisix_rem");
			String twoiiseven_rem = ParamUtil.getString(resourceRequest, "twoiiseven_rem");
			String threea_rem = ParamUtil.getString(resourceRequest, "threea_rem");
			String threeb_rem = ParamUtil.getString(resourceRequest, "threeb_rem");
			String threec_rem = ParamUtil.getString(resourceRequest, "threec_rem");
			String threeci_rem = ParamUtil.getString(resourceRequest, "threeci_rem");
			String threecii_rem = ParamUtil.getString(resourceRequest, "threecii_rem");
			String threeciii_rem = ParamUtil.getString(resourceRequest, "threeciii_rem");
			String threeciv_rem = ParamUtil.getString(resourceRequest, "threeciv_rem");
			String foura_rem = ParamUtil.getString(resourceRequest, "foura_rem");
			String fourb_rem = ParamUtil.getString(resourceRequest, "fourb_rem");
			String fourc_rem = ParamUtil.getString(resourceRequest, "fourc_rem");
			String fivei_rem = ParamUtil.getString(resourceRequest, "fivei_rem");
			String fiveii_rem = ParamUtil.getString(resourceRequest, "fiveii_rem");
			String fiveiii_rem = ParamUtil.getString(resourceRequest, "fiveiii_rem");
			String fiveiv_rem = ParamUtil.getString(resourceRequest, "fiveiv_rem");
			String fiveva_rem = ParamUtil.getString(resourceRequest, "fiveva_rem");
			String fivevb_rem = ParamUtil.getString(resourceRequest, "fivevb_rem");
			String fivevc_rem = ParamUtil.getString(resourceRequest, "fivevc_rem");
			String fivevia_rem = ParamUtil.getString(resourceRequest, "fivevia_rem");
			String fivevib_rem = ParamUtil.getString(resourceRequest, "fivevib_rem");
			String sixi_rem = ParamUtil.getString(resourceRequest, "sixi_rem");
			String sixiia_rem = ParamUtil.getString(resourceRequest, "sixiia_rem");
			String sixiib_rem = ParamUtil.getString(resourceRequest, "sixiib_rem");
			String sixiic_rem = ParamUtil.getString(resourceRequest, "sixiic_rem");
			String sevenia_rem = ParamUtil.getString(resourceRequest, "sevenia_rem");
			String sevenib_rem = ParamUtil.getString(resourceRequest, "sevenib_rem");
			String sevenic_rem = ParamUtil.getString(resourceRequest, "sevenic_rem");
			String sevenid_rem = ParamUtil.getString(resourceRequest, "sevenid_rem");
			String sevenie_rem = ParamUtil.getString(resourceRequest, "sevenie_rem");
			String eightia_rem = ParamUtil.getString(resourceRequest, "eightia_rem");
			String eightib_rem = ParamUtil.getString(resourceRequest, "eightib_rem");
			String eightii_rem = ParamUtil.getString(resourceRequest, "eightii_rem");
			String eightiii_rem = ParamUtil.getString(resourceRequest, "eightiii_rem");
			String eightiv_rem = ParamUtil.getString(resourceRequest, "eightiv_rem");
			String eightv_rem = ParamUtil.getString(resourceRequest, "eightv_rem");
			String eightvia_rem = ParamUtil.getString(resourceRequest, "eightvia_rem");
			String eightvib_rem = ParamUtil.getString(resourceRequest, "eightvib_rem");
			String eightviia_rem = ParamUtil.getString(resourceRequest, "eightviia_rem");
			
			String eightviib_rem = ParamUtil.getString(resourceRequest, "eightviib_rem");
			String eightviii_rem = ParamUtil.getString(resourceRequest, "eightviii_rem");
			String eightix_rem = ParamUtil.getString(resourceRequest, "eightix_rem");
			String eightx_rem = ParamUtil.getString(resourceRequest, "eightx_rem");
			String ninea_rem = ParamUtil.getString(resourceRequest, "ninea_rem");
			String nineb_rem = ParamUtil.getString(resourceRequest, "nineb_rem");
			String ten_rem = ParamUtil.getString(resourceRequest, "ten_rem");
			String elevena_rem = ParamUtil.getString(resourceRequest, "elevena_rem");
			String elevenb_rem = ParamUtil.getString(resourceRequest, "elevenb_rem");
			String twelve_rem = ParamUtil.getString(resourceRequest, "twelve_rem");
			
			String oneia_rem_intermediary = ParamUtil.getString(resourceRequest, "oneia_rem_intermediary");
			String oneib_rem_intermediary = ParamUtil.getString(resourceRequest, "oneib_rem_intermediary");
			String oneiia_rem_intermediary = ParamUtil.getString(resourceRequest, "oneiia_rem_intermediary");
			String oneiib_rem_intermediary = ParamUtil.getString(resourceRequest, "oneiib_rem_intermediary");
			String oneiic_rem_intermediary = ParamUtil.getString(resourceRequest, "oneiic_rem_intermediary");
			String oneiid_rem_intermediary = ParamUtil.getString(resourceRequest, "oneiid_rem_intermediary");
			String oneiiia_rem_intermediary = ParamUtil.getString(resourceRequest, "oneiiia_rem_intermediary");
			String oneiiib_rem_intermediary = ParamUtil.getString(resourceRequest, "oneiiib_rem_intermediary");
			String oneiva_rem_intermediary = ParamUtil.getString(resourceRequest, "oneiva_rem_intermediary");
			String onev_rem_intermediary = ParamUtil.getString(resourceRequest, "onev_rem_intermediary");
			String twoia_rem_intermediary = ParamUtil.getString(resourceRequest, "twoia_rem_intermediary");
			String twoib_rem_intermediary = ParamUtil.getString(resourceRequest, "twoib_rem_intermediary");
			String twoic_rem_intermediary = ParamUtil.getString(resourceRequest, "twoic_rem_intermediary");
			String twoiione_rem_intermediary = ParamUtil.getString(resourceRequest, "twoiione_rem_intermediary");
			String twoiitwo_rem_intermediary = ParamUtil.getString(resourceRequest, "twoiitwo_rem_intermediary");
			String twoiithree_rem_intermediary = ParamUtil.getString(resourceRequest, "twoiithree_rem_intermediary");
			String twoiifour_rem_intermediary = ParamUtil.getString(resourceRequest, "twoiifour_rem_intermediary");
			String twoiifive_rem_intermediary = ParamUtil.getString(resourceRequest, "twoiifive_rem_intermediary");
			String twoiisix_rem_intermediary = ParamUtil.getString(resourceRequest, "twoiisix_rem_intermediary");
			String twoiiseven_rem_intermediary = ParamUtil.getString(resourceRequest, "twoiiseven_rem_intermediary");
			String threea_rem_intermediary = ParamUtil.getString(resourceRequest, "threea_rem_intermediary");
			String threeb_rem_intermediary = ParamUtil.getString(resourceRequest, "threeb_rem_intermediary");
			String threec_rem_intermediary = ParamUtil.getString(resourceRequest, "threec_rem_intermediary");
			String threeci_rem_intermediary = ParamUtil.getString(resourceRequest, "threeci_rem_intermediary");
			String threecii_rem_intermediary = ParamUtil.getString(resourceRequest, "threecii_rem_intermediary");
			String threeciii_rem_intermediary = ParamUtil.getString(resourceRequest, "threeciii_rem_intermediary");
			String threeciv_rem_intermediary = ParamUtil.getString(resourceRequest, "threeciv_rem_intermediary");
			String foura_rem_intermediary = ParamUtil.getString(resourceRequest, "foura_rem_intermediary");
			String fourb_rem_intermediary = ParamUtil.getString(resourceRequest, "fourb_rem_intermediary");
			String fourc_rem_intermediary = ParamUtil.getString(resourceRequest, "fourc_rem_intermediary");
			String fivei_rem_intermediary = ParamUtil.getString(resourceRequest, "fivei_rem_intermediary");
			String fiveii_rem_intermediary = ParamUtil.getString(resourceRequest, "fiveii_rem_intermediary");
			String fiveiii_rem_intermediary = ParamUtil.getString(resourceRequest, "fiveiii_rem_intermediary");
			String fiveiv_rem_intermediary = ParamUtil.getString(resourceRequest, "fiveiv_rem_intermediary");
			String fiveva_rem_intermediary = ParamUtil.getString(resourceRequest, "fiveva_rem_intermediary");
			String fivevb_rem_intermediary = ParamUtil.getString(resourceRequest, "fivevb_rem_intermediary");
			String fivevc_rem_intermediary = ParamUtil.getString(resourceRequest, "fivevc_rem_intermediary");
			String fivevia_rem_intermediary = ParamUtil.getString(resourceRequest, "fivevia_rem_intermediary");
			String fivevib_rem_intermediary = ParamUtil.getString(resourceRequest, "fivevib_rem_intermediary");
			String sixi_rem_intermediary = ParamUtil.getString(resourceRequest, "sixi_rem_intermediary");
			String sixiia_rem_intermediary = ParamUtil.getString(resourceRequest, "sixiia_rem_intermediary");
			String sixiib_rem_intermediary = ParamUtil.getString(resourceRequest, "sixiib_rem_intermediary");
			String sixiic_rem_intermediary = ParamUtil.getString(resourceRequest, "sixiic_rem_intermediary");
			String sevenia_rem_intermediary = ParamUtil.getString(resourceRequest, "sevenia_rem_intermediary");
			String sevenib_rem_intermediary = ParamUtil.getString(resourceRequest, "sevenib_rem_intermediary");
			String sevenic_rem_intermediary = ParamUtil.getString(resourceRequest, "sevenic_rem_intermediary");
			String sevenid_rem_intermediary = ParamUtil.getString(resourceRequest, "sevenid_rem_intermediary");
			String sevenie_rem_intermediary = ParamUtil.getString(resourceRequest, "sevenie_rem_intermediary");
			String eightia_rem_intermediary = ParamUtil.getString(resourceRequest, "eightia_rem_intermediary");
			String eightib_rem_intermediary = ParamUtil.getString(resourceRequest, "eightib_rem_intermediary");
			String eightii_rem_intermediary = ParamUtil.getString(resourceRequest, "eightii_rem_intermediary");
			String eightiii_rem_intermediary = ParamUtil.getString(resourceRequest, "eightiii_rem_intermediary");
			String eightiv_rem_intermediary = ParamUtil.getString(resourceRequest, "eightiv_rem_intermediary");
			String eightv_rem_intermediary = ParamUtil.getString(resourceRequest, "eightv_rem_intermediary");
			String eightvia_rem_intermediary = ParamUtil.getString(resourceRequest, "eightvia_rem_intermediary");
			String eightvib_rem_intermediary = ParamUtil.getString(resourceRequest, "eightvib_rem_intermediary");
			String eightviia_rem_intermediary = ParamUtil.getString(resourceRequest, "eightviia_rem_intermediary");
			
			String eightviib_rem_intermediary = ParamUtil.getString(resourceRequest, "eightviib_rem_intermediary");
			String eightviii_rem_intermediary = ParamUtil.getString(resourceRequest, "eightviii_rem_intermediary");
			String eightix_rem_intermediary = ParamUtil.getString(resourceRequest, "eightix_rem_intermediary");
			String eightx_rem_intermediary = ParamUtil.getString(resourceRequest, "eightx_rem_intermediary");
			String ninea_rem_intermediary = ParamUtil.getString(resourceRequest, "ninea_rem_intermediary");
			String nineb_rem_intermediary = ParamUtil.getString(resourceRequest, "nineb_rem_intermediary");
			String ten_rem_intermediary = ParamUtil.getString(resourceRequest, "ten_rem_intermediary");
			String elevena_rem_intermediary = ParamUtil.getString(resourceRequest, "elevena_rem_intermediary");
			String elevenb_rem_intermediary = ParamUtil.getString(resourceRequest, "elevenb_rem_intermediary");
			String twelve_rem_intermediary = ParamUtil.getString(resourceRequest, "twelve_rem_intermediary");
			
			String annex_i_rem = ParamUtil.getString(resourceRequest, "annex_i_rem");
			String board_a_rem =  ParamUtil.getString(resourceRequest, "board_a_rem");
			String board_b_rem =  ParamUtil.getString(resourceRequest, "board_b_rem");
			String investment_a_rem = ParamUtil.getString( resourceRequest, "investment_a_rem");
			String investment_b_rem = ParamUtil.getString(resourceRequest, "investment_b_rem");
			String risk_a_rem = ParamUtil.getString(resourceRequest, "risk_a_rem");
			String risk_b_rem = ParamUtil.getString(resourceRequest, "risk_b_rem");
			String annex_ii_rem = ParamUtil.getString(resourceRequest, "annex_ii_rem");
			String annex_iii_rem = ParamUtil.getString(resourceRequest, "annex_iii_rem");
			String annex_iv_rem = ParamUtil.getString(resourceRequest, "annex_iv_rem");
			String annex_comp_certificate_rem = ParamUtil.getString(resourceRequest, "annex_comp_certificate_rem");
			//Long reportMasterId = ParamUtil.getLong(resourceRequest, "reportMasterId");
			
			Long createdBy = themeDisplay.getUserId();
			String statusByUserName = themeDisplay.getUser().getFullName();
			long annex_i = inputQuarterlyInterval.getAnnex_i();
			long board_a =  inputQuarterlyInterval.getBoard_a();
			String notedate=inputQuarterlyInterval.getNotedate();
			String companies=inputQuarterlyInterval.getCompanies();
			String date_3=inputQuarterlyInterval.getDate_3();
			String place=inputQuarterlyInterval.getPlace();
			String ceoname=inputQuarterlyInterval.getCeoname();
			String ceonameii=inputQuarterlyInterval.getCeonameii();
			String board_a_rem_intermediary =inputQuarterlyInterval.getBoard_a_rem();
			String board_b_rem_intermediary =inputQuarterlyInterval.getBoard_b_rem();
			String investment_a_rem_intermediary =inputQuarterlyInterval.getInvestment_a_rem();
			String investment_b_rem_intermediary =inputQuarterlyInterval.getInvestment_b_rem();
			String risk_a_rem_intermediary =inputQuarterlyInterval.getRisk_a_rem();
			String risk_b_rem_intermediary =inputQuarterlyInterval.getRisk_b_rem();
			String annex_i_rem_intermediary =inputQuarterlyInterval.getAnnex_i_rem();
			String annex_ii_rem_intermediary =inputQuarterlyInterval.getAnnex_ii_rem();
			String annex_iii_rem_intermediary=inputQuarterlyInterval.getAnnex_iii_rem();
			String annex_iv_rem_intermediary=inputQuarterlyInterval.getAnnex_iv_rem();
			String annex_comp_certificate_rem_intermediary=inputQuarterlyInterval.getAnnex_comp_certificate_rem();
			Date reportDate=inputQuarterlyInterval.getReportDate();
			String dDate[]=dateFormat.format(reportDate).split("-");

			int month=Integer.parseInt(dDate[1]);
			int year=Integer.parseInt(dDate[0]);
			if(month==1){
				year=year-1;
				month=12;
			}else{
				month=month-1;	
			}
			String formDate1=month+"/"+year;
			
			_log.info("isNonNPSUser========"+isNonNPSUser);
			if(isNonNPSUser) {
				try {
					
					File file=QuarterlyCompCertificateCreatePdfUtil.QuarterlyComplianeCertificatePFMPDF(reportUploadLogId,dateFormat.format(reportDate), formDate1,
							
							oneia, oneib, oneiia, oneiib, oneiic, oneiid, oneiiia,oneiiib, oneiva, onev, twoia, twoib, twoic, twoiione, twoiitwo, twoiithree, twoiifour, twoiifive,
							twoiisix, twoiiseven, threea, threeb, threec, threeci, threecii, threeciii, threeciv, foura, fourb, fourc, fivei, fiveii, fiveiii, fiveiv, fiveva, fivevb,
							fivevc, fivevia, fivevib, sixi, sixiia, sixiib, sixiic,sevenia, sevenib, sevenic, sevenid, sevenie, eightia, eightib, eightii, eightiii, eightiv, eightv,
							eightvia, eightvib, eightviia, eightviib, eightviii,eightix,eightx,ninea, nineb, ten, elevena, elevenb, twelve, 
							"","", "", "", "", "", "","", "", "", "","", "", "","", "", "", "", "", "","", "", "", "", "", "","", "", "", "", "", "",
							"", "", "", "", "", "","", "","", "", "", "","", "", "", "", "", "","", "", "", "", "", "","", "", "", "", "", "","", "", "","","",
							oneia_rem_intermediary, oneib_rem_intermediary, oneiia_rem_intermediary, oneiib_rem_intermediary, oneiic_rem_intermediary,
							oneiid_rem_intermediary, oneiiia_rem_intermediary,oneiiib_rem_intermediary, oneiva_rem_intermediary, onev_rem_intermediary, twoia_rem_intermediary, twoib_rem_intermediary, twoic_rem_intermediary, twoiione_rem_intermediary, twoiitwo_rem_intermediary, twoiithree_rem_intermediary, twoiifour_rem_intermediary,
							twoiifive_rem_intermediary, twoiisix_rem_intermediary, twoiiseven_rem_intermediary, threea_rem_intermediary, threeb_rem_intermediary, threec_rem_intermediary, threeci_rem_intermediary, threecii_rem_intermediary, threeciii_rem_intermediary,threeciv_rem_intermediary, foura_rem_intermediary, fourb_rem_intermediary, 
							fourc_rem_intermediary, fivei_rem_intermediary, fiveii_rem_intermediary, fiveiii_rem_intermediary, fiveiv_rem_intermediary, fiveva_rem_intermediary, fivevb_rem_intermediary,fivevc_rem_intermediary, fivevia_rem_intermediary, fivevib_rem_intermediary, sixi_rem_intermediary, sixiia_rem_intermediary,sixiib_rem_intermediary,
							sixiic_rem_intermediary, sevenia_rem_intermediary, sevenib_rem_intermediary, sevenic_rem_intermediary,sevenid_rem_intermediary, sevenie_rem_intermediary, eightia_rem_intermediary,eightib_rem_intermediary, eightii_rem_intermediary, eightiii_rem_intermediary, eightiv_rem_intermediary, eightv_rem_intermediary, eightvia_rem_intermediary,
							eightvib_rem_intermediary, eightviia_rem_intermediary, eightviib_rem_intermediary, eightviii_rem_intermediary,eightix_rem_intermediary,eightx_rem_intermediary, ninea_rem_intermediary, nineb_rem_intermediary, ten_rem_intermediary, elevena_rem_intermediary, elevenb_rem_intermediary, twelve_rem_intermediary,  notedate, companies, date_3, place, ceoname, ceonameii);
					ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
					long fileEntryId=inputQuarterlyInterval.getFileEntryId();
					int isAmRejected=0;
					ReportUploadFileLog reportUploadFileLog =null;
					try {
						 reportUploadFileLog = ReportUploadFileLogLocalServiceUtil.findByFileEntryId(fileEntryId);
						FileEntry dlFile=DLAppLocalServiceUtil.getFileEntry(fileEntryId);
//					if(certificate.getIsAmRejected()==1 || Validator.isNotNull(reportUploadFileLog.getSignature())) {
						if(Validator.isNotNull(reportUploadFileLog.getSignature())) {
						fileEntryId=uploadFile(file, themeDisplay, resourceRequest);
						
						ReportUploadFileLogLocalServiceUtil.addReportUploadFileLog(inputQuarterlyInterval.getReportUploadLogId(), fileEntryId, themeDisplay.getUserId());
						reportUploadFileLog = ReportUploadFileLogLocalServiceUtil.findByFileEntryId(fileEntryId);
						isAmRejected=2;
					}else {
						if(Validator.isNull(reportUploadFileLog.getSignature())) {
						DLVersionNumberIncrease dlVersionNumberIncrease = DLVersionNumberIncrease.AUTOMATIC;
						DLAppLocalServiceUtil.updateFileEntry(themeDisplay.getUserId(), fileEntryId, dlFile.getFileName(), dlFile.getMimeType(), dlFile.getTitle(), dlFile.getDescription(),
								"", dlVersionNumberIncrease, file, serviceContext);
						}
					}
					}catch (Exception e) {
						_log.error(e.getMessage());
					}
					
					if(Validator.isNull(reportUploadFileLog.getSignature())) {
						InputQuarterlyIntervalLocalServiceUtil.addInputQuarterlyInterval(reportUploadLogId, formDate, oneia, oneib, oneiia, oneiib, oneiic, oneiid, oneiiia, oneiiib,
								oneiva, onev, twoia, twoib, twoic, twoiione, twoiitwo, twoiithree, twoiifour, twoiifive, twoiisix, twoiiseven, threea, threeb, threec, threeci, 
								threecii, threeciii, threeciv, foura, fourb, fourc, fivei, fiveii, fiveiii, fiveiv, fiveva, fivevb, fivevc, fivevia, fivevib, sixi, sixiia, sixiib, 
								sixiic, sevenia, sevenib, sevenic, sevenid, sevenie, eightia, eightib, eightii, eightiii, eightiv, eightv, eightvia, eightvib, eightviia, eightviib, 
								eightviii,eightix,eightx, ninea, nineb, ten, elevena, elevenb, twelve, oneia_rem_intermediary, oneib_rem_intermediary, oneiia_rem_intermediary, oneiib_rem_intermediary, oneiic_rem_intermediary, oneiid_rem_intermediary,
								oneiiia_rem_intermediary, oneiiib_rem_intermediary, oneiva_rem_intermediary, onev_rem_intermediary, twoia_rem_intermediary, twoib_rem_intermediary, twoic_rem_intermediary, twoiione_rem_intermediary, twoiitwo_rem_intermediary, twoiithree_rem_intermediary,
								twoiifour_rem_intermediary, twoiifive_rem_intermediary, twoiisix_rem_intermediary, twoiiseven_rem_intermediary, threea_rem_intermediary, threeb_rem_intermediary, threec_rem_intermediary, threeci_rem_intermediary, threecii_rem_intermediary, 
								threeciii_rem_intermediary, threeciv_rem_intermediary, foura_rem_intermediary, fourb_rem_intermediary, fourc_rem_intermediary, fivei_rem_intermediary, fiveii_rem_intermediary, fiveiii_rem_intermediary, fiveiv_rem_intermediary, fiveva_rem_intermediary,
								fivevb_rem_intermediary, fivevc_rem_intermediary, fivevia_rem_intermediary, fivevib_rem_intermediary, sixi_rem_intermediary, sixiia_rem_intermediary, sixiib_rem_intermediary, sixiic_rem_intermediary, sevenia_rem_intermediary, sevenib_rem_intermediary, 
								sevenic_rem_intermediary, sevenid_rem_intermediary, sevenie_rem_intermediary, eightia_rem_intermediary, eightib_rem_intermediary, eightii_rem_intermediary, eightiii_rem_intermediary, eightiv_rem_intermediary, eightv_rem_intermediary, eightvia_rem_intermediary,
								eightvib_rem_intermediary, eightviia_rem_intermediary, eightviib_rem_intermediary, eightviii_rem_intermediary,eightix_rem_intermediary,eightx_rem_intermediary, ninea_rem_intermediary, nineb_rem_intermediary, ten_rem_intermediary, elevena_rem_intermediary, elevenb_rem_intermediary, twelve_rem_intermediary, 
								board_a_rem_intermediary, board_b_rem_intermediary, investment_a_rem_intermediary, investment_b_rem_intermediary, risk_a_rem_intermediary, risk_b_rem_intermediary, annex_i_rem_intermediary, annex_ii_rem_intermediary, annex_iii_rem_intermediary, annex_iv_rem_intermediary, 
								annex_comp_certificate_rem_intermediary, notedate, companies, date_3, place, ceoname, ceonameii, board_a, 0, 0, 0, 0, 0, 0,
								0, 0, 0, new Date(), createdBy, annex_i,fileEntryId,0);
					}
					
				} catch (Exception e) {
					 _log.error(e);
				}
			}else {
				_log.info("reportUploadLogId --------------------------------------------------------- "+reportUploadLogId);
					try {
						ScrutinyInputQuarterlyIntervalLocalServiceUtil.addScrutinyInputQuarterlyInterval(oneia_rem, oneib_rem, oneiia_rem, oneiib_rem, oneiic_rem, oneiid_rem, oneiiia_rem, oneiiib_rem,
								oneiva_rem, onev_rem, twoia_rem, twoib_rem, twoic_rem, twoiione_rem, twoiitwo_rem, twoiithree_rem, twoiifour_rem, twoiifive_rem, twoiisix_rem, twoiiseven_rem, threea_rem,
								threeb_rem, threec_rem, threeci_rem, threecii_rem, threeciii_rem, threeciv_rem, foura_rem, fourb_rem, fourc_rem, fivei_rem, fiveii_rem, fiveiii_rem, fiveiv_rem, fiveva_rem,
								fivevb_rem, fivevc_rem, fivevia_rem, fivevib_rem, sixi_rem, sixiia_rem, sixiib_rem, sixiic_rem, sevenia_rem, sevenib_rem, sevenic_rem, sevenid_rem, sevenie_rem, eightia_rem,
								eightib_rem, eightii_rem, eightiii_rem, eightiv_rem, eightv_rem, eightvia_rem, eightvib_rem, eightviia_rem, eightviib_rem, eightviii_rem,eightix_rem, eightx_rem, ninea_rem, nineb_rem, ten_rem, elevena_rem,
								elevenb_rem, twelve_rem, board_a_rem, board_b_rem, investment_a_rem, investment_b_rem, risk_a_rem, risk_b_rem, annex_ii_rem, annex_iii_rem, annex_iv_rem, annex_comp_certificate_rem,
								new Date() , createdBy, statusByUserName, version, createdBy, reportUploadLogId, annex_i_rem);
					} catch (Exception e) {
						_log.error("Exception in Persisting the table data one  : "+e.getMessage());
					}
			}
		return false;
  }
	
	
	private long uploadFile(File file,ThemeDisplay themeDisplay,ResourceRequest resourceRequest){
		Date date=new Date();
	
		String fileName = date.getTime()+"_"+"Compliance_certificate_at_quarterly_interval"+".pdf";
		String title = fileName; 
		String description = "Compliance certificate at quarterly interval PDF";
		

		String mimeType =  MimeTypesUtil.getContentType(file);

		long repositoryId = themeDisplay.getScopeGroupId();
	

		try {

			Folder folder = CompCerUtil.getFolder(themeDisplay);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),resourceRequest);

			FileEntry fileEntry = DLAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(), fileName, mimeType, title, description,
					"", file, serviceContext);
			return fileEntry.getFileEntryId();
		}catch (Exception e) {
			_log.error("error while uploading file:  "+e.getMessage());
		}
		return repositoryId;
			

		
	}
	private static Log _log = LogFactoryUtil.getLog(SaveCompliance_certificate_at_quarterly_intervalScrutiny.class.getName());
}



