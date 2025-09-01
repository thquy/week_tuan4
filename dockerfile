# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM tomcat:10.1-jdk17

# Xóa ROOT mặc định
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy WAR vào ROOT.war
COPY --from=build /app/target/Bai_tap-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Render sẽ cấp PORT động
EXPOSE 8080

# Thay 8080 bằng $PORT và chạy Tomcat
CMD ["catalina.sh","run"]
