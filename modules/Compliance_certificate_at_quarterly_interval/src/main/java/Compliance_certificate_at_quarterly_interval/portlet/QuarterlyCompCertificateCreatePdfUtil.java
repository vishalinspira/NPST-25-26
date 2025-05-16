package Compliance_certificate_at_quarterly_interval.portlet;

import com.itextpdf.io.util.FileUtil;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.UnitValue;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.util.FileUtil;
//import java.io.File;

import java.io.File;

public class QuarterlyCompCertificateCreatePdfUtil {
	
	
	public static File QuarterlyComplianeCertificatePFMPDF(long reportuploadlogid,String dueDate,String formDate, String oneia, String oneib, String oneiia, String oneiib, String oneiic, String oneiid, 
			String oneiiia, String oneiiib, String oneiva, String onev, String twoia, String twoib, String twoic, String twoiione, String twoiitwo, String twoiithree, 
			String twoiifour, String twoiifive, String twoiisix, String twoiiseven, String threea, String threeb, String threec, String threeci, String threecii, String threeciii,
			String threeciv, String foura, String fourb, String fourc, String fivei, String fiveii, String fiveiii, String fiveiv, String fiveva, String fivevb, String fivevc,
			String fivevia, String fivevib, String sixi, String sixiia, String sixiib, String sixiic, String sevenia, String sevenib, String sevenic, String sevenid, 
			String sevenie, String eightia, String eightib, String eightii, String eightiii, String eightiv, String eightv, String eightvia, String eightvib, String eightviia,
			String eightviib, String eightviii,String eightix,String eightx, String ninea, String nineb, String ten, String elevena, String elevenb, String twelve, String oneia_remark, String oneib_remark,
			String oneiia_remark, String oneiib_remark, String oneiic_remark, String oneiid_remark, String oneiiia_remark, String oneiiib_remark, String oneiva_remark,
			String onev_remark, String twoia_remark, String twoib_remark, String twoic_remark, String twoiione_remark, String twoiitwo_remark, String twoiithree_remark,
			String twoiifour_remark, String twoiifive_remark, String twoiisix_remark, String twoiiseven_remark, String threea_remark, String threeb_remark, String threec_remark,
			String threeci_remark, String threecii_remark, String threeciii_remark, String threeciv_remark, String foura_remark, String fourb_remark, String fourc_remark,
			String fivei_remark, String fiveii_remark, String fiveiii_remark, String fiveiv_remark, String fiveva_remark, String fivevb_remark, String fivevc_remark, 
			String fivevia_remark, String fivevib_remark, String sixi_remark, String sixiia_remark, String sixiib_remark, String sixiic_remark, String sevenia_remark, 
			String sevenib_remark, String sevenic_remark, String sevenid_remark, String sevenie_remark, String eightia_remark, String eightib_remark, String eightii_remark, 
			String eightiii_remark, String eightiv_remark, String eightv_remark, String eightvia_remark, String eightvib_remark, String eightviia_remark, String eightviib_remark,
			String eightviii_remark,String eightix_remark,String eightx_remark, String ninea_remark, String nineb_remark, String ten_remark, String elevena_remark, String elevenb_remark, String twelve_remark,
			String oneia_rem_pfm, String oneib_rem_pfm,
			String oneiia_rem_pfm, String oneiib_rem_pfm, String oneiic_rem_pfm, String oneiid_rem_pfm, String oneiiia_rem_pfm, String oneiiib_rem_pfm, String oneiva_rem_pfm,
			String onev_rem_pfm, String twoia_rem_pfm, String twoib_rem_pfm, String twoic_rem_pfm, String twoiione_rem_pfm, String twoiitwo_rem_pfm, String twoiithree_rem_pfm,
			String twoiifour_rem_pfm, String twoiifive_rem_pfm, String twoiisix_rem_pfm, String twoiiseven_rem_pfm, String threea_rem_pfm, String threeb_rem_pfm, String threec_rem_pfm,
			String threeci_rem_pfm, String threecii_rem_pfm, String threeciii_rem_pfm, String threeciv_rem_pfm, String foura_rem_pfm, String fourb_rem_pfm, String fourc_rem_pfm,
			String fivei_rem_pfm, String fiveii_rem_pfm, String fiveiii_rem_pfm, String fiveiv_rem_pfm, String fiveva_rem_pfm, String fivevb_rem_pfm, String fivevc_rem_pfm, 
			String fivevia_rem_pfm, String fivevib_rem_pfm, String sixi_rem_pfm, String sixiia_rem_pfm, String sixiib_rem_pfm, String sixiic_rem_pfm, String sevenia_rem_pfm, 
			String sevenib_rem_pfm, String sevenic_rem_pfm, String sevenid_rem_pfm, String sevenie_rem_pfm, String eightia_rem_pfm, String eightib_rem_pfm, String eightii_rem_pfm, 
			String eightiii_rem_pfm, String eightiv_rem_pfm, String eightv_rem_pfm, String eightvia_rem_pfm, String eightvib_rem_pfm, String eightviia_rem_pfm, String eightviib_rem_pfm,
			String eightviii_rem_pfm,	String eightix_rem_pfm,	String eightx_rem_pfm, String ninea_rem_pfm, String nineb_rem_pfm, String ten_rem_pfm, String elevena_rem_pfm, String elevenb_rem_pfm, String twelve_rem_pfm,
			String notedate, String companies, String date_3, String place, String ceoname, String role){
		File file=null;
		try  {  
			 file=FileUtil.createTempFile("Quarterly Compliance Certificate.pdf");
			PdfWriter writer = new PdfWriter(file);
		    PdfDocument pdfDoc= new PdfDocument(writer);
		    Document doc = new Document(pdfDoc); 
		    Text text1 = new Text("Quarterly Compliance Certificate");
		    text1.setBold();
		    Paragraph para1 = new Paragraph(text1);
            doc.add(para1);
            doc.add(new Paragraph().setBorder(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f)).setMarginTop(5f).setWidth(UnitValue.createPercentValue(100)));
		
            Table tablex = new Table(new float[] {5f, 5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    String[] headersx = {"Report Due Date","For the Quarter Ended"};
		    for (int col = 0; col < 2; col++) {
	            tablex.addCell(new Cell().add(new Paragraph(headersx[col])).setBold().setBorder(null));
	            
	        }
		    doc.add(tablex);
		    String[][] cellContentx =	{
		    		{dueDate,formDate}
             };
		    
		    for (int row = 0; row < 1; row++) {
		        for (int col = 0; col < 2; col++) {
		            tablex.addCell(new Cell().add(new Paragraph(cellContentx[row][col])).setBorder(null));
		            
		        }
		        }
		    tablex.complete();
            
            
            
		    Text text5 = new Text("\n  Company Name: "+companies).setBold();
		    Paragraph para2 = new Paragraph(text5);
		    Text text6 = new Text("\n \n To, \n National Pension System Trust,\n Tower B, B-302, Third Floor, \n World Trade Center,\nNauroji Nagar,\n New Delhi-110029\n");		   
		    para2.add(text6); 
		    Text text8 = new Text("\n In my/our opinion and to the best of my/our information and according to the examinations carried out by me/us and explanations furnished to me/us, I/We certify the following in respect of the quarter mentioned above. \n \n");
		     para2.add(text8);
		     doc.add(para2);
		     int colsize=4;
		    Table table1 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    String[] headers1 = {"S.No", "Parameters","Yes/No/NA", "PFM Remarks","NPST Remarks"};
		    //String[] headers1 = {"S.No", "Test","Yes/ No", "Intermediary Comment"};
		    for (int col = 0; col < colsize; col++) {
	            table1.addCell(new Cell().add(new Paragraph(headers1[col])).setBold());
	            
	        }
		    doc.add(table1);
			Cell cell1 = new Cell(1, colsize)
		            .add(new Paragraph("1. Management ,Sponser, Net-worth")).setBold();
		    table1.addCell(cell1);
		    String[][] cellContent1 =	{
		    		{"(i)","a) Whether composition of the Board, Investment committee and Risk Management committeeis as per PFRDA (Pension Fund) regulations?",
		    			oneia,oneia_rem_pfm,oneia_remark},
		    		{"","b) Whether Principal officer and key personnel have been appointed as per PFRDA (Pension Fund) regulations.",
		    				oneib,oneib_rem_pfm,oneib_remark},
		    		{"(ii)","a) Whether bio-data of all the directors along with their interest in other companies has been filed with the NPS Trust within 15 days of their appointment?",
		    					oneiia,oneiia_rem_pfm,oneiia_remark},
		    		{" ","b) Whether subsequent change in the interest of the directors havebeen filed with the NPS Trust?",
		    						oneiib,oneiib_rem_pfm,oneiib_remark},
		    		{" ","c) Whether minimum 51% of the directors have adequate professional experience in finance and financial services related fields.",
		    							oneiic,oneiic_rem_pfm,oneiic_remark},
		    		{" ","d) Whether change in key personnel has been intimated to the PFRDA within 15days of the occurrence of such change.",
		    								oneiid,oneiid_rem_pfm,oneiid_remark},
		    		{"(iii)","a) Whether the Sponsor and the Pension Fund(PFM) fulfill and comply with the eligibility requirements as specified under the PFRDA (Pension Fund) Regulations?",
		    									oneiiia,oneiiia_rem_pfm,oneiiia_remark},
		    		{"","b) Whether there is any material change in the information or particulars previously furnished which have a bearing onPFMs certificate of registration?",
		    										oneiiib,oneiiib_rem_pfm,oneiiib_remark},
		    		{"(iv) ","a) Whether there is any major change in the management or ownership or shareholding pattern or any change in controlling interest of the Sponsor?",
		    											oneiva,oneiva_rem_pfm,oneiva_remark},
			    	{"(v)","Whether PFM is maintaining minimum Tangible Net-worth as stipulated by PFRDA?",
		    												onev,onev_rem_pfm,onev_remark}
			    		
		    };
		    
		    for (int row = 0; row < 10; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table1.addCell(new Cell().add(new Paragraph(cellContent1[row][col])));
		            
		        }
		        }
		    table1.complete();
		    doc.add(new Paragraph(""));
		    
		    
		    Table table2 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table2);
			Cell cell2 = new Cell(1, colsize)
		            .add(new Paragraph("2. Investment Policy")).setBold();
		    table2.addCell(cell2);
		    
		    String[][] cellContent2 =	{
		    		{"(i)","a) Whether Investment Policy has been drawn in accordance with the investment guidelines approved by the PFRDA and has been approved by the Board of Directors (BOD) of the PFM?",
		    			twoia,twoia_rem_pfm,twoia_remark},
		    		{"","b) Whether Investment Policy is being reviewed periodically (minimum half yearly basis) by the PFM?",
		    				twoib,twoib_rem_pfm,twoib_remark},
		    		{" ","c) Whether the PFM has submitted details of the Investment Policy reviewed by its board to the NPS Trust within 30days of such review.",
		    					twoic,twoic_rem_pfm,twoic_remark}
		    		
		            };		
		    				
		    for (int row = 0; row < 3; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table2.addCell(new Cell().add(new Paragraph(cellContent2[row][col])));
		            
		        }
		        }
		    Cell cellp = new Cell(5, colsize)
		            .add(new Paragraph("Please provide the following:\r\n" + 
		            		"i) Initial BOD approval date\r\n" + 
		            		"ii) Last BOD review date\r\n" + 
		            		"iii) Frequency of review in a year as decided by the BOD"));
		    table2.addCell(cellp);
		    table2.complete();	
		    	
		    
		    Table table = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table);
		    String[][] cellContent =	{
		    		
		    		{"(ii)","Please mention (individually), whether Investment Policy covers the following:","","",""},
		    		{"","i) Prudential norms, Income recognition, Asset classification and Provisioning",
		    			twoiione,twoiione_rem_pfm,twoiione_remark},
		    		{"","ii) Sector limits as stipulated in the Investment guidelines",
		    				twoiitwo,twoiitwo_rem_pfm,twoiitwo_remark},
		    		{"","iii) Sponsor and Non-Sponsor Group limits as stipulated in the Investment guidelines",
		    					twoiithree,twoiithree_rem_pfm,twoiithree_remark},
			        {"","iv) Liquidity and Asset/liability management",
		    						twoiifour,twoiifour_rem_pfm,twoiifour_remark},
			    	{"","v) Stop Loss Limits",
		    							twoiifive,twoiifive_rem_pfm,twoiifive_remark},
				        {"","vi)Broker limit",
		    								twoiisix,twoiisix_rem_pfm,twoiisix_remark},
				        {"","vii) Investment audits",
		    									twoiiseven,twoiiseven_rem_pfm,twoiiseven_remark}
						       
		    };
		    for (int row = 0; row < 8; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table.addCell(new Cell().add(new Paragraph(cellContent[row][col])));
		            
		        }
		        }
		    table.complete();
		    doc.add(new Paragraph(""));
		    
		   Table table3 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table3);
			Cell cell3 = new Cell(1, colsize)
		            .add(new Paragraph("3. Risk Management Policy")).setBold();
		    table3.addCell(cell3);
		    String[][] cellContent3 =	{
		    		{"","a) Whether Risk Management Policy hasbeen drawn in accordance with the guidelines approved by the PFRDA and has been approved by the Board of Directors?",
		    			threea,threea_rem_pfm,threea_remark},
		    		{" ","b) Whether Risk Management Policy is being reviewed periodically (minimum half yearly basis) by the PFM?",
		    				threeb,threeb_rem_pfm,threeb_remark},
		    		{"","c) Whether the PFM has submitted details of the Risk Management Policy reviewed by its board to the NPS Trust?",
		    					threec,threec_rem_pfm,threec_remark}
		    };
		    for (int row = 0; row < 3; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table3.addCell(new Cell().add(new Paragraph(cellContent3[row][col])));
		            
		        }
		        }
		    table3.complete();
		    
		    Table table4 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table4);
			Cell cell4 = new Cell(1, colsize)
		            .add(new Paragraph("Please mention (individually), whether Risk Management policy covers the following:"));
		    table4.addCell(cell4);
		    String[][] cellContent4 =	{
		    		{"1.","Risk Management functions",
		    			threeci,threeci_rem_pfm,threeci_remark},
		    		{"2.","Disaster recovery and business continuity plans",
		    				threecii,threecii_rem_pfm,threecii_remark},
		    		{"3.","Insurance cover against risks",
		    					threeciii,threeciii_rem_pfm,threeciii_remark},
		    		{"4."," Ensuring risk adjusted returns to subscribers consistent with the protection, safety and liquidity of such funds.",
		    						threeciv,threeciv_rem_pfm,threeciv_remark}
		    };
		    
		    for (int row = 0; row < 4; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table4.addCell(new Cell().add(new Paragraph(cellContent4[row][col])));
		            
		        }
		        }
		    table4.complete();
		    
		    Table table5 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table5);
			Cell cell5 = new Cell(1, colsize)
		            .add(new Paragraph("4. Reporting of Investment Deviations")).setBold();
		    table5.addCell(cell5);
		    String[][] cellContent5 =	{
		    		{"","a) Whether the PFM has ensured that all investments are made as per the investment guidelines?",
		    			foura,foura_rem_pfm,foura_remark},
		    		{"","b) In case of a deviation (downgrade to a rating not permitted under the regulations for corporate bonds or any other non-compliance in any scheme/asset class post investment), whether the PFM has recorded an internal note justifying its decision to hold such securities where deviation has occurred.",
		    				fourb,fourb_rem_pfm,fourb_remark},
		    		{"","c) Whether all such investment deviations are being reported to the Investment Committee and Board of the PFM for their approval to continue to remain invested in these securities.",
		    					fourc,fourc_rem_pfm,fourc_remark}
		    };
		    
		    for (int row = 0; row < 3; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table5.addCell(new Cell().add(new Paragraph(cellContent5[row][col])));
		            
		        }
		        }
		    Cell cell = new Cell(5, colsize)
		            .add(new Paragraph("(Please provide details of the deviations that occurred in the quarter in Annexure 2 along with details of justification note and Investment Committee & Board approval extracts)"));
		    table5.addCell(cell);
		   table5.complete();
		    
		    
		    Table table6 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table6);
			Cell cell6 = new Cell(1, colsize)
		            .add(new Paragraph("5. Code of Conduct")).setBold();
		    table6.addCell(cell6);
		    String[][] cellContent6 =	{
		    		{"(i)","Whether the PFM is engaged in any other business activity except those relating to pension schemes or funds, regulated by the PFRDA.",
		    			fivei,fivei_rem_pfm,fivei_remark},
		    		{"(ii)","Whether the PFM has ensured that adequate disclosures are made to the PFRDA, the NPS Trust or subscriber in a comprehensible and timely manner.",
		    				fiveii,fiveii_rem_pfm,fiveii_remark},
		    		{"(iii)","Whether the PFM has ensured that there has not been any misrepresentation or submission of any misleading information to the PFRDA, NPS Trust or subscribers.",
		    					fiveiii,fiveiii_rem_pfm,fiveiii_remark},
		    		{"(iv)","Whether the PFM has divulged to anybody, either orally or in writing, directly or indirectly any confidential information about the PFRDA, the NPS Trust or subscribers, which has come to its knowledge, without taking prior permission of the PFRDA,the NPS Trust except where such disclosures are required to be made in compliance with any law for the time being in force.",
		    						fiveiv,fiveiv_rem_pfm,fiveiv_remark},
		    		{"(v)","a) Whether the PFM has made adequate disclosures of potential areas of conflict of duties or interest to the PFRDA, the NPS Trust or subscribers.",
		    							fiveva,fiveva_rem_pfm,fiveva_remark},
					 {"","b) Whether the PFM has established a mechanism to resolve any conflict of interest situation in an equitable manner, which may arise in the conduct of business.",
		    								fivevb,fivevb_rem_pfm,fivevb_remark},
					  {"","c) Whether the PFM is satisfied that there has been no instances of self-dealing or front running or insider trading by any of the directors and Key personnel through their accounts or through their family members, relatives or friends.",
		    									fivevc,fivevc_rem_pfm,fivevc_remark}
					
		    };
		    
		    for (int row = 0; row < 7; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table6.addCell(new Cell().add(new Paragraph(cellContent6[row][col])));
		            
		        }
		        }
	    Cell newcell = new Cell(9, colsize)
		            .add(new Paragraph("Kindly provide list of directors and key personnel with status of submissions of self-declarations in Annexure 3."));
		    table6.addCell(newcell);
		  table6.complete();
		    
		  Table table7 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table7);
			
		    String[][] cellContent7 =	{
		    		{"(vi)","a) Whether PFM has promptly informed the PFRDA and the NPS Trust, if there is any change in the registration status or any penal action taken by any Authority or any material change in financials which may adversely affect the interest of the subscribers.",
		    			fivevia,fivevia_rem_pfm,fivevia_remark},
		    		{"","b) Whether the PFM has promptly informed the PFRDA and the NPS Trust about any action, legal proceedings initiated against it in respect of any material breach or non-compliance by it of any law, rules, regulations and directions of the PFRDA or any other regulatory body.",
		    				fivevib,fivevib_rem_pfm,fivevib_remark}						
		    };
		    
		    for (int row = 0; row < 2; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table7.addCell(new Cell().add(new Paragraph(cellContent7[row][col])));
		            
		        }
		        }
		    table7.complete();
		    
		    Table table8 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table8);
			Cell cell8 = new Cell(1, colsize)
		            .add(new Paragraph("6. Internal Auditors")).setBold();
		    table8.addCell(cell8);
		    String[][] cellContent8 =	{
		    		{"(i)","Whether appointment of the Internal Auditor and scope of internal audit is as per the Regulations/Guidance note issued by the PFRDA?",
		    			sixi,sixi_rem_pfm,sixi_remark},
		    		{"(ii)","a) Whether the PFM has produced to the Auditors such books, accounts, records and other documents in its custody or control and furnish such statement and information relating to its activities entrusted to its by the PFRDA, as it or he may require, within such reasonable time may be specified.",
		    				sixiia,sixiia_rem_pfm,sixiia_remark},
		    		{"","b) Whether the PFM has allowed Auditor's reasonable access to the premises occupied by it and also extend reasonable facility for examining any books, records, documents and computer data in the possession of the PFM.",
		    					sixiib,sixiib_rem_pfm,sixiib_remark},
		    		{"","c)Whether audit observations till previous quarter have been closed and suggestions of PFRDA/NPS Trust thereto have been complied with?",
		    						sixiic,sixiic_rem_pfm,sixiic_remark}			    		
		    };
		    
		    for (int row = 0; row < 4; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table8.addCell(new Cell().add(new Paragraph(cellContent8[row][col])));
		            
		        }
		        }
		    table8.complete();
		    
		    Table table9 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table9);
			Cell cell9 = new Cell(1, colsize)
		            .add(new Paragraph("7. Related Party engagement / transaction")).setBold();
		    table9.addCell(cell9);
		    String[][] cellContent9 =	{
		    		{"(i)","a) Whether any transactions or engagement have been carried out by the PFM with a related party except investments of National Pension SystemTrust funds?",
		    			sevenia,sevenia_rem_pfm,sevenia_remark},
		    		{"","b) Whether prior permission of the NPS Trust was taken before entering into such engagement/transaction?",
		    				sevenib,sevenib_rem_pfm,sevenib_remark},
		    		{"","c) Whether such engagement/transactions have been disclosed to the NPS Trust in its periodic reports.",
		    					sevenic,sevenic_rem_pfm,sevenic_remark},
		    		{"","d) Whether such related party engagements / transactions aredone at fair market price?",
		    						sevenid,sevenid_rem_pfm,sevenid_remark},
		    		{"","e) Whether such transaction is recurring in nature?",
		    							sevenie,sevenie_rem_pfm,sevenie_remark}	
		    };
		    
		    for (int row = 0; row < 5; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table9.addCell(new Cell().add(new Paragraph(cellContent9[row][col])));
		            
		        }
		        }
		    table9.complete();
		    
		    Table table10 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table10);
			Cell cell10 = new Cell(1, colsize)
		            .add(new Paragraph("8. Operations / Data Security / Infrastructure")).setBold();
		    table10.addCell(cell10);
		    String[][] cellContent10 =	{
		    		{"(i)","a)Whether the PFM has complied with circular no. PFRDA/2017/30/PF/4 dated09.10.2017 onguidelines on outsourcing of activities by the Pension Fund?",
		    			eightia,eightia_rem_pfm,eightia_remark},
		    		{"","b) Whether the PFM has complied with the reporting requirements of the circular no. PFRDA/2017/30/PF/4 dated 09.10.2017.",
		    				eightib,eightib_rem_pfm,eightib_remark},
		    		{"(ii)","Whether all investments are held in the name of NPS Trust?",
		    					eightii,eightii_rem_pfm,eightii_remark},
		    		{"(iii)","Whether PFM has pledged or hypothecated or lent any scheme assets which would amount to leverage on schemes portfolio?",
		    						eightiii,eightiii_rem_pfm,eightiii_remark},
		    		{"(iv)","Whether all charges/fees debited to the schemes aredeterminedas stipulated by the PFRDA?",
		    							eightiv,eightiv_rem_pfm,eightiv_remark},
		    		{"(v)","Whether all interest,dividendsor any such accrual income and proceeds of redemption/sale were collected on due dates and promptly credited to the scheme accounts?",
		    								eightv,eightv_rem_pfm,eightv_remark},
		    		{"(vi)","a) Whether the PFM has taken adequate and necessary steps to ensure that the data and records pertaining to its activities are maintained and are intact.",
		    									eightvia,eightvia_rem_pfm,eightvia_remark},
		    		{"","b) Whether the PFM has ensured that for electronic records and data, up-to-date backup is always available with it.",
		    										eightvib,eightvib_rem_pfm,eightvib_remark},
		    		{"(vii)","a) Whether the PFM has maintained adequate infrastructure facilities to be able to discharge its services to the satisfaction of the PFRDA, the NPS Trust.",
		    											eightviia,eightviia_rem_pfm,eightviia_remark},
		    		{"","b) Whether the operating procedures and systems of the PFM are well documented and backed by operation manuals.",
		    												eightviib,eightviib_rem_pfm,eightviib_remark},
		    		{"(viii)","Whether the PFM has informed the entities in which investment of NPS funds have been made that interest received on the said investment is not liable for deduction of tax at source under the Income Tax Act, 1961",
		    													eightviii,eightviii_rem_pfm,eightviii_remark},
		    		{"(ix)","In case any Income tax has been deducted on the investment of NPS funds made by PFM, whether PFM has collected refund of such tax deducted within the same financial year.\r\n"+"In case any Income Tax has been deducted on the investment of NPS funds and PFM has not obtained the refund of such tax within the same financial year at its own cost and expense, the PFM fund shall reimburse the NPS Trust, of the said amounts, being deducted as tax, upon being notified by the NPS Trust.",
			    													eightix,eightix_rem_pfm,eightix_remark},
		    		{"(x)","Whether proper amount of tax has been deducted and deposited on payment of investment management fees and the custodian fees by the PFM on behalf of NPS Trust and within the prescribed timelines.",
				    													eightx,eightx_rem_pfm,eightx_remark}
		    };
		    
		    for (int row = 0; row < 13; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table10.addCell(new Cell().add(new Paragraph(cellContent10[row][col])));
		            
		        }
		        }
		    table10.complete();
		    
		    
		    Table table11 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table11);
			Cell cell11 = new Cell(1, colsize)
		            .add(new Paragraph("9. Brokers empanelment:")).setBold();
		    table11.addCell(cell11);
		    String[][] cellContent11 =	{
		    		{"","* Whether Brokers empanelment is done in accordance to the guidelines issued by the PFRDA?",
		    			ninea,ninea_rem_pfm,ninea_remark},
		    		{"","* Whether prescribed limit of percentage of transactions through any single broker is complied with?",
		    				nineb,nineb_rem_pfm,nineb_remark}
		    		    		
		    };
		    
		    for (int row = 0; row < 2; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table11.addCell(new Cell().add(new Paragraph(cellContent11[row][col])));
		            
		        }
		        }

			Cell cell12 = new Cell(4, colsize)
		            .add(new Paragraph("10. Inter-Scheme Investment Parameter")).setBold();
		    table11.addCell(cell12);
		    String[][] cellContent12 =	{
		    		{"","Whether all such Inter-Scheme transfers are in conformity with the investment objective of the scheme to which such transfer has been made?",
		    			ten,ten_rem_pfm,ten_remark}		    		    		
		    };
		    for (int row = 0; row < 1; row++) {
		      for (int col = 0; col < colsize; col++) {
		            table11.addCell(new Cell().add(new Paragraph(cellContent12[row][col])));
		            
		        }
	}
		    table11.complete();
		    
		    Table table12 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    doc.add(table12);
			Cell cell13 = new Cell(1, colsize)
		            .add(new Paragraph("11. Voting Obligation")).setBold();
		    table12.addCell(cell13);
		    String[][] cellContent13 =	{
		    		{"","a) Whether the PFM has complied with its obligation to exercise its voting rights on behalf of the NPS Trust?",
		    			elevena,elevena_rem_pfm,elevena_remark},
		    		{"","b)Whether, quarterly voting report has been submitted to the NPS Trust in compliance to Circular PFRDA/2017/17/PF/1 dated 20.04.2017?",
		    				elevenb,elevenb_rem_pfm,elevenb_remark}
		    		    		
		    };
		    
		    for (int row = 0; row < 2; row++) {
		        for (int col = 0; col < colsize; col++) {
		            table12.addCell(new Cell().add(new Paragraph(cellContent13[row][col])));
		            
		        }
		        }

			Cell cell14 = new Cell(4, colsize)
		            .add(new Paragraph("12. Reports and Disclosures as per Schedule V of PFRDA (PF) Regulations")).setBold();
		    table12.addCell(cell14);
		    String[][] cellContent14 =	{
		    		{"","Whether quarterly periodic reports as per schedule V are submitted to NPS Trust within 10 days from the end of the quarter.",
		    			twelve,twelve_rem_pfm,twelve_remark}		    		    		
		    };
		    for (int row = 0; row < 1; row++) {
		      for (int col = 0; col < colsize; col++) {
		            table12.addCell(new Cell().add(new Paragraph(cellContent14[row][col])));
		            
		        }
	}
		    table12.complete();
		    
