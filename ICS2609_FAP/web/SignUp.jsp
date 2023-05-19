<%-- 
    Document   : SignUp
    Created on : May 12, 2023, 9:09:21 AM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/signup.css">
        <title>Sign Up Page</title>
    </head>
    <body>
        <div class="bothside" id="bothside">
            <div class="leftbody">

            </div>

            <div class="rightbody" >
                <h3>Sign Up</h3>
                <% if (session.getAttribute("warningSignUp") != null) {%>
                <p align="center"><font color="red"><%=session.getAttribute("warningSignUp")%></font></p>
                    <%} else if (session.getAttribute("approvedSignUp") != null) {%>
                <p align="center"><font color="green"><%=session.getAttribute("approvedSignUp")%></font></p>
                    <%}%>
                <form action="SignUp" method="post">
                    <fieldset>
                        <label for="username">Username
                            <input id="username" name="username" type="text" required />
                        </label>
                        <label for="reusername">Re-type Username
                            <input id="reusername" name="reusername" type="text" required />
                        </label>
                        <label for="password">Password
                            <input id="password" name="password" type="password" required />
                        </label>
                        <label for="repassword">Re-type Password
                            <input id="repassword" name="repassword" type="password" required />
                        </label>
                    </fieldset>
                    <div class="center">
                        <img src="CaptchaServlet" class="captcha">
                    </div>

                    <fieldset>
                        <label for="answer"> Type the Captcha
                            <input id="answer" name="captchaGuess" type ="text" required />
                        </label>
                    </fieldset>

                    <input type="submit" name="submit" value="Sign Up" class="submit"/>

                </form>
                
                <a href="LoginPage.jsp"> Or, Log-In to your Account. </a> 

            </div>

        </div>
    </body>
</html>

