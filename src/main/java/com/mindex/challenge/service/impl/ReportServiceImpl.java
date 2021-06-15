package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);
    private int count = 0;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure create(String id) {
        LOG.debug("Creating ReportingStructure for employee id [{}]", id);

        count = 0;
        Employee employee = employeeService.read(id);
        countTotalReports(employee);

        return new ReportingStructure(employee, count);
    }

    /**
     * Recursively traverses the employee's list of reports and counts the total.
     * @param employee the "root" employee
     */
    private void countTotalReports(Employee employee) {
        // FIXME: not the most optimal approach -- refactor function to directly return total count.
        if (employee.getDirectReports() != null) {
            for (Employee i : employee.getDirectReports()) {
                countTotalReports(employeeService.read(i.getEmployeeId()));
                count++;
            }
        }
    }
}
