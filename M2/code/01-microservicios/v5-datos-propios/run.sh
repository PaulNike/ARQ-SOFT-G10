#!/bin/sh
# Cada servicio con su propia base. Esquema vigente.
rm -rf datos out
find src -name "*.java" > sources.txt
javac -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt

java -cp out pe.ticketpe.servicios.ServicioCatalogo & P1=$!
java -cp out pe.ticketpe.servicios.ServicioVenta    & P2=$!
java -cp out pe.ticketpe.servicios.ServicioReporte  & P3=$!
sleep 2
java -cp out pe.ticketpe.servicios.Cliente
echo "--- las bases, separadas ---"
echo "datos/catalogo.db:"; sed 's/^/   /' datos/catalogo.db
echo "datos/venta.db:";    sed 's/^/   /' datos/venta.db
kill $P1 $P2 $P3 2>/dev/null
wait 2>/dev/null
