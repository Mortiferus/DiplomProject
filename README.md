
# **Запуск тестов**

1. склонировать репозиторий `git clone`
2. запустить контейнер с MySql,Node.js,PostgreSQL используя команду `docker-compose up -d --build` (необходим установленный Docker); 
3. запустить приложение:
    * для запуска под MySQL использовать команду   
    ```java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar```
    * для запуска под PostgreSQL использовать команду      
   ```java -Dspring.datasource.url=jdbc:postgres://localhost:3306/app -jar aqa-shop.jar```
4. запустить тесты:
   * для запуска под MySQL использовать команду                                          
   ```gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean test```
   * для запуска под PostgreSQL использовать команду      
   ```gradlew -Ddb.url=jdbc:postgres://localhost:3306/app clean test```
5. после окончания тестов завершить работу приложения (Ctrl+C), остановить контейнеры командой `docker-compose down`   

# **Документация**
1. [**Планирование**](https://github.com/Mortiferus/DiplomProject/blob/master/documents/Plan.md)
2. [**Отчёт о проведённом тестировании**](https://github.com/Mortiferus/DiplomProject/blob/master/documents/Report.md)
3. [**Отчёт по итогам автоматизации**](https://github.com/Mortiferus/DiplomProject/blob/master/documents/Summary.md)
