package com.contact.geo.contactgeo.dto.contacts;

public enum Status {
    PENDING(false, "Pendente"),
    OK(true, "Processado");

    private Boolean status;
    private String description;

    Status(Boolean status, String description) {
        this.status = status;
        this.description = description;
    }

    public boolean getValue() {
        return this.status;
    }
}
