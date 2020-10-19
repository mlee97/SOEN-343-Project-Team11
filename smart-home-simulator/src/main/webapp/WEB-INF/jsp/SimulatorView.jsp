<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>Current Simulator</title>
    </head>
<body>

    <h2>Submitted Simulation Information</h2>
    <table>
        <tr>
            <td>Date :</td>
            <td>${date}</td>
        </tr>
        <tr>
            <td>Time :</td>
            <td>${time}</td>
        </tr>
        <tr>
            <td>Outside Temperature:</td>
            <td>${tempOut}</td>
        </tr>
        <tr>
            <td>Default Indoor Room Temperature :</td>
            <td>${defaultTempIn}</td>
        </tr>
    </table>
</body>
</html>