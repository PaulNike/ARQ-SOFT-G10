#!/bin/sh
# Lo mismo, pero con DOS instancias del consumidor corriendo.
# Es lo normal en produccion: se escala el consumidor para aguantar carga.
# Observar cuantos comprobantes se emiten por cada venta.
rm -rf datos out
find src -name "*.java" > sources.txt
javac -d out @sources.txt || { rm -f sources.txt; exit 1; }
rm -f sources.txt

java -cp out pe.ticketpe.servicios.Consumidores     & K1=$!
java -cp out pe.ticketpe.servicios.Consumidores     & K2=$!
java -cp out pe.ticketpe.servicios.ServicioCatalogo & PC=$!
java -cp out pe.ticketpe.servicios.ServicioVenta    & PV=$!
sleep 2
echo
echo "--- una sola venta de 2 entradas ---"
java -cp out pe.ticketpe.servicios.UnaVenta
sleep 1
echo
echo "--- el registro de eventos: UN solo evento ---"
sed 's/^/   /' datos/eventos.log
kill $K1 $K2 $PC $PV 2>/dev/null
wait 2>/dev/null
