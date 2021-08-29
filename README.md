# Auction-Project
An Auction website.
Users can Put up items for sale, bid on currently active auctions and write reviews for items that they have won in the auctions.

## Requirements
- Tomcat 10.0.10
- Maven 3.8.2
- JDK 16
- Running MySql server

## How to run
- Create a MySQL database
- create src/main/java/resources/dbconfig.properties
- in it write db.url, db.username, db.password:
```
db.url = jdbc:mysql://localhost:3306/oop_project
db.username = username
db.password = password
```
- run the CreateTables.sql to setup the database
- from maven do : "mvn tomcat7:run" (make sure port 8080 is empty)
- website can be entered on http://localhost:8080/
- after creating first account, give it admin by the following command: 
```
UPDATE users SET is_admin = true WHERE ID = 1;
```

## Usage
There are 3 types of users: admins, dealers and customers.
- Admins can appoint dealers and other admins. Admins can also ban other users.
- Dealers can start auctions. Dealers can also see reviews of their products.
- Everyone can write a review on an auction they have won.
- Everyone can bid on active auctions.
- Everyone can see what auctions they have put their bids on and if they are winning them or not.
- Everyone can see what auctions they have won.
- There is a Leaderboard of users who have the most auction wins.

## Auction
Auction is described with the following traits:
- Item code
- Item name
- End date
- Starting Price
- Minimum increment
- Item description

An auction is active if the current date is before the end date.

## Some Safety precautions
- Password is hashed with SHA 256.
- App remembers the session so just entering a url won't redirect.
- Protected from SQL injections
- dbconfig.properties is hidden with gitignore
