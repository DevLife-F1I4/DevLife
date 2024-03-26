#스프링 부트 프로젝트 빌드 및 jar 파일 생성
FROM openjdk:17-jdk-slim as builder
COPY gradlew .

# 실제 파일을 이미지에 복사
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
# 프로젝트 루트 디렉토리에서 아래 명령어를 실행하면 ./build/libs 디렉토리에 jar 실행파일 생성
RUN ./gradlew bootjar



#builder에서 생성된 jar파일 복사 및 실행
FROM openjdk:17-jre-slim
# heurit-refactoring-0.0.1-SNAPSHOT.jar 이라는 이름으로 현재 이미지에 저장한다.
COPY --from=builder build/libs/*.jar app.jar
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]