package FinalIntimation_NPS.mvc;

import com.daily.average.service.model.GrievanceFinalIntimPao;
import com.daily.average.service.model.GrievanceFinalIntimreglite;
import com.daily.average.service.service.GrievanceFinalIntimPaoLocalServiceUtil;
import com.daily.average.service.service.GrievanceFinalIntimregliteLocalServiceUtil;
import com.daily.average.service.service.IntermediaryListLocalServiceUtil;
import com.daily.average.service.service.ReportUploadLogGrievancesLocalServiceUtil;
import com.daily.average.service.service.ReportUploadLogPFMLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import FinalIntimation_NPS.Util.PaoGrievances;
import FinalIntimation_NPS.Util.Reg_lite;
import FinalIntimation_NPS.Util.ValidateFileName;
import FinalIntimation_NPS.constants.FinalIntimation_NPSPortletKeys;
import nps.common.service.util.CommonParser;
import nps.email.api.api.ExcelValidationAn10Api;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" +  FinalIntimation_NPSPortletKeys.FINALINTIMATION_NPS,
				"mvc.command.name=/saveFinalIntimationNPSURL/data"
		},
		service = MVCResourceCommand.class
)

public class FinalIntimation_NPSMvcResources implements MVCResourceCommand{

	private static Log _log = LogFactoryUtil.getLog(FinalIntimation_NPSMvcResources.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
			_log.info("grivances Serve Resource method");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			JSONObject resultJsonObject = fileUpload(themeDisplay, resourceRequest);
			try {
				PrintWriter writer = resourceResponse.getWriter();
				writer.write(resultJsonObject.toString());
			} catch (IOException e) {
				 _log.error(e);
			}
			return false;
	}
	
	
	public JSONObject fileUpload(ThemeDisplay themeDisplay, ResourceRequest resourceRequest) {
		Long userId = themeDisplay.getUserId();
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

		String statusByUserName = themeDisplay.getUser().getFullName();
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(resourceRequest);
		} catch (PortalException e) {
			 _log.error(e); 
		}
		
		
		Long reportMasterId = ParamUtil.getLong(resourceRequest, "reportMasterId");
		
		
		String reportUploadLogIdString = uploadPortletRequest.getParameter("reportUploadLogId");
		_log.info("reportUploadLogIdString" + reportUploadLogIdString);
		Long reportUploadLogId = Long.parseLong(reportUploadLogIdString);
		_log.info("reportUploadLogId" + reportUploadLogId);
		String cra = IntermediaryListLocalServiceUtil.fetchIntermediaryByReportLogId(reportUploadLogId);
		String fileName = uploadPortletRequest.getFileName("FinalIntimationnps");

		File file = uploadPortletRequest.getFile("FinalIntimationnps");

		String mimeType = uploadPortletRequest.getContentType("FinalIntimationnps");

		String title = fileName;
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		
		//Form 2
		JSONArray finalIntimPaoArray = JSONFactoryUtil.createJSONArray();
		List<GrievanceFinalIntimPao> finalIntimPaoList = new ArrayList<GrievanceFinalIntimPao>();
		
		//Form 1
		JSONArray finalIntimliteArray = JSONFactoryUtil.createJSONArray();
		List<GrievanceFinalIntimreglite> finalIntimregliteList = new ArrayList<GrievanceFinalIntimreglite>();
		
	
		
