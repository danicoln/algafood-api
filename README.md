# Curso da AlgaWorks

## Tecnologias utilizadas

![IntelliJ_IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

## Módulo 2 Spring e Injeção de Dependências

### 2.5 Criando um projeto Spring Boot com Spring Initializr

Versão do Spring Boot utilizado nas aulas é o 2.1.7.
Esta versão já está muito depreciada, sendo assim, mudei para a versão 2.7.17.

### 2.20 Mudando o comportamento da aplicação com Spring Profiles

Aula sobre o uso do Spring Profiles.
Nesta aula o professor ensina as formas de como utilizar o Spring Profiles. Eu não consegui fazer funcionar, então sigo nesta aula para verificar uma solução.
Provavelmente pode ser versão do spring boot.

#### Solução: 
O problema estava no service, o atributo "notificador" estava anotado com "@TipoDoNotificador(NivelUrgencia.URGENTE)", sendo assim no console mostrava apenas a entidade de SMS. Após a mudança para "@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)", o serviço funcionou corretamente.

### 2.21. Criando métodos de callback do ciclo de vida dos beans

Todos os beans tem um ciclo de vida. São as fases desde o surgimento do bean até onde ele deixa de existir no container.
Estes ciclos têm 3 fases: Inicialização do bean, fase de utilização e fase de destruição onde ele deixa de existir. 

Podemos implementar métodos de callback desses ciclos de vidas. Existe alguns meios de implementar esses métodos, conforme demonstrado em aula.
 
### Explanação do uso de callback:

Em Java, em vez de passar funções diretamente como callbacks, você normalmente usa interfaces funcionais e classes anônimas (ou lambdas, a partir do Java 8) para definir comportamentos personalizados que são executados em resposta a eventos ou condições específicas.

Aqui estão alguns cenários comuns em que você pode usar callbacks em Java no contexto do desenvolvimento do backend:

#### Tratamento de Requisições HTTP: 
Em um framework web como Spring ou Java Servlets, você pode definir callbacks para lidar com diferentes tipos de requisições HTTP, como GET, POST, ou outras, e especificar o que deve acontecer quando essas requisições ocorrem.

#### Tratamento de Eventos em Aplicativos Multithread: 
Em aplicativos multithread, você pode usar callbacks para lidar com eventos assíncronos, como conclusão de tarefas em segundo plano ou notificações entre threads.

#### Manipulação de Eventos de Banco de Dados: 
Quando ocorrem eventos no banco de dados, como inserções, atualizações ou exclusões de registros, você pode usar callbacks (por exemplo, "event listeners" em JPA/Hibernate) para reagir a essas mudanças.

#### Tratamento de Erros Personalizados: 
Você pode usar callbacks para lidar com exceções específicas em operações de banco de dados, requisições HTTP, ou outros cenários de erro.

#### Iteração em Coleções e Streams: 
Em Java 8 e versões posteriores, você pode usar lambdas como callbacks para iterar sobre coleções e realizar operações de filtragem, mapeamento e redução.

#### Tratamento de Eventos de Serviços Externos: 
Quando você integra serviços externos (por exemplo, APIs de terceiros), callbacks podem ser usados para lidar com eventos ou respostas desses serviços.

Em Java, os callbacks são implementados geralmente através de interfaces funcionais que definem um único método abstrato (conhecidas como interfaces funcionais) e, em seguida, você instancia essas interfaces com classes anônimas ou lambdas para fornecer a implementação do método.


### 2.23. Configurando projetos Spring Boot com o application.properties

[Documentação das propriedades comuns da aplicação (application.properties)](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

### 2.24. Substituindo propriedades via linha de comando e variáveis de ambiente

Um exemplo na linha de comando seria:

~~~
java -jar target/algafood-api-0.0.1-SNAPSHOT.jar --server.port=8082
~~~

Outra forma, definir uma variável de ambiente na linha de comando, no Linux / Mac por exemplo:

~~~
export SERVER_PORT=8083
~~~

Para saber se a variável de ambiente foi atribuída, verificar com o seguinte comando:

~~~
echo %SERVER_PORT
~~~

#### No Windows: 

~~~
set SERVER_PORT=8083
~~~

Para checar se definiu a variável de ambiente, inserir o seguinte comando:
~~~
echo %SERVER_PORT%
~~~


Para rodar o projeto, basta inserir o comando:

~~~
java -jar target/algafood-api-0.0.1-SNAPSHOT.jar
~~~

### 2.25. Criando e acessando propriedades customizadas com @Value


### 2.26. Acessando propriedades com @ConfigurationProperties

Quando um projeto começa a crescer e o ter muitas propriedades customizadas, pode ficar muito complexo ter que repetir mesmas injeções de valores em classes diferentes.

Por isso existe uma forma de organizar, criando uma classe que representa um grupo de configurações com essas propriedades.

⚠️ Analisar alterações: 

    ✅ NotificadorProperties
    ✅ NotificadorEmail
    ✅ application.properties

### 2.27. Alterando a configuração do projeto dependendo do ambiente (com Spring Profiles)

📌 Configurando ambientes com Spring Profiles.

⚠️Analisar alterações: 

    ✅ application.properties
    ✅ application-dev.properties
    ✅ application-prod.properties
    ✅ Em "Run" > "Edit Configuration..." > "Environment variables" adicionar a propriedade de um profile para ser ativado o ambiente, por exemplo: "spring.profiles.active=prod".

### 2.28. Ativando o Spring Profile por linha de comando e variável de ambiente

⚠️Analisar alterações: 

📌 Nesta aula, é dado um exemplo de como ativar o Spring Profile apenas por linha de comando e variável de ambiente. Primeiramente precisamos gerar o arquivo jar com o seguinte passo a passo:

✅ Primeiro necessário limpar o arquivo:
```
./mvnw clean
```

✅ Em seguida, construir o arquivo:
```
./mvnw package
```

Insere a propriedade:
![Alt text](images/image.png)

Com o programa rodando, confirmamos o ambiente ativo
![Alt text](images/image-1.png)

Ao chamar o serviço
![Alt text](images/image-3.png)

✅ Pela linha de comando, ativando por variável de ambiente (no Mac/Linux ao invés de set, colocar "export")

```
set SPRING_PROFILES_ACTIVE=dev
```

✅ Para consultar a variável de ambiente:

```
echo $SPRING_PROFILES_ACTIVE
```

## Módulo 3 - Introdução ao JPA e Hibernate

### 3.3. Adicionando JPA e configurando o Data Source

#### Nota para atualização de versão:

Este documento irá te auxiliar a fazer esta aula com a versão 2.7 do Spring Boot e suas dependências.

A partir da versão 2.7.12, é necessário adicionar a seguinte dependência do driver do MySQL no pom.xml:

```
<dependency>
      <groupId>com.mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
    </dependency>
```

[Documentação do driver JDBC do MySQL](https://dev.mysql.com/doc/connector-j/5.1/en/)


⚠️Analisar alterações: 

📌 Nesta aula, apagamos todas as classes de exemplos das aulas anteriores.

✅ Adicionado o Spring Data JPA;

✅ Configurado no application.properties os dados do banco de dados.


### 3.4. Mapeando entidades com JPA

⚠️ Analisar alterações: 

📌 Nesta aula, foi criada:

✅ Cozinha;

✅ Restaurante;

#### Diagrama de classe

![Diagrama](images/diagrama-1.png)

![Diagrama](images/diagrama-2.png)

### 3.5. Criando as tabelas do banco a partir das entidades

⚠️ Analisar alterações: 

📌 O que vimos nesta aula:

✅ Sobre as formas de geração das tabelas.

No ambiente de desenvolvimento, a forma mais comum de gerar as tabelas é criando automaticamente pela seguinte configuração no application.propierties:

```
spring.jpa.generate-ddl=true
```

Outra configuração, esta, específica do hibernate, define a forma que seja executado.
Nesta forma (create), dropa todas as tabelas e recria toda vez que reiniciar a aplicação (pode perder dados, recomendado para ambiente de desenvolvimento).

```
spring.jpa.hibernate.ddl-auto=create
```

Já no ambiente de produção, a forma de criar tabelas será demonstrada em outra aula.


### 3.6. Mapeando o id da entidade para autoincremento

📌 O que vimos nesta aula:

✅ Sobre o mapeamento com a anotação @GeneratedValue.

Nesta anotação, inserimos como parâmetro GenerationType.IDENTITY. Esta propriedade significa que estamos passando a responsabilidade de gerar o valor do identificador para o provedor de persistência, ou seja, para o banco de dados:

![cmd](images/3.6-describe-tab_cozinhas.png)


### 3.7. Importando dados de teste com import.sql

📌 O que vimos nesta aula:

✅ Sobre a inserção de dados via arquivo .sql no banco de dados.

Consultando os dados através do comando:

```
select * from cozinha;
```

### 3.15. Conhecendo e usando o Lombok

📌 Usando Lombok, uma biblioteca Java. 

[Projeto Lombok](https://projectlombok.org/)


### 3.16 Desafio: Lombok e repositório de restaurantes

Realizado o Desafio.

### 3.17. Mapeando relacionamento com @ManyToOne

📌 O que vimos nesta aula:

✅ Mapeamento do relacionamento @ManyToOne na entidade Restaurante, onde a leitura correta é "MUITOS restaurantes contém UMA Cozinha". 

✅ A configuração do dialeto do banco de dados, fazendo uma consulta através do buscador da IDE, inserindo o nome do banco de dados, no nosso caso Mysql, para consultar o pacote e o nome da classe.

✅ Com os dados, configuração no application.properties:

```
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
```

Esta configuração serve para que o hibernate use o dialeto do MySQL.

✅ Alteração no import.sql.

✅ Testando mudanças através da classe BuscaRestauranteMain.

### 3.20. Desafio: mapeando entidades

#### Diagrama
<img src="https://raw.githubusercontent.com/algaworks/curso-especialista-spring-rest/master/ESR%20-%20Diagrama%20de%20classes.png">

## REST COM SPRING

### 4.1. O que é REST?

API REST, também chamada de API RESTful, é uma interface de programação de aplicações (API ou API web) em conformidade com as restrições do estilo de arquitetura REST, permitindo a interação com serviços web RESTful. REST é a sigla em inglês para "Representational State Transfer", que em português significa tansferência de estado representacional. Essa arquitetura foi criada pelo cientista da computação Roy Fielding.

Vantagens: 

    ✅ Separação de cliente e servidor;
    ✅ Escalabilidade;
    ✅ Independência de linguagem;
    ✅ Mercado;

[Artigo sobre API REST](https://www.ibm.com/br-pt/topics/rest-apis)


### 4.2. Conhecendo as constraints do REST

✅ Cliente-servidor: deve ser uma aplicação com clientes e servidores separados.

✅ Stateless server: o servidor não deve guardar informações sobre o estado do cliente. Cada requisição deve ser independente, contendo as informações necessárias para ser atendida.

✅ Cacheable: é necessário que as respostas possam ser cacheadas, e o cliente deve ser informado sobre as propriedades de cache de um recurso a fim de decidir quando utilizar cache ou não.

✅ Interface uniforme: deve existir uma interface uniforme entre cliente e servidor. Para tanto, algumas convenções devem ser seguidas:

    1. Identificação de recursos por URI: cada recurso deve possuir sua respectiva URI de acesso
    2. Manipulação de recursos a partir de suas representações (que podem ser em formato HTML, XML, JSON, TXT, etc)
    3. Mensagens auto-descritivas
    4. Hypermedia as the engine of application state (HATEOAS): as respostas devem conter todas as informações necessárias para que o cliente possa navegar pela aplicação
✅ Sistema em camadas: a aplicação deve ser composta por camadas de fácil alteração, sendo possível adicionar ou remover camadas.

✅ Código sob demanda (opcional): o cliente pode solicitar um código do servidor e executá-lo.

[Artigo sobre as constraints](https://www.dio.me/articles/o-que-e-rest)

[Documentação da API REST do GitHub](https://docs.github.com/pt/rest?apiVersion=2022-11-28)
### 4.3. Diferença entre REST e RESTful

[Ver artigo](https://blog.geekhunter.com.br/sua-api-nao-e-restful-entenda-por-que/)

### 4.4. Desenvolvedores de REST APIs puristas e pragmáticos

### 4.5. Conhecendo o protocolo HTTP

 [Veja esta documentação](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Overview)

 ### 4.6. Usando o protocolo HTTP

#### Instalando o GnuTLS no Windows

Acesse [este link](https://gnutls.org/), faça download do arquivo de instalação (ZIP) para Windows e descompacte o arquivo em uma pasta.

⚠️ Este programa é apenas para meios didáticos para entender o protocolo HTTP, não sendo obrigatória a instalação.

### 4.7. Instalando e testando o Postman

[Postman](https://www.getpostman.com/)

[Documentação usada na aula](https://docs.github.com/pt/rest?apiVersion=2022-11-28)

### 4.8. Entendendo o que são Recursos REST

Um resource é qualquer coisa exposto na web.

### 4.9. Identificando recursos REST

URI vs URL: 

URI significa Uniform Resource Identifier. É um conjunto de caracteres que tem como objetivo de dar um endereço para os objetos de forma não ambígua.
A URI deve se referenciar a um substantivo. Por exemplo:
    
    /produtos

Para buscar um único produto, seria da seguinte forma:
Ex.: /produtos{codigo}

    /produtos/331

URL significa Uniform Resource Locator. É um tipo de identificador de recurso também, ela especifica não apenas o identificador, mas a localização do recurso também.

A URL completa por exemplo, seria assim:

    https://api.algamarket.com.br/produtos

    
### 4.10. Modelando e requisitando um Collection Resource com GET

⚠️ Alterando o localhost no Windows:

    C:Windows/System32/drivers/etc

Abra o arquivo hosts no bloco de notas e insira, por exemplo:

    #	127.0.0.1       localhost
    #	127.0.0.1       api.algafood.local ⚠️


### 4.13. Implementando content negotiation para retornar JSON ou XML

✅ Foi preciso adicionar uma dependência que é uma extensão do Jackson para serialização de objeto Java para XML.

        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>

### 4.14. Consultando Singleton Resource com GET e @PathVariable

Singleton Resource significa quando o usuário quer buscar apenas um objeto específico no BD.

### 4.15. Customizando as representações XML e JSON com @JsonIgnore, @JsonProperty e @JsonRootName

    ✅ @JsonIgnore: Para caso, queira ignorar algum atributo específico no BD;

    ✅ @JsonProperty: Alterar a nomeclatura do atributo/propriedade sem modificar no atributo em si;

    ✅ @JsonRootName: Para customizar a nomeclatura da entidade que é exibida no BD.


### 4.16. Customizando a representação em XML com Wrapper e anotações do Jackson

Existe a possibilidade de customizar a representação em XML.

✅ Para isso, precisamos criar um método no controller, listarXml(), para que podemos customizar a representação no formato XML.

✅ Criamos uma classe "CozinhasXmlWrapper" para que seja a responsável em fazer o empacotamento das listas de "Cozinhas".

Por enquanto a representação está da seguinte forma:

```
<CozinhasXmlWrapper>
    <cozinhas>
        <cozinhas>
            <id>1</id>
            <titulo>Tailandesa</titulo>
        </cozinhas>
        <cozinhas>
            <id>2</id>
            <titulo>Indiana</titulo>
        </cozinhas>
    </cozinhas>
</CozinhasXmlWrapper>
```

Observe que está um pouco confuso. Com as customizações:

✅ @JacksonXmlRootElement: Esta anotação na classe "CozinhasXmlWrapper", podemos customizar inserindo como parâmetro "cozinhas", para que na representação deixe de mostrar o nome da entidade;

✅ @JsonProperty: No atributo da classe,inserimos esta anotação para passarmos como parâmentro a customização ("cozinha"), para que seja representado cada objeto da lista.  

✅ @JacksonXmlElementWrapper: Inserimos também no método esta anotação para passar como parâmentro o userWrapping = false, para desabilitarmos a representação do empacotamento/embrulho, fazendo com que a representação fique mais sucinta.

📌 A representação em xml com estas alterações ficam da seguinte forma:


```
<cozinhas>
    <cozinha>
        <id>1</id>
        <titulo>Tailandesa</titulo>
    </cozinha>
    <cozinha>
        <id>2</id>
        <titulo>Indiana</titulo>
    </cozinha>
</cozinhas> 
```

### 4.17. Conhecendo os métodos HTTP

📌 Conceito Idempotência

A idempotência é um conceito da área de matemática e ciência da computação, que se refere à possibilidade de uma determinada operação ser aplicada múltiplas vezes e, em todas elas, obter um único resultado.

⚠️ Principais métodos HTTP:

✅ Get;

✅ Post;

✅ Put;

✅ Patch; 

✅ Delete;

✅ Head;

✅ Options.


### 4.18. Conhecendo os códigos de status HTTP

⚠️ Status HTTP.

✅ Nível 200: Significa que o recurso foi processado com sucesso;

✅ Nível 201: Significa que o recurso foi criado com sucesso;

✅ Nível 204: Sem conteúdo. Significa que o recurso foi processado com sucesso, sem a necessidade de resposta. Exemplo, a exclusão com sucesso;

✅ Nível 301: Movido permanentemente;

✅ Nível 302: Encontrado;

✅ Nível 400: Significa requisição mal feita;

✅ Nível 401: Não autorizado;

✅ Nível 403: Proibido;

✅ Nível 404: Não encontrado;

✅ Nível 405: Método não permitido;

✅ Nível 406: Não aceito;

✅ Nível 500: Significa erro no servidor (responsabilidade do dev);

✅ Nível 503: Serviço indisponível;


[Registro oficial IANA de códigos de status HTTP](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml)

[Documentação de códigos de status HTTP no MDN](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)


### 4.19. Definindo o status da resposta HTTP com @ResponseStatus

📌 Nesta aula, vimos uma maneira de inserir no método os métodos HTTP através da anotação @ReponseStatus() passando como parâmetro o status desejado. Exemplo: no método de salvar um recurso, o parâmetro seria o HttpStatus.CREATED.

### 4.20. Manipulando a resposta HTTP com ResponseEntity

📌 Alguns exemplos de implementação usando ResponseEntity, e HttpHeaders.

### 4.21. Corrigindo o Status HTTP para resource inexistente

📌 Alguns exemplos de implementação usando ResponseEntity, e Not Found.

### 4.22. Status HTTP para collection resource vazia: qual usar?

📌 O certo é o status ser 200 mesmo, pois o recurso está apenas vazio, ou seja, quando chamamos um serviço de lista de cozinhas, se este não contém dados, o serviço foi chamado com sucesso, apenas está com a lista vazia, sendo assim, o retorno 200 é considerado o mais correto.

### 4.23. Modelando e implementando a inclusão de recursos com POST

### 4.24. Negociando o media type do payload do POST com Content-Type

📌 Na aula vimos que podemos passar no postman, no Header o Content-Type e o Accept, para recebermos e enviarmos os dados como xml, ou json.

![aula 4.24](images/image-4.24.png)
