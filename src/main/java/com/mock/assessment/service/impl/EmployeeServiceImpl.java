package com.mock.assessment.service.impl;

import com.mock.assessment.dao.EmployeeDAO;
import com.mock.assessment.mapper.EmployeeMapper;
import com.mock.assessment.model.dto.EmployeeDto;
import com.mock.assessment.model.entity.Employee;
import com.mock.assessment.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    EmployeeMapper employeeMapper;

    private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);


    /**
     * fetches all the employees
     *
     * @return employees
     */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeDAO.fetchAllEmployees();
        List<EmployeeDto> consolidatedEmployees = employees.stream().map(employeeMapper::mapToEmployeeDTO).collect(Collectors.toList());
        LOGGER.info("the number of employees fetched are of size:{} ", consolidatedEmployees.size());
        return consolidatedEmployees;
    }

    /**
     * save an employeea
     *
     * @param employee employee
     */
    @Override
    @Transactional
    public void saveEmployee(EmployeeDto employee) {
        LOGGER.info("persisting employee with name: {}", employee.getName());
        employeeDAO.saveEmployee(employeeMapper.mapToEmployeeEntity(employee));
    }

    /**
     * fetches employee by id
     *
     * @param id employee id
     * @return employee
     */
    @Override
    public EmployeeDto getEmployeeById(int id) {
        return null;
    }
}
