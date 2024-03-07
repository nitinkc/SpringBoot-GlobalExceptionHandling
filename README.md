# SpringBoot-GlobalExceptionHandling

(https://nitinkc.github.io/spring/microservices/spring-exceptions/)[https://nitinkc.github.io/spring/microservices/spring-exceptions/]
```shell
curl --location 'localhost:8089/api/word' \
--header 'Content-Type: application/json' \
--data '{
    "word":"discover",
    "max":"5"
}'
```

Invoke the number format exception

```shell
curl --location 'localhost:8089/api/word' \
--header 'Content-Type: application/json' \
--data '{
    "word":"discover",
    "max":"word"
}'
```

# Validation Exceptions

For the Request body

```java
@Data
public class MyRequestDTO {
    @NotNull
    private String max;

    @NotNull
    @Size(min = 3, max = 10)
    private String word;
}
```

```java
@PostMapping(path = "/word")
    public ResponseEntity<List<String>> runController(@RequestBody @Valid MyRequestDTO request) {
}
```

MethodArgumentNotValidException from the Jakarta Validation

- `ConstraintViolationException`
- `MethodArgumentNotValidException`

```shell
curl --location 'localhost:8089/api/word' \
--header 'Content-Type: application/json' \
--data '{
    "word":"tt",
    "max":"five"
}'
```

```json5
{
    "from": "Exception Response :: MethodArgumentNotValidException",
    "errorCode": "140 :: Error: :: Validation Error",
    "errorMessage": "{word=size must be between 3 and 10}",
    "methodName": "POST",
    "requestedURI": "/api/word",
    "thrownByMethod": "resolveArgument",
    "thrownByClass": "org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor",
    "exceptionType": null,
    "timestamp": "2024-03-06 23:51:08.663 PM MST(GMT-7)"
}
```

# BadInputException

```java
if(!StringUtils.isNumeric(max)){
            throw new BadInputException("String is not a Number");
        }
```
```shell
curl --location 'localhost:8089/api/word' \
--header 'Content-Type: application/json' \
--data '{
    "word":"12345",
    "max":"five"
}'
```

```json5
{
    "from": "From Exception Response :: handleInputExceptions",
    "errorCode": "140 :: Error: :: Validation Error",
    "errorMessage": "String is not a Number",
    "methodName": "POST",
    "requestedURI": "/api/word",
    "thrownByMethod": "runController",
    "thrownByClass": "com.spring.GlobalExceptionHandling.controller.SimpleController",
    "exceptionType": "BadInputException",
    "timestamp": "2024-03-06 23:55:18.036 PM MST(GMT-7)"
}
```

When there is data, either an empty response can be sent or no data found exception can be thrown based on business use case

```java
List<String> data = service.getData(request.getWord(), max);
    if (data.isEmpty()){
        throw new WordsNotFoundException("No Words found");
    }
```

```shell
curl --location 'localhost:8089/api/word' \
--header 'Content-Type: application/json' \
--data '{
    "word":"12345",
    "max":"5"
}'
```

```json5
{
    "from": "From Traceable Error",
    "path": "/api/word",
    "timestamp": "2024-03-07 00:01:50.683 AM MST(GMT-7)",
    "errorCode": "123",
    "errorDescription": "ERROR: DB is down",
    "exceptionType": "WordsNotFoundException",
    "statusCode": "UNAVAILABLE_FOR_LEGAL_REASONS",
    "thrownByMethod": "runController",
    "thrownByClass": "com.spring.GlobalExceptionHandling.controller.SimpleController",
    "exceptionMessage": "No Words found",
    "errorTitle": "124 :: Error: :: Some Business Exception"
}
```
