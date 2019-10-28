package com.threetee.formationSpring.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
@Repository
//@Component
//@Named("namedClient")
@Scope("prototype")
//@Primary
public class Client extends AbstractEntity implements InitializingBean{
	
	@Value(value = "TestCompoenent")
	private String firstName;
	private String lastName;
	private Date BirthDate;
	private Address address;
	private List<Account> accounts = new ArrayList<>();

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Long id, Date modifiedAt, Date createdAt) {
		super(id, modifiedAt, createdAt);
		// TODO Auto-generated constructor stub
	}

	public Client(String firstName, String lastName, Date birthDate, Address address, List<Account> accounts) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		BirthDate = birthDate;
		this.address = address;
		this.accounts = accounts;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Client [firstName=" + firstName + ", lastName=" + lastName + ", BirthDate=" + BirthDate + ", address="
				+ address + ", accounts=" + accounts + "]";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(" client afterPropertiesSet()");
		
		
		
	}
	
	private void init() {
		System.out.println(" client init-method()");
	}
	
	@PostConstruct
	private void init2() {
		System.out.println(" client PostConstruct");
	}

}
