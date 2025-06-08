FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/hotelmanagement-0.0.1-SNAPSHOT.jar app.jar

COPY params.env .
COPY uploads ./uploads

RUN apt-get update && apt-get install -y tzdata \
  && ln -fs /usr/share/zoneinfo/Asia/Ho_Chi_Minh /etc/localtime \
  && dpkg-reconfigure -f noninteractive tzdata

ENV TZ=Asia/Ho_Chi_Minh

# Mở port
EXPOSE 8080

# Khởi động app
ENTRYPOINT ["java", "-jar", "app.jar"]
