package Compliance_certificate_at_quarterly_interval.asset;

import com.daily.average.service.model.InputQuarterlyInterval;
import com.daily.average.service.model.ReportMaster;
import com.daily.average.service.model.ReportUploadFileLog;
import com.daily.average.service.model.ScrutinyInputQuarterlyInterval;
import com.daily.average.service.service.ReportMasterLocalService;
import com.daily.average.service.service.ReportMasterLocalServiceUtil;
import com.daily.average.service.service.ReportUploadFileLogLocalService;
import com.daily.average.service.service.ReportUploadFileLogLocalServiceUtil;
import com.daily.average.service.service.ReportUploadLogLocalService;
import com.daily.average.service.service.ScrutinyInputQuarterlyIntervalLocalServiceUtil;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;

import Compliance_certificate_at_quarterly_interval.util.CompCerUtil;
import Compliance_certificate_at_quarterly_interval.util.ComplianceCertificateAtQuartelyIntervalUtil;
import nps.common.service.util.WorkFlowTaskUtil;

public class Compliance_certificate_at_quarterly_intervalAssetRender extends BaseJSPAssetRenderer<InputQuarterlyInterval> {
	
	private final InputQuarterlyInterval inputQuarterlyInterval;
	
	@Reference
	UserLocalService userLocalService; 
	
	public Compliance_certificate_at_quarterly_intervalAssetRender(InputQuarterlyInterval inputQuarterlyInterval) {
		this.inputQuarterlyInterval = inputQuarterlyInterval;
	}

	@Override
	public InputQuarterlyInterval getAssetObject() {
		return inputQuarterlyInterval;
	}

	@Override
	public long getGroupId() {
		return userLocalService.fetchUser(inputQuarterlyInterval.getCreatedby()).getGroupId();
	}

	@Override
	public long getUserId() {
		return inputQuarterlyInterval.getCreatedby();
	}

	@Override
	public String getUserName() {
		return userLocalService.fetchUser(inputQuarterlyInterval.getCreatedby()).getFullName();		
	}

	@Override
	public String getUuid() {
		return inputQuarterlyInterval.getUuid();
	}

	@Override
	public String getClassName() {
		return InputQuarterlyInterval.class.getName();
	}

	@Override
	public long getClassPK() {
		return inputQuarterlyInterval.getPrimaryKey();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		ReportMaster reportMaster =  ReportMasterLocalServiceUtil.fetchReportMaster(inputQuarterlyInterval.getReportMasterId());
		return  "Report Name : "+reportMaster.getReportName() +", Report due date"+inputQuarterlyInterval.getReportDate();
	}