		JSONObject resultJsonObject = JSONFactoryUtil.createJSONObject();
		if(ValidateFileName.isValidfile(fileName)) {
		resultJsonObject = excelValidationAn10Api.validateExcelFile(file, resourceRequest);
		if(resultJsonObject.getBoolean("status")) {
		
			JSONArray errorArray = JSONFactoryUtil.createJSONArray();
			XSSFWorkbook workbook = null;
			
			/**
			 * Create error excel
			 */
			XSSFWorkbook errorExcel = new XSSFWorkbook();
			XSSFSheet xx = (XSSFSheet) errorExcel.createSheet();
			XSSFRow xxx = xx.createRow(1);
			XSSFCell rowNumCel = xxx.createCell(1);
			rowNumCel.setCellValue("RowNum");
			XSSFCell fileNamecell = xxx.createCell(2);
			fileNamecell.setCellValue("Pension Fund");
			
			boolean isexcelhaserror = false;
			
			resultJsonObject = PaoGrievances.savePaoGrievances(file, workbook, userId, errorArray, xx, isexcelhaserror, finalIntimPaoArray, finalIntimPaoList,decimalFormat,cra, reportUploadLogId);
			if(!resultJsonObject.getBoolean("status")) {
				return resultJsonObject;
			}
			resultJsonObject = Reg_lite.saveReg_lite(file, workbook, userId, errorArray, xx, isexcelhaserror, finalIntimliteArray, finalIntimregliteList,decimalFormat,cra, reportUploadLogId);
			if(!resultJsonObject.getBoolean("status")) {
				return resultJsonObject;
			}
			if (!isexcelhaserror) {
				
				try {
					GrievanceFinalIntimPaoLocalServiceUtil.addGrievanceFinalIntimPao(finalIntimPaoList);
					GrievanceFinalIntimregliteLocalServiceUtil.addGrievanceFinalIntimreglite(finalIntimregliteList);
				} catch (Exception e1) {
					 resultJsonObject.put("status", false);
					 resultJsonObject.put("msg", CommonParser.fileExceptionMsg);
					 return resultJsonObject;
				}
				
				
				Long fileEntryId = 0l;
				fileEntryId = uploadFILETOFOLDER(themeDisplay, resourceRequest);
				try {
					errorExcel.close();
					String remarks = ParamUtil.getString(uploadPortletRequest, "remarks");
					updateReportLog ( userId, fileEntryId, true, reportUploadLogId,  WorkflowConstants.STATUS_PENDING, statusByUserName, serviceContext,remarks);
					//resultJsonObject.put("pdfURL",  pdfTable(eTierIList, themeDisplay, resourceRequest));
				} catch (Exception e) {
					 _log.error(e);
				}
				resultJsonObject.put("status", true);
			} else {
				String downloadUrl = null;
				try {
	
					OutputStream stream = null;
	
					File errortempfile = File.createTempFile("error", "xlsx");
					stream = new FileOutputStream(errortempfile);
	
					errorExcel.write(stream);
	
					String filePath = errortempfile.getPath();
					String filename = errortempfile.getName();
					stream.close();
					errorExcel.close();
					downloadUrl = fileUpload(themeDisplay, filePath, filename, resourceRequest);
				} catch (Exception e) {
					 _log.error(e);
				} 
				resultJsonObject.put("downloadUrl", downloadUrl);
				resultJsonObject.put("status", false);
			}
		}
		}else {
			resultJsonObject.put("status", false);
			resultJsonObject.put("msg","Please upload files with a valid filename.");
			return resultJsonObject;
		}
		return resultJsonObject;
	}
	
	
	@SuppressWarnings("deprecation")
	public String fileUpload(ThemeDisplay themeDisplay, String filepath, String filename,
			ResourceRequest resourceRequest) {

		File file = new File(filepath);
		String mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String title = filename + ".xlsx";
		String description = "This file is added via programatically";
		long repositoryId = themeDisplay.getScopeGroupId();
		String downloadUrl = StringPool.BLANK;
		try {

			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					resourceRequest);
			FileEntry entry = DLAppServiceUtil.addFileEntry(repositoryId, 0, title, mimeType, title, description, "",
					file, serviceContext);
			FileVersion fileVersion = (FileVersion) entry.getLatestFileVersion();
			downloadUrl = DLURLHelperUtil.getDownloadURL(entry, fileVersion, themeDisplay, StringPool.BLANK);
		} catch (Exception e) {
			_log.info(e.getMessage());
			 _log.error(e);
		}
		return downloadUrl;
	}
	
	@SuppressWarnings("deprecation")
	public long uploadFILETOFOLDER(ThemeDisplay themeDisplay, ResourceRequest resourceRequest) {
		Date date =new Date();
		
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);

		String fileName = date.getTime() + uploadPortletRequest.getFileName("FinalIntimationnps");

		File file = uploadPortletRequest.getFile("FinalIntimationnps");

		String mimeType = uploadPortletRequest.getContentType("FinalIntimationnps");

		String title = fileName;

		String description = "FinalIntimationnps";

		long repositoryId = themeDisplay.getScopeGroupId();
	

		try {

			Folder folder = getFolder(themeDisplay);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
					resourceRequest);

			FileEntry fileEntry = DLAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(), fileName, mimeType, title, description,
					"", file, serviceContext);
			return fileEntry.getPrimaryKey();

		} catch (Exception e) {

			_log.info(e.getMessage());

			 _log.error(e);
		}
		return 0;
	}
	
	private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	
	public Folder getFolder(ThemeDisplay themeDisplay) {

		Folder folder = null;
		try {
			folder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), PARENT_FOLDER_ID, "Monthly");
			_log.info(folder);

		} catch (Exception e) {

			_log.info(e.getMessage());
		}
		return folder;

	}
	
	public void updateReportLog( long createdBy, long fileEntryId, boolean uploaded, long reportUploadLogId, int status, String statusByUserName, ServiceContext serviceContext, String remarks) {
		Date createDate = new Date();
		ReportUploadLogGrievancesLocalServiceUtil.updateReportUploadLog(createDate, createdBy, fileEntryId, uploaded, reportUploadLogId, status, createdBy, statusByUserName, createDate, serviceContext,remarks);//(createDate, createdBy, fileEntryId, uploaded, reportUploadLogId);
	}
	
	@Reference
	ExcelValidationAn10Api excelValidationAn10Api;
		
}
