# SAT Technology
##To generate the SSL KEY
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650

C:\Users\satmunia\Downloads\demo\demo>keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
Enter keystore password:
Re-enter new password:
What is your first and last name?
  [Unknown]:  sathish
What is the name of your organizational unit?
  [Unknown]:  sat
What is the name of your organization?
  [Unknown]:  sat
What is the name of your City or Locality?
  [Unknown]:  chennai
What is the name of your State or Province?
  [Unknown]:  tamilnadu
What is the two-letter country code for this unit?
  [Unknown]:  india
Is CN=sathish, OU=sat, O=sat, L=chennai, ST=tamilnadu, C=india correct?
  [no]:  yes

##TO Run the Spring boot
spring-boot:run