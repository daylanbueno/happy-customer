# happy-customer-api

SalesApp is an API to count the points earned by the customer for each purchase.

This API consists of registering customers, products and transactions.
The objective is to calculate the points accumulated on each purchase.

Technologies
+ Spring boot
+ Java 11
+ H2 Database
+ Lombok
+ Swagger2
+ Jpa/Hibernate
+ Rest
+ Maven

* To run the project on your machine it is important that you have java 11 and maven installed.
* After clones the project on your machine
* In the root folder of the project run the following command
  - mvn spring-boot:run
  
 ![image](https://user-images.githubusercontent.com/17939912/170051155-870bbd4c-036c-499a-8dd7-0a59c041d56b.png)

* Api will run on port 8081 using in-memory h2 database.

See swagger documentation

Swagger link to test the api: http://localhost:8181/swagger-ui.html#/

![image](https://user-images.githubusercontent.com/17939912/170896205-cc5e3bf4-9d86-4e72-ab7c-f9bf105bb36a.png)

Given a record of each transaction over a three-month period, calculate the reward points earned for each customer by month and total.


You can use the test in swagger: http://localhost:8181/api/transactions/group-last-three-month
![image](https://user-images.githubusercontent.com/17939912/170894675-e39a9950-a923-4bc8-a54d-523292f9bfbd.png)



Customers
![image](https://user-images.githubusercontent.com/17939912/170896551-e55e6224-9a7b-4a8f-80c3-2409f4fb4ea8.png)


All transactions
![image](https://user-images.githubusercontent.com/17939912/170896807-4fedfbd6-1348-4859-a742-7ad136bb5060.png)



You can filter by date and customer.
![image](https://user-images.githubusercontent.com/17939912/170896912-18d64b99-37de-4ac6-a8eb-57e273ee0cb3.png)

