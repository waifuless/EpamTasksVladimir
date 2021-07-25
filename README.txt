Данная ветка не содержит lib/ и app/

Для создания fat-jar с скомпилированными классами и зависимостями с помощью maven в pom.xml были добавлены плагины. Для запуска:

1. mvn package                                                                                                  ✔ 
2. java -jar target/task1-1.0-SNAPSHOT-jar-with-dependencies.jar 

При исполнении создается папка logs, куда записываются логи.




Компиляция в папку app(требуется lib):

1. javac -d app/ -cp "lib/*" -proc:none -sourcepath "src/main/java" src/main/java/com/epam/jwd/app/Main.java
2. В папку app необходимо добавить log4j2.xml
3. app и lib можно переместить в другое место
4. java -cp "lib/*:app" com.epam.jwd.app.Main
