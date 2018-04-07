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
        <a href="#">New! coming soon</a>
    </header>


    <div id="sidebar">

    </div>

    <div id="content">
        <br>
        <div><pre><h2>
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


        <br/><br/>


        <h3>AppointmentApi</h3>
        <form action="services/lunchcrunch/appointments" method="GET">
            <b>GET Function: /appointments?apiKey=...</b><br/>
            <input type="submit" value="Find all appointments for a user">
            API Key <input type="text" name="apiKey" value="1234567890">
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments/location" method="GET">
            <b>GET Function: /appointments?apiKey=...</b><br/>
            <input type="submit" value="Find appointments by location">
            API Key <input type="text" name="location" value=4>
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments/topic" method="GET">
            <b>GET Function: /appointments?apiKey=...</b><br/>
            <input type="submit" value="Find appointments by topic">
            API Key <input type="text" name="topic" value=2>
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments" method="POST">
            <b>POST Function: /appointments?apiKey=...&firstname=...&lastname=...&organization=...</b><br/>
            <input type="submit" value="Add Appointment">
            First Name <input type="text" name="firstname">
            Last Name <input type="text" name="lastname">
            Organization <input type="text" name="organization">
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments" method="POST">
            <b>POST Function: /appointments?apiKey=...&firstname=...&lastname=...&organization=...</b><br/>
            <input type="submit" value="Update Appointment">
            API Key <input type="text" name="apiKey">
            Fir
            st Name <input type="text" name="firstname">
            Last Name <input type="text" name="lastname">
            Organization <input type="text" name="organization">
        </form>
        <br/>
        <form action="services/lunchcrunch/appointments" method="POST">
            <b>POST Function: /appointments?apiKey=...</b><br/>
            <input type="submit" value="Delete Appointment">
            API Key <input type="text" name="apiKey">
        </form>
        <br>

    </div>
</div>
</body>
</html>