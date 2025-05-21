package com.quarterly.stewardship.report.asset;

import com.daily.average.service.model.QtrStewardshipReport;
import com.daily.average.service.model.QtrStewardshipReportScrutiny;
import com.daily.average.service.model.ReportMaster;
import com.daily.average.service.model.ReportUploadFileLog;
import com.daily.average.service.model.ReportUploadLog;
import com.daily.average.service.service.QtrStewardshipReportScrutinyLocalServiceUtil;
import com.daily.average.service.service.ReportMasterLocalServiceUtil;
import com.daily.average.service.service.ReportUploadFileLogLocalServiceUtil;
import com.daily.average.service.service.ReportUploadLogLocalServiceUtil;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.quarterly.stewardship.report.util.QuartelyStewardshipReportUtil;
import com.quarterly.stewardship.report.util.StewardshipRepDocumentUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;

public class QuarterlyStewardshipReportAssetRender extends BaseJSPAssetRenderer<QtrStewardshipReport> {
	
	private QtrStewardshipReport qtrStewardshipReport;
	
	public QuarterlyStewardshipReportAssetRender(QtrStewardshipReport qtrStewardshipReport) {
		this.qtrStewardshipReport = qtrStewardshipReport;
	}

	@Override
	public QtrStewardshipReport getAssetObject() {
		
		return qtrStewardshipReport;
	}

	@Override
	public long getGroupId() {
		
		return userLocalService.fetchUser(qtrStewardshipReport.getCreatedby()).getGroupId();
	}

	@Override
	public long getUserId() {

		return qtrStewardshipReport.getCreatedby();
	}

	@Override
	public String getUserName() {

		return userLocalService.fetchUser(qtrStewardshipReport.getCreatedby()).getFullName();
	}

	@Override
	public String getUuid() {
		
		return qtrStewardshipReport.getUuid();
	}

	@Override
	public String getClassName() {
		
		return QtrStewardshipReport.class.getName();
	}

	@Override
	public long getClassPK() {
		
		return qtrStewardshipReport.getPrimaryKey();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		ReportMaster reportMaster =  ReportMasterLocalServiceUtil.fetchReportMaster(qtrStewardshipReport.getReportMasterId());
		return  "Report Name : "+reportMaster.getReportName() +", Report due date"+qtrStewardshipReport.getReportDate();
	}

	@Override
	public String getTitle(Locale locale) {
		ReportMaster reportMaster =  ReportMasterLocalServiceUtil.fetchReportMaster(qtrStewardshipReport.getReportMasterId());
		return reportMaster.getReportName();
	}

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest, String template) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		
		String reportName = "NA";
		String reportDate ="dd/MM/yyyy hh:mm";
		String uploadDate = "dd/MM/yyyy hh:mm";
		String Annexure_aURL="";
		String Annexure_b_iURL="";
		String Annexure_b_iiURL="";
		String Annexure_cURL="";
		boolean isNonNPSUser = false;
		ThemeDisplay themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<ReportUploadFileLog> reportUploadFileLogs = new ArrayList<ReportUploadFileLog>();
		try {
			_log.info("qtrStewardshipReport---"+qtrStewardshipReport);
			if(qtrStewardshipReport!=null) {
				Long reportUploadLogId = qtrStewardshipReport.getPrimaryKey();
				_log.info("reportUploadLogId---"+reportUploadLogId);
				//ReportUploadLog reportUploadLog = reportUploadLogLocalService.fetchReportUploadLog(reportUploadLogId);
				if(reportUploadLogId!=null)
					try {
			
						ReportUploadLog reportUploadLog = ReportUploadLogLocalServiceUtil.fetchReportUploadLog(reportUploadLogId);
						reportUploadFileLogs = ReportUploadFileLogLocalServiceUtil.findByReportUploadLogId(reportUploadLogId);
						Annexure_aURL=StewardshipRepDocumentUtil.getDocumentURL(qtrStewardshipReport.getAnnexure_a());
						Annexure_b_iURL=StewardshipRepDocumentUtil.getDocumentURL(qtrStewardshipReport.getAnnexure_b_i());
						Annexure_b_iiURL=StewardshipRepDocumentUtil.getDocumentURL(qtrStewardshipReport.getAnnexure_b_ii());
						Annexure_cURL=StewardshipRepDocumentUtil.getDocumentURL(qtrStewardshipReport.getAnnexure_c());
						httpServletRequest.setAttribute("reportUploadLogId", reportUploadLogId);
						
						reportDate=dateFormat.format(reportUploadLog.getReportDate());
						
						_log.info("original reportdate"+reportUploadLog.getReportDate()+" after changes "+ reportDate);
						
					} catch (Exception e) {
						_log.error(e);
					}
					
				try {
					ReportMaster reportMaster = ReportMasterLocalServiceUtil.getReportMaster(qtrStewardshipReport.getReportMasterId());
					reportName = reportMaster.getReportName() + (Validator.isNotNull(reportMaster.getCra()) ? " - " + reportMaster.getCra() : "");
				} catch (Exception e) {
					_log.error("Exception on getting report file name : "+e.getMessage());
				}
				uploadDate = dateFormat.format(qtrStewardshipReport.getCreatedon());
				try {
					QtrStewardshipReportScrutiny qtrStewardshipReportScrutiny=null;
					List<QtrStewardshipReportScrutiny> qtrStewardshipReportScrutinies=	QtrStewardshipReportScrutinyLocalServiceUtil.findQtrStewardshipReportScrutinyByReportUploadLogId(reportUploadLogId);
				if(qtrStewardshipReportScrutinies!= null || Validator.isNotNull(qtrStewardshipReportScrutinies)) {
					//qtrStewardshipReportScrutiny=qtrStewardshipReportScrutinies.get(0);
					
					qtrStewardshipReportScrutiny=qtrStewardshipReportScrutinies.get(qtrStewardshipReportScrutinies.size()-1);
					httpServletRequest.setAttribute("qtrstewardshipscrutiny", qtrStewardshipReportScrutiny);
				}
				}catch (Exception e) {
					_log.error(e);
				}
			}
			QuartelyStewardshipReportUtil quartelyStewardshipReportUtil =  new QuartelyStewardshipReportUtil();
			isNonNPSUser = quartelyStewardshipReportUtil.isNonNpsUser(themeDisplay.getUserId());
		} catch(Exception e) {
			_log.error(e);
		}
		
		httpServletRequest.setAttribute("reportName", reportName);
		httpServletRequest.setAttribute("reportDate", reportDate);
		httpServletRequest.setAttribute("uploadDate", uploadDate);
		httpServletRequest.setAttribute("reportUploadFileLogs", reportUploadFileLogs);
		httpServletRequest.setAttribute("qtrStewardshipReport", qtrStewardshipReport);
		httpServletRequest.setAttribute("Annexure_aURL", Annexure_aURL);
	    httpServletRequest.setAttribute("Annexure_b_iURL", Annexure_b_iURL);
	    httpServletRequest.setAttribute("Annexure_b_iiURL", Annexure_b_iiURL);
	    httpServletRequest.setAttribute("Annexure_cURL", Annexure_cURL);
	    httpServletRequest.setAttribute("isNonNPSUser", isNonNPSUser);
		
		return "/asset/stewardship_report_workflow_abstract_view.jsp";
	}
	
	@Reference
	UserLocalService userLocalService;
	
	Log _log = LogFactoryUtil.getLog(QuarterlyStewardshipReportAssetRender.class);

}
