/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.daily.average.service.service.impl;

import com.daily.average.service.model.HDFCInternal_Audit_Report_Scrutiny;
import com.daily.average.service.service.base.HDFCInternal_Audit_Report_ScrutinyLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class HDFCInternal_Audit_Report_ScrutinyLocalServiceImpl
	extends HDFCInternal_Audit_Report_ScrutinyLocalServiceBaseImpl {
	
	public HDFCInternal_Audit_Report_Scrutiny saveHDFCInternal_Audit_Report_Scrutiny(String username,double mnversion,long userid, 
			String management_comments, String certificate_pdf_file_remarks, String auditor_pdf_file_remarks, String iarcircular_pdf_file_remarks, Date createdon, long createdby, long reportUploadLogId) {
				
		HDFCInternal_Audit_Report_Scrutiny hdfcInternal_Audit_Report_Scrutiny = hdfcInternal_Audit_Report_ScrutinyPersistence.create(counterLocalService.increment(HDFCInternal_Audit_Report_Scrutiny.class.getName()));
		
		hdfcInternal_Audit_Report_Scrutiny.setReportUploadLogId(reportUploadLogId);
		hdfcInternal_Audit_Report_Scrutiny.setUsername(username);
		hdfcInternal_Audit_Report_Scrutiny.setVersion(mnversion);
		hdfcInternal_Audit_Report_Scrutiny.setUserid(userid);
		
		hdfcInternal_Audit_Report_Scrutiny.setManagement_comments(management_comments);
		hdfcInternal_Audit_Report_Scrutiny.setCertificate_pdf_file_remarks(certificate_pdf_file_remarks);
		hdfcInternal_Audit_Report_Scrutiny.setAuditor_pdf_file_remarks(auditor_pdf_file_remarks);
		hdfcInternal_Audit_Report_Scrutiny.setIarcircular_pdf_file_remarks(iarcircular_pdf_file_remarks);
		
		hdfcInternal_Audit_Report_Scrutiny.setCreatedon(createdon);
		hdfcInternal_Audit_Report_Scrutiny.setCreatedby(createdby);
		
		return hdfcInternal_Audit_Report_ScrutinyPersistence.update(hdfcInternal_Audit_Report_Scrutiny);
		
	}
	
	public List<HDFCInternal_Audit_Report_Scrutiny> findByReportUploadLogId(long reportUploadLogId) {
		return hdfcInternal_Audit_Report_ScrutinyPersistence.findByReportUploadLogId(reportUploadLogId);
	}
	
}