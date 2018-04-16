package com.qzyz.contact.service;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.*;
import com.qzyz.contact.bean.MessageBean;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactImpl implements Contact {

	private String inputFilePathName = "/media/contact.xlsx";
	private String outputFilePathName = "/media/contact.xlsx";
//	private String compiledOutputFilePathName = "C:/Users/base/Desktop/MyWorkspace/qzyz-contact/out/production/resources/static/高三一班通讯录.xlsx";

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
//		FileOutputStream compiledFileOutputStream = new FileOutputStream(compiledOutputFilePathName);
//		workbook.write(compiledFileOutputStream);
//		compiledFileOutputStream.close();

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

	@Override
	public void downloadToLocal() throws IOException {
		Storage storage = StorageOptions.getDefaultInstance().getService();
		String bucketName = "qzyz-contact.appspot.com";
		String objectName = "高三一班通讯录.xlsx";
		Path downloadTo = Paths.get("/media/contact.xlsx");
		BlobId blobId = BlobId.of(bucketName, objectName);
		Blob blob = storage.get(blobId);
		if (blob == null) {
			System.out.println("No such object");
			return;
		}
		PrintStream writeTo = System.out;
		if (downloadTo != null) {
			writeTo = new PrintStream(new FileOutputStream(downloadTo.toFile()));
		}
		if (blob.getSize() < 1_000_000) {
			// Blob is small read all its content in one request
			byte[] content = blob.getContent();
			writeTo.write(content);
		} else {
			// When Blob size is big or unknown use the blob's channel reader.
			try (ReadChannel reader = blob.reader()) {
				WritableByteChannel channel = Channels.newChannel(writeTo);
				ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
				while (reader.read(bytes) > 0) {
					bytes.flip();
					channel.write(bytes);
					bytes.clear();
				}
			}
		}
		if (downloadTo == null) {
			writeTo.println();
		} else {
			writeTo.close();
		}
	}

	@Override
	public void uploadToCloud() throws FileNotFoundException {
		Storage storage = StorageOptions.getDefaultInstance().getService();
		String bucketName = "qzyz-contact.appspot.com";
		String objectName = "高三一班通讯录.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		Bucket bucket = storage.get(bucketName);

		InputStream content = new FileInputStream("/media/contact.xlsx");
		Blob blob = bucket.create(objectName, content, contentType);
		blob.updateAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
	}
}
