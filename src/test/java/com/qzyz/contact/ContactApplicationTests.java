package com.qzyz.contact;

import com.qzyz.contact.service.ContactImpl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactApplicationTests {

	@Autowired
	ContactImpl contactImpl;

	@Test
	public void contextLoads() throws IOException {
		contactImpl.update("陈伟城", "17091955633", "东京");
		List<String> strList = new ArrayList<>();
		strList.add("sss");

		strList.forEach( (String e) -> System.out.println(e));

	}

	@Test
	public void test() {


	}


}
