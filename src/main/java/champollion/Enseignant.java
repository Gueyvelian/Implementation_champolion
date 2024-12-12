package champollion;

import java.util.Date;
import java.util.LinkedList;

import static java.lang.Math.round;

/**
 * Un enseignant est caractérisé par les informations suivantes : son nom, son adresse email, et son service prévu,
 * et son emploi du temps.
 */
public class Enseignant extends Personne {

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML
    LinkedList <ServicePrevu> listeHueres = new LinkedList<>();
    float heure_totale = 0;
    LinkedList <Intervention> listeHueresPlanifies = new LinkedList<>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        float somme_des_heure_prevues = 0;
        for (int i = 0; i < listeHueres.size(); i++) {
            somme_des_heure_prevues = (float) (somme_des_heure_prevues + listeHueres.get(i).volumeCM*1.5 + listeHueres.get(i).volumeTD + listeHueres.get(i).volumeTP*0.75);
        }
        return round(somme_des_heure_prevues);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        ServicePrevu ue1;
        float anciene_heure = heure_totale;
        for (int i = 0; i < listeHueres.size(); i++) {
            ue1 = listeHueres.get(i);
            String intituleUe1=ue1.intitule;
            if (intituleUe1.equals(ue.getIntitule())) {
                heure_totale = (float) (listeHueres.get(i).volumeCM*1.5 + listeHueres.get(i).volumeTD + listeHueres.get(i).volumeTP*0.75);
            }
        }
        return round(heure_totale)+round(anciene_heure);

    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magistral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        this.listeHueres.add(new ServicePrevu(volumeCM, volumeTD, volumeTP, ue.getIntitule()));
        //throw new UnsupportedOperationException("Pas encore implémenté");
    }

    public boolean enSousService(){
        if (heuresPrevues() < 192){
            return true;
        }else{
            return false;
        }
    }

    /*public void ajouteIntervention(Intervention inter){
        TypeIntervention type = inter.typeIntervention;
        if (type.equals(CM)){
            if ()
        }
    }*/

    public int resteAPlanifier(UE ue, TypeIntervention type){
        if (heuresPrevuesPourUE(ue) > heuresPrevues()){
            return heuresPrevuesPourUE(ue) - heuresPrevues();
        }
        else{
            return 0;
        }
    }
}
