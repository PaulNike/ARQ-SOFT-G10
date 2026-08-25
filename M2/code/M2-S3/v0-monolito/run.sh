#!/bin/sh
# Compila y ejecuta. Requiere solo el JDK: sin Maven, sin dependencias.
find src -name "*.java" > sources.txt
javac -d out @sources.txt && java -cp out pe.ticketpe.Main
rm -f sources.txt
