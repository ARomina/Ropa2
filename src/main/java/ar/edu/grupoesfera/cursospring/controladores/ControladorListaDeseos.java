package ar.edu.grupoesfera.cursospring.controladores;

import java.util.Iterator;

import javax.inject.Inject;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.grupoesfera.cursospring.servicios.ProductoServicio;
import ar.edu.grupoesfera.cursospring.modelo.ColeccionListaDeseos;
import ar.edu.grupoesfera.cursospring.modelo.ColeccionProducto;
import ar.edu.grupoesfera.cursospring.modelo.Producto;
import ar.edu.grupoesfera.cursospring.servicios.ListaDeseosServicio;

@RestController
public class ControladorListaDeseos {

	@Inject
	private ProductoServicio servicioproducto;
	private ProductoServicio servicioListaDeseos;
	
	@RequestMapping(path = "/agregarAListaDeseos")
	public ModelAndView agregarProductoAListaDeseos(@ModelAttribute("producto")Producto producto){
		ModelMap modelo = new ModelMap();
		ColeccionProducto servicioproducto = ColeccionProducto.getInstance();
		ColeccionListaDeseos servicioListaDeseos = ColeccionListaDeseos.getInstance();
		String info = "";
		
		//Itero ColeccionProductos y llamo a la funcion para ver si esta en la lista de deseos --> si no lo esta, lo agrega a la lista
		for(Iterator<Producto> it = servicioproducto.getProductos().iterator(); it.hasNext();){
			Producto each = it.next();
			if(servicioListaDeseos.ponerProductoLista(each)){
			   info = "PRODUCTO PUESTO Y GUARDADO EN LISTA DE DESEOS";
			}
			else{
			   info = "PRODUCTO NO PUESTO EN LISTA DE DESEOS";
			}
		}
		
		//Comprobacion de lista vacia
		String info2 = "";
		try{
			if(servicioListaDeseos.listaVacia()){
				info2 = "LISTA VACIA";
			}
		}catch(Exception e){
			info2 = "LISTA NO VACIA";
		}
		
		modelo.put("info", info);
		modelo.put("info2", info2);
		modelo.put("servicioListaDeseos", servicioListaDeseos.verProductosListaDeseos());
		return new ModelAndView("verListaDeseos", modelo);
	}
	

	//Getters y Setters
	public ProductoServicio getServicioproducto() {
		return servicioproducto;
	}

	public void setServicioproducto(ProductoServicio servicioproducto) {
		this.servicioproducto = servicioproducto;
	}

	public ProductoServicio getServicioListaDeseos() {
		return servicioListaDeseos;
	}

	public void setServicioListaDeseos(ProductoServicio servicioListaDeseos) {
		this.servicioListaDeseos = servicioListaDeseos;
	}

}
