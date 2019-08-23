package dto;

import entities.Employee;

/**
 *
 * @author runin
 */
public class EmployeeDTO {

    private Long id;
    private String name, address;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
    }

}
