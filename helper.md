###filter xml - 

https://www.journaldev.com/1933/java-servlet-filter-example-tutorial

journal dev - java servelt filter 

com.rpaser.server.filter 

public dofilter (){

}

###postgres dependency - 

<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.2.18</version>
</dependency>

###postgres connection - 

dzone how to connect postgres

https://dzone.com/articles/how-to-connect-postgresql-with-java-application


###struct doc - 

struct apache  - for all search and rest 


war is in the target/

webapps /root ->  resumeparser.war and extract ==> jar -xvf resumeparser.war


install postgres 
sudo apt-get update
sudo apt-get -y install postgresql


<!-- Create table userinfo (id int not null,) -->
sudo -i -u postgres 
psql
create database rparser
\c rparser
CREATE TABLE userinfo(
   ID  SERIAL PRIMARY KEY,
   username           TEXT      NOT NULL,
   apikey           TEXT      NOT NULL,
   hitsallowed            INT       NOT NULL,
   hitsused            INT       NOT NULL,
   createddt date,
   updateddt date
);
insert into userinfo (username,apikey,hitsallowed,hitsused,createddt,updateddt) values('WORKLINE','WORKLINEec633f34845a69f7d4d9decc38511861',10,0,now(),now())
root@101.53.147.58

##tomcat 

use tomcat 9 // 10 wont be supported

Linux use ufw to allow the ports - 8080,80,443

