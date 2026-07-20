# Stage 1: Build
FROM node:22-alpine AS build
WORKDIR /app

# Copy package files
COPY portfolio-app/package*.json ./
RUN npm install

# Copy source
COPY portfolio-app/ .

# Build WITHOUT prerendering to save resources and avoid the timeout error
RUN npm run build -- --configuration=production --prerender false

# Stage 2: Serve
FROM nginx:1.27-alpine

# Remove default nginx HTML files to prevent conflicts
RUN rm -rf /usr/share/nginx/html/*

# Angular 17+ outputs to dist/[project-name]/browser
# Based on your logs, the folder is indeed portfolio-app
COPY --from=build /app/dist/portfolio-app/browser /usr/share/nginx/html

# Rename index.csr.html to index.html if it exists and index.html doesn't
RUN if [ -f /usr/share/nginx/html/index.csr.html ] && [ ! -f /usr/share/nginx/html/index.html ]; then \
        cp /usr/share/nginx/html/index.csr.html /usr/share/nginx/html/index.html; \
    fi

# Custom Nginx config to handle Angular routing (SPA)
RUN echo 'server { \
    listen 80; \
    location / { \
        root /usr/share/nginx/html; \
        try_files $uri $uri/ /index.html; \
    } \
}' > /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
