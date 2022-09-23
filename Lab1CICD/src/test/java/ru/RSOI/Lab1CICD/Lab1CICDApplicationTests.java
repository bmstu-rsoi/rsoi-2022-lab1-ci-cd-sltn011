package ru.RSOI.Lab1CICD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.RSOI.Lab1CICD.Controller.CPerson;
import ru.RSOI.Lab1CICD.Error.EBadRequestError;
import ru.RSOI.Lab1CICD.Error.ENotFoundError;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
class Lab1CICDApplicationTests {

	@Autowired
	private CPerson personController;

	@Test
	void testGetNonExisting()
	{
		assertThrows(ENotFoundError.class, () -> personController.getOnePerson(-1));
	}

	@Test
	void testPost()
	{
		Map<String, String> values = new HashMap<String, String>();
		values.put("name", "TestPerson");
		values.put("age", "100");
		ResponseEntity<Object> response = personController.addPerson(values);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testPatchNonExisting()
	{
		Map<String, String> values = new HashMap<String, String>();
		values.put("name", "TestPerson");
		values.put("age", "100");
		assertThrows(ENotFoundError.class, () -> personController.patchPerson(-1, values));
	}

	@Test
	void testPatchInvalidData()
	{
		Map<String, String> values = new HashMap<String, String>();
		values.put("age", "NotAnInteger");
		assertThrows(EBadRequestError.class, () -> personController.patchPerson(1, values));
	}

}
