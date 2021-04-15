# luis-assessment-crawler
Web Crawler
This is a Java Web Crawler implementation for extracts embedded JSON-LD data from https://www.britishcornershop.co.uk/" part of Nakko assesment.

DESCRIPTION

Actually this maven java crawler application uses diferents types of tools and libraries, list above:
  
  Jsoup: for parse html documents and search ld-json objects.
  jsonld-java: for seralize json-ld objects to Java Objects.
  lombok: library for reduce code lines in POJOS,creating getter,setters,constructors.
  hibernate: ORM for mapping POJOS to database.
  maven-assembly: plugin for create jar with dependencies.
  jackson: seralize to POJOs.
 
Basically this apps connect with Jsoup to the page and search script html elements and filter as "application/ld+json" type scripts. After this with jsonld-java seralize to String and next with Jackson serialize to POJOS.

As POJOS with JPA and Hibernate the json-ld objects are persisted in the database with this respective relations, the default database is allocated in AWS RDS.

To check data stored in database you can access publicly to britishcorner.cykipvvitlzp.us-east-2.rds.amazonaws.com/britishcornershop 3306 with the credentials that are in the persistence.xml file

USAGE

This app can run native with maven running on sdk 16 or as a docker image.
