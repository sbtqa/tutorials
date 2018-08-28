## До запуска приложения и тестов нужно восстановить базу данных Postgres из бэкапа следующей командой:

Для создания бд

_$ psql template1 -c 'create database pcdb with owner имя_пользователя_бд;'_  
 
Для восстановления бэкапа, находится в jacoco/processing-center/src/test/resources/pcdbbackup.sql

_$ psql pcdb < путь_до_бэкапа/pcdbbackup.sql_


изменить логин и пароль на свои в файле ProcessingcenterApplicationTests.java,

_dataSource.setUsername("логин_постгрес");_
_dataSource.setPassword("пароль");_

## Запуск приложения, тестов и генерация отчета

## 1. Чтобы собрать приложение, введите находясь в директории проекта в командной строке

_$ mvn package -DskipTests_

## 2. Чтобы запустить приложение локально

_$ java -jar target/processingcenter-0.0.1-SNAPSHOT.jar_

## 3. Чтобы запустить тесты введите

_$ mvn clean test_