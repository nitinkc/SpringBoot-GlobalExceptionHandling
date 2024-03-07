# SpringBoot-GlobalExceptionHandling


```shell
curl --location 'localhost:8089/api/word' \
--header 'Content-Type: application/json' \
--data '{
    "word":"discover",
    "max":"5"
}'
```


Upload a pic to test via postman form-data uploaded as a file

```shell
curl --location 'localhost:8089/api/upload' \
--form 'file=@"/Users/nichaurasia/Programming/SpringBootProjects/SpringBoot-GlobalExceptionHandling/src/main/resources/pic.png"'
```
![form-data.png](src%2Fmain%2Fresources%2Fform-data.png)