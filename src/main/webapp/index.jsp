<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <meta charset="utf-8">
    <title>LunchCrunch</title>
    <link rel="stylesheet" type="text/css" href="css/lunchStyle.css?version=51">
</head>

<body>
<div id="wrap">
    <header>
        <h1>LunchCrunch</h1>
        <a href="index2.jsp">New! coming soon</a>
    </header>

    <div id="content">
        <br>
        <div><pre><br><h2>
                The LunchCrunch API allows people of an organization to connect with
                each other to discuss certain topics at specific locations and
                meeting times.

                The organization will register for an API-key that will be used to
                track activity and allow members to browse topics, locations
                and existing discussion groups.

                Users can add their own meeting or decide to join other existing user groups.

                The LunchCrunch API consist of 4 individual API's:
                1)  UserApi
                2)  AppointmentApi
                3)  LocationApi
                4)  TopicApi

                The format of each request and responds are illustrated below.
                </h2>
            </pre>
        </div>

        <br/><br/>

        <h3>UserApi</h3>
        <form action="services/lunchcrunch/users" method="GET">
            <b>GET Function: /users?apiKey=...</b><br/>
            <input type="submit" value="Find a User">
            API Key <input type="text" name="apiKey" value="0998877543">
        </form>
        <br/>
        <form action="services/lunchcrunch/users" method="POST">
            <b>POST Function: /users?apiKey=...&firstname=...&lastname=...&organization=...</b><br/>
                         <input type="submit" value="Add a User">
            First Name   <input type="text" name="firstname">
            Last Name    <input type="text" name="lastname">
            Organization <input type="text" name="organization">
        </form>
        <br/>
        <form action="services/lunchcrunch/users" method="POST">
            <b>POST Function: /users?apiKey=...&firstname=...&lastname=...&organization=...</b><br/>
                         <input type="submit" value="Update a User">
            API Key      <input type="text" name="apiKey">
            First Name   <input type="text" name="firstname">
            Last Name    <input type="text" name="lastname">
            Organization <input type="text" name="organization">
        </form>
        <br/>
        <form action="services/lunchcrunch/users" method="POST">
            <b>POST Function: /users?apiKey=...</b><br/>
                    <input type="submit" value="Delete User">
            API Key <input type="text" name="apiKey">
        </form>


        <br/>


        <h3>AppointmentApi</h3>
        <form action="services/lunchcrunch/appointments" method="GET">
            <b>GET Function: /appointments?apiKey=value</b><br/>
                    <input type="submit" value="Find all appointments for a user">
            API Key <input type="text" name="apiKey" value="1234567890">
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments/location" method="GET">
            <b>GET Function: /appointments/location?location=value</b><br/>
                    <input type="submit" value="Find appointments by location">
            Location Id <input type="text" name="location" value=4>
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments/topic" method="GET">
            <b>GET Function: /appointments/topic?topic=value</b><br/>
                     <input type="submit" value="Find appointments by topic">
            Topic Id <input type="text" name="topic" value=2>
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments/appointment" method="GET">
            <b>GET Function: /appointments/appointment?appointment=value</b><br/>
                           <input type="submit" value="Find appointment by appointment ID">
            Appointment Id <input type="text" name="appointment">
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments" method="POST">
            <b>POST Function: /appointments?apiKey=value&location=value&topic=value&datetime=value</b><br/>
                         <input type="submit" value="Add Appointment">
            API key      <input type="text" name="apikey" value="1122334455" required>
            Location Id  <input type="text" name="locationid" value=3 required>
            Topic Id     <input type="text" name="topicid" value=4 required>
            Date Time    <input type="datetime-local" name="datetime" value="2018-05-07T11:46" required>
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments" method="POST">
            <b>POST Function: /appointments?appointmentid=value?&datetime=value</b><br/>
                           <input type="submit" value="Update Appointment">
            Appointment id <input type="text" name="appointmentid" value=5 required>
            Date Time      <input type="datetime-local" name="datetime" value="2018-06-07T11:46" required>
        </form>

        <br>

        <h3>LocationApi</h3>
        <form action="services/lunchcrunch/locations" method="GET">
            <b>GET Function: /locations?apiKey=...</b><br/>
            <input type="submit" value="Get Locations">
            API Key <input type="text" name="apiKey" value="0998877543">
        </form>
        <form action="services/lunchcrunch/locations" method="POST">
            <b>POST Function: /locations?apiKey=value?userId=value?&description=value</b><br/>
            <input type="submit" value="Add Location">
            API key      <input type="text" name="apikey" value="1122334455" required>
            User Id  <input type="text" name="userId" value=3 required>
            Description  <input type="text" name="description" value="Collaboration Area 3" required>
        </form>

        <h3>TopicApi</h3>
        <form action="services/lunchcrunch/topics" method="GET">
            <b>GET Function: /topics?apiKey=...</b><br/>
            <input type="submit" value="Get Topics">
            API Key <input type="text" name="apiKey" value="0998877543">
        </form>



    </div>

</div>
</body>
</html>