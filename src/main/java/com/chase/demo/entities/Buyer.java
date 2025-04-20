package com.chase.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Buyer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Purchase> purchasedItems;

    public Buyer() {
    }

    public Buyer(Long id, String name, String email, List<Purchase> purchasedItems) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.purchasedItems = purchasedItems;
    }
    
    public void update(Buyer buyer)
    {
        this.name = buyer.name;
        this.email = buyer.email;
        this.purchasedItems = buyer.purchasedItems;
    }
    
    public Long getId()
    {
    	return id;
    }
    
    public void setId(Long id)
    {
    	this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    // Getters and Setters
    // (you can use your IDE to generate them)
}
