package com.galen.ordertracker.business;

import jakarta.persistence.*;

@Entity
@Table(name = "businesses")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessType businessTypeId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 10)
    private String phone;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "lon")
    private Double longitude;

    @Column(nullable = false)
    private boolean isOpen;

    protected Business() {}

    public Business(
            String id, BusinessType businessTypeId, String name,
            String phone, String street, String city,
            boolean isOpen
    ) {
        this.id = id;
        this.businessTypeId = businessTypeId;
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.isOpen = isOpen;
    }

    public String getId() { return id; }
    public BusinessType getBusinessTypeId() { return businessTypeId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPhone() { return phone; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }
    public boolean isOpen() { return isOpen; }

    public void setBusinessTypeId(BusinessType businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}