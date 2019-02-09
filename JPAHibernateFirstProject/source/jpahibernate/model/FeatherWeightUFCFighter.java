package jpahibernate.model;

import javax.persistence.Entity;

/**
 * Created by kings on 05-Mar-17.
 */
@Entity
public class FeatherWeightUFCFighter extends UFCFighter{
    private String championOfTheDivision;

    public String getChampionOfTheDivision() {
        return championOfTheDivision;
    }

    public void setChampionOfTheDivision(String championOfTheDivision) {
        this.championOfTheDivision = championOfTheDivision;
    }

    @Override
    public Double getWeight(){
        return 135.00;
    }
}
