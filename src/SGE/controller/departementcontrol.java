package SGE.controller;

import SGE.models.Departement;
import SGE.services.DB;
import SGE.services.departementservice;
import SGE.services.enseignantservice;

import static SGE.Main.*;
import static java.util.Objects.isNull;

public class departementcontrol {


    public static void showMenu(){
        System.out.println("[ Départements ]");
        System.out.println("1: Pour ajouter un département");
        System.out.println("2: Pour afficher les départements");
        System.out.println("3: Pour modifier un département");
        System.out.println("4: Pour supprimer un département");
        System.out.println("0: Pour retourner au menu principal");

        //"Veuillez sélectionner une option : ")
        int option = getIntInput("Veuillez sélectionner une option : ");
        switch(option) {
            case 1:
                createDepartement();
                break;
            case 2:
                showDepartements();
                break;
            case 3:
                editDepartement();
                break;
            case 4:
                destroyDepartement();
                break;
            default:
                showPrincipalMenu();
        }}
    public static void showDepartements(){
        for(Departement departement : DB.departements){
            System.out.println("Id :" +departement.getId());
            System.out.println("Intitule :"+departement.getIntitule());
            if(!isNull(departement.getResponsable())){
                System.out.println(" responsable :"+departement.getResponsable().getNom()+" "+
                        departement.getResponsable().getPrenom());
            }
            System.out.println(" ");
        }

    }
    public static void createDepartement(){
        String intitule =getStringInput("entrez l'intitule :");
        enseignantcontrol.showEnseignant();
        int id=getIntInput("selectionnez un enseignant par id:");
        departementservice.addDept(intitule, enseignantservice.getEnsById(id));
        showDepartements();
        showMenu();
    }
    public static void editDepartement(){
        showDepartements();
        int id = getIntInput("selectionnez un departement par id :");
        String intitule=getStringInput("entrez l'intitule :");
        enseignantcontrol.showEnseignant();
        int idEns=getIntInput("selectionnez un enseignant par id:");
        departementservice.updateDept(id,intitule,enseignantservice.getEnsById(idEns));

        showDepartements();
        showMenu();
    }
    public static void destroyDepartement(){
        showDepartements();
        int id=getIntInput("Sélecionnez un departement par id :");
        departementservice.deleteDeptById(id);
        showDepartements();
    }
}
