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


import com.daily.average.service.model.annual_audited_scheme_financials_1;
import com.daily.average.service.service.base.annual_audited_scheme_financials_1LocalServiceBaseImpl;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class annual_audited_scheme_financials_1LocalServiceImpl
	extends annual_audited_scheme_financials_1LocalServiceBaseImpl {
	
	public void addannual_audited_scheme_financials_1(List<annual_audited_scheme_financials_1> list){
		for(Iterator<annual_audited_scheme_financials_1> iterator = list.iterator(); iterator.hasNext();) {
			annual_audited_scheme_financials_1 annual_audited_scheme_financials_1 = (annual_audited_scheme_financials_1)iterator.next();
			annual_audited_scheme_financials_1LocalService.addannual_audited_scheme_financials_1(annual_audited_scheme_financials_1);
		}
}
	
	public void deleteannual_audited_scheme_financials_1ByReportUploadLogId(Long reportUploadLogId) {
		  List<annual_audited_scheme_financials_1> annual_audited_scheme_financials =
				  annual_audited_scheme_financials_1Persistence.findByReportUploadLogId(reportUploadLogId);
		  for(annual_audited_scheme_financials_1 annual_audited_scheme_financials_1 : annual_audited_scheme_financials) {
			  annual_audited_scheme_financials_1LocalService.deleteannual_audited_scheme_financials_1(annual_audited_scheme_financials_1); } }
}