FROM eclipse-temurin:21
WORKDIR /app

# Expose the backend port
EXPOSE 9090

# Copy the pre-built JAR file
COPY build/libs/searchflights-0.0.1-SNAPSHOT.jar /app/

# Copy the .env file
COPY .env /app/

# Command to run the application
ENTRYPOINT ["java", "-jar", "searchflights-0.0.1-SNAPSHOT.jar"]

