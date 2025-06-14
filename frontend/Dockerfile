FROM node:20-alpine as builder
WORKDIR /app

# Á¾¼Ó¼º Ä³½Ì
COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

FROM nginx:stable-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
COPY ./nginx/nginx.conf /etc/nginx/conf.d/default.conf
