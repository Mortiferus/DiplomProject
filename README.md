
# **Запуск тестов**

1. склонировать репозиторий `git clone`
2. запустить контейнер с MySql,Node.js,PostgreSQL используя команду `docker-compose up -d --build` (необходим установленный Docker); 
3. запустить приложение:
    * для запуска под MySQL использовать команду   
    ```java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar```
    * для запуска под PostgreSQL использовать команду      
     ``` docker exec -it pstg-container psql -U postgres ```
4. запустить тесты:
   * для запуска под MySQL использовать команду                                          
   ```gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean test```
   * для запуска под PostgreSQL использовать команду      
   ```docker run --name pstg-container -d test -d postgres```
5. после окончания тестов завершить работу приложения (Ctrl+C), остановить контейнеры командой `docker-compose down`   
