package com.contracts.logai.v1;

import com.contracts.common.BaseEvent;

public class LogEvent extends BaseEvent {

    private String employeeId;
    private String level;
    private String message;

    public LogEvent() {}

    public LogEvent(String correlationId,
                    String employeeId,
                    String level,
                    String message) {

        super(correlationId,
                "LOG_EVENT",
                "PAYROLL_SERVICE",
                "v1");

        this.employeeId = employeeId;
        this.level = level;
        this.message = message;
    }

    public String getEmployeeId() { return employeeId; }
    public String getLevel() { return level; }
    public String getMessage() { return message; }
}