## Prerequire
1. Docker Desktop
2. Java 17
3. Maven build tool
4. [Producer application](https://github.com/jimmy706/file-aggregate-producer) up and running

## Initial set up
To start this producer application on your local machine, run this command: `./mvnw spring-boot:run`

## How to test
You can upload the sales file using this API request and see the message sending to this consumer through kafka producer:
```curl
curl --location 'http://localhost:8081/sales-files' \
--header 'Cookie: csrftoken=yprQAO8C3jnb3FoC0DC355KahDDiFd5i' \
--form 'file=@"/C:/Users/dungdang/Downloads/sales.csv"'
```
![Example Message](/documents/example-message.png "Example Message")
