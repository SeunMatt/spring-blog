How to Deploy a Spring Boot Application to Heroku
=================================================
Deploying a Spring-Boot application to Heroku is very straight forward.
The not-so-obvious part of the process is the application.properties file and required configuration.
All other steps are just straight forward and easy to get. So let's get started.


Create Heroku Account
---------------------
- Sign up at (https://www.heroku.com)[https://www.heroku.com/]
- Then you will have a free account that can host applications.

The way heroku works is that, parts of your applications like database, logging and others are catered for by heroku add-ons
So if you want to use MySql/MariaDB or MongoDB with your application, you have to provision the add-on for your application.


From the heroku dashboard create a new app and give it a name of your choice. Heroku will give you a free domain 
in the format https://<your-app-name>.herokuapp.com for the app you created.


Provisioning database add-on
----------------------------

- Login to heroku app dashboard
- Click on the app you have created
- Click on the resources tab
- In the Add-ons section, search for JawsDB Maria (or other database add-ons you prefer. (View all add-ons here)[https://elements.heroku.com/addons]) 
- Click on it to provision it for your app - select the free plan or others that suits you.
- On successful provisioning of the add-on, JawsDB Maria will be listed as your add-ons. Click on it to take you
to the add-on dashboard where you can see config variables like: host, username, password, and port.

- Finally if you goto the Settings tab, and you click on Reveal Config Vars, you will see an existing
entry with key: 
```properties
JAWSDB_MARIA_URL 
```
and value that has a format like this: 
```properties
mysql://<username>:<username>@<host>:<port>/<databasename>
```

Take note of the variables that correspond to the placeholders in your own URL as we will be using them next.



Environment Configuration Variables
-----------------------------------

Wondering how you are going to specify config variables like server port and others? 
No worries amigo! Heroku's got you covered.

So first create another .properties file and name it `application-heroku.properties`

Take note of the naming pattern; it is such that Spring can recognize it when we ask it to use
a particular profile - in this case `heroku`.

Just copy the contents of `application.properties` file and paste in to `application-heroku.properties` file.

What we want to do is to define different set of properties for different environment of our application.

When our app is running locally, `application.properties` will be used but when it is running on heroku
`application-heroku.properties` will be used.

In our `application-heroku.properties` file, do not specify values for the properties that will be provided
at runtime by the environment like the ones below.

```properties
#server
server.port
server.servlet-path=/
server.context-path=/

#spring-data properties
spring.datasource.url
spring.datasource.username
spring.datasource.password
```

So, we have to specify the port, datasource url, username and password.

- Go to your app dashboard
- click on the app that you have created
- click on the Settings tab
- click Reveal Config Vars. You will see other config vars which you shouldn't touch/modify
- So we will add the properties that we want Spring to detect on startup and use for our app
- **Note:** for `spring.datasource.url` the key will be `SPRING_DATASOURCE_URL`, 
        for `spring.datasource.username` the key will be `SPRING_DATASOURCE_USERNAME`
        for `spring.datasource.password` the key will be `SPRING_DATASOURCE_PASSWORD`
        
When you provisioned the database add-on, as described above, 
the database will have created a config var for the database connection string. 
You can click on the value to copy it in full.

From this let's extract values for our spring properties. In the Config Vars section of the
settings tab, you will see an editable field as the last of the KEY-VALUE Pairs for adding a new config

In the input field labelled `KEY` add `SPRING_DATASOURCE_URL` and 
in the field labelled `VALUE` add `jdbc:<databasetype>://<host>:<port>/<databasename>`.
Remember to replace the placeholders with your own.
 
 - `<databasetype>` = `mysql` as can be seen from the value of `JAWSDB_MARIA_URL`   
 - host, port and databasename can be extracted from the `JAWSDB_MARIA_URL` as described above
 
 Once you are done, just click the add button to add the config variable.
 
 Repeat the procedure to add the following vars:
 
 - `SPRING_DATASOURCE_USERNAME` = `<username-from-connection-string`
 - `SPRING_DATASOURCE_PASSWORD` = `<password-from-connection-string`

If you are wondering why we are not specifying any config var for `server.port`, that's because, 
Heroku will detect that our app is a Spring application and by default will provide that during application startup.



Conclusion
----------
So that's how to deploy your Spring application to Heroku and connect it to a provisioned database.
This tutorial is part of a series of tutorials around the creation of (Spring Blog)[https://github.com/SeunMatt/spring-blog]
A complete full functional open source blog application developed with Spring Framework.

View the project on (Github)[https://github.com/SeunMatt/spring-blog] and *show some love by giving it a star*.