# AppointmentApp
AppointmentApp is application providing creating appointments by customers and assign doctors to these appointments.

Functionality:
  - Creating appointment by customer
  - Canceling appointment by customer at specific date
  - Showing all appointments of doctor at specific day

Application have two created customers and doctors with their personal information and database is reseting after each restart. To create appointment and cancel them customer need to send his customer digital pin.
# Configuration
Application is based on Spring boot, Hibernate and Rest Api and uses Postgres SQL database.
### Database
To connect to database you need to create database named "appointmentdata" on server postgres. 

Connection string:
```
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/appointmentdata
```
# Endpoints

Application provides three endpoints:

Create appointment
```
http://localhost:8081/customers/createAppointment
```
Cancel appointment
```
http://localhost:8081/customers/cancelAppointment
```
Get doctor appointments at specific day
```
http://localhost:8081/appointments/doctorAppointments
```

Create appointment example body to send to create appointment
```
{
    "customerId" : "0001",
    "doctorId": "0001",
    "customerPin": "1234",
    "appointment": {
        "appointmentDate": "2020-04-12T14:12Z"
    }
}
```
Cancel appointment example body to cancel created appointment before
```
{
    "customerId" : "0001",
    "customerPin": "1234",
    "appointmentDate": "2020-04-12T14:12Z"
}
```
Get doctor appointments of specific day example body
```
{
    "doctorId": "0001",
    "appointmentDate": "2020-04-12"
}
```

### Others

Application has created JUnit testing of services and repositories of application.

For more info write to me via email: 
wwesolowski97@gmail.com :)