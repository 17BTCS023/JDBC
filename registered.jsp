<%@ page language="java" %>
 
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <style>
        body{
        margin: 0%;
        padding: 0%;   
        justify-content: center;
        background:linear-gradient(-90deg, #222, #777) ;
        background: black;
        background-size: cover;  
        color: white;
    }
    h1{
        text-align: center;
        padding-top:100px;
    }
    p{
    	padding: 50px;
        left: 100px;
    }
    
    </style>
</head>
<body>
<%
    HttpSession hses=request.getSession();
		
		String NewUser=(String)hses.getAttribute("NewUser");
		String balance=(String)hses.getAttribute("balance");
	%>
    <div class="reg-done">
        <h1>Registration Successful! <%=NewUser %></h1>
        <p>Your current balance is:<%=balance %></p>

    
    <div class="reg-done">
        <h1>Registration Successful!</h1>
        <!-- <img src="../../Media/image/tree.jpg" alt=""> -->
        <p>Your current balance is:</p>


    </div>
    
</body>
</html>