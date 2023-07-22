# Define a imagem base com o OpenJDK 17 e Maven instalado
FROM maven:3.8.3-openjdk-17-slim AS builder

# Copia os arquivos de configuração do Maven para utilizar o cache de dependências
COPY pom.xml /app/
WORKDIR /app

# Baixa as dependências do Maven (aqui, apenas as dependências serão baixadas, o código-fonte não)
RUN mvn dependency:go-offline

# Copia todo o código-fonte da aplicação
COPY src /app/src

# Realiza o build da aplicação
RUN mvn package -DskipTests

# Define a imagem base, normalmente uma imagem oficial do OpenJDK ou do AdoptOpenJDK é recomendada para Java
FROM adoptopenjdk:11-jre-hotspot

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR da aplicação do estágio de build para o contêiner
COPY --from=builder /app/target/dslist-0.0.1-SNAPSHOT.jar /app/dslist.jar

# Define a porta que será exposta pela aplicação (ajuste se sua aplicação usa uma porta diferente)
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for executado
CMD ["java", "-jar", "dslist.jar"]
