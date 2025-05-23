package com.status.days.resource;


import com.daily.average.service.model.StatusDays;
import com.daily.average.service.model.StatusDaysOne;
import com.daily.average.service.model.StatusDaysTwo;
import com.daily.average.service.service.IntermediaryListLocalServiceUtil;
import com.daily.average.service.service.ReportUploadLogGrievancesLocalServiceUtil;
import com.daily.average.service.service.StatusDaysLocalServiceUtil;
import com.daily.average.service.service.StatusDaysOneLocalServiceUtil;
import com.daily.average.service.service.StatusDaysTwoLocalServiceUtil;
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
import com.status.days.constants.StatusDaysPortletKeys;
import com.status.days.util.ParseSheetOne;
import com.status.days.util.ParseSheetThree;
import com.status.days.util.ParseSheetTwo;
import com.status.days.util.ValidateFileName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import nps.common.service.util.CommonParser;
import nps.email.api.api.ExcelValidationAn10Api;


@Component(property = { "javax.portlet.name=" + StatusDaysPortletKeys.STATUSDAYS,
"mvc.command.name=/statusdays/save" }, service = MVCResourceCommand.class)
public class SaveStatusDays implements MVCResourceCommand{
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		_log.info("/statusdays/save............");
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
		String reportUploadLogIdString = uploadPortletRequest.getParameter("reportUploadLogId");
		Long reportUploadLogId = Long.parseLong(reportUploadLogIdString);
		
		String cra = IntermediaryListLocalServiceUtil.fetchIntermediaryByReportLogId(reportUploadLogId);
		String fileName = uploadPortletRequest.getFileName("statusdaysFile");

		File file = uploadPortletRequest.getFile("statusdaysFile");
		

		String mimeType = uploadPortletRequest.getContentType("statusdaysFile");

		String title = fileName;
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		
		JSONArray statusdaysJsonArray = JSONFactoryUtil.createJSONArray();
		List<StatusDays> statusdayss = new ArrayList<StatusDays>();
		
		JSONArray statusDaysOneJsonArray= JSONFactoryUtil.createJSONArray();
		List<StatusDaysOne> statusdaysones= new ArrayList<StatusDaysOne>();
		
		JSONArray statusDaysTwoJsonArray= JSONFactoryUtil.createJSONArray();
		List<StatusDaysTwo> statusdaystwos= new ArrayList<StatusDaysTwo>();
		
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
		XSSFCell customerIdcell = xxx.createCell(2);
		customerIdcell.setCellValue("Sr.No");
		
		List<String> errorList = new ArrayList<String>();
		errorList.add("Griev Pending for resolution");
		errorList.add("Pending Data_NPS");
		errorList.add("Pending Data_APY");
		
		try {
			if (file != null) {
				OPCPackage pkg = OPCPackage.open(file);
				workbook = new XSSFWorkbook(pkg);
				//int sheetcount = 0;
				Iterator<Sheet> sheets = workbook.sheetIterator();
				while (sheets.hasNext()) {
					XSSFSheet sheet = (XSSFSheet) sheets.next();
					if (errorList.contains(sheet.getSheetName())) {
						errorList.remove(sheet.getSheetName());
					}
				}
			
				if (errorList.size()>0) {
					resultJsonObject.put("status", false);
					resultJsonObject.put("sheeterror",true);
					JSONArray  errorSheetNameList = JSONFactoryUtil.createJSONArray(errorList);
					resultJsonObject.put("errorSheetNameList", errorSheetNameList);
					return resultJsonObject;
					
				}
			}
		}catch (Exception e) {
		}
		
		boolean isexcelhaserror = false;
		
		//parsing sheetone in util class
		resultJsonObject = ParseSheetOne.saveSheetOne(file, workbook, userId, errorArray, xx, isexcelhaserror, statusdaysJsonArray, statusdayss, cra, reportUploadLogId);
		if(!resultJsonObject.getBoolean("status"))
			return resultJsonObject;
		resultJsonObject = ParseSheetTwo.saveSheetTwo(file, workbook, userId, errorArray, xx, isexcelhaserror, statusDaysOneJsonArray, statusdaysones, decimalFormat, cra, reportUploadLogId);
		if(!resultJsonObject.getBoolean("status"))
			return resultJsonObject;
		resultJsonObject = ParseSheetThree.saveSheetThree(file, workbook, userId, errorArray, xx, isexcelhaserror, statusDaysTwoJsonArray, statusdaystwos,decimalFormat, cra, reportUploadLogId);
		if(!resultJsonObject.getBoolean("status"))
			return resultJsonObject;
		_log.info(statusdaysJsonArray.toString());
		
		if (!isexcelhaserror) {
			
			try {
				StatusDaysLocalServiceUtil.addStatusDays(statusdayss);
				StatusDaysOneLocalServiceUtil.addStatusDaysOnes(statusdaysones);
				StatusDaysTwoLocalServiceUtil.addStatusDaysTwos(statusdaystwos);
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
				
				//resultJsonObject.put("pdfURL",  pdfTable(statusDayss, themeDisplay, resourceRequest));
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

		String fileName = date.getTime()+ uploadPortletRequest.getFileName("statusdaysFile");

		File file = uploadPortletRequest.getFile("statusdaysFile");

		String mimeType = uploadPortletRequest.getContentType("statusdaysFile");

		String title = fileName;

		String description = "statusdaysFile";

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
			folder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), PARENT_FOLDER_ID, "Grievances");
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
	
	Log _log = LogFactoryUtil.getLog(getClass());

}

