reminder_restful_webservice
=========================

Jersey 2.5.1 RESTful Web Service with JPA 2.1 implemented using EclipseLink 2.5.2 and using Derby 10.10.1.1 In Memory Database

Steps to build and run this application:
1. mvn clean install reminder_restful_app
2. jetty:run-war to start and deploy on jetty

Once application is build and server started.Please test.

-------------------------
Test
-------------------------

Post of web server is 8080


Once you have started up the application

http://localhost:8080/
- Will navigate you to the index.jsp to ensure the application is running.

----------------------
Example Restful Calls
----------------------

Ensure you set the content-type in your request to application/json

POST:

url = localhost:8080/api/reminders
method = POST
data =
    {
        "description": "testing",
        "dueDate": "2017-01-01",
        "name": "John2",
        "status": "DONE"
    }

GET ALL REMINDERS:

url = localhost:8080/api/reminders
method = GET

GET REMINDER BY STATUS:

url = localhost:8080/api/reminders?status=DONE
method = GET

GET REMINDER BY DATE:

url = localhost:8080/api/reminders?dueDate=2017-01-01
method = GET

GET REMINDER BY STATUS AND DATE:

url = localhost:8080/api/reminders?status=DONE&&dueDate=2017-01-01
method = GET

UPDATE:

url = localhost:8080/api/reminders
method = PUT
data = 
    {
        "description": "testing",
        "dueDate": "2017-01-01",
        "id": 1,
        "name": "John_NEW",
        "status": "DONE"
    }

