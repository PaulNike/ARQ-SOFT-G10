#!/bin/sh
# Base de datos compartida, esquema vigente. Todo funciona.
rm -rf datos out
find src -name "*.java" > sources.txt
javac -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt

java -cp out pe.ticketpe.servicios.ServicioVenta   & P1=$!
java -cp out pe.ticketpe.servicios.ServicioReporte & P2=$!
sleep 2
java -cp out pe.ticketpe.servicios.Cliente
echo "--- contenido de la base compartida ---"
cat datos/ticketpe.db
kill $P1 $P2 2>/dev/null
wait 2>/dev/null
