package idao;

import java.util.List;

import model.Especificaciones;

public interface ISpecs {

	public List<Especificaciones> getAllSpecs();
	public boolean createSpec(String desc, double horas, int idProject, int sprint);
	public boolean existSpec(String desc, double horas,int idProject,int sprint);
	
}
