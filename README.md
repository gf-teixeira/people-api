Sistema para o gerenciamento de pessoas de uma empresa através de uma API REST, criada com o Spring Boot.

- (Cadastro, leitura, atualização e remoção de pessoas de um sistema).

---
Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

 Para visualizar a execução do projeto:

```
http://localhost:8080/api/v1/people
```

---
Rotas:
```
GET: /api/v1/people
POST: /api/v1/people
GET (By id): /api/v1/people/id
PUT (By id): /api/v1/people/id
DELETE (By id): /api/v1/people/id
```

```JSON
Person: {
 "id": number,
 "firstName": string,
 "lastName": string,
 "cpf": string,
 "birthDate": string,
 "phones": [
    {
       "id": number,
       "type": "MOBILE" | "HOME" | "COMMERCIAL",
       "number": string
    }
 ]
}
```
