# Teste API Transação

Feito em Java e Spring Framework

## Iniciar

```shell script
mvnw spring-boot:run
```

## Parâmetros

* `id`: `long`
* `date`: `LocalDate`, "dd/MM/yyyy"
* `time`: `LocalTime`, "HH:mm:ss"
* `value`: `BigDecimal`
* `cardApplication`: `Enum` CardApplication('DEBITO', 'CREDITO', 'VOUCHER')
* `status`: `Enum` PaymentStatus('SUCCESS', 'PENDING', 'CANCELED', 'FAILED')

## Rotas

### Criar Transação
`POST /transaction`

__Payload__
```json
{
  "date": "01/05/2020",
  "time": "13:55:33",
  "value": 10.99,
  "cardApplication": "CREDITO",
  "status": "PENDING"
}
```

### Listar Transações
`GET /transaction/all`

__Response__
```json
[
    {
      "id": 0,
      "date": "01/05/2020",
      "time": "13:55:33",
      "value": 10.99,
      "cardApplication": "CREDITO",
      "status": "PENDING"
    }
]
```


### Atualizar Transação
`PUT /transaction`

__Payload__
```json
{
  "id": 0,
  "date": "01/05/2020",
  "time": "13:56:00",
  "value": 10.99,
  "cardApplication": "CREDITO",
  "status": "SUCCESS"
}
```
