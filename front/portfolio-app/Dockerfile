# Stage 1: Build the application
FROM node:latest as build
WORKDIR /app
COPY package*.json ./
RUN npm install
RUN npm ci
RUN npm install -g @angular/cli
COPY . .
ARG NODE_ENV=production  # Default to production
ARG BUILD_TIMESTAMP
RUN echo "NODE_ENV is set to $NODE_ENV"  # Print NODE_ENV
RUN ng build --configuration=$NODE_ENV

# Stage 2: Serve the application with NGINX
FROM nginx:latest

# Install Certbot
RUN apt-get update && \
    apt-get install -y certbot python3-certbot-nginx

# Remove default NGINX configuration
RUN rm /etc/nginx/conf.d/default.conf

# Copy NGINX configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Copy Certbot renewal configuration
COPY certbot-renew /etc/cron.daily/certbot-renew

# Expose ports
EXPOSE 80
EXPOSE 443

# Copy built application files
COPY --from=build /app/dist/portfolio-app/browser /usr/share/nginx/html
COPY --from=build /app/dist/portfolio-app/server /usr/share/nginx/html

# Start NGINX and run Certbot for HTTPS
CMD service nginx start && certbot -v --nginx --agree-tos --no-eff-email -m contact@samuelpetre.com -d samuelpetre.com -d www.samuelpetre.com && service nginx reload
