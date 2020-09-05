spring.jpa.database=MYSQL
spring.datasource.platform=mysql
spring.datasource.url = ${dbURL}?createDatabaseIfNotExist=true
spring.datasource.username =${dbUsername}
spring.datasource.password =${dbPassword}
spring.datasource.driver-class-name=${driverClassName}
spring.datasource.sql-script-encoding=UTF-8
server.port=9090
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false
spring.jpa.hibernate.ddl-auto = create
spring.datasource.initialization-mode=always

spring.datasource.tomcat.test-while-idle = true
spring.datasource.tomcat.validation-query = SELECT 1

spring.jpa.show-sql = true
