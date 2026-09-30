package com.rica.api.publicaciones.aplicacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
 
import com.rica.api.compartido.RecursoNoEncontradoException;
import com.rica.api.publicaciones.dominio.Publicacion;

class PublicacionServiceConFalsoTest {

     private static final String CORREO = "ana.torres@uptc.edu.co";
 
    private Publicacion publicacion(String correo, String titulo, int anio) {
        Publicacion p = new Publicacion();
        p.setInvestigadorCorreo(correo);
        p.setTitulo(titulo);
        p.setTipo("ARTICULO");
        p.setAnio(anio);
        return p;
    }
 
    @Test
    void registraYRecuperaUnaPublicacionSinSpringNiBaseDeDatos() {
        RepositorioPublicacionesFalso repositorio = new RepositorioPublicacionesFalso();
        PublicacionService service = new PublicacionService(
                repositorio, new VerificadorInvestigadorFalso(CORREO));
 
        Publicacion guardada = service.registrar(publicacion(CORREO, "Hexagonal", 2026));
 
        assertThat(guardada.getId()).isNotNull();
        assertThat(service.buscarPorId(guardada.getId()).getTitulo()).isEqualTo("Hexagonal");
    }
 
    @Test
    void rechazaPublicacionDeUnInvestigadorDesconocido() {
        PublicacionService service = new PublicacionService(
                new RepositorioPublicacionesFalso(), new VerificadorInvestigadorFalso(CORREO));
 
        assertThatThrownBy(() -> service.registrar(
                publicacion("fantasma@uptc.edu.co", "Nada", 2026)))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }
 
    @Test
    void listaSoloLasPublicacionesDelInvestigadorPedido() {
        RepositorioPublicacionesFalso repositorio = new RepositorioPublicacionesFalso();
        PublicacionService service = new PublicacionService(
                repositorio, new VerificadorInvestigadorFalso(CORREO, "luis@uptc.edu.co"));
 
        service.registrar(publicacion(CORREO, "A", 2026));
        service.registrar(publicacion(CORREO, "B", 2026));
        service.registrar(publicacion("luis@uptc.edu.co", "C", 2026));
 
        assertThat(service.listarPorInvestigador(CORREO))
                .extracting(Publicacion::getTitulo)
                .containsExactly("A", "B");
    }
 
    @Test
    void ellimiteAnualSeCalculaContraElAlmacenReal() {
        RepositorioPublicacionesFalso repositorio = new RepositorioPublicacionesFalso();
        PublicacionService service = new PublicacionService(
                repositorio, new VerificadorInvestigadorFalso(CORREO));
        LimitePublicacionesAnualesService limite = new LimitePublicacionesAnualesService(repositorio);
 
        for (int i = 1; i <= 5; i++) {
            service.registrar(publicacion(CORREO, "Pub " + i, 2026));
        }
 
        assertThat(limite.puedeRegistrar(publicacion(CORREO, "Sexta", 2026))).isFalse();
        assertThat(limite.puedeRegistrar(publicacion(CORREO, "Otro anio", 2027))).isTrue();
    }
}
