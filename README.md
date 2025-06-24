Desafio 6
Projeto do curso 3035Teach, Módulo 6 (Back-End).


🚀 Tecnologias Utilizadas
Java 21v
Spring Boot
Spring Security
Swagger
PostgreSQL
JWT


📁 Como inciar
Faça a criação do Banco de Dados usando o PgAdmin 4 e PostGreSql com o nome de 'todo-teach'.

Altere no Arquivo application.yml: url: jdbc:postgresql://localhost:5432/todo-teach //Se seu banco se encontra com LocalHost diferente;
username: postgres //se seu nome de usuário for diferente;
password: root //se sua senha for diferente de root.

Abra o arquivo do Código com o IntelliJ usando o arquivo pom.xml;
O IntelliJ detecta o arquivo SecurityApplication como executável.

Após inciar use o Swagger com o link http://localhost:8080/swagger-ui.html.


⚠️ Observações
O Código já inicia as tabelas se elas ainda não foram criadas (não crie elas manualmente).

Se for usar o PostMan, vai precisar dos links abaixo para criar as requests:

Create User: http://localhost:8080/users/register
Passar o arquivo JSON assim:
{
    "username": "test3",
    "password": "test",
    "role": "USER"
}

Login: http://localhost:8080/users/login
Passar o arquivo JSON assim:
{
    "username": "test321",
    "password": "test"
}


⚠️ Todos os REQUESTS abaixo precisaram de um TOKEN JWT para que seja possível enviá-las ao banco.

Create Task: http://localhost:8080/tasks
Passar o arquivo JSON assim:
{
  "titulo": "test232341",
  "descricao": "test1",
  "status": "PENDENTE"
}

Delete Task: http://localhost:8080/tasks/10 - ID da task que quer excluir

Modify Task: http://localhost:8080/tasks/11 - ID da task que quer modificar
Passar o arquivo JSON assim:
{
  "descricao": "modificado",
  "status": "EM_ANDAMENTO"
}

Get All Tasks: http://localhost:8080/tasks

Get Task by ID: http://localhost:8080/tasks/5 - ID da task que quer buscar
