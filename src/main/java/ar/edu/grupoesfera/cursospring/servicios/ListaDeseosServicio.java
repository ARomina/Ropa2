package ar.edu.grupoesfera.cursospring.servicios;

import java.util.Set;

import ar.edu.grupoesfera.cursospring.modelo.Producto;

public interface ListaDeseosServicio {

	void buscaProductoLista();
	Boolean ponerProductoLista();
	Boolean quitarProductoLista();
	Set<Producto> verProductosListaDeseos();
	Boolean listaVacia();
	
}