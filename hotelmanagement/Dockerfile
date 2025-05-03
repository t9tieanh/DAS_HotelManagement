FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

COPY --from=builder /app/target/hotelmanagement-0.0.1-SNAPSHOT.jar app.jar

COPY params.env /app/params.env


# 6.5. Cài tzdata và set timezone cho container
RUN apt-get update && apt-get install -y tzdata \
  && ln -fs /usr/share/zoneinfo/Asia/Ho_Chi_Minh /etc/localtime \
  && dpkg-reconfigure -f noninteractive tzdata

ENV TZ=Asia/Ho_Chi_Minh


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
