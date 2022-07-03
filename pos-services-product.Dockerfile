FROM gradle:jdk11-alpine AS build

WORKDIR /app
COPY . /app/

RUN gradle :pos-services-product:bootJar


FROM eclipse-temurin:11-alpine

WORKDIR /app
COPY --from=build /app/pos-services-product/build/libs/*.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/pos-services-product-0.0.1-SNAPSHOT.jar"]
