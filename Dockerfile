FROM node:alpine as builder
WORKDIR '/app'
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx

<<<<<<< HEAD
COPY --from=builder /app/build /usr/share/nginx/html
EXPOSE 3000
=======
COPY --from=builder /app/dist/* /usr/share/nginx/html
EXPOSE 3000
>>>>>>> 570f629736a31fe5bb2efc37a6ea44ec77570ee7
