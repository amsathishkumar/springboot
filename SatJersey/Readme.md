### SAT 

#Reference:
https://www.youtube.com/watch?v=u68OsrLnqc4

#Integration Testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JerseyApplication.class )

Run Test: mvn clean test
Create Executable jar: mvn clean package
java -jar <Jar file path>
Reference: https://dzone.com/articles/unit-and-integration-tests-in-spring-boot


