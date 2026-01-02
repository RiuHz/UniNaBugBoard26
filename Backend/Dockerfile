# ----- Dipendenza Maven -----
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia solo i file necessari per scaricare le dipendenze Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia il codice sorgente e builda l'applicazione
COPY src ./src
RUN mvn clean package -DskipTests

# ----- STAGE 2: App -----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia il jar dal primo stage
COPY --from=build /app/target/UniNaBugBoard26-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta usata da Spring Boot
EXPOSE 8080

# Avvio applicazione all'avvio del container
ENTRYPOINT ["java", "-jar", "app.jar"]
