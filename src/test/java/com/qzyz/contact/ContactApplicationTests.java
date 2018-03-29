package com.qzyz.contact;

import com.qzyz.contact.service.ContactImpl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactApplicationTests {

	@Autowired
	ContactImpl contactImpl;

	@Test
	public void contextLoads() throws IOException {
		contactImpl.update("陈伟城", "17091955633", "东京");
	}

}