//		  List<PFM_executive_summary>executive_summaries=  PFM_executive_summaryLocalServiceUtil.getExecutiveSummaryByReportUploadLogId(reportuploadlogid);
//		    ///////////////
//		    Table table13 = new Table(new float[] {0.5f, 2.5f, 0.5f,1.5f,1.5f,1.5f}, true)
//	                .setWidth(UnitValue.createPercentValue(100));
//		    doc.add(table13);
//			Cell cell131 = new Cell(1, 6)
//		            .add(new Paragraph("Executive Summary")).setBold();
//			table13.addCell(cell131);
//		    String[][] cellContent131 =	new String[executive_summaries.size()][6];
//		    
//		    for(int row=0;row<executive_summaries.size();row++) {
//		    	PFM_executive_summary executive_summary=executive_summaries.get(row);
//		    	cellContent131[row][0]=row+1+"";
//		    	cellContent131[row][1]=executive_summary.getBoardDescription();
//		    	cellContent131[row][2]=executive_summary.getAuditorobservation();
//		    	cellContent131[row][3]=executive_summary.getRiskrating();
//		    	cellContent131[row][4]=executive_summary.getManagementResponse();
//		    	cellContent131[row][5]=executive_summary.getNpstComment();
//		    }
//		    
//		    for (int row = 0; row < executive_summaries.size(); row++) {
//		        for (int col = 0; col < 6; col++) {
//		        	table13.addCell(new Cell().add(new Paragraph(cellContent131[row][col])));
//		            
//		        }
//		        }
//		    table13.complete();
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    Paragraph para4 = new Paragraph(new Text("\n Note: \n \n").setBold());
		    Text text9 = new Text("1) Wherever there is non-compliance due to any reason, the remark/reason thereof has been separately appended there to.\r\n\n" + 
		    	                  //"2.This Compliance Certificate shall be put up to the Board at its meeting which to be held on "+date_3+"\r\n\n"+
		    		              "2.This Compliance Certificate(s)n shall be put up to the Board at its ensuing Board Meeting and the remarks related thereto, if any, would be forwarded to NPS Trust subsequently.\r\n\n"+
		    	                  "and the Remarks if any related thereto would be forwarded to NPS Trust subsequently.\r\n\n"+
		    	                  "Certified that the Information given, herein is correct and complete to the best of my/our knowledge and belief.");
		    	
		    		para4.add(text9);
		    		Text text10 = new Text("\n \n For \n "+companies);
		 		    para4.add(text10);
		            doc.add(para4);
		    
		    
		    
		    Table table13 = new Table(new float[] {5f, 5f}, true)
	                .setWidth(UnitValue.createPercentValue(100));
		    String[] headers13 = {"Date:","Name"};
		    for (int col = 0; col < 2; col++) {
	            table13.addCell(new Cell().add(new Paragraph(headers13[col])).setBold().setBorder(null));
	            
	        }
		    doc.add(table13);
		    
		    String[] values1 = {date_3,ceoname};
		    for (int col = 0; col < 2; col++) {
	            table13.addCell(new Cell().add(new Paragraph(values1[col])).setBorder(null));
	            
	        }
		    String[] headers14 = {"Place:","Role"};
		    for (int col = 0; col < 2; col++) {
	            table13.addCell(new Cell().add(new Paragraph(headers14[col])).setBold().setBorder(null));
	            
	        }
		    
		    
		    String[] values2 = {place,role};
		    for (int col = 0; col < 2; col++) {
	            table13.addCell(new Cell().add(new Paragraph(values2[col])).setBorder(null));
	            
	        }
		 
		    table13.complete();
		    
		    
		    
		    
		    
		    
		    

	/*	    Text text11 = new Text("\n Date:                                                 Name").setBold();
		    para3.add(text11);
		    Text text12 = new Text("\n "+date_3+"                                                 "+ceoname+"\n");
		    para3.add(text12);
		    Text text13 = new Text("\n Place:                                                 Role").setBold();
		    para3.add(text13);
		    Text text14 = new Text("\n "+place+"                                                 "+role+"\n");
		    para3.add(text14);
		    doc.add(para3);
		*/    
		        doc.close();
		        pdfDoc.close();
		        writer.close();
		        log.info("file created successfully");
	}
	
		
		
	
		catch (Exception e)  
		{  
		log.error("error while create PDF "+ e.getMessage()); 
		}
		return file;  
			
		
	}

private static final Log log=LogFactoryUtil.getLog(QuarterlyCompCertificateCreatePdfUtil.class.getName()); 


}
