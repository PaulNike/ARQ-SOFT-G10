# Reto — agregar un consumidor sin tocar a quien publica

Cuando alguien compra una entrada, marketing quiere enviarle un correo de confirmación.

## Lo que hay que hacer

1. Ejecútalo primero y mira la salida actual:

```
sh run.sh
```

2. Agrega un consumidor nuevo llamado `notificaciones` que imprima, por cada venta:

```
   [notificaciones] correo enviado a C-001
```

3. Vuelve a ejecutarlo. Los tres consumidores tienen que aparecer.

## Las reglas del reto

- **No puedes tocar `ServicioVenta.java`.** Ni una línea.
- No puedes agregar un puerto nuevo ni una URL nueva.
- No puedes cambiar el formato del evento.

## Las preguntas que hay que responder al terminar

- ¿Cuántos archivos tocaste?
- ¿Tuviste que avisarle a alguien del equipo de venta?
- Ahora imagina lo mismo en `v7-llamadas-directas`, donde venta llama a cada
  interesado por su URL. ¿Cuántos archivos habrías tocado ahí? ¿Y a quién habrías
  tenido que avisar?

> *Pista:* en `Consumidores.java` hay un bloque comentado que marca exactamente
> dónde va. Lee cómo están escritos los dos que ya existen.
