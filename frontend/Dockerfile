# Usa una imagen base de Node.js para construir la aplicación
FROM node:14 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de package.json y package-lock.json
COPY package*.json ./

# Instala las dependencias
RUN npm install

# Copia el resto de los archivos del proyecto
COPY . .

# Establece la variable de entorno para el backend
ENV REACT_APP_BACKEND_URL=http://localhost:8080/api

# Construye la aplicación
RUN npm run build

# Usa una imagen base de Nginx para servir la aplicación construida
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE 80

# Inicia Nginx
CMD ["nginx", "-g", "daemon off;"]
