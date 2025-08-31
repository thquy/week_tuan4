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
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Render sẽ cung cấp biến PORT
ENV PORT=8080
EXPOSE ${PORT}

# Chạy Tomcat và thay cổng mặc định bằng $PORT
CMD ["sh", "-c", "sed -i \"s/8080/${PORT}/\" /usr/local/tomcat/conf/server.xml && catalina.sh run"]
