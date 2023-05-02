# Handout of Software Security

## Authors:
### Bruno Cabrita - 57833
### Lucas Stanik - 66922
### Team Name - Lusitano-Polish Authentication Squad

## Brief Description
This repository contains a web application (which is basically a counter) where the increment counter operation is only executed if the person trying to use it is authenticated. This authentication is performed by a module developed by us, which uses Java Web Tokens that are one-time use and expire after a while without use.

This application is accessed through [here](http://localhost:8080/myApp/Counter).
WARNING: The link is associated to localhost so it will only work if you have the tomcat server running in your machine with our application.

The repository also contains another application which is essentialy used for [User Management](http://localhost:8080/myApp/UserManagement) operations such as [logging in](http://localhost:8080/myApp/UserManagement/login), [create new accounts](http://localhost:8080/myApp/UserManagement/register), [delete accounts](http://localhost:8080/myApp/UserManagement/delete), [changing password](http://localhost:8080/myApp/UserManagement/ChangePassword) and of course logging out.

For development purposes:
- Whenever you change some kind of code in the "myApp" folder, you will need to compile its classes and then put the output binaries into the respective "WEB-INF/classes" directory.
- To run the tomcat server, either run (from the apache directory):
-- ./bin/catalina.sh run (this will lock your terminal)
-- ./bin/startup.sh run (this will not lock your terminal and you will need to use "shutdown.sh" to stop the server)
