package com.assignment.ai_safety;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;

@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Severity cannot be null")
    @Pattern(regexp = "(?i)LOW|MEDIUM|HIGH", message = "Severity must be LOW, MEDIUM, or HIGH")
    private String severity;

    private Date reported_at;

    public Incident() {
    }

    public Incident(int id, String title, String description, String severity, Date reported_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.reported_at = reported_at;
    }

    public Incident(String title, String description, String severity, Date reported_at) {
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.reported_at = reported_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Date getReported_at() {
        return reported_at;
    }

    public void setReported_at(Date reported_at) {
        this.reported_at = reported_at;
    }

    @PrePersist
    public void setReportedAt() {
        this.reported_at = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", severity='" + severity + '\'' +
                ", reported_at=" + reported_at +
                '}';
    }
}
