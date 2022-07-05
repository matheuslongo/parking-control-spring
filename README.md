# Parking-Controll-Spring
## Autor Matheus Macedo Longo

# Objetivo:
Controlar vagas de estacionamento para um condomínio, onde o micro serviço fornece para o administrador os seguintes recursos: cadastro de vagas, placas, carros, pessoas e apartamentos. Fazendo a associação desses atributos com a vaga onde o condômino ira utilizar, a api também fornece um método de pesquisa com paginação facilitando a busca pelos usuários que são cadastrados, também é bem importante lembrar que cada informação inserida é guardada em um banco de dados onde nada é perdido a não ser que o administrador da API delete ou altere  algumas informações de acordo com as necessidades do dia a dia.

# Protocolo HTTP:
Como está API é RESTFUL, ela tem como obrigação seguir o protocolo HTTP que define como deve ser feita uma requisição para uma aplicação,incluindo a URL, os parâmetros os cabeçalhos e o tipo de resposta esperado. Os serviços que seguem o protocolo HTTP devem ser configurados com um verbo, que indica qual é o comportamento esperado do serviço. Os verbos HTTP mais utilizados são:

### GET:
OS métodos GET devem recuperar dados, não afetando o estado da aplicação.

### POST:
Os métodos POST enviam dados para o servidor para serem processados. Os dados vão no corpo da requisição e não na URL. Normalmente são usados para criar novos recursos no servidor.

### PUT: 
Funciona de modo similar ao POST, mas deve ser utilizado para atualizar as informações no servidor e não para novos registros.

### DELETE:
Utilizado para excluir elementos do servidor.



