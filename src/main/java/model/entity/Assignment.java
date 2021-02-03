package model.entity;

import java.time.LocalDate;
import java.util.Set;

public class Assignment {

    private long id;
    private AssignmentType type;
    private String name;
    private LocalDate date;
    private int quantity;
    private int doneTimes;
    private String currentDiagnosis;
    private String notes;
    private  boolean isComplete = false;
    private long cardId;

    public Assignment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AssignmentType getType() {
        return type;
    }

    public void setType(AssignmentType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDoneTimes() {
        return doneTimes;
    }

    public void setDoneTimes(int doneTimes) {
        this.doneTimes = doneTimes;
    }

    public String getCurrentDiagnosis() {
        return currentDiagnosis;
    }

    public void setCurrentDiagnosis(String currentDiagnosis) {
        this.currentDiagnosis = currentDiagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean complete) {
        isComplete = complete;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", quantity=" + quantity +
                ", doneTimes=" + doneTimes +
                ", currentDiagnosis='" + currentDiagnosis + '\'' +
                ", notes='" + notes + '\'' +
                ", isComplete=" + isComplete +
                ", cardId=" + cardId +
                '}';
    }
}
