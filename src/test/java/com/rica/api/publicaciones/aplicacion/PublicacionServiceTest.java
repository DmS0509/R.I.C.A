package com.rica.api.publicaciones.aplicacion;

import java.util.List;
import java.util.Optional;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.rica.api.compartido.RecursoNoEncontradoException;
import com.rica.api.publicaciones.dominio.Publicacion;

@ExtendWith(MockitoExtension.class)
public class PublicacionServiceTest {

    private static final String CORREO = "ana.torres@uptc.edu.co";
 
    @Mock
    private RepositorioPublicaciones repositorioPublicaciones;
 
    @Mock
    private VerificadorInvestigador verificadorInvestigador;
 
    @InjectMocks
    private PublicacionService publicacionService;
 
    private Publicacion publicacion(String correo) {
        Publicacion p = new Publicacion();
        p.setInvestigadorCorreo(correo);
        p.setTitulo("Arquitectura hexagonal en la practica");
        p.setTipo("ARTICULO");
        p.setAnio(2026);
        return p;
    }
 
    @Test
    void registrarGuardaLaPublicacionCuandoElInvestigadorExiste() {
        Publicacion nueva = publicacion(CORREO);
        when(verificadorInvestigador.existe(CORREO)).thenReturn(true);
        when(repositorioPublicaciones.guardar(nueva)).thenReturn(nueva);
 
        Publicacion resultado = publicacionService.registrar(nueva);
 
        assertThat(resultado).isSameAs(nueva);
        verify(repositorioPublicaciones).guardar(nueva);
    }
 
    @Test
    void registrarLanzaExcepcionYNoGuardaCuandoElInvestigadorNoExiste() {
        Publicacion nueva = publicacion("fantasma@uptc.edu.co");
        when(verificadorInvestigador.existe("fantasma@uptc.edu.co")).thenReturn(false);
 
        assertThatThrownBy(() -> publicacionService.registrar(nueva))
                .isInstanceOf(RecursoNoEncontradoException.class)
                .hasMessageContaining("fantasma@uptc.edu.co");
 
        verify(repositorioPublicaciones, never()).guardar(nueva);
    }
 
    @Test
    void listarPorInvestigadorDelegaEnElRepositorio() {
        List<Publicacion> esperadas = List.of(publicacion(CORREO));
        when(repositorioPublicaciones.listarPorInvestigador(CORREO)).thenReturn(esperadas);
 
        assertThat(publicacionService.listarPorInvestigador(CORREO)).isEqualTo(esperadas);
    }
 
    @Test
    void buscarPorIdDevuelveLaPublicacionCuandoExiste() {
        Publicacion existente = publicacion(CORREO);
        existente.setId("abc123");
        when(repositorioPublicaciones.buscarPorId("abc123")).thenReturn(Optional.of(existente));
 
        assertThat(publicacionService.buscarPorId("abc123").getTitulo())
                .isEqualTo("Arquitectura hexagonal en la practica");
    }
 
    @Test
    void buscarPorIdLanzaExcepcionCuandoNoExiste() {
        when(repositorioPublicaciones.buscarPorId("nope")).thenReturn(Optional.empty());
 
        assertThatThrownBy(() -> publicacionService.buscarPorId("nope"))
                .isInstanceOf(RecursoNoEncontradoException.class)
                .hasMessageContaining("nope");
    }
}
