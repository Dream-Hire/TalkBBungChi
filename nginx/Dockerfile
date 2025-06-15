# 프론트엔드 빌드 전용 이미지
FROM node:20-alpine as builder
WORKDIR /app
COPY . .
RUN npm ci && npm run build

# nginx로 빌드 결과 서빙
FROM nginx:stable-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
