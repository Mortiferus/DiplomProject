
# **Запуск тестов**

1. склонировать репозиторий `git clone`
2. запустить контейнер с MySql и Node.js используя команду `docker-compose up -d --build` (необходим установленный Docker); 
3. запустить приложение:
    * для запуска под MySQL использовать команду   
    ```java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar```
4. запустить тесты:
   * для запуска под MySQL использовать команду                                          
   ```gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean test```
5. после окончания тестов завершить работу приложения (Ctrl+C), остановить контейнеры командой `docker-compose down`   
