<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <meta charset="utf-8">
    <title>LunchCrunch</title>
    <link rel="stylesheet" href="css/lunchStyle.css">
</head>

<body>
<div id="wrap">
    <header>
        <h1>LunchCrunch</h1>

    </header>

    <div id="sidebar">

        <nav>
            <ul>
                <li><a href="#">stuff</a></li>
                <li><a href="#">More stuff</a></li>
                <li><a href="services/lunchcrunch/appointments">Get All Appointments</a></li>

                <form action="services/lunchcrunch/appointments/get/99" method="GET">
                    <%--<br>User Id<input type="text" name="user" value=3>--%>
                    <%--<br> Location Id<input type="text" name="location" value=7>--%>
                    <%--<br> Topic<input type="text" name="topic" value=2>--%>
                    <br> Date<input type="text" name="name"   value="Elise">

                    <input type="submit" value="Add Appointment">
                </form>
                <%--<li><a href="services/lunchcrunch/appointments?id=3&id=7&id=2&date=2018-01-01">Add appoinment</a></li>--%>
            </ul>
        </nav>
    </div>

    <div id="content">
        <br>
        <div><pre><h2>

                This will be the user documentation for LunchCrunch......
                </h2>
            </pre>

            <br/><br/>
            <form action="services/lunchcrunch/users" method="GET">
                <b>UserApi GET Function: /users?apiKey=...</b><br/>
                API Key <input type="text" name="apiKey" value="0998877543">
                <input type="submit" value="Find User">
            </form>
            <br/>
            <form action="services/lunchcrunch/users" method="POST">
                <b>UserApi POST Function: /users?apiKey=...&firstname=...&lastname=...&organization=...</b><br/>
                First Name <input type="text" name="firstname">
                Last Name <input type="text" name="lastname">
                Organization <input type="text" name="organization">
                <input type="submit" value="Add User">
            </form>
            <br/>
            <form action="services/lunchcrunch/users" method="POST">
                <b>UserApi POST Function: /users?apiKey=...&firstname=...&lastname=...&organization=...</b><br/>
                API Key <input type="text" name="apiKey">
                First Name <input type="text" name="firstname">
                Last Name <input type="text" name="lastname">
                Organization <input type="text" name="organization">
                <input type="submit" value="Update User">
            </form>
            <br/>
            <form action="services/lunchcrunch/users" method="POST">
                <b>UserApi POST Function: /users?apiKey=...</b><br/>
                API Key <input type="text" name="apiKey">
                <input type="submit" value="Delete User">
            </form>
        </div>
        <br>

        <br><br>
        <h3>Get all Topics</h3>

        <form action="services/lunchcrunch/topics" method="GET">
            <b>TopicApi GET Function: /topics?apiKey=...</b><br/>
            API Key <input type="text" name="apiKey" value="0998877543">
            <input type="submit" value="Get Topics">
        </form>

    </div>


    <footer>
        THIS IS THE FOOTER
    </footer>
</div>
</body>
</html>