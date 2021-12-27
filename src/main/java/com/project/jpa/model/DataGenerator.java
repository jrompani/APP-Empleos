package com.project.jpa.model;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


public class DataGenerator {


	List<Vacante> listaVacantes = new LinkedList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public DataGenerator() {
		super();
		this.listaVacantes = this.generateData();
	}

	
	public List<Vacante> generateData() {
		try {
			
			List<Vacante> sda = new LinkedList<>();

			//VACANTE1
			Vacante vacante1 = new Vacante();
			vacante1.setId(1L);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setFecha(sdf.parse("05-03-2020"));
			vacante1.setDescripcion("Solicitamos Ing. Civil para diseñar puente peatonal");
			vacante1.setDestacado(1);
			vacante1.setStatus("Aprobado");
			vacante1.setImagen("coca.png");
			vacante1.setSalario(8500.0);
			//VACANTE2
			Vacante vacante2 = new Vacante();
			vacante2.setId(2L);
			vacante2.setNombre("Contador Público");
			vacante2.setStatus("Aprobado");
			vacante2.setFecha(sdf.parse("09-02-2020"));
			vacante2.setDescripcion("Importante empresa solicita contador con 5 años de experiencia titulado");
			vacante2.setDestacado(0);
			vacante2.setImagen("steeve.png");
			vacante2.setSalario(8500.0);
			//VACANTE3
			Vacante vacante3 = new Vacante();
			vacante3.setId(3L);
			vacante3.setNombre("Ingeniero Eléctrico");
			vacante3.setStatus("Aprobado");
			vacante3.setFecha(sdf.parse("09-02-2019"));
			vacante3.setDescripcion("Empresa internacional solicita Ingeniero mecánico para mantenimiento de la instalación eléctrica");
			vacante3.setDestacado(0);
			vacante3.setImagen("no-image.png");
			vacante3.setSalario(8500.0);
			//VACANTE4
			Vacante vacante4 = new Vacante();
			vacante4.setId(4L);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setStatus("Aprobado");
			vacante4.setFecha(sdf.parse("09-02-2020"));
			vacante4.setDescripcion("Solicitamos diseñador gráfico para diseñar cartelería para calles");
			vacante4.setDestacado(1);
			vacante4.setImagen("usa.png");
			vacante4.setSalario(8500.0);
			
			sda.add(vacante1);
			sda.add(vacante2);
			sda.add(vacante3);
			sda.add(vacante4);
			return sda;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;


	}
	
	
}



