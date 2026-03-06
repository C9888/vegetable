@echo off
cd /d "E:\MyJava\bishe\vegetable"
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_281
set PATH=%JAVA_HOME%\bin;%PATH%
echo Starting server...
java -jar target\vegetable.jar
pause