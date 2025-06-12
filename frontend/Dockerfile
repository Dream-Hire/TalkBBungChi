# 1단계: 빌드
FROM node:20-alpine as builder
WORKDIR /app
COPY . .
RUN npm ci && npm run build

# 2단계: Nginx로 정적 파일 서빙
FROM nginx:stable-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
