import java.util.ArrayList;

abstract class Employe{
    protected String nom;
    protected double salairebase;
    Employe(String a,double b){
        this.nom = a;
        this.salairebase = b;
    }
    abstract public double calculerSalaire();
    public String getNOM(){
        return this.nom;
    }
}

class EmployePermanent extends Employe{
    private double primeFixe;
    EmployePermanent(String a,double b,double c){
        super(a,b);
        this.primeFixe  = c;
    }
    @Override
    public double calculerSalaire(){
        return this.salairebase+this.primeFixe;
    }
}

class EmployeTemporaire extends Employe{
    private int nomberDeJoursTraivailles;
    EmployeTemporaire(String a,double b, int c){
        super(a, b);
        this.nomberDeJoursTraivailles = c;
    }
    @Override
    public double calculerSalaire(){
        return this.salairebase*(double)this.nomberDeJoursTraivailles/22;
    }
}
public class SystemEmploye {
    public static void main(String[] s){
        EmployePermanent ep1 = new EmployePermanent("adam", 2000, 1000);
        EmployePermanent ep2 = new EmployePermanent("ahmed",2500,1000);
        EmployeTemporaire et1 = new EmployeTemporaire("jhon",2000,14);
        ArrayList<EmployePermanent> listeEmployePermanents = new ArrayList<>();
        listeEmployePermanents.add(ep1);
        listeEmployePermanents.add(ep2);
        for(EmployePermanent ep:listeEmployePermanents){
            System.out.println("nom "+ep.getNOM()+"salaire: "+ep.calculerSalaire());
        }
        System.out.println("nom: "+et1.getNOM()+" salaire: "+et1.calculerSalaire());
    }
} 