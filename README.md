# **Força Solidária API**

API RESTful desenvolvida com Quarkus para gerenciar cadastros de voluntários, login, solicitações de ajuda e geração de relatórios. Este sistema visa conectar voluntários a diversas necessidades da comunidade, facilitando o apoio e a organização de iniciativas solidárias.

## **Visão Geral**

O projeto Força Solidária permite que:

* **Voluntários** se cadastrem e façam login na plataforma.
* **Usuários** (aqueles que necessitam de ajuda ou conhecem quem precisa) criem solicitações de diversos tipos (alimentação, moradia, transporte, etc.).
* A plataforma liste as **solicitações pendentes** para que voluntários possam atendê-las.
* Gere **relatórios** sobre o status e as categorias das solicitações.

## **Tecnologias Utilizadas**

* **Framework Principal:** Quarkus (Versão: 3.22.3)
* **Linguagem de Programação:** Java (Versão do Compilador: 21)
* **Gerenciador de Dependências:** Apache Maven
* **APIs Web:** Jakarta REST (JAX-RS) para endpoints RESTful
* **Serialização/Deserialização JSON:** Jackson (integrado com Quarkus REST)
* **Validação de Dados:** Hibernate Validator
* **Acesso a Banco de Dados:** JDBC
    * **Driver Específico:** Oracle JDBC Driver (ojdbc10)
* **Gerenciamento de Configuração Externa:** `dotenv-java` para carregar variáveis de ambiente de um arquivo `.env`.
* **Injeção de Dependência:** Quarkus Arc (Implementação CDI)

## **Pré-requisitos**

Para executar este projeto, você precisará ter instalado:

* **Java Development Kit (JDK):** Versão 21 ou superior.
* **Apache Maven:** Versão 3.6.2 ou superior.
* **Banco de Dados Oracle:** Uma instância do Oracle Database acessível (Ex: Oracle XE, Oracle Cloud Free Tier, etc.).
* **Arquivo de Configuração de Ambiente:** Um arquivo chamado `enviromentfile.env` na raiz do projeto.

## **Configuração do Projeto**

### **1. Clone o Repositório:**
```bash
git clone github.com/JoaoVictor087/forcasolidariajava
cd forcasolidariajava
```

### **2. Crie o Arquivo enviromentfile.env:**
Na raiz do seu projeto, crie um arquivo chamado enviromentfile.env. Este arquivo é usado pela classe DatabaseConfig.java para carregar as credenciais e a URL do banco de dados.
Adicione o seguinte conteúdo, substituindo pelos seus dados de acesso ao Oracle:

DATABASE_USER=seu_usuario_oracle
DATABASE_PASSWORD=sua_senha_oracle
DATABASE_URL=jdbc:oracle:thin:@seu_host_oracle:sua_porta:seu_sid_ou_service_name

### **3. Estrutura do Banco de Dados:**
Este projeto interage com as seguintes tabelas principais no Oracle:

T_FS_VOLUNTARIO: Armazena dados dos voluntários (para login e cadastro).

T_FS_USUARIO: Armazena dados dos usuários que criam solicitações.

T_FS_SOLICITACAO: Armazena os detalhes das solicitações de ajuda, incluindo status, categoria, zona e descrição.

(Implícito, mas não diretamente gerenciado por repositórios separados: tabelas para Categorias e Zonas, cujos IDs são usados em T_FS_SOLICITACAO).


### **Executando a Aplicação**
Modo de Desenvolvimento
Para executar a aplicação em modo de desenvolvimento, o que habilita o live coding:
```
./mvnw quarkus:dev
```
A aplicação estará acessível em http://localhost:8080.


# **Endpoints da API**

A API expõe os seguintes endpoints:

# Recursos de Login e Cadastro de Voluntários (/user)

## POST /user/cadastro
Descrição: Cadastra um novo voluntário no sistema.

Consumes: application/json

