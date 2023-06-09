<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>       
        <title>Tempo Haven</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="CSS/index.css">
    </head>
    <body>       
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            
            if(session.getAttribute("userid") == null){
                System.out.println("null!");
            }
            
            else if(session.getAttribute("userid") != null){
                response.sendRedirect("MemberAccount.jsp");
            }
        %>
        <div class="slider">
            <div class="slide">
                <h1>text</h1>
                <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                    sed do eiusmod tempor incididunt ut labore et dolore 
                    magna aliqua. Ut enim ad minim veniam, quis nostrud 
                    exercitation ullamco laboris nisi ut aliquip ex ea 
                    commodo consequat. Duis aute irure dolor in reprehenderit 
                    in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
                    Excepteur sint occaecat cupidatat non proident, sunt in culpa 
                    qui officia deserunt mollit anim id est laborum.</h2>
                <a href="#choose" class="redirect">click</a>
            </div>
            <div class="slide">
                <h1>text</h1>
                <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                    sed do eiusmod tempor incididunt ut labore et dolore 
                    magna aliqua. Ut enim ad minim veniam, quis nostrud 
                    exercitation ullamco laboris nisi ut aliquip ex ea 
                    commodo consequat. Duis aute irure dolor in reprehenderit 
                    in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
                    Excepteur sint occaecat cupidatat non proident, sunt in culpa 
                    qui officia deserunt mollit anim id est laborum.</h2>
                <a href="#choose" class="redirect">click</a>
            </div>
            <div class="slide">
                <h1>text</h1>
                <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                    sed do eiusmod tempor incididunt ut labore et dolore 
                    magna aliqua. Ut enim ad minim veniam, quis nostrud 
                    exercitation ullamco laboris nisi ut aliquip ex ea 
                    commodo consequat. Duis aute irure dolor in reprehenderit 
                    in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
                    Excepteur sint occaecat cupidatat non proident, sunt in culpa 
                    qui officia deserunt mollit anim id est laborum.</h2>
                <a href="#choose" class="redirect">click</a>
            </div>
            <div class="slide">
                <h1>text</h1>
                <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                    sed do eiusmod tempor incididunt ut labore et dolore 
                    magna aliqua. Ut enim ad minim veniam, quis nostrud 
                    exercitation ullamco laboris nisi ut aliquip ex ea 
                    commodo consequat. Duis aute irure dolor in reprehenderit 
                    in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
                    Excepteur sint occaecat cupidatat non proident, sunt in culpa 
                    qui officia deserunt mollit anim id est laborum.</h2>
                <a href="#choose" class="redirect">click</a>
            </div>
            <div class="slide">
                <h1>text</h1>
                <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                    sed do eiusmod tempor incididunt ut labore et dolore 
                    magna aliqua. Ut enim ad minim veniam, quis nostrud 
                    exercitation ullamco laboris nisi ut aliquip ex ea 
                    commodo consequat. Duis aute irure dolor in reprehenderit 
                    in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
                    Excepteur sint occaecat cupidatat non proident, sunt in culpa 
                    qui officia deserunt mollit anim id est laborum.</h2>
                <a href="#choose" class="redirect">click</a>
            </div>
        </div>

        <div class="choose" id="choose">
            <div class="leftbody">
                <h3>Login</h3>
                <form action="LoginServlet" method="post">
                    <fieldset>
                        <label for="username">Username
                            <input id="username" name="username" type="text" required />
                        </label>
                        <label for="password">Password
                            <input id="password" name="password" type="password" required />
                        </label>
                    </fieldset>

                    <input type="submit" name="submit" value="Login" class="submit"/>
                </form>

                <h5><span>or</span></h5>

                <a href="MemberLanding.jsp">
                <input type="submit" class="submit" value="Login as Guest">
                </a>
            </div>

            <div class="rightbody">
                
                <div class="register">
                    <h3>Welcome!</h3>
                        <h4>Don't have an account?</h4>
                        <h4 class="second">Consider signing up:</h4>
                        <a href="SignUp.jsp">
                            <input type="submit" class="submit" value="Sign Up">
                        </a>
                </div>
            </div>

        </div>
    </body>
</html>

