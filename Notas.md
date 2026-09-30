
--------------------------------------------------- 
# rica-api (archivado)

Este proyecto fue dividido en `investigadores_service` y `publicaciones_service`
disponible en: https://github.com/DmS0509/RICA_Microservicios.git

--------------------------------------------------- 
# _TALLER DDD_ 


 > **_¿Hay algún campo o método con un nombre genérico (data, info, value, item) que debería tener un nombre del dominio?_** 

 * Si, en InvestigadorController se encuentran los metodos listar() y registrar() los cuales se pueden inferir hace referencia a listar investigadores y registrar investigadores, por lo que los metodos podrian renombrarse para ser mas explicitos en su funcion.

> **_¿correoInstitucional y grupoInvestigacion son términos que reconocería alguien de la Facultad sin que se los tradujeran?_**

 * Si, correoInstitucional y grupoInvestigacion pueden ser reconocidos por cualquier tipo de persona o usuario fuera de la facultad, pues son lo suficientemente claros para comprender a que hacen referencia sin que alguien tenga que explicarlos.

> **_En Publicacion, el campo se llama investigadorCorreo, no investigadorId ni autorId. Escribe una frase explicando por qué ese nombre es más preciso para el Lenguaje Ubicuo de este dominio que una alternativa genérica como refId._**

 Porque es mas sencillo para todos los usuarios y personas de interes comprender que un investigador puede distinguirse por su correo "investigadorCorreo" que por un ID, el cual puede resultar confuso para personas ajenas al proceso de codificacion, pero pueden comprender que un correo es unico para cada investigador, y esta es la forma de distinguir a uno del otro sin necesidad de evaluar su ID dentro del sistema.


--------------------------------------------------- 
## _SECCIÓN ADR_ 


> **_¿Cuál es la raíz del Agregado Investigador?_**

 * La raiz del agregado Investigador es la entidad `Investigador`, el cual es el unico punto de entrada para identificar y registrar al investigador. Tambien es el responsable de mantener su informacion principal.

> **_¿Qué vive dentro del límite?_**

 * Dentro del limite viven los datos y las reglas que describen al investigador como su nombre completo, su correo institucional, su grupo de investigacion, y el objeto de valor `CorreoInstitucional`. Tambien posee la regla de que no se puede registrar otro investigador con el mismo correo institucional.

> **_¿Por qué Publicacion NO está dentro de este límite?_**

* `Publicacion` no esta dentro del limite por tener una identidad, un ciclo de vida y una persistencia propios, pues se almacena como un documento independiente en MongoDB y se administra gracias a su propio servicio y repositorio. Su relacion con el investigador es mediante `investigadorCorreo`, el cual hace referencia al lenguaje del dominio.

> **_¿Qué pasaría si alguien agrega un campo `List<Publicacion>` publicaciones directo en Investigador?_**

* Se puede romper el limite del agregado e `Investigador` quedaria acoplado a `Publicacion`, lo cual mezclaria dos ciclos de vida y dos mecanismos de persistencia, y cualquier carga o modificacion del investigador podria arrastrar una coleccion de publicaciones grande. Finalmente, podria debilitar la separacion de responsabiliades, pues reglas como el limite anual de publicaciones dependerian de la entidad `Investigador`.

--------------------------------------------------- 
# _TALLER ARQUITECTURA HEXAGONAL_

> **_`InvestigadorRepository` es una interfaz, nunca una clase concreta, desde el Tutorial 4. Según la guía (sección 3), ¿es un puerto primario o secundario? Justifica con una frase: ¿quién inicia la llamada, el núcleo o algo externo?_**

* `InvestigadorRepository` es un puerto secundario, esto debido a que el nucleo de la aplicación inicia la llamada hacia el exterior (base de datos) utilizando la interfaz basada en `SpringDataJPA`.

> **_ `InvestigadorController` — ¿es un adaptador primario o secundario? ¿Qué tecnología concreta envuelve?_**

* `InvestigadorController` es un adaptador primario, que envuelve la tecnologia Spring web / API REST (HTTP) mediante el uso de anotaciones como lo son `@RestController`.

> **_ `InvestigadorService` hoy es una clase concreta, no una interfaz. `InvestigadorController` la llama directamente. Según la nota de la guía (sección 6, "Lo que sí falta hoy en rica-api"), ¿qué pieza falta para que exista un puerto primario explícito?_**

* La pieza que falta es una interfaz de puerto primario (o puerto de entrada) que desacople el controlador del servicio, permitiendo que el adaptador primario llame a una abstracción del núcleo y no a una clase concreta de manera directa.

> **_`InvestigadorFactory` del Taller de la Lección 3 — ¿pertenece al núcleo o a un adaptador? Pista: revisa sus import (sección 5 de la guía, "La prueba del núcleo limpio")._**

* `InvestigadorFactory` pertenece al núcleo dado que encapsula la lógica del negocio critica para la creación de entidades y validación de reglas del dominio (ejm: restriccion de correos duplicados), a pesar de estar apoyada por anotaciones de infraestructura como `@Component`.


--------------------------------------------------- 
# _TALLER MICROSERVICIOS_

| Paquete / clase actual | Destino |
|---|---|
| `investigadores.*` | `investigadores_service` |
| `publicaciones.*` | `publicaciones_service` |
| `compartido.GlobalExceptionHandler`, `RecursoNoEncontradoException` | Se duplica en ambos |
| `plataforma.StatusController`, CorsConfig | Se duplica en ambos, adaptado |
| `plataforma.ArranqueInformativo`, `SaludoInstitucionalService` | Se descarta (demo del Tutorial 2) |

* Decisión de arquitectura: duplicar `compartido` evita acoplar los servicios,
pero obliga a mantener dos copias. 

