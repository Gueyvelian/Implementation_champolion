package champollion;

import java.util.Date;

public class Intervention {
    protected Date debut;
    protected int duree;
    protected boolean annulee;
    private int heureDebut;
    protected TypeIntervention typeIntervention;

    public Intervention(Date debut, int duree, int heureDebut, TypeIntervention typeIntervention) {
        this.debut = debut;
        this.duree = duree;
        this.heureDebut = heureDebut;
        this.annulee = false;
        this.typeIntervention = typeIntervention;
    }
}
