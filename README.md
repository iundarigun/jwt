# jwt
Repository to test spring-boot and security with JWT

## MySQL

Create a new docker instance of mysql with name local-mysql, listen in 3306 port (once)

```
$ docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --name local-mysql -d mysql
```

Connect and create the database

```
$ docker exec -it local-mysql mysql -uroot -proot
> create database jwt;
```

Start container next time:
```
$ docker start local-mysql
```

## References
[1] https://www.djamware.com/post/5c819d0180aca754f7a9d1ee/securing-restful-api-with-spring-boot-security-and-data-mongodb
[2] http://andreybleme.com/2017-04-01/autenticacao-com-jwt-no-spring-boot/