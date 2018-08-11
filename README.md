As configurações feitas no projeto utilizaram as configurações padrões de conexão com o banco MySql.

//CRIAÇÃO DO BANCO--------------------------------------------------------------------------------------------------------------
Executar no banco de dados, o arquivo db_estrutura.sql que esta dentro da pasta bd.

Caso necessário também é possível executar o arquivo db_dados.sql que contém alguns registros de teste.

//------------------------------------------------------------------------------------------------------------------------------


//INSTRUÇÃO PARA CONECTAR NO BANCO----------------------------------------------------------------------------------------------

Acessar o arquivo ConexaoMySQL.java (caminho: desafio_restful\src\java\br\teste\servicos)

Caso seja necessário, mudar o conteúdo da variável "banco" para as configurações do respectivo banco de dados que será utilizado para executar a aplicação.

Alterar as variáveis "usuario" e "senha" - 
Na variável "usuario" colocar o nome de usuário que fará o acesso ao banco de dados.
Na variável "senha" colocar a senha desse usuário que fará o acesso ao banco de dados. 
//------------------------------------------------------------------------------------------------------------------------------


//Após essas configurações o sistema esta apto a ser executado.



//URLS PARA CHAMADA-------------------------------------------------------------------------------------------------------------

POST = /webresources/user/inserir
{
	"name": "Nome Completo do Usuário",
    "login": "Email do Usuário",
    "pass": "Senha do Usuário"
}

GET = /webresources/user/buscar/{id}

PATCH = /webresources/user/alterar/{id}
{
	"name": "Nome Completo do Usuário",
    "login": "Email do Usuário",
    "pass": "Senha do Usuário"
}

DELETE = /webresources/user/excluir/{id}
