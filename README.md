# Handout of Software Security

## Authors
Bruno Cabrita - 57833\
Lucas Stanik - 66922\
Team Name - Lusitano-Polish Authentication Squad\

## Brief Description
This repository contains a web application (which is basically a counter) where the increment counter operation is only executed if the person trying to use it is authenticated. This authentication is performed by a module developed by us, which uses Java Web Tokens that are one-time use and expire after a while without use.

This application is accessed through [here](http://localhost:8080/myApp/Counter).\
WARNING: The link is associated to localhost so it will only work if you have the tomcat server running in your machine with our application.\
If the links are changed in the code, they must be changed in this document aswell to keep consistency.

The repository also contains another application which is essentialy used for [User Management](http://localhost:8080/myApp/UserManagement) operations such as:
- [Logging in](http://localhost:8080/myApp/UserManagement/login) (method of authentication);
- [Create new accounts](http://localhost:8080/myApp/UserManagement/register) (needs to be authenticated with root);
- [Delete accounts](http://localhost:8080/myApp/UserManagement/delete) (needs to be authenticated with root);
- [Changing password](http://localhost:8080/myApp/UserManagement/ChangePassword) (needs to be authenticated with user which you want to change the password of);
- Logging out.

For development purposes:
- Whenever you change some kind of code in the "myApp" folder, you will need to compile its classes and then put the output binaries into the respective "WEB-INF/classes" directory.
- The root account's password is "root". (For simplicity)
- To run the tomcat server, either run (from the apache directory):
```bash
./bin/catalina.sh run
```
Or if you don't want the terminal to be locked:
```bash
-- ./bin/startup.sh run
```
You will need to use the "shutdown.sh" script to stop the tomcat server if using the second.
