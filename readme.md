# Kafka Spring Boot - Producer & Consumer

This Service will Consume the Kafka Messages (successful from First Service), and will validate them and then will move
it to next service - If it's Valid. Else Will make an Error entry in Redis.

I have used a simple random Error Generator Function ( based on the Order ID's - if it is a Multiple of 17)

Sample JSON Message :

```json
{
  "id": 1,
  "first_name": "Elaine",
  "last_name": "Dallosso",
  "email": "edallosso0@networkadvertising.org",
  "product": "Soup Campbells Beef With Veg",
  "address": "575 Portage Hill",
  "phone_number": "659-993-4818",
  "count": 17,
  "cost": 21.38,
  "currency": "USD",
  "orderId": "cae1cf7e-0cb8-426e-a3dd-955b86866fba"
}
```
