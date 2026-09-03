#!/bin/sh
# Demo de despliegue independiente.
#   1) los cuatro servicios arriba, se venden entradas
#   2) la puerta empieza a validar sin parar
#   3) REINICIAMOS el servicio de venta mientras la puerta sigue trabajando
rm -rf datos out
find src -name "*.java" > sources.txt
javac -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt

java -cp out pe.ticketpe.servicios.ServicioCatalogo & PC=$!
java -cp out pe.ticketpe.servicios.ServicioVenta    & PV=$!
java -cp out pe.ticketpe.servicios.ServicioAcceso   & PA=$!
java -cp out pe.ticketpe.servicios.ServicioReporte  & PR=$!
sleep 2

echo
echo "--- 1. se venden 6 entradas ---"
java -cp out pe.ticketpe.servicios.Cliente > /dev/null 2>&1
echo "   listo"

echo
echo "--- 2. la puerta empieza a validar (una por segundo) ---"
java -cp out pe.ticketpe.servicios.Puerta 8 &
PP=$!

sleep 3
echo
echo "   >>> APAGANDO el servicio de venta (despliegue de una version nueva)"
kill $PV 2>/dev/null
sleep 3
echo "   >>> LEVANTANDO el servicio de venta otra vez"
java -cp out pe.ticketpe.servicios.ServicioVenta > /dev/null 2>&1 & PV=$!

wait $PP 2>/dev/null
echo
echo "--- 3. fin ---"
kill $PC $PV $PA $PR 2>/dev/null
wait 2>/dev/null
