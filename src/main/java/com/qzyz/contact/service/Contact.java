package com.qzyz.contact.service;

import com.google.cloud.storage.Storage;
import com.qzyz.contact.bean.MessageBean;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Contact {

	String update(String name, String phone, String city) throws IOException, InvalidFormatException;

	List<MessageBean> getMessages() throws IOException;

	void downloadToLocal() throws IOException;

	void uploadToCloud() throws FileNotFoundException;

}
