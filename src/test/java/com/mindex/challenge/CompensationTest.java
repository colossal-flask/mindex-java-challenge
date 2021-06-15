package com.mindex.challenge;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompensationTest {

    @Autowired
    CompensationService compensationService;

    @Test
    public void testCompensationInsertionAndRetrieval() {
        compensationService.create("16a596ae-edd3-4847-99fe-c4518e82c86f", 70000, "01-01-2022");
        compensationService.create("c0c2293d-16bd-4603-8e08-638a9d18b22c", 66000, "10-01-2021");

        Compensation johnLennon = compensationService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        Compensation georgeHarrison = compensationService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");

        assertEquals("John Lennon", johnLennon.getEmployee().getFirstName() + " " + johnLennon.getEmployee().getLastName());
        assertEquals(70000, johnLennon.getSalary());
        assertEquals("01-01-2022", johnLennon.getEffectiveDate());

        assertEquals("George Harrison", georgeHarrison.getEmployee().getFirstName() + " " + georgeHarrison.getEmployee().getLastName());
        assertEquals(66000, georgeHarrison.getSalary());
        assertEquals("10-01-2021", georgeHarrison.getEffectiveDate());
    }
}
