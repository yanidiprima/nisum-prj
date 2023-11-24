# Nisum - CRUD Users with JWT

![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Technological stack

![](https://img.shields.io/badge/java_11-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/h2_database-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/openapi_3-✓-blue.svg)

# Description

Service for the registration, delete and search of users using tokens for the security of the application 

# Diagram Solution

![](./resources/DiagramSolution.png)

# Start Service

1. Requsitos: [Java 11](https://www.java.com/download/) y [Maven](https://maven.apache.org)

2. Clone Repository

```
$ git clone https://github.com/yanidiprima/nisum-prj.git
```

3. Go to the folder root

```
$ cd nisum-prj
```

4. Compile and install project dependencies

```
$ mvn clean package
```

5. Start Service

```
$ mvn spring-boot:run
```

```javascript
 
2023-10-16 10:11:05.824  INFO 15548 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-10-16 10:11:05.834  INFO 15548 --- [           main] com.nisum.users.ServiceApplication       : Started ServiceApplication in 4.42 seconds (JVM running for 4.894)

```

# OpenApi Access
 

1. Open Api

`http://localhost:8085/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/` 

2.- H2 Data Base

`http://localhost:8085/h2-console` 

Usuario: root
Password: root
BD: nisum

![](./resources/DB_h2.png)


![](./resources/DB_EXAMPLE.png)


# Tes

Una vez iniciado el servicio puede acceder a las consolas:

1. Operación `POST /api/user/register`

{
  "name": "User Example",
  "username": "Example",
  "email": "userexample@email.com.cl",
  "password": "example123456",
  "phones": [
    {
      "numberPhone": "+54236598",
      "cityCode": "BUE",
      "countryCode": "AR"
    }
  ]
}


```

Respuesta

```
{
  "id": 1,
  "uuid": "77ccbc84-dc6f-4505-993a-7a1074353cec",
  "name": "User Example",
  "username": "Example",
  "email": "userexample@email.com.cl",
  "isActive": true,
  "createdDate": "2023-11-24",
  "modifyDate": null,
  "lastLogin": "2023-11-24",
  "phones": [
    {
      "numberPhone": "+54236598",
      "cityCode": "BUE",
      "countryCode": "AR"
    }
  ],
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFeGFtcGxlIiwiaWF0IjoxNzAwODQ5NjgyLCJleHAiOjE3MDA4NTMyODJ9.JeTFUqzMdxVWOF6Z9Va5NbywGC-_YeqDMMcbUp3zkak"
}
```

2. Operación `POST /users/login`

On this part, the user must to be register to has a success authentication

Ejecución
```
curl -X 'POST' \
  'http://localhost:8085/api/user/login?username=Example&password=example123456' \
  -H 'accept: */*' \
  -d ''
```
Respuesta
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFeGFtcGxlIiwiaWF0IjoxNzAwODQ5NzIzLCJleHAiOjE3MDA4NTMzMjN9.bnXwbbmOGhjMptVTfsEZW7pe_Qx4_qgCDUC_V-2BEOM

```

3. Operación `GET /users/{username}`

Return user information

Ejecution
```
curl -X 'GET' \
  'http://localhost:8085/api/user/Example' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFeGFtcGxlIiwiaWF0IjoxNzAwODQ5NzIzLCJleHAiOjE3MDA4NTMzMjN9.bnXwbbmOGhjMptVTfsEZW7pe_Qx4_qgCDUC_V-2BEOM'
```
Response
```
{
  "id": 1,
  "uuid": "77ccbc84-dc6f-4505-993a-7a1074353cec",
  "name": "User Example",
  "username": "Example",
  "email": "userexample@email.com.cl",
  "isActive": true,
  "createdDate": "2023-11-24",
  "modifyDate": null,
  "lastLogin": "2023-11-24",
  "phones": [
    {
      "numberPhone": "+54236598",
      "cityCode": "BUE",
      "countryCode": "AR"
    }
  ],
  "token": null
}
```

4. Operation `PUT /api/user/update`

Update user information

Ejecution

```
{
  "name": "User Example",
  "username": "Example",
  "email": "userexample@email.com.cl",
  "password": "example123456",
  "phones": [
    {
      "numberPhone": "+54236598",
      "cityCode": "BUE",
      "countryCode": "AR"
    }
  ]
}
```
Response
```
{
  "id": 1,
  "uuid": "b431991b-97f9-4b85-af61-f5a39464ab88",
  "name": "User Example",
  "username": "Example",
  "email": "userexample@email.com.cl",
  "isActive": true,
  "createdDate": "2023-11-24",
  "modifyDate": null,
  "lastLogin": "2023-11-24",
  "phones": [
    {
      "numberPhone": "+54236598",
      "cityCode": "BUE",
      "countryCode": "AR"
    }
  ],
  "token": null
}
```

5. Operación `GET /users/refreshToken`

Update user login token

Ejecution
```
curl -X 'GET' \
  'http://localhost:8085/api/user/refreshToken' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFeGFtcGxlIiwiaWF0IjoxNzAwODYxNTA2LCJleHAiOjE3MDA4NjUxMDZ9.7jXpU9PIuniKMZMrlPCU4nGbYNdaiPdvNOAQrN4-Ebs'
  
```
Ejecution
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFeGFtcGxlIiwiaWF0IjoxNzAwODYxNTIxLCJleHAiOjE3MDA4NjUxMjF9.IdpDcORX8tlVx04RWJCKhZzeuHNKOCgXEvAKt4MYGP4
```

6. Operación `DELETE /api/user/{username}`

Delete a username

Ejecution
```
curl -X 'DELETE' \
  'http://localhost:8085/api/user/Example' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFeGFtcGxlIiwiaWF0IjoxNzAwODYxMjQ3LCJleHAiOjE3MDA4NjQ4NDd9.qj91n954FwZy2pogvYvXa_5j0dxFNtpeWAIZ9eVHA34'
  
```
Ejecution
```
Example
```

# Unit Test

Please, use the command below

```
mvn clean package
```

Result Expected
```
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-jar-plugin:3.2.2:jar (default-jar) @ nisum-prj ---
[INFO] Building jar: D:\NisumCode\code\nisum-prj\target\nisum-prj-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot-maven-plugin:2.6.13:repackage (repackage) @ nisum-prj ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  28.312 s
[INFO] Finished at: 2023-11-24T14:29:46-03:00
[INFO] ------------------------------------------------------------------------

```