FROM node:20-alpine as frontend-builder
WORKDIR /app
COPY frontend/ .
RUN npm ci && npm run build

FROM nginx:stable-alpine
COPY --from=frontend-builder /app/dist /usr/share/nginx/html
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf