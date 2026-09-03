#!/bin/sh
# Venta llama a cada interesado, uno por uno, por su URL.
rm -rf datos out
find src -name "*.java" > sources.txt
javac -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt

java -cp out pe.ticketpe.servicios.ServicioCatalogo     & PC=$!
java -cp out pe.ticketpe.servicios.ServicioFacturacion  & PF=$!
java -cp out pe.ticketpe.servicios.ServicioFidelizacion & PD=$!
java -cp out pe.ticketpe.servicios.ServicioVenta        & PV=$!
java -cp out pe.ticketpe.servicios.ServicioReporte      & PR=$!
sleep 2
java -cp out pe.ticketpe.servicios.Cliente
kill $PC $PF $PD $PV $PR 2>/dev/null
wait 2>/dev/null
