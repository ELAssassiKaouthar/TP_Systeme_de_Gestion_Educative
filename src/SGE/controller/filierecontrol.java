package SGE.controller;

import SGE.models.Filiere;
import SGE.services.DB;
import SGE.services.departementservice;
import SGE.services.enseignantservice;
import SGE.services.filiereservice;

import static SGE.Main.*;

public class filierecontrol {
    public static void showMenu(){
        System.out.println("[ Filieres ]");
        System.out.println("1: Pour ajouter une filiere");
        System.out.println("2: Pour afficher les filieres");
        System.out.println("3: Pour modifier une filiere");
        System.out.println("4: Pour supprimer une filiere");
        System.out.println("0: Pour retourner au menu principal");

        //("Veuillez sélectionner une option : ")
        int option = getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createFiliere();
                break;
            case 2:
                showFiliere();
                break;
            case 3:
                editFiliere();
                break;
            case 4:
                destroyFiliere();
                break;
            default:
                showPrincipalMenu();
        }}
    public static void showFiliere(){
        for(Filiere filiere : DB.filieres){
            System.out.println("Id :" +filiere.getId());
            System.out.println("Intitule :"+filiere.getIntitule());
            if(!isNull(filiere.getResponsable())){
                System.out.println(" responsable :"+filiere.getResponsable().getNom()+" "
                        +filiere.getResponsable().getPrenom());
            }
            if(!isNull(filiere.getDepartement())){
                System.out.println(" departement :"+filiere.getDepartement().getIntitule()+" "
                        +filiere.getDepartement().getId());
            }
        /*   if(!isNull(filiere.getModules())){
                System.out.println("Modules :"+filiere.getModules().getId()+" "
                        +filiere.getModules().getIntitule());
            }*/
            System.out.println(" ");


        }

    }
    public static void createFiliere(){
        String intitule =getStringInput("entrez l'intitule :");
        enseignantcontrol.showEnseignant();
        departementcontrol.showDepartements();
        int id=getIntInput("selectionnez un enseignant par id:");
        int idp=getIntInput("selectionnez un departement par id:");
       int idm=getIntInput("selectionnez un module par id:");
        /*filiereservice.addFiliere(intitule, enseignantservice.getEnsById(id),departementservice.getDeptById(idp),
                 moduleservice.getModuleById(idm));*/


        showFiliere();
        showMenu();
    }
    public static void editFiliere(){
        showFiliere();
        int id=getIntInput("selectionnez une filiere par id :");
        String intitule=getStringInput("entrez l'intitule :");
        enseignantcontrol.showEnseignant();
        departementcontrol.showDepartements();
        int idEns=getIntInput("selectionnez un enseignant par id:");
        int idDpt=getIntInput("selectionnez un departement par id:");
        filiereservice.updateFiliere(id,intitule,enseignantservice.getEnsById(idEns),
                departementservice.getDeptById(idDpt));

        showFiliere();
        showMenu();
    }
    public static void destroyFiliere(){
        showFiliere();
        int id=getIntInput("Sélecionnez une filiere par id :");
        filiereservice.deleteFiliereById(id);
        showFiliere();
    }
}
