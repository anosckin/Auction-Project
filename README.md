# Auction-Project
An Auction website.
Users can Put up items for sale, bid on currently active autcions and write reviews for items that they have won in the auctions.

## Requirements
- need to have tomcat installed
- need to have maven installed
- JDK 16
- MySql server

## How to run
- Create a MySQL database
- create src/main/java/resources/dbconfig.properties
- in it write db.url, db.username, dq.password:
```
db.url=jdbc:mysql://localhost:3306/oop_project
db.username=username
db.password=password
```
- run the CreateTables.sql to setup the database
- from maven do : "mvn tomcat7:run" (make sure port 8080 is empty)
- website can be entered on http://localhost:8080/
- after creating first account, give it admin by the following command: "UPDATE users SET is_admin = true WHERE ID = 1;"

## Usage
There are 3 types of users: admins, dealers and customers.
- Admins can appoint dealers and other admins. Admins can also ban other users.
- Dealers can start auctions. Dealers can also see reviews of their products.
- Everyone can write a review on an auction they have won.
- Everyone can bid on active auctions.
