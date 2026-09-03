#!/bin/sh
# La pregunta incomoda: si alguien compra una entrada, ¿que pasa exactamente?
# Busquemos la respuesta en el codigo de las dos versiones.
V7=../v7-llamadas-directas
echo "#############################################################"
echo "#  v7 — llamadas directas                                   #"
echo "#############################################################"
echo "Abrimos ServicioVenta.java y leemos que pasa despues de vender:"
grep -n "notificar(" $V7/src/pe/ticketpe/servicios/ServicioVenta.java | sed 's/^/   /'
echo
echo "El flujo completo esta en UN archivo. Se lee de arriba a abajo."
echo
echo "#############################################################"
echo "#  v8 — eventos                                             #"
echo "#############################################################"
echo "Abrimos ServicioVenta.java y leemos que pasa despues de vender:"
grep -n "Bus.publicar" src/pe/ticketpe/servicios/ServicioVenta.java | sed 's/^/   /'
echo
echo "Eso es todo lo que dice. ¿Quien escucha? Hay que buscarlo aparte:"
grep -rn "VENTA_REALIZADA" src --include=*.java | grep -v ServicioVenta | sed 's/^/   /'
echo
echo "El flujo no esta escrito en ningun archivo. Esta repartido."
echo "Y esto es un proyecto de 15 archivos. Imaginen uno de 300."
