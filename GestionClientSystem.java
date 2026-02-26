import java.util.ArrayList;
import java.io.*;

class Client{
    private int id;
    private String nom;
    private String email;
    Client(int i,String n,String e){
        this.id = i;
        this.nom = n;
        this.email = e;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
class GestionClient{
    private ArrayList<Client> listClients;
    GestionClient(){
        listClients = new ArrayList<>();
    }
    public void ajouterClient(Client c){
        listClients.add(c);
    }
    public boolean cheekExistants(String chemin,int id){
        boolean temps = false;
        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            String line;
            while ((line = br.readLine())!=null) {
                String[] args = line.split(" ");
                if(Integer.parseInt(args[0]) == id){
                    System.out.println("id is exit: "+args[0]);
                    temps =true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temps;
    }
    public void sauvegarderDansFichier(String chemin){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt",true))) {
            int id;
            String nom,email;
            for(Client c:listClients){
                id = c.getId();
                if(!cheekExistants(chemin, id)){
                    nom = c.getNom();
                    email = c.getEmail();
                    bw.write(id+" "+nom+" "+email);
                    bw.newLine();
                    listClients.remove(c);
                    System.out.println("added sucssusffuly");
                }else{
                    continue;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("completed saving in file");
        }
    }
    public void chargerDepuisFichier(String chemin){
        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            String line;
            int linescount = 0;
            while ((line = br.readLine())!=null) {
                linescount++;
                System.out.println(linescount+"-"+line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class GestionClientSystem {
    public static void main(String[] a){
        GestionClient gl1 = new GestionClient();
        Client c1 = new Client(001,"tomas", "tomas3@gmail.com");
        Client c2 = new Client(002,"rahang","Non email");
        Client c3 = new Client(003,"adam","adamadam33amdam@gmail.com");
        gl1.ajouterClient(c1);
        gl1.sauvegarderDansFichier("test.txt");
        gl1.ajouterClient(c2);
        gl1.ajouterClient(c3);
        gl1.sauvegarderDansFichier("test.txt");
        gl1.chargerDepuisFichier("test.txt");
        
    }
}
