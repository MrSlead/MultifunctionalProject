# О проекте
### Суть
Проект представляет собой приложение с двумя главными потоками: 

- через брокер сообщений
- через кэш


### Через брокер сообщений
В брокер ActiveMQ попадает сообщение через HTTP запрос, затем через Apache Camel машрутизируется в очередь, из которой осуществляется импорт документа в БД. 


### Через кэш
В кэш Hazelcast сообщение попадает через HTTP запрос, затем при помощи многопоточности осуществляется вычитка документов и импорт в БД.


### Используемый стек:
- Spring Framework (Core, Boot, Data JPA)
- Hibernate, MySQL
- Apache Camel, JMS, ActiveMQ
- Java Concurrency
- Hazelcast
- Jackson
- Lombok
- Maven
- Docker

---
Swagger<br>
http://localhost:8080/swagger-ui/index.html#/