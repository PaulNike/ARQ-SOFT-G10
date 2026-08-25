@echo off
REM Compila y ejecuta. Requiere solo el JDK: sin Maven, sin dependencias.
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt && java -cp out pe.ticketpe.Main
del sources.txt
