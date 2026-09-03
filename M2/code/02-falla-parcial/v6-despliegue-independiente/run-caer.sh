#!/bin/sh
# La MISMA caida, con la otra decision: acceso no deja pasar a nadie si no puede verificar.
# Comparar con run.sh. El codigo es identico salvo una bandera.
rm -rf datos out
find src -name "*.java" > sources.txt
javac -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt

java -cp out pe.ticketpe.servicios.ServicioCatalogo                & PC=$!
java -cp out pe.ticketpe.servicios.ServicioVenta                   & PV=$!
java -Dtolerante=false -cp out pe.ticketpe.servicios.ServicioAcceso & PA=$!
java -cp out pe.ticketpe.servicios.ServicioReporte                 & PR=$!
sleep 2

echo
echo "--- 1. se venden entradas ---"
java -cp out pe.ticketpe.servicios.Cliente > /dev/null 2>&1
echo "   listo"

echo
echo "--- 2. la puerta empieza a validar ---"
java -cp out pe.ticketpe.servicios.Puerta 8 &
PP=$!
sleep 3
echo
echo "   >>> APAGANDO el servicio de venta"
kill $PV 2>/dev/null
sleep 3
echo "   >>> LEVANTANDO el servicio de venta otra vez"
java -cp out pe.ticketpe.servicios.ServicioVenta > /dev/null 2>&1 & PV=$!
wait $PP 2>/dev/null
echo
kill $PC $PV $PA $PR 2>/dev/null
wait 2>/dev/null
