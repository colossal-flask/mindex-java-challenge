package com.mindex.challenge;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingStructureTest {

    @Autowired
    private ReportService reportService;

    @Test
    public void testTotalNumberOfReports() {
        ReportingStructure johnLennon = reportService.create("16a596ae-edd3-4847-99fe-c4518e82c86f");
        ReportingStructure georgeHarrison = reportService.create("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        ReportingStructure ringoStarr = reportService.create("03aa1462-ffa9-4978-901b-7c001562cf6f");

        assertEquals(4, johnLennon.getNumberOfReports());
        assertEquals(0, georgeHarrison.getNumberOfReports());
        assertEquals(2, ringoStarr.getNumberOfReports());
    }
}
