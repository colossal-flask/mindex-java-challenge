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

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure create(String id) {
        LOG.debug("Creating ReportingStructure for employee id [{}]", id);

        Employee employee = employeeService.read(id);
        int totalReports = countTotalReports(employee);

        return new ReportingStructure(employee, totalReports);
    }

    /**
     * Recursively counts all reports, both direct and distant, under a given employee.
     * @param employee the "root" employee
     * @return total number of reports under the employee
     */
    private int countTotalReports(Employee employee) {
        int count = 0;
        if (employee.getDirectReports() == null) return 0;
        for (Employee i : employee.getDirectReports()) {
            count = count + recursionHelper(i);
        }
        return count;
    }

    /**
     * Helper method for the report counter.
     */
    private int recursionHelper(Employee employee) {
        if (employeeService.read(employee.getEmployeeId()).getDirectReports() == null) {
            return 1;
        } else {
            return 1 + countTotalReports(employeeService.read(employee.getEmployeeId()));
        }
    }
}
