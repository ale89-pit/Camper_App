package com.camper_app_server.testuser;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.camper_app_server.CamperAppServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.MOCK,
		classes = CamperAppServerApplication.class
		)
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-integration.proprierties")
public class UserIntegrationTest {

}
