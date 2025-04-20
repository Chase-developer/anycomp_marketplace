package com.chase.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
    
    public Seller() {
    }

    public Seller(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public void update(Seller seller)
    {
        this.name = seller.name;
        this.email = seller.email;
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
	
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	public List<Item> getItems()
	{
		return items;
	}
}
