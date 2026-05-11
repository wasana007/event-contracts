package com.contracts.logai.v1;

import com.contracts.common.BaseEvent;

public class LogAnalysisEvent extends BaseEvent {

    private String rootCause;
    private String suggestion;

    public LogAnalysisEvent() {}

    public LogAnalysisEvent(String correlationId,
                            String rootCause,
                            String suggestion) {

        super(correlationId,
                "LOG_ANALYSIS",
                "LOGAI_SERVICE",
                "v1");

        this.rootCause = rootCause;
        this.suggestion = suggestion;
    }

    public String getRootCause() { return rootCause; }
    public String getSuggestion() { return suggestion; }
}