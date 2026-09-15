#¿Hay algún campo o método con un nombre genérico (data, info, value, item) que debería tener un nombre del dominio?

Si, en InvestigadorController se encuentran los metodos listar() y registrar() los cuales se pueden inferir hace referencia a listar investigadores y registrar investigadores, por lo que los metodos podrian renombrarse para ser mas explicitos en su funcion.

#¿correoInstitucional y grupoInvestigacion son términos que reconocería alguien de la Facultad sin que se los tradujeran?

Si, correoInstitucional y grupoInvestigacion pueden ser reconocidos por cualquier tipo de persona o usuario fuera de la facultad, pues son lo suficientemente claros para comprender a que hacen referencia sin que alguien tenga que explicarlos.

#En Publicacion, el campo se llama investigadorCorreo, no investigadorId ni autorId. Escribe una frase explicando por qué ese nombre es más preciso para el Lenguaje Ubicuo de este dominio que una alternativa genérica como refId.

Porque es mas sencillo para todos los usuarios y personas de interes comprender que un investigador puede distinguirse por su correo "investigadorCorreo" que por un ID, el cual puede resultar confuso para personas ajenas al proceso de codificacion, pero pueden comprender que un correo es unico para cada investigador, y esta es la forma de distinguir a uno del otro sin necesidad de evaluar su ID dentro del sistema.

============================================
================= ADR ======================
============================================

#¿Cuál es la raíz del Agregado Investigador?

La raiz del agregado Investigador es la entidad "Investigador", el cual es el unico punto de entrada para identificar y registrar al investigador. Tambien es el responsable de mantener su informacion principal.

#¿Qué vive dentro del límite?

Dentro del limite viven los datos y las reglas que describen al investigador como su nombre completo, su correo institucional, su grupo de investigacion, y el objeto de valor "CorreoInstitucional". Tambien posee la regla de que no se puede registrar otro investigador con el mismo correo institucional.

#¿Por qué Publicacion NO está dentro de este límite?

"Publicacion" no esta dentro del limite por tener una identidad, un ciclo de vida y una persistencia propios, pues se almacena como un documento independiente en MongoDB y se administra gracias a su propio servicio y repositorio. Su relacion con el investigador es mediante "investigadorCorreo", el cual hace referencia al lenguaje del dominio.

#¿Qué pasaría si alguien agrega un campo List<Publicacion> publicaciones directo en Investigador?

Se puede romper el limite del agregado e "Investigador" quedaria acoplado a "Publicacion", lo cual mezclaria dos ciclos de vida y dos mecanismos de persistencia, y cualquier carga o modificacion del investigador podria arrastrar una coleccion de publicaciones grande. Finalmente, podria debilitar la separacion de responsabiliades, pues reglas como el limite anual de publicaciones dependerian de la entidad "Investigador".
