package SGE.login;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnexionDB {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-connexion","root","KATA#sitd2023");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from etudiants");
            while (resultSet.next()){
                System.out.println(resultSet.getString("nom"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/

import java.sql.*;

import static SGE.Main.getIntInput;
import static SGE.Main.getStringInput;


public class ConnexionDB {


    static Connection cnx;
    static Statement st;
    static ResultSet rst;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
updateEtd();
    }

    public static Connection  connecterDB(){
        try{
            /*  Class.forName("com.mysql.jdbc.Driver");*/

            String url="jdbc:mysql://localhost:3306/jdbc-connexion";
            String user="root";
            String password="KATA#sitd2023";
            Connection cnx=DriverManager.getConnection(url,user,password);
            System.out.println("Connexion bien établié");
            return cnx;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void addEtd() {
        int id=getIntInput("entrez un id:");
        String nom =getStringInput("entrez le nom :");
        String prenom =getStringInput("entrez le prenom :");
        String email =getStringInput("entrez l'email:");
        int apogee =getIntInput("entrez le code apogee:");
        int id_fil=getIntInput("entrez un id du filiere:");
        int note=getIntInput("entrez la note:");

        /* filierecontrol.showFiliere();*/


        try{
            String query="INSERT INTO etudiants (idEtudiants,nom,prenom,email,apogee,filiere,note) VALUES('"+id+"','"+nom+"','"
                    +prenom+"','"+email+"','"+apogee+"','"+id_fil+"','"+note+"')";
            cnx=connecterDB();
            st=cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("student well added");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void updateEtd(){
        int id=getIntInput("entrez un id:");
        String nom =getStringInput("entrez le nom :");
        String prenom =getStringInput("entrez le prenom :");
        String email =getStringInput("entrez l'email:");
        int apogee =getIntInput("entrez le code apogee:");
       String filiere=getStringInput("entrez un id du filiere:");
        int note=getIntInput("entrez la note:");
        try{
            String query="UPDATE etudiants  SET nom='"+nom
                    +"', prenom='"+prenom
                    +"', email='"+email
                    +"', apogee='"+apogee
                    +"', filiere='"+filiere
                    +"', note='"+note
                    +"' WHERE idEtudiants="+id;
            cnx=connecterDB();
            st=cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("well-modified student");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteEtdById(){
        int id=getIntInput("entrez un id:");
        try{
            String query="DELETE FROM etudiants  WHERE idEtudiants="+id;
            cnx=connecterDB();
            st=cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("well deleted student");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void getEtdById() {
        int id = getIntInput("entrez un id:");
        try {
            String query = "SELECT * FROM etudiants WHERE idEtudiants='" + id + "'";
            cnx = connecterDB();
            st = cnx.createStatement();
            rst = st.executeQuery(query);
            rst.next();
            int nbrRow = rst.getRow();
            if (nbrRow != 0) {
                System.out.println("student well found");
            } else {
                System.out.println("student not found");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void getTeachById() {
        int id=getIntInput("entrez l'id du teacher:");
        try{
            String query="SELECT * FROM enseignants WHERE id_ens='"+id+"'";
            cnx=connecterDB();
            st=cnx.createStatement();
            rst= st.executeQuery(query);
            rst.next();
            int nbrRow = rst.getRow();
            if(nbrRow!=0){
                System.out.println("teacher well found");
            }else{
                System.out.println("teacher not found");

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateteach(){
        int id=getIntInput("entrez l'id du teacher:");
        String nom =getStringInput("entrez le nom du teacher :");
        String prenom =getStringInput("entrez le prenom du teacher:");
        String email =getStringInput("entrez l'email du teacher:");
        String grade =getStringInput("entrez la grade du teacher:");
        try{
            String query="UPDATE enseignants SET nom='"+nom
                    +"', prenom='"+prenom

                    +"', email='"+email
                    +"', grade='"+grade
                    +"' WHERE id_ens="+id;
            cnx=connecterDB();
            st=cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("well-modified teacher");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addTeach() {
        String nom =getStringInput("entrez le nom du teacher :");
        String prenom =getStringInput("entrez le prenom du teacher:");
        String email =getStringInput("entrez l'email du teacher:");
        String grade =getStringInput("entrez la grade du teacher:");
        try{
            String query="INSERT INTO enseignants (nom,prenom,email,grade) VALUES('"+nom+"','"
                    +prenom+"','"+email+"','"+grade+"')";
            cnx=connecterDB();
            st=cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("well added teacher");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void deleteTeachById(){
        int id=getIntInput("entrez un id du teacher:");
        try{
            String query="DELETE FROM enseignants WHERE id_ens="+id;
            cnx=connecterDB();
            st=cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("well deleted teacher");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
