

Hotel Booking 
===============================
#Functional web application "Hotel Booking"

#Using the application, you will be able to

1. **Look through the list of available numbers (in the room there is room, category, price, additional options, for example breakfast, cleaning for an additional fee).**

2. **View rooms filtered by category.**

3. **Create a user.**

4. **The user can book a room for the specified days.**

5. **The user can view his reservation.**

6. **The user can get the total cost of the reservation (number for dates + cost of additional options).**

7. **View all bookings for the hotel.**

####The project contains an SQL script for creating database tables and populating them with the data

![poster](https://www.hotel-alpenglueck.de/s/cc_images/cache_67768577.jpg?t=1514956627)

Technology Stack:

- <a href="https://java.com/ru/download/">Java 8</a>
- <a href="https://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>
- <a href="https://projects.spring.io/spring-boot/">Spring Boot</a>
- <a href="https://spring.io/guides/gs/serving-web-content/">Spring MVC</a>
- <a href="http://hibernate.org/">Hibernate</a>
- <a href="https://maven.apache.org/">Maven</a>
- <a href="http://www.h2database.com/html/main.html">H2</a>


## Install:

    git clone https://github.com/AndrewChertok/hotelbooking


##Run (from project directory)

$ mvn spring-boot:run

or

$ mvn clean package
$ java -jar target/hotelbooking.jar

    JDBC URL: jdbc:h2:mem:booking

## Rooms handling

##All booked rooms by specific hotel
- <a href="http://localhost:8080/rest/hotel/booked/1006">by hotel № 1</a>
- <a href="http://localhost:8080/rest/hotel/booked/1007">by hotel № 2</a>

CURL:

    # get All Rooms by hotel № 1
    curl http://localhost:8080/rest/hotel/booked/1006
    # get All Rooms by hotel № 2
     curl http://localhost:8080/rest/hotel/booked/1007


##All available rooms by specific dates and specific hotel
- <a href="http://localhost:8080/rest/hotel/1006?in=2018-07-12&out=2018-07-23">by hotel № 1</a>
- <a href="http://localhost:8080/rest/hotel/1007?in=2018-07-12&out=2018-07-23">by hotel № 2</a>

CURL:

    
    # available rooms by dates and hotel №1
    curl http://localhost:8080/rest/hotel/1006?in=2018-07-12&out=2018-07-23
    # available rooms by dates and hotel №2
        curl http://localhost:8080/rest/hotel/1007?in=2018-07-12&out=2018-07-23

##All rooms by specific hotel filter by room type
- <a href="http://localhost:8080/rest/hotel/filter/1006?type=royal">by hotel number 1 type: royal</a>
- <a href="http://localhost:8080/rest/hotel/filter/1007?type=single">by hotel number 2 type: single</a>

CURL:

    
    # filter by room type hotel №1
    curl http://localhost:8080/rest/hotel/filter/1006?type=royal
     # filter by room type hotel №2
        curl http://localhost:8080/rest/hotel/filter/1007?type=single
    


## Reservation handling


##Client can view own reservation using email
- <a href="http://localhost:8080/rest/reservation/?email=good@gmail.com">reservation for "Samatha Good"</a>
- <a href="http://localhost:8080/rest/reservation/?email=river@gmail.com">reservation for "John River</a>

CURL:

     # view reservation using email №1
     curl http://localhost:8080/rest/reservation/?email=good@gmail.com
     
     # view reservation using email №2
          curl http://localhost:8080/rest/reservation/?email=river@gmail.com



##Client can check total price of reservation using email
- <a href="http://localhost:8080/rest/reservation/total?email=good@gmail.com">total costs for "Samatha Good"</a>
- <a href="http://localhost:8080/rest/reservation/total?email=river@gmail.com">total costs for "John River</a>


CURL:

  
     # check total price of reservation using email №1
     curl http://localhost:8080/rest/reservation/total?email=good@gmail.com 
     
     # check total price of reservation using email №2
          curl http://localhost:8080/rest/reservation/total?email=river@gmail.com 
     


##Create a client

CURL:

    
    # create a Client
    curl –X POST -d '{"phone":"+44 713 335-55-12", "email":"somebody@gmail.com","address":"Hermit st. 3 app.77","city":"London","country":"England"}' -H 'Content-Type:application/json' http://localhost:8080/rest/reservation?firstName=Somebody&lastName=Unknown
    

## Create a reservation


CURL:

     
     curl –X POST -d '{"checkIn": "2018-09-01","checkOut": "2018-09-20","isBreakfast": true,"isCleaning": true}' -H 'Content-Type:application/json' http://localhost:8080/rest/reservation/1012?email=somebody@gmail.com


