# bmt
Bacula Management Tool

# Database Configuration

You need to add the following configuration to Apache Tomcat server.xml file, inside GlobalNamingResources session.
```xml
    <!-- 
        Bacula Management Tool database resource.
    -->
    <Resource name="jdbc/bmtdb" auth="Container" type="javax.sql.DataSource" 
        factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
        driverClassName="org.postgresql.Driver" 
        maxActive="20" 
        maxIdle="10" 
        maxWait="10000" 
        username="bmt"
        password="bmt12345" 
        url="jdbc:postgresql://192.168.103.1:5432/bmtdb" 
        removeAbandoned="true"
        removeAbandonedTimeout="60"
        logAbandoned="true"
        validationQuery="select 1"
        poolPreparedStatements="true"
        description="BMT Database"/>
```