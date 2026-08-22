#!/bin/sh
# Compila y ejecuta. Requiere solo el JDK: sin Maven, sin dependencias.
javac -d out $(find src -name "*.java") && java -cp out pe.ticketpe.Main
