package com.contracts.common;

import java.time.Instant;

public abstract class BaseEvent {

    private String correlationId;
    private String eventType;
    private String source;
    private String version;
    private Instant timestamp;

    protected BaseEvent() {}

    protected BaseEvent(String correlationId,
                        String eventType,
                        String source,
                        String version) {

        this.correlationId = correlationId;
        this.eventType = eventType;
        this.source = source;
        this.version = version;
        this.timestamp = Instant.now();
    }

    public String getCorrelationId() { return correlationId; }
    public String getEventType() { return eventType; }
    public String getSource() { return source; }
    public String getVersion() { return version; }
    public Instant getTimestamp() { return timestamp; }
}