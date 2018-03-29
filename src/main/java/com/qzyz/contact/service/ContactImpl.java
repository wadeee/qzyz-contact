package com.qzyz.contact.service;

import com.qzyz.contact.bean.MessageBean;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactImpl implements Contact {

	private String inputFilePathName = "C:\\Users\\Wadec\\Desktop\\Workspace\\contact\\src\\main\\resources\\static\\高三一班通讯录.xlsx";
	private String outputFilePathName = "C:\\Users\\Wadec\\Desktop\\Workspace\\contact\\src\\main\\resources\\static\\高三一班通讯录.xlsx";
	private String compiledOutputFilePathName = "C:\\Users\\Wadec\\Desktop\\Workspace\\contact\\out\\production\\resources\\static\\高三一班通讯录.xlsx";

	@Override
	public String update(String name, String phone, String city) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(inputFilePathName);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		fileInputStream.close();

		XSSFSheet informationSheet = workbook.getSheet("Information");
		Map<String, Integer> nameToRowMap = nameToRow(informationSheet);
		informationSheet.getRow(nameToRowMap.get(name)).getCell(1).setCellValue(phone);
		informationSheet.getRow(nameToRowMap.get(name)).getCell(2).setCellValue(city);

		FileOutputStream fileOutputStream = new FileOutputStream(outputFilePathName);
		workbook.write(fileOutputStream);
		fileOutputStream.close();
		FileOutputStream compiledFileOutputStream = new FileOutputStream(compiledOutputFilePathName);
		workbook.write(compiledFileOutputStream);
		compiledFileOutputStream.close();

		return "success";
	}

	@Override
	public List<MessageBean> getMessages() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(inputFilePathName);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		fileInputStream.close();

		XSSFSheet informationSheet = workbook.getSheet("Information");
		List<MessageBean> messageBeans = new ArrayList<>();
		for (int i = 1; i < informationSheet.getPhysicalNumberOfRows(); i++) {
			MessageBean messageBean = new MessageBean();
			messageBean.setName(informationSheet.getRow(i).getCell(0).toString());
			messageBean.setPhone(informationSheet.getRow(i).getCell(1).toString());
			messageBean.setCity(informationSheet.getRow(i).getCell(2).toString());
			messageBeans.add(messageBean);
		}

		return messageBeans;
	}

	private Map<String, Integer> nameToRow(XSSFSheet informationSheet) {
		Map<String, Integer> nameToRowMap = new HashMap<>();
		for (int i = 1; i < informationSheet.getPhysicalNumberOfRows(); i++) {
			nameToRowMap.put(informationSheet.getRow(i).getCell(0).toString(), i);
		}
		return nameToRowMap;
	}

}
