# Step 1: Gradle을 사용하여 JAR 파일 빌드
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Gradle 캐시를 살리기 위해 의존성 관련 파일만 먼저 복사
COPY app/build.gradle app/settings.gradle ./
RUN gradle build --no-daemon -x test || return 0

# 소스 코드 복사
COPY app/ .

# 실제 빌드
RUN gradle clean build -x test --no-daemon

# Step 2: 경량 실행용 컨테이너
FROM openjdk:17-jdk-slim
WORKDIR /app

# wait-for-it.sh 다운로드 및 권한 설정
RUN apt-get update && apt-get install -y --no-install-recommends curl tzdata \
    && curl -o wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x wait-for-it.sh \
    && ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime \
    && echo "Asia/Seoul" > /etc/timezone \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# 환경변수: JVM 시간대 설정
ENV JAVA_OPTS="-Duser.timezone=Asia/Seoul"

# JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# wait-for-it 실행 후 Spring Boot 앱 시작
ENTRYPOINT ["sh", "-c", "./wait-for-it.sh database:3306 --timeout=60 --strict -- java $JAVA_OPTS -jar app.jar"]
