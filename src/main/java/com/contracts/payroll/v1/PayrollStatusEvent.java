package com.contracts.payroll.v1;

import com.contracts.common.BaseEvent;

import java.math.BigDecimal;

public class PayrollStatusEvent extends BaseEvent {

    private String status;
    private BigDecimal tax;

    public PayrollStatusEvent() {}

    public PayrollStatusEvent(String correlationId,
                              String status,
                              BigDecimal tax) {

        super(correlationId,
                "PAYROLL_STATUS",
                "PAYROLL_SERVICE",
                "v1");

        this.status = status;
        this.tax = tax;
    }

    public String getStatus() { return status; }
    public BigDecimal getTax() { return tax; }
}