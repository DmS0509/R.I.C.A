package com.rica.api.publicaciones.aplicacion;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.rica.api.publicaciones.dominio.Publicacion;

@ExtendWith(MockitoExtension.class)
public class LimitePüblicacionesAnualesServiceTest {

    private static final String CORREO = "ana.torres@uptc.edu.co";
 
    @Mock
    private RepositorioPublicaciones repositorioPublicaciones;
 
    @InjectMocks
    private LimitePublicacionesAnualesService limiteService;
 
    private Publicacion nueva() {
        Publicacion p = new Publicacion();
        p.setInvestigadorCorreo(CORREO);
        p.setAnio(2026);
        return p;
    }
 
    @Test
    void permiteRegistrarCuandoLlevaMenosDeCincoEnElAnio() {
        when(repositorioPublicaciones.contarPorInvestigadorYAnio(CORREO, 2026)).thenReturn(4L);
 
        assertThat(limiteService.puedeRegistrar(nueva())).isTrue();
    }
 
    @Test
    void rechazaCuandoYaAlcanzoElMaximoDeCincoEnElAnio() {
        when(repositorioPublicaciones.contarPorInvestigadorYAnio(CORREO, 2026)).thenReturn(5L);
 
        assertThat(limiteService.puedeRegistrar(nueva())).isFalse();
    }
 
    @Test
    void permiteRegistrarCuandoNoTienePublicacionesEseAnio() {
        when(repositorioPublicaciones.contarPorInvestigadorYAnio(CORREO, 2026)).thenReturn(0L);
 
        assertThat(limiteService.puedeRegistrar(nueva())).isTrue();
    }
}
