package model.entity;


public class MedicalCard {

    private long id;

    private String finalDiagnosis;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFinalDiagnosis() {
        return finalDiagnosis;
    }

    public void setFinalDiagnosis(String finalDiagnosis) {
        this.finalDiagnosis = finalDiagnosis;
    }

    @Override
    public String toString() {
        return "MedicalCard{" +
                "id=" + id +
                ", finalDiagnosis='" + finalDiagnosis + '\'' +
                '}';
    }
}

