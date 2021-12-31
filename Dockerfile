FROM openjdk:17
ADD target/desafio-jr-blog-1.0.jar desafio-jr-blog-1.0.jar
ENTRYPOINT ["java", "-jar","desafio-jr-blog-1.0.jar"]
EXPOSE 8080