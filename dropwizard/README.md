# small dropwizard example

## Getting started

#### Build
```
mvn clean package
```

#### Starting
```
java -jar target/dropwizard-example.jar server /service.yml
```

After the application has started, you can start sending requests:
```
curl http://localhost:8080/hello
curl http://localhost:8080/hello?name=Sonic
``` 
