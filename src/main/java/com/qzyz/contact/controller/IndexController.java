package com.qzyz.contact.controller;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.*;
import com.qzyz.contact.bean.MessageBean;
import com.qzyz.contact.service.Contact;
import com.qzyz.contact.service.ContactImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	ContactImpl contactImpl;

	@RequestMapping("")
	public String index(ModelMap modelMap) throws IOException {
		List<MessageBean> messageBeans = contactImpl.getMessages();
		List<String> nameList = new ArrayList<>();
		for (MessageBean messageBean : messageBeans) {
			nameList.add(messageBean.getName());
		}
		modelMap.put("nameList", nameList);

		return "/index";
	}

	@RequestMapping("update/")
	public String update(ModelMap modelMap, @RequestParam String name) throws IOException {

		List<MessageBean> messageBeans = contactImpl.getMessages();
		Map<String, MessageBean> nameToMessageBean = new HashMap<>();
		for (MessageBean messageBean: messageBeans) {
			nameToMessageBean.put(messageBean.getName(), messageBean);
		}

		modelMap.put("name", name);
		modelMap.put("phone", nameToMessageBean.get(name).getPhone());
		modelMap.put("city", nameToMessageBean.get(name).getCity());

		return "/update";
	}

	@RequestMapping("updateResult/")
	public String update(ModelMap modelMap, @RequestParam(required = false) String name, @RequestParam(required = false) String phone, @RequestParam(required = false) String city) throws IOException {
		if (name != null){
			contactImpl.update(name, phone, city);
			contactImpl.uploadToCloud();
		}
		List<MessageBean> messageBeans = contactImpl.getMessages();
		modelMap.put("messageBeans", messageBeans);

		return "/updateResult";
	}

	@RequestMapping("updateLocalFile")
	public String updateLocalFile() throws IOException {
//		Storage storage = StorageOptions.getDefaultInstance().getService();
//		String bucketName = "qzyz-contact.appspot.com";
//		String objectName = "高三一班通讯录.xlsx";
//		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//		Bucket bucket = storage.get(bucketName);
//
//		InputStream content = new FileInputStream("D:/tmp/高三一班通讯录.xlsx");
//		Blob blob = bucket.create(objectName, content, contentType);
//		blob.updateAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

		contactImpl.downloadToLocal();

		return "redirect:/";
	}

}
