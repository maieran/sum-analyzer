FROM node:20
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --configuration=production
RUN npm install -g http-server
EXPOSE 4200
CMD ["http-server", "dist/frontend/browser", "-p", "4200", "--host", "0.0.0.0", "-c-1", "-d", "false"]
