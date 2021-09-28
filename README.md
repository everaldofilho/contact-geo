# Contatos por GEO


## Estrutura de Arquivos dentro do framework

- controller
- resource
- dto
- entity
- repository
- service
- exception

## Recursos/Ferramentas

- MongoDB
- SpringBoot
- Kafka (V2)
- Redis (V2)


## Rotas iniciais


POST /api/contacts
````json
{
    "name": "basss",
    "email": "exemplo@gmail.com",
    "cep": "1093313-212"
}
````

GET /api/contacts
````json
[
    {
        "name": "basss",
        "foto": "https://www.gravatar.com/avatar/{$hash}",
        "endereco": "rua azul n 32",
        "status": "processing|processed|invalid",
        "geo": {
            "lat": "-131.43113",
            "long": "-131.43113"
        },
        "email": "exemplo@gmail.com",
        "cep": "1093313-212"
    }
]
````

GET /api/contacts/{id} (Opcional)
````json
{
    "name": "basss",
    "foto": "https://www.gravatar.com/avatar/{$hash}",
    "endereco": "rua azul n 32",
    "status": "processing|processed|invalid",
    "geo": {
        "lat": "-131.43113",
        "long": "-131.43113"
    },
    "email": "exemplo@gmail.com",
    "cep": "1093313-212"
}
````