#!/bin/sh
# El equipo de venta cambia el formato de SU tabla. Igual que en el ciclo anterior.
# La diferencia: ahora la tabla es suya.
rm -rf datos out
find src -name "*.java" > sources.txt
javac -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt

java -cp out pe.ticketpe.servicios.ServicioCatalogo          & P1=$!
java -Desquema=2 -cp out pe.ticketpe.servicios.ServicioVenta & P2=$!
java -cp out pe.ticketpe.servicios.ServicioReporte           & P3=$!
sleep 2
java -cp out pe.ticketpe.servicios.Cliente
echo "--- las bases, separadas ---"
echo "datos/catalogo.db:"; sed 's/^/   /' datos/catalogo.db
echo "datos/venta.db:";    sed 's/^/   /' datos/venta.db
kill $P1 $P2 $P3 2>/dev/null
wait 2>/dev/null
