1. Reto técnico:
- docker-compose up
- docker-compose down
- docker-compose ps
- docker-compose logs
- docker build -t tcambio . -f ./tipocambio/Dockerfile
- docker run -p 8034:8034 --rm -d --name tcambio tcambio

- api rest para obtener tipo de cambio: https://open.er-api.com/v6/latest/USD
