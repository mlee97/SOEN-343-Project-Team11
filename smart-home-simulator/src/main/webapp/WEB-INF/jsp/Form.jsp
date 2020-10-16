<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title></title>
<link href="/css/main.css" rel="stylesheet">
</head>
<%@ page import="smarthomesimulator.HouseLayoutFile" %>
<form action=<%HouseLayoutFile.LoadLayout(request.getParameter("file")); %>>
	<input type="file" name="file" />
	<input type="submit" value="upload" />
</form>

</html>