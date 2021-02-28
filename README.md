<div style="text-align:center">
    <p align="center">
        <img width="210" src="git-assets/profile.png" alt="Mediatek"/>
    </p>
    <p>Jules Doumèche • Hélène Te</p>
</div>

## Prerequisites

### Database

1. Install Oracle XE (tested version: 18c)

2. Login as SYSTEM in SQL-PLUS and add user `mediatek`:
```
CREATE USER mediatek IDENTIFIED BY *password*;
GRANT CONNECT, RESOURCE, DBA, CREATE SESSION, UNLIMITED TABLESPACE TO mediatek;
```
3. Edit `persistance.DatabaseConnection.java` with Oracle credentials:
```
String host = "localhost";
String sid = "XE";
String user = "mediatek";
String password = "*password*";
```
4. Load `database.sql` script in the freshly created database `MEDIATEK`

### Tomcat
    
tested version: 9.0.41

You can use JetBrain IntelliJ built-in Tomcat server support for development purposes.

For deployment, put compiled sources in tomcat's webapp folder.