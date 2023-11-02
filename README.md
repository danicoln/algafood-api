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

### 3.8. Consultando objetos do banco de dados

📌 O que vimos nesta aula:

✅ Exemplo de consulta de objetos do banco de dados;

Para esta aula, criamos as classes:

    ✅ CadastroCozinha;
    ✅ ConsultaCozinhaMain;

Inserimos uma nova configuração em application.properties para que possamos verificar no console os selects de tabelas feitas pelo hibernate:

```
spring.jpa.show-sql=true
```

⚠️ Obs:

Esta configuração só pode ser usada em ambiente de desenvolvimento, não sendo uma boa prática usar em ambiente de produção/homologação.

### 3.9. Adicionando um objeto no banco de dados

📌 O que vimos nesta aula:

✅ Criamos a classe InclusaoCozinhaMain;

✅ Inserimos um novo método na classe CadastroCozinha.

### 3.10. Buscando um objeto pelo id no banco de dados

📌 O que vimos nesta aula:

✅ Criamos a classe BuscaCozinhaMain;

✅ Inserimos um novo método na classe CadastroCozinha, o de buscar.

### 3.11. Atualizando um objeto no banco de dados

📌 O que vimos nesta aula:

✅ Criamos a classe AlteracaoCozinhaMain;

✅ Alteramos o nome do método adicionar para "salvar".

### 3.12. Excluindo um objeto do banco de dados

📌 O que vimos nesta aula:

✅ Criamos a classe ExclusaoCozinhaMain;

✅ Inserimos um novo método na classe CadastroCozinha, o de remover.

⚠️ Observações:

O método criado "remover", em seu parâmetro, o objeto passado está em seu estado "Transient", se não fizermos a busca deste objeto no BD antes da exclusão, dá o seguinte erro: 
```
Exception in thread "restartedMain" java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:50)
Caused by: java.lang.IllegalArgumentException: Removing a detached instance com.algaworks.algafood.domain.model.Cozinha#1
```

Para que este  erro não aconteça, é necessário a busca do objeto no BD para  que o mesmo
saia do estado "Transient" para o estado "Detached" para poder ser gerenciada com a chamada do método merge.

```
    @Transactional
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        manager.remove(cozinha);
    }
```

### [Veja este artigo para maiores iformações](https://blog.algaworks.com/tutorial-jpa/)

