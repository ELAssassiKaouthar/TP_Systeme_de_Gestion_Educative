package SGE.services;

import SGE.models.Enseignant;
import SGE.models.Filiere;
import SGE.models.Module;

import java.util.ArrayList;

import static SGE.services.DB.modules;

public class moduleservice {
    public static Module addModule(String intitule, Enseignant professeur, Filiere filiere) {

        Module module =new Module();
        module.setIntitule(intitule);
        module.setProfesseur(professeur);
        module.setFiliere(filiere);
        module.setId(DB.getModId());
        DB.modules.add(module);
        return module;

    }

    public static Module updateModule(int id , String intitule, Enseignant professeur,  Filiere filiere){

        for (Module module :DB.modules){
            if(module.getId()==id){
                module.setIntitule(intitule);

                return module;
            }
        }


        return  new Module();


    }
    public static ArrayList<Module> deleteModuleById(int id){
        modules.remove(getModuleById(id));
        return modules;
        }




    public static Module getModuleById(int id){
        for (Module module: modules){
            if (module.getId()==id)
                return module;
        }
        return  new Module();
    }

    public static ArrayList<Module> getAllModule(){
        return  DB.modules;
    }
}
