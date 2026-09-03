#!/bin/sh
# Venta publica un evento. Los consumidores lo recogen del bus.
rm -rf datos out
find src -name "*.java" > sources.txt
javac -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt

java -cp out pe.ticketpe.servicios.Consumidores    & PK=$!
java -cp out pe.ticketpe.servicios.ServicioCatalogo & PC=$!
java -cp out pe.ticketpe.servicios.ServicioVenta    & PV=$!
java -cp out pe.ticketpe.servicios.ServicioReporte  & PR=$!
sleep 2
java -cp out pe.ticketpe.servicios.Cliente
sleep 1
echo "--- el registro de eventos ---"
sed 's/^/   /' datos/eventos.log
kill $PK $PC $PV $PR 2>/dev/null
wait 2>/dev/null
