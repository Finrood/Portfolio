# Build stage
FROM node:22-alpine AS build
WORKDIR /app/portfolio-app
# Copy package files and install all dependencies (including devDependencies)
COPY portfolio-app/package*.json ./
RUN npm install --silent
# Copy source and build
COPY portfolio-app .
ARG NODE_ENV=production
ARG BUILD_TIMESTAMP
ENV NODE_ENV=${NODE_ENV}
RUN echo "Building with NODE_ENV=${NODE_ENV}"
RUN npm run build -- --configuration=${NODE_ENV}
# Copy built assets to /app/dist
RUN mkdir -p /app/dist && \
    if [ -d "dist/portfolio-app/browser" ]; then \
        cp -r dist/portfolio-app/browser/* /app/dist/; \
    elif [ -d "dist" ]; then \
        cp -r dist/* /app/dist/; \
    else \
        echo "Error: No build output found"; exit 1; \
    fi

# Final stage (minimal runtime to populate volume)
FROM alpine:3.20
WORKDIR /app
COPY --from=build /app/dist /app/dist
# Use a non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup && \
    chown -R appuser:appgroup /app/dist
USER appuser
# Keep container running to populate the volume
CMD ["tail", "-f", "/dev/null"]