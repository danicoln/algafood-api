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