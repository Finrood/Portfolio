# Stage 1: Build the application
FROM node:20 as build

WORKDIR /app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install --silent

# Copy application code
COPY . .

# Build the application
ARG NODE_ENV=production
ARG BUILD_TIMESTAMP
RUN echo "NODE_ENV is set to $NODE_ENV"  # Print NODE_ENV
RUN npm run build -- --configuration=$NODE_ENV

# Stage 2: Serve the application with NGINX
FROM nginx:alpine

# Copy NGINX configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy built files from the previous stage
COPY --from=build /app/dist/portfolio-app/browser /usr/share/nginx/html

EXPOSE 80
EXPOSE 443

# Start NGINX
CMD ["nginx", "-g", "daemon off;"]
