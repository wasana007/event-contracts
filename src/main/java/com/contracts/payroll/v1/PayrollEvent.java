package com.contracts.payroll.v1;

import com.contracts.common.BaseEvent;

import java.math.BigDecimal;

public class PayrollEvent extends BaseEvent {

    private String employeeId;
    private BigDecimal salary;
    private String month;
    private String status;

    public PayrollEvent() {}

    public PayrollEvent(String correlationId,
                        String employeeId,
                        BigDecimal salary,
                        String month,
                        String status) {

        super(
                correlationId,
                "PAYROLL_CREATED",
                "PAYROLL_SERVICE",
                "v1"
        );

        this.employeeId = employeeId;
        this.salary = salary;
        this.month = month;
        this.status = status;
    }

    public String getEmployeeId() { return employeeId; }
    public BigDecimal getSalary() { return salary; }
    public String getMonth() { return month; }
    public String getStatus() { return status; }

    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public void setMonth(String month) { this.month = month; }
    public void setStatus(String status) { this.status = status; }
}