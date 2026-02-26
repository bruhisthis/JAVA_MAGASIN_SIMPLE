import java.util.ArrayList;
class Produit{
    private int id;
    private String nom;
    private double prix;
    private int quantite;
    public Produit(int a ,String b,float c,int d){
        this.id = a;
        this.nom = b;
        this.prix = c;
        this.quantite = d;
    }
    public void afficherDetails(){
        System.out.println("id: "+this.id+" nom: "+this.nom+" prix: "+this.prix+"quantite: "+this.quantite);
    }
    public double calculerValeurStock(){
        return prix*(double)quantite;
    }
}

class Magasin{
    private ArrayList<Produit> listProduits;
    public  Magasin(){
        this.listProduits = new ArrayList<>();
    }
    public void ajouterProduit(Produit p){
        this.listProduits.add(p);
    }
    public  void afficherTousLesProduits(){
        for(Produit p:listProduits){
            p.afficherDetails();
        }
    }
    public void afficherValeurTotalStock(){
        double total = 0;
        for(Produit p:listProduits){
            total += p.calculerValeurStock();
        }
        System.out.println("total de stock de magasin : " +total);
    }

}
public class GestionDeProduit {
    public static void main(String[] a){
        Magasin monMagasin = new Magasin();
        Produit p1 = new Produit(001, "phone", 1000, 100);
        Produit p2 = new Produit(002,"laptop",3000,20);
        Produit p3 = new Produit(003, "sdd", 300, 1000);
        monMagasin.ajouterProduit(p1);
        monMagasin.ajouterProduit(p2);
        monMagasin.ajouterProduit(p3);
        monMagasin.afficherTousLesProduits();
        monMagasin.afficherValeurTotalStock();
    }
}
