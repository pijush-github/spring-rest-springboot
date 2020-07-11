package com.learning.example.basic.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.learning.example.basic.custom.validation.Author;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

	@Id
    @GeneratedValue
    private Long id;
	
	@NotEmpty(message = "Please provide a name")
    private String name;
	
	@Author
	@NotEmpty(message = "Please provide a author")
    private String author;
	
	@NotNull(message = "Please provide a price")
    @DecimalMin("1.00")
    private BigDecimal price;
    
    public Book(String name, String author, BigDecimal price){
    	this.name = name;
    	this.author = author;
    	this.price = price;
    	
    }
}
