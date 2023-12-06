# Medication Management - DEVinPharmacy LTDA

### Medication Management é uma API REST voltada para a gestão de farmácias, medicamentos e estoques. Permite o controle de entradas e saídas do estoque, cadastro de farmácias e medicamentos, assim como a listagem dos mesmos.

## Tecnologias

### Java 17, Spring Boot 3.2.0, Maven 3.9

## Rodando o projeto

### Pré-requisito para rodar o projeto é ter instalado o **<a href="https://www.oracle.com/java/technologies/downloads/" target="_blank">Java 17</a>** e <a href="https://maven.apache.org/download.cgi" target="_blank">Maven 3.9</a>
### Após o clone do projeto, abra o terminal na raiz da pasta e digite:
``` shell
./mvnw clean package
```
>O comando acima irá limpar o projeto recriando o diretório "target", compilará o fonte, executará testes e empacotará o código compilado em um formato distribuível (.jar)

``` shell
./mvnw spring-boot:run
```
>O comando acima irá executar a aplicação por padrão na porta 8080 (localhost:8080)

## Endpoints
### A documentação dos endpoints é feita por meio do Swagger. 
>Após aplicação em execução, acesse o Swagger através do seguinte endereço: ${URL_PADRAO}/swagger-ui/index.html.

## Melhorias futuras:
### Desenvolver:
* Segurança (Security e JWT)
* Testes

### Sugestões / Troca de ideias: <a href="https://instagram.com/marcelo_junqueira_/" target="_blank">Me chame no IG</a>
<br>


> Desenvolvido por: Marcelo Teixeira