<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="smarthomesimulator.layout.ParseLayout" %>
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
        <tr>
            <td>House Layout :</td>
            <td> ${fileName}</td>
        </tr>
    </table>
    <form action=<%	
        		try {ParseLayout.parse(request.getParameter("fileName"));
            		} catch (Exception e) {
                        out.println("An exception occurred: " + e.getMessage());
            			}
            			%> >
            			
            	<input type="submit" value="Render"/>
            </form>
</body>
</html>