# Процедура запуска автотестов:  

1. Открыть проект в IntelliJ IDEA
2. В командной строке выполнить `wsl --shutdown`
3. Запустить Docker в терминале Ubuntu
4. В терминале Ubuntu выполнить команду `docker-compose up`
5. В терминале IntelliJ IDEA для запуска приложения выполнить команду:  

    **MySQL:** `java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app`  
    
    **Postgres:** `java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app`
6. В терминале IntelliJ IDEA для прогона автотестов выполнить команду:  

    **MySQL:** `./gradlew clean test -D dbUrl=jdbc:mysql://localhost:3306/app -D dbUser=app -D dbPass=pass`  
    
    **Postgres:** `./gradlew clean test -D dbUrl=jdbc:postgresql://localhost:5432/app -D dbUser=app -D dbPass=pass`
7. Для получения отчета в Allure выполнить команду: `./gradlew allureServe`
