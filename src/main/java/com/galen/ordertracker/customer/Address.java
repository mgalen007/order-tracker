package com.galen.ordertracker.customer;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerId;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 10)
    private String postalCode;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    protected Address() {}

    public Address(
            Customer customerId, String street,
            String city, Double latitude,
            Double longitude
    ) {
        this.customerId = customerId;
        this.street = street;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.postalCode = "00000";
    }

    public String getId() { return id; }
    public Customer getCustomerId() { return customerId; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