Request Body: CadastroDTO
```
{
  "email": "voluntario.novo@example.com",
  "senha": "umaSenhaForte123",
  "cpf": "111.222.333-44",
  "dt_nascimento_voluntario": "1995-08-22",
  "gen_voluntario": "N",
  "nm_voluntario": "Nome Completo do Novo Voluntario"
}
```

Produces: text/plain

Respostas:

201 CREATED: "Usuário cadastrado com sucesso"

400 BAD_REQUEST: "Há algum campo obrigatório vazio" ou devido a falhas de validação do @Valid.

500 INTERNAL_SERVER_ERROR: "Não foi possível se conectar ao banco de dados" ou "Erro interno do servidor". 

POST /user/login
Descrição: Autentica um voluntário existente.

Consumes: application/json

Request Body: LoginRequestDTO
```
{
  "email": "voluntario.novo@example.com",
  "senha": "umaSenhaForte123"
}
```
Produces: application/json

Respostas:

200 OK: LoginReponseDTO
```
{
  "logado": true,
  "mensagem": "Usuário logado com sucesso"
}
```
401 UNAUTHORIZED: LoginReponseDTO
```
{
  "logado": false,
  "mensagem": "email e/ou senha do usuário inválido"
}
```
500 INTERNAL_SERVER_ERROR: LoginReponseDTO 

#  Recursos de Solicitação (/Solicitacao)

## POST /Solicitacao/criarSolicitacao

Descrição: Permite que um usuário crie uma nova solicitação de ajuda.

Consumes: application/json

Request Body: SolicitacaoRequestDTO
```
{
  "dsc": "Preciso de ajuda com cestas básicas para minha família de 4 pessoas.",
  "titulo": "Ajuda com Alimentação Urgente",
  "nm_usuario": "Maria Silva",
  "gen_usuario": "F",
  "dt_nascimento_usuario": "1980-05-10",
  "nm_categoria": "Alimentação",
  "zona": "Zona Leste",
  "endereco": "Rua das Palmeiras, 10, Vila Esperança, São Paulo - SP"
}
```
Produces: text/plain

Respostas:

201 CREATED: "Solicitação enviada com sucesso"

500 INTERNAL_SERVER_ERROR: "Erro ao conectar no banco de dados" ou "Erro interno do servidor".

## GET /Solicitacao/GetSolicitacoes
Descrição: Retorna uma lista de todas as solicitações que estão com o status "pendente".

Produces: application/json

Respostas:

200 OK: GetSolicitacoesDTO
```
{
  "solicitacoes": [
    {
      "dsc": "Preciso de ajuda com cestas básicas para minha família de 4 pessoas.",
      "titulo": "Ajuda com Alimentação Urgente",
      "status": "pendente",
      "endereco": "Rua das Palmeiras, 10, Vila Esperança, São Paulo - SP",
      "nm_usuario": "Maria Silva",
      "categoria": null,
      "zona": "zona leste"
    }
  ]
}
```
500 INTERNAL_SERVER_ERROR: "Erro ao conectar no banco de dados" ou "Erro interno do servidor".

## GET /Solicitacao/relatorio
Descrição: Gera e retorna um relatório agregado sobre as solicitações.

Produces: application/json

Respostas:

200 OK: RelatorioResponseDTO
```
{
  "relatorio": {
    "totalPendente": 15,
    "qntAlimentacaoPendente": 5,
    "qntMoradiaPendente": 2,
    "qntTransportePendente": 1,
    "qntOrientacaoPendente": 3,
    "qntEmergenciasMedicasPendente": 1,
    "qntDesastresNaturaisPendente": 0,
    "qntOutrosPendente": 3,
    "totalConcluido": 25,
    "qntAlimentacaoConcluido": 10,
    "qntMoradiaConcluido": 4,
    "qntOutrosConcluido": 2
  }
}
```
500 INTERNAL_SERVER_ERROR: "Erro ao conectar no banco de dados" ou "Erro interno do servidor".

