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

