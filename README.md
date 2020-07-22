# Keycloak training

### Create and execute a Keycloak docker container
Access the keycloak-test folder and run:
`docker-compose up -d`

### Access Keycloak console using the top secret user and password(admin-admin)
http://localhost:8180/

### Configuring Keycloak server
- Create a **Realm** named **Lab**;
- Create a **Client** named **ui-employee-service**;
	- Set the **Client Protocol** as **openid-connect**;
	- Set the **Access Type** as **public**;
	- Set the **Valid Redirect URIs** as http://localhost:3000/*
	- Select the **Roles** tab and add a new item:
		- Set **Name** as USER;
	- Select the **Mappers** tab and add a new item:
		- Set **Name** as **Username**;
		- Set **Token Claim Name** as user_name;
		- Set **Mapper Type** as User Property;
		- Set **Claim JSON Type** as String;
- Select the **Users** menu:
	- Create a new user;
	- Set the credentials. The credentials mustn't set as temporary, not so far(:p);
	- Set the role created in **ui-employee-service** client;


### Application properties attributes
#### Application and authorization/authentication resource
rest.security.enabled=true
rest.security.issuer-uri=http://localhost:8180/auth/realms/lab
rest.security.api-matcher=/api/**

#### CORS
rest.security.cors.allowed-origins=*
rest.security.cors.allowed-headers=*
rest.security.cors.allowed-methods=GET,POST,PUT,PATCH,DELETE,OPTIONS
rest.security.cors.max-age=3600

#### Keycloak client data
security.oauth2.resource.id=ui-employee-service
security.oauth2.resource.token-info-uri=${rest.security.issuer-uri}/protocol/openid-connect/token/introspect
security.oauth2.resource.user-info-uri=${rest.security.issuer-uri}/protocol/openid-connect/userinfo
security.oauth2.resource.jwt.key-value=-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAseblZ6ZrVMv+riHbA/ltoDqOemvP292oMpvfTp1+36tK8drKGV/mUuLLAdF+Vw9yuBtyq7hsqpEL6FlL/M1TKHbartHhzYgAq9xrLQ8omIzkNoUdbxIRxu+u+F0enCqRgpWaX9YMncsLt3tUwfhNiTwjJORONrsZcWjCpT96U4qCbkqCGFyy4P2zpNs60LMTSx9pN8UeyI2xrQ0qmhvAmtTt2oH1YKSzA4cqaA+djVf0uF1tfTOe1qNi14dbS9vNZYiaQK3cBvLVKvItiLLjOtAUW42MMSBgvdpB6HMbVLmylYCe6psCGD4E1EwIrHTVBqOBb2gTyl8B4Nb8jTe66wIDAQAB\n-----END PUBLIC KEY-----

>> Extract the Public Key from *Realm Settings -> Keys -> Public Key*

#### Add the spring oauth dependency

<dependency>
	<groupId>org.springframework.security.oauth.boot</groupId>
	<artifactId>spring-security-oauth2-autoconfigure</artifactId>
	<version>2.3.1.RELEASE</version>
</dependency>


### Classes created to extract and configure the JWT authentication:

- SecurityProperties
	- Extract CORS attributes from application.properties;

- JwtAccessTokenCustomizer
	- Extract the JWT payload data as username, userId, etc...

- SecurityConfigurer
	- Set the CORS configuration;
	- Set security resource;
	- Set endpoint coverage by authorization;
	- Set the JwtAccessTokenCustomizer to read roles and user_name;

- SecurityContextUtils
	- Get the user data extract from JWT;

- OAuth2RestTemplateConfigurer
	- It generates and set a token into restTemplate context, it's usually used through the microservices communication;


## References
- https://www.baeldung.com/spring-security-oauth-jwt
- https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-security
- https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world


## Next steps
* [x] - Update Spring Security class;
* [ ] - Extract and return username from JWT;




