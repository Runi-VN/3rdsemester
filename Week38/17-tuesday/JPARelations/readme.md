# Notes for tuesday exercises

## Collections of basic types
First we see our collection just as a `blob`  
By adding `@ElementCollection` we now see the `customer`-table has divided into two, now also including `Customer_HOBBIES`.

## JPA Entity Mappings  

### 2) One to One – Bidirectional  
**Run the project and investigate the generated tables (the foreign key).  
Is there any difference compared to the previous exercise. If not explain why.**  
- In the first exercise, the `CUSTOMER`-table has a foreign key `ADDRESS_ID` pointing to the `ADDRESS-TABLE` and it's `ID`-column.  

*Same thing in exercise two...*  
I believe because we use OneToOne, JPA works it magic by using inverse.  
There is only one matching `address_id` in the `customer`-table and vice-versa. Therefore no changes were necessary.

### 3) OneToMany (unidirectional)  
**How many tables were generated? Explain the purpose of each of the tables.**  

- ADDRESS
  - Empty, contains ID, City, Street.
- CUSTOMER
  - Contains our customers as before.
- CUSTOMER_ADDRESS
  - Empty. A *reference* table with a `Customer_CUSTOMER_ID` and a `addresses_ID`
  
The `ADDRESS`- and `CUSTOMER`-tables can now be joined with the help of this reference table: `CUSTOMER_ADDRESS`.

### 4) OneToMany (bidirectional)  
**Observe the generated code, especially where we find the mappedBy value. Explain.**  

We specified during the setup that we wanted a bidirectional relation, owner being `ADDRESS`.  
The wizard generates a `customer`-field and an `@ManyToOne`-annotation in the `Address.java`-class.  
In the `Customer.java`-class we see the `@OneToMany(mappedBy = "customer")-annotation.

I understand it so that the owner of the relation is `ADDRESS` (meaning the foreign key)  
and so any `customers` *answers to* (is mapped by) their respective (owner) `ADDRESS`.

*Create a "test" method and insert a number of Customers with Addresses into the tables, using JPA.  
**Which extra step is required for this strategy compared to OneToMany unidirectional?***

Before the extra step, `ADDRESS` contains a column `CUSTOMER_CUSTOMER_ID` with all `null` values.

I excepted to fix this with some smart JPA-join but failed to find a solution.  
Instead I found two solutions, both with the goal of persisting `ADDRESS` with a `CUSTOMER` **through the new `customer`-field:  
 1) Generate and use a setter for `ADDRESS` like  
 
```java  
address.setCustomer(Customer customer)
```  
 2) Generate and use a new constructor for `ADDRESS` like:  
 
 ```java  
 public Address(String street, String city, Customer customer) {
        this.street = street;
        this.city = city;
        this.customer = customer;
    }
```  
    
### 5) Many To Many (bidirectional)  
(**In regards to 1st and 2nd semester**)  
- How can we implement ManyToMany relationships in an OO-language like Java?  
  - I'm not sure what the answer is here. In java?  
  As we saw above, having two classes that can contain eachother would be ManyToMany.
- How can we implement ManyToMany relationships in a Relational Database?
  - Reference tables.

**Run the project and investigate the generated tables. Explain ALL generated tables.**
- `ADDRESS` (ID, CITY, STREET, CUSTOMER_ID)  
  - Empty. Will contain data from our Address.java-object once set up.  
- `CUSTOMER` (ID, FIRSTNAME, LASTNAME)
  - Contains data from our Customer.java-object.
- `CUSTOMER_ADDRESS` (customers_id, addresses_id)
  - Empty. Contains FK to tables above. Reference table.


