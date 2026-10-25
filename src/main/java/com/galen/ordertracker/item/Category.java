package com.galen.ordertracker.item;

import com.galen.ordertracker.business.Business;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Column(nullable = false, length = 100)
    private String name;

    public Category() {}

    public Category(Business business, String name) {
        this.business = business;
        this.name = name;
    }

    public String getId() { return id; }
    public Business getBusiness() { return business; }
    public String getName() { return name; }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public void setName(String name) {
        this.name = name;
    }
}
