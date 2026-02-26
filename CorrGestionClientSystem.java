import java.util.ArrayList;
import java.io.*;

class Client {
    private int id;
    private String nom;
    private String email;
    
    Client(int i, String n, String e) {
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
    
    @Override
    public String toString() {
        return id + " " + nom + "\t|\t" + email;
    }
}

class GestionClient {
    private ArrayList<Client> listClients;
    
    GestionClient() {
        listClients = new ArrayList<>();
    }
    
    public void ajouterClient(Client c) {
        listClients.add(c);
    }
    public boolean clientExisteDansFichier(String chemin, int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] args = line.split(" ");
                if (args.length > 0 && Integer.parseInt(args[0]) == id) {
                    System.out.println("Client avec ID " + id + " existe déjà dans le fichier.");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé, il sera créé.");
            return false;
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public void sauvegarderDansFichier_Solution1(String chemin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(chemin, true))) {

            for (int i = listClients.size() - 1; i >= 0; i--) {
                Client c = listClients.get(i);
                int id = c.getId();
                
                if (!clientExisteDansFichier(chemin, id)) {
                    String nom = c.getNom();
                    String email = c.getEmail();
                    bw.write(id + " " + nom + "\t|\t" + email);
                    bw.newLine();
                    listClients.remove(i); 
                    System.out.println("Client " + id + " ajouté avec succès.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Sauvegarde terminée.");
        }
    }
    public void sauvegarderDansFichier_Solution2(String chemin) {
        ArrayList<Client> clientsASauvegarder = new ArrayList<>();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(chemin, true))) {

            for (Client c : listClients) {
                if (!clientExisteDansFichier(chemin, c.getId())) {
                    bw.write(c.toString());
                    bw.newLine();
                    clientsASauvegarder.add(c);
                    System.out.println("Client " + c.getId() + " ajouté avec succès.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde: " + e.getMessage());
            e.printStackTrace();
        }
        
        for (Client c : clientsASauvegarder) {
            listClients.remove(c);
        }
        
        System.out.println("Sauvegarde terminée. " + clientsASauvegarder.size() + " clients sauvegardés.");
    }
    
    public void sauvegarderDansFichier_Solution3(String chemin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(chemin, true))) {
            for (Client c : listClients) {
                if (!clientExisteDansFichier(chemin, c.getId())) {
                    bw.write(c.toString());
                    bw.newLine();
                    System.out.println("Client " + c.getId() + " ajouté avec succès.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Sauvegarde terminée.");
        }
    }
    
    public void chargerDepuisFichier(String chemin) {
        System.out.println("\n=== Contenu du fichier " + chemin + " ===");
        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            String line;
            int linescount = 0;
            while ((line = br.readLine()) != null) {
                linescount++;
                System.out.println(linescount + " - " + line);
            }
            if (linescount == 0) {
                System.out.println("Le fichier est vide.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé: " + chemin);
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("=====================================\n");
    }
    
    public int getNombreClients() {
        return listClients.size();
    }
}

public class CorrGestionClientSystem {
    public static void main(String[] args) {
        GestionClient gl1 = new GestionClient();
        
        Client c1 = new Client(1, "tomas", "tomas3@gmail.com");
        Client c2 = new Client(2, "rahang", "rahang@example.com");
        Client c3 = new Client(4, "mozan", "mozanadam33amdam@gmail.com");
        
        System.out.println("=== Test avec Solution 2 (Recommandée) ===\n");
        
        gl1.ajouterClient(c1);
        System.out.println("Clients en mémoire: " + gl1.getNombreClients());
        gl1.sauvegarderDansFichier_Solution2("test.txt");
        System.out.println("Clients en mémoire après sauvegarde: " + gl1.getNombreClients());
        
        gl1.ajouterClient(c2);
        gl1.ajouterClient(c3);
        System.out.println("\nClients en mémoire: " + gl1.getNombreClients());
        gl1.sauvegarderDansFichier_Solution2("test.txt");
        System.out.println("Clients en mémoire après sauvegarde: " + gl1.getNombreClients());
        
        gl1.chargerDepuisFichier("test.txt");
    }
}