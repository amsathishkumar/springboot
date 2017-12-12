### SAT 

#Reference:
https://www.youtube.com/watch?v=u68OsrLnqc4

#Integration Testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JerseyApplication.class )

Run Test: mvn clean test
Create Executable jar: mvn clean package
java -jar <Jar file path>
Reference: https://dzone.com/articles/unit-and-integration-tests-in-spring-boot

#Self Signed 
C:\Users\satmunia\git\springboot\SatJersey>keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
Enter keystore password:
Re-enter new password:
What is your first and last name?
  [Unknown]:  sathish kumar muniappan
What is the name of your organizational unit?
  [Unknown]:  sat
What is the name of your organization?
  [Unknown]:  sat
What is the name of your City or Locality?
  [Unknown]:  chennai
What is the name of your State or Province?
  [Unknown]:  tamilnadu
What is the two-letter country code for this unit?
  [Unknown]:  91
Is CN=sathish kumar muniappan, OU=sat, O=sat, L=chennai, ST=tamilnadu, C=91 correct?
  [no]:  yes