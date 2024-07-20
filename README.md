# Marvel HQs app

## Estrutura do projeto
O projeto foi realizado utilizando os conceitos de Clean Architecture. Também foi feito utilizando o padrão MVVM.
O seu intuito é realizar um login e recuperara da api forneceda os dados os mostrando em uma lista e possibilitando ao usuário favoritar itens dessa lista.
Os itens que são favoirtado devem ser salvos em um banco de dados local e recuperados em uma terceira tela específica para os favoritos.

### Telas

Para o desenvolvimento das telas foi utilizado o Jatpack Compose.

O Aplicativo tem 3 telas -> LoginScreen, ComicScreen e FavoriteScreen

O login é simulado por um mock de usuários fixos no aplicativo.


https://github.com/user-attachments/assets/5250a214-6cfe-46ca-a2fa-b840a5fd39a6

Para recuperar os dados foi utilizada a [api da marvel](https://developer.marvel.com/) utilizando-se o endpoint  /v1/public/comics  

## Como rodar o projeto

Para rodar esse código é necessário ter um cadastro [plataforma da api] e, uma vez baixado o código da branch main, deve-se colocar no arquivo local.properties, duas variávies PUBLIC_KEY e PRIVATE_KEY. Nessas variáveis deve-se colocar as chaves publica e privada respectivamente.

![image](https://github.com/user-attachments/assets/ed63ca68-aa24-48ad-bc00-723be8a926f8)

Isso é feito pois esse arquivo é local e, assim sendo as chaves não são expostas.

Após essa configuração, o projeto pode ser utilizado normalmente.

## Api

Como os dados da api são opicionais no endpoint existem dados que não estão em todos os objetos recuperados o qeu gera um certo desafio no design.
Também o timeout das requisições teve que ser aumentado devido a ocilação de tempo para realizar uma requisição na api.



