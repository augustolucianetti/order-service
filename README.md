# order-service

1. Salvar order: 
  URL: http://localhost:8080/save
  Método: POST
  Exemplo de request: 
  {
    "id": 4,
    "email" : "jessica@gmail.com",
    "completeName": "Jessica",
    "address": "Rua 123",
    "priceItems":[
    	123456,
    	123456
    ],
    "payment" : {
    	"type" : "cartao",
    	"transactionId" : "1",
    	"cardNumber" : 123456,
    	"validateDateCard" : "1",
    	"flag" : "elo"
    },
    "totalPaymentPrice": "1234",
    "date": "19/12/1990",
    "status": "teste",
    "quantity" : "2"
}

2. Buscar por id:
  URL: http://localhost:8080/findById/{id}
  Método: GET

3. Atualizar dados:
  URL: http://localhost:8080/update/{id}
  Método: PUT
  Exemplo de request: 
  {
    "email" : "jessica@gmail.com",
    "completeName": "Jessicaa",
    "address": "Rua 123",
    "priceItems":[
    	123456,
    	123456,
    	654321
    ],
    "payment" : {
    	"type" : "cartao2",
    	"transactionId" : "1",
    	"cardNumber" : 123456,
    	"validateDateCard" : "1",
    	"flag" : "elo"
    },
    "totalPaymentPrice": "1234",
    "date": "19/12/1990",
    "status": "teste",
    "quantity" : "3"
}

4. Excluir registro:
  URL: http://localhost:8080/delete/{id}
  Método: DELETE
