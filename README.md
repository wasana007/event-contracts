# 🚀 Oversikt

`event-contracts` er et delt bibliotek som samler alle felles event-klasser brukt mellom mikrotjenester i systemet. Biblioteket fungerer som en sentral kontrakt (*shared contract library*) mellom produsenter og konsumenter av Kafka-events.

Eksempler på events:

* `PayrollEvent`
* `PayrollStatusEvent`
* `LogEvent`
* `LogAnalysisEvent`
* `EventVersion`

Målet er å sikre at alle tjenester bruker samme struktur, samme felter og samme versjonering av events.

---

# 🧱 Arkitekturbruk

```text
payroll-backend      ─────┐
                          ├── event-contracts (JAR)
logsense-ai-backend  ─────┘
```

Begge tjenester importerer samme JAR-fil for å:

* produsere Kafka-events
* konsumere Kafka-events
* validere event-struktur
* unngå duplisering av DTO-klasser

---

# 📂 Prosjektstruktur

```text
event-contracts/
│
├── src/
│   └── main/
│       └── java/
│           └── com/contracts/
│               │
│               ├── common/
│               │   ├── BaseEvent.java
│               │   └── EventVersion.java
│               │
│               ├── logai/v1/
│               │   ├── LogAnalysisEvent.java
│               │   └── LogEvent.java
│               │
│               └── payroll/v1/
│                   ├── PayrollEvent.java
│                   └── PayrollStatusEvent.java

```

---

# 🧩 Forklaring av pakkestruktur

## 📁 `common/`

Inneholder felles klasser som brukes av alle domener.

### `BaseEvent.java`

Basisklasse for alle events.

Eksempel:

```java
public abstract class BaseEvent {
    private String correlationId;
    private String eventId;
    private Instant timestamp;
    private String version;
}
```

Fordeler:

* standardiserer metadata
* enklere tracing/logging
* enklere observability
* støtte for distributed systems

---

### `EventVersion.java`

Holder sentrale versjonskonstanter.

```java
public class EventVersion {
    public static final String V1 = "v1";
    public static final String V2 = "v2";
}
```

Brukes for:

* schema evolution
* kompatibilitet mellom tjenester
* breaking/non-breaking changes

---

# 📁 `logai/v1/`

Inneholder events relatert til AI-logganalyse.

## `LogEvent.java`

Representerer rå loggdata sendt til analyse.

```java
public class LogEvent {
    private String correlationId;
    private String employeeId;
    private String level;
    private String message;
}
```

Eksempel:

```json id="t5mbna"
{
  "correlationId": "abc-123",
  "employeeId": "EMP001",
  "level": "ERROR",
  "message": "Database connection timeout"
}
```

---

## `LogAnalysisEvent.java`

Resultat fra AI-analyse av logger.

Eksempel:

```java
public class LogAnalysisEvent {
    private String correlationId;
    private String severity;
    private String aiSummary;
    private String recommendation;
}
```

Brukes av:

* AI agents
* observability pipeline
* alerting systems

---

# 📁 `payroll/v1/`

Inneholder payroll-relaterte events.

---

## `PayrollEvent.java`

Brukes når lønn beregnes eller opprettes.

```java
public class PayrollEvent {
    private String correlationId;
    private String employeeId;
    private BigDecimal salary;
    private BigDecimal tax;
    private String status;
}
```

---

## `PayrollStatusEvent.java`

Brukes for statusoppdateringer.

Eksempel:

```java
public class PayrollStatusEvent {
    private String payrollId;
    private String status;
    private Instant updatedAt;
}
```

Typiske statuser:

* CREATED
* PROCESSING
* COMPLETED
* FAILED

---

# ⚙️ Bygg prosjektet

Bygg prosjektet med Maven:

```bash id="7r6fdi"
mvn clean package
```

Output:

```text id="e4s6aj"
target/event-contracts-1.0.0.jar
```

---

# 📥 Bruk i andre tjenester

## Maven-avhengighet

```xml id="s9u7xw"
<dependency>
    <groupId>com.contracts</groupId>
    <artifactId>event-contracts</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## Lokal JAR-bruk

```xml id="8e98ol"
<dependency>
    <groupId>com.contracts</groupId>
    <artifactId>event-contracts</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/event-contracts-1.0.0.jar</systemPath>
</dependency>
```

---

# 🔥 Eksempel på event-flyt

```text
Payroll Backend
   ↓
oppretter PayrollEvent
   ↓
Kafka Topic: payroll-events
   ↓
Logsense AI Backend konsumerer event
   ↓
LogAnalysisEvent genereres
   ↓
AI analyse / observability / alerting
```

---

# 📈 Strategi for versjonering

Prosjektet bruker pakkebasert versjonering:

```text
com.contracts.payroll.v1
com.contracts.logai.v1
```

Fordeler:

* enklere migrering
* støtte for flere versjoner samtidig
* trygg schema evolution
* backward compatibility

Ved breaking changes:

* opprett ny pakke (`v2`)
* behold eksisterende `v1`
* migrer tjenester gradvis

---

# 🛠 Teknologistack

* Java 21
* Maven
* Jackson
* Apache Kafka
* DTO-basert eventmodell
* Kafka-kompatibel serialisering/deserialisering

---

# 📌 Beste praksis

* Hold events immutable når mulig
* Ikke legg inn business logic i DTO-er
* Bruk `BaseEvent` for felles metadata
* Bruk versjonering ved breaking changes
* Hold events små og serialiserbare
* Unngå service-spesifikke avhengigheter
* Sørg for backward compatibility

---

# 🎯 Fordeler med denne strukturen

✅ Tydelig domeneseparasjon
✅ Skalerbar eventarkitektur
✅ Bedre vedlikeholdbarhet
✅ Konsistent eventdesign
✅ Enklere observability og tracing
✅ Redusert coupling mellom tjenester
✅ Klar støtte for fremtidige versjoner (`v2`, `v3`)

---