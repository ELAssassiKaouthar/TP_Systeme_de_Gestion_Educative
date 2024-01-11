package SGE.controller;
import SGE.models.Module;
import SGE.services.DB;
import SGE.services.enseignantservice;
import SGE.services.filiereservice;
import SGE.services.moduleservice;

import static SGE.Main.*;

public class modulecontrol {
    public static void showMenu(){
        System.out.println("[  Modules ]");
        System.out.println("1: Pour ajouter un  module");
        System.out.println("2: Pour afficher les  modules");
        System.out.println("3: Pour modifier un  module");
        System.out.println("4: Pour supprimer un  module");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createModule();
                break;
            case 2:
              /*  showModule();*/
                break;
            case 3:
                editModule();
                break;
            case 4:
                destroyModule();
                break;
            default:
                showPrincipalMenu();
        }}
    public static void showModule(){
        for(Module module : DB.modules){
            System.out.println("Id :" +module.getId());
            System.out.println("Intitule :"+module.getIntitule());
            if(!isNull(module.getProfesseur())){
                System.out.println(" professeur :"+module.getProfesseur().getNom()+" "
                        +module.getProfesseur().getPrenom());
            }
            if(!isNull(module.getFiliere())){
                System.out.println(" filiere :"+module.getFiliere().getId()+" "
                        +module.getFiliere().getIntitule()+" "
                        +module.getFiliere().getResponsable()+" "
                        +module.getFiliere().getDepartement());
            }
            System.out.println(" ");
        }

    }
    public static void createModule(){
        String intitule =getStringInput("entrez l'intitule :");
        enseignantcontrol.showEnseignant();
        filierecontrol.showFiliere();
        int id=getIntInput("selectionnez un enseignant par id:");
        int idf=getIntInput("selectionnez une filiere par id:");
        moduleservice.addModule(intitule, enseignantservice.getEnsById(id), filiereservice.getFiliereById(idf));
        /*showModule();*/
        showMenu();
    }
    public static void editModule(){
        /*showModule();*/
        int id=getIntInput("selectionnez un module par id :");
        String intitule=getStringInput("entrez l'intitule :");
        enseignantcontrol.showEnseignant();
        filierecontrol.showFiliere();
        int idEns=getIntInput("selectionnez un enseignant par id:");
        int idFil=getIntInput("selectionnez une filiere par id:");
        moduleservice.updateModule(id,intitule,enseignantservice.getEnsById(idEns)
                ,filiereservice.getFiliereById(idFil));

       /* showModule();*/
        showMenu();
    }
    public static void destroyModule(){
        showModule();
        int id=getIntInput("Sélecionnez un module par id :");
        moduleservice.deleteModuleById(id);
        showModule();
    }
}
