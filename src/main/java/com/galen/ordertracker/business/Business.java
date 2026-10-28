package com.galen.ordertracker.business;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "businesses")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessType businessType;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 16, unique = true)
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
    private boolean open;

    protected Business() {}

    public Business(
            BusinessType businessType, String name,
            String phone, String street, String city
    ) {
        this.businessType = businessType;
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.open = true;
    }

    public UUID getId() { return id; }
    public BusinessType getBusinessType() { return businessType; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPhone() { return phone; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }
    public boolean isOpen() { return open; }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
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

    public void setOpen(boolean open) {
        this.open = open;
    }
}