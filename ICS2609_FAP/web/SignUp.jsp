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

            <div class="rightbody">
                <h3>Sign Up</h3>
                <form action="">
                    <fieldset>
                        <label for="username">Username</label>
                            <input id="username" name="username" type="text" required /> 
                        <label for="reusername">Re-type Username</label>
                            <input id="reusername" name="reusername" type="text" required />
                        <label for="password">Password</label>
                            <input id="password" name="password" type="password" required />
                        <label for="repassword">Re-type Password</label>
                            <input id="repassword" name="repassword" type="password" required />
                    </fieldset>

                    <div class="center">
                        <img src="CaptchaServlet" class="captcha">
                    </div>

                    <fieldset>
                        <label for="answer">Type the Captcha</label>
                            <input id="answer" name="answer" type="answer" required />
                    </fieldset>

                    <input type="submit" name="submit" value="Sign Up" class="submit"/>

                </form>


            </div>

        </div>
    </body>
</html>


