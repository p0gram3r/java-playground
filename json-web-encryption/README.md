# JSON Web Encryption

- In fact a JWT does not exist itself — either it has to be a JWS (JSON Web Signature) or a JWE (JSON Web Encryption). Its like an abstract class — the JWS and JWE are the concrete implementations.


- The first part (once parted by the periods) of the JWT is known as the JOSE header. JOSE stands for **Javascript Object Signing and Encryption**.

## More information
- [Nimbus JOSE](https://nimbusds.com/products/nimbus-jose-jwt) - library for creating and parsing JWS and JWE tokens
- [JWT, JWS and JWE for Not So Dummies! (Part I)](https://medium.facilelogin.com/jwt-jws-and-jwe-for-not-so-dummies-b63310d201a3) - very detailed introduction
- [Securing Your Spring boot with JWE](https://medium.com/@parsag67/securing-your-spring-boot-with-jwe-a-guide-to-implementing-jwt-encryption-for-user-authentication-173fcc4fd970) - guide for leveraging JWE in Spring Boot