	@Override
	public String getTitle(Locale locale) {
		ReportMaster reportMaster =  ReportMasterLocalServiceUtil.fetchReportMaster(inputQuarterlyInterval.getReportMasterId());
		return reportMaster.getReportName();
	}

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest, String template) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		
		String reportName = "NA";
		String uploadDate = "dd/MM/yyyy hh:mm";
		String annex_comp_certificate = "";
		String annex_i = "";
		String board_a = "";
		String board_b = "";
		String investment_a = "";
		String investment_b = "";
		String risk_a = "";
		String risk_b = "";
		String annex_ii = "";
		String annex_iii = "";
		String annex_iv = "";
		boolean isNonNPSUser = false;
		boolean isAssignable=false;
		ThemeDisplay themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<ReportUploadFileLog> reportUploadFileLogs = new ArrayList<ReportUploadFileLog>();
		try {
			_log.debug("inputQuarterlyInterval---"+inputQuarterlyInterval);
			if(inputQuarterlyInterval!=null) {
				Long reportUploadLogId = inputQuarterlyInterval.getPrimaryKey();
				httpServletRequest.setAttribute("reportUploadLogId", reportUploadLogId);
				_log.info("reportUploadLogId---"+reportUploadLogId);
				//ReportUploadLog reportUploadLog = reportUploadLogLocalService.fetchReportUploadLog(reportUploadLogId);
				if(reportUploadLogId!=null) {
					try {
						httpServletRequest.setAttribute("reportUploadLogId", reportUploadLogId);
						reportUploadFileLogs = ReportUploadFileLogLocalServiceUtil.findByReportUploadLogId(reportUploadLogId);
						annex_comp_certificate = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getAnnex_comp_certificate());
						annex_i = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getAnnex_i());
						board_a =  CompCerUtil.getDocumentURL(inputQuarterlyInterval.getBoard_a());
						board_b = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getBoard_b());
						investment_a = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getInvestment_a());
						investment_b = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getInvestment_b());
						risk_a = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getRisk_a());
						risk_b = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getRisk_b());
						annex_ii = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getAnnex_ii());
						annex_iii = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getAnnex_iii());
						annex_iv = CompCerUtil.getDocumentURL(inputQuarterlyInterval.getAnnex_iv());
					} catch (Exception e) {
						_log.error(e);
					}
				}
				try {
					ReportMaster reportMaster = ReportMasterLocalServiceUtil.getReportMaster(inputQuarterlyInterval.getReportMasterId());
					reportName = reportMaster.getReportName() + (Validator.isNotNull(reportMaster.getCra()) ? " - " + reportMaster.getCra() : "");
				} catch (Exception e) {
					_log.error("Exception on getting report file name : "+e.getMessage());
				}
				uploadDate = dateFormat.format(inputQuarterlyInterval.getCreatedate());
				try {
					List<ScrutinyInputQuarterlyInterval>	scrutinyInputQuarterlyIntervals=ScrutinyInputQuarterlyIntervalLocalServiceUtil.findByReportUploadLogId(reportUploadLogId);
					ScrutinyInputQuarterlyInterval scrutinyInputQuarterlyInterval=scrutinyInputQuarterlyIntervals.get(scrutinyInputQuarterlyIntervals.size()-1);
					httpServletRequest.setAttribute("scrutinyInputQuarterlyInterval", scrutinyInputQuarterlyInterval);
				}catch (Exception e) {
					_log.error(e);
				}
			}
			ComplianceCertificateAtQuartelyIntervalUtil complianceCertificateAtQuartelyIntervalUtil = new ComplianceCertificateAtQuartelyIntervalUtil();
			isNonNPSUser = complianceCertificateAtQuartelyIntervalUtil.isNonNpsUser(themeDisplay.getUserId());
			WorkflowTask workflowTask= WorkFlowTaskUtil.getWorkFlowtask(InputQuarterlyInterval.class.getName(), inputQuarterlyInterval.getReportUploadLogId(), themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), themeDisplay.getUserId());
			 isAssignable= WorkFlowTaskUtil.isAssignable(themeDisplay.getCompanyId(), themeDisplay.getUserId(), workflowTask.getWorkflowTaskId());
			 boolean isSelfAsignee=WorkFlowTaskUtil.isSelfAsignee(themeDisplay.getCompanyId(), themeDisplay.getUserId(), workflowTask.getWorkflowInstanceId());
				httpServletRequest.setAttribute("isSelfAsignee", isSelfAsignee);	
			 _log.info("isAssignable "+isAssignable+ "is self assignee: "+isSelfAsignee+"  taskid:  "+workflowTask.getWorkflowTaskId());
		
		} catch(Exception e) {
			_log.error(e);
		}
		
		
		httpServletRequest.setAttribute("reportName", reportName);
		httpServletRequest.setAttribute("uploadDate", uploadDate);
		httpServletRequest.setAttribute("reportUploadFileLogs", reportUploadFileLogs);
		httpServletRequest.setAttribute("inputQuarterlyInterval", inputQuarterlyInterval);
		
		httpServletRequest.setAttribute("annex_i", annex_i);
		httpServletRequest.setAttribute("annex_comp_certificate", annex_comp_certificate);
		httpServletRequest.setAttribute("board_a", board_a);
		httpServletRequest.setAttribute("board_b", board_b);
		httpServletRequest.setAttribute("investment_a", investment_a);
		httpServletRequest.setAttribute("investment_b", investment_b);
		httpServletRequest.setAttribute("risk_a", risk_a);
		httpServletRequest.setAttribute("risk_b", risk_b);
		httpServletRequest.setAttribute("annex_ii", annex_ii);
		httpServletRequest.setAttribute("annex_iii", annex_iii);
		httpServletRequest.setAttribute("annex_iv", annex_iv);
		httpServletRequest.setAttribute("isNonNPSUser", isNonNPSUser);
		httpServletRequest.setAttribute("isAssignable", isAssignable);

		
		return "/asset/scrutiny.jsp";
	}
	
	@Reference
	ReportMasterLocalService reportMasterLocalService;
	
	@Reference
	ReportUploadLogLocalService reportUploadLogLocalService;
	
	@Reference	
	ReportUploadFileLogLocalService reportUploadFileLogLocalService;
	
	Log _log = LogFactoryUtil.getLog(Compliance_certificate_at_quarterly_intervalAssetRender.class);

}
