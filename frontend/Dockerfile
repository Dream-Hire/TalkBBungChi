FROM node:20-alpine as builder
WORKDIR /app
COPY . .
RUN npm ci && npm run build