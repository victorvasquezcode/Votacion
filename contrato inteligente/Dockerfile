# Usa una imagen base de Node.js
FROM node:14

# Instala Truffle y Ganache CLI globalmente
RUN npm install -g truffle ganache-cli

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo de configuración de Truffle
COPY . .

# Expone el puerto de Ganache
EXPOSE 8545

# Ejecuta Ganache CLI
CMD ["ganache-cli", "--host", "0.0.0.0"]