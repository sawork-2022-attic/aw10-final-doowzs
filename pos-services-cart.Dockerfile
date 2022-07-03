FROM gradle:jdk11-alpine AS build

WORKDIR /app
COPY . /app/

RUN gradle :pos-services-cart:bootJar


FROM eclipse-temurin:11-alpine

WORKDIR /app
COPY --from=build /app/pos-services-cart/build/libs/*.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/pos-services-cart-0.0.1-SNAPSHOT.jar"]
