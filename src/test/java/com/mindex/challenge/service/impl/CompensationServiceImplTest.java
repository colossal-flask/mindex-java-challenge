package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compCreationUrl;
    private String compGetUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compCreationUrl = "http://localhost:" + port + "/compensation/{salary}/{date}";
        compGetUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCompensationCreationAndRetrieval() {
        // create a new compensation instance and persist it in the database
        Compensation testComp = restTemplate.postForEntity(compCreationUrl, "16a596ae-edd3-4847-99fe-c4518e82c86f", Compensation.class,
                80000, "01-01-2021").getBody();

        // query the just-created compensation by employee ID
        Compensation retrievedComp = restTemplate.getForEntity(compGetUrl, Compensation.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();

        // validation
        assertEmployeeEquivalence(testComp.getEmployee(), retrievedComp.getEmployee());
        assertEquals(testComp.getSalary(), retrievedComp.getSalary());
        assertEquals(testComp.getEffectiveDate(), retrievedComp.getEffectiveDate());
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
