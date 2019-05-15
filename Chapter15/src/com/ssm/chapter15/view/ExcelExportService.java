package com.ssm.chapter15.view;

import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;
public interface ExcelExportService {
	
	/***
	 *  生成exel文件规则
	 * @param model 数据模型
	 * @param workbook excel workbook
	 */
	public void makeWorkBook(Map<String, Object> model, Workbook workbook);

}