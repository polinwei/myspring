學習用 gradle 建置 Spring Boot
===============================

command file : spring_boot.bat 
--------------------------------
`set JAVA_HOME=V:\Java64\jdk1.8.0_45<br />
set PATH=.;%JAVA_HOME%\jre\bin;%JAVA_HOME%\bin;V:\gradle\bin;<br />`

run the application
-------------------------------
V:myspringboot\> `gradlew bootRun`

build the JAR file & Run JAR file
-------------------------------
V:myspringboot\> `gradlew build`<br />
Then you can run the JAR file: <br />
V:myspringboot\> `java -jar build/libs/myspring.jar`

