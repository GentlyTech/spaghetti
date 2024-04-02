package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.Autofill;
import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import ca.uottawa.csi2132.group196.spaghetti.Utils.AutoFillableClass;

import java.time.LocalDateTime;
import java.util.List;

public class Employee extends AutoFillableClass<Employee> {
    @MappedField("employee_id")
    @Autofill
    private int employeeId = -1;

    @MappedField("creation_date")
    @Autofill
    private String creationDate = null;

    @MappedField
    @Autofill
    private String name = null;

    @MappedField
    private Address address = null;

    @MappedField("sin")
    @Autofill
    private String sinNumber = null;

    private List<Role> roles = null;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getCreationDate() {
        return LocalDateTime.parse(this.creationDate);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinNumber() {
        return sinNumber;
    }

    public void setSinNumber(String sinNumber) {
        this.sinNumber = sinNumber;
    }
}
