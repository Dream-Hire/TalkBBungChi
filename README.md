# TalkBBungChi - Docker 구성

## 구성 요소
- frontend: React/Vite 기반 SPA
- nginx: frontend 정적 파일을 빌드 후 서빙
- backend: Spring Boot
- mysql, mongo, jenkins 포함

## 실행 방법

```bash
# 루트 위치에서 실행
docker compose up --build
```

## 구조 설명

- frontend에서 빌드된 결과물을 nginx가 받아 `/` 경로로 서빙
- /api → backend:8080
- /jenkins → jenkins:8080/jenkins
