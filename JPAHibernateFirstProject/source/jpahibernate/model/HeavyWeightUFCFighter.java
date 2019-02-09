package jpahibernate.model;

import javax.persistence.Entity;

/**
 * Created by kings on 05-Mar-17.
 */
@Entity
public class HeavyWeightUFCFighter extends UFCFighter {
    private String legendOfThisDivision;

    public String getLegendOfThisDivision() {
        return legendOfThisDivision;
    }

    public void setLegendOfThisDivision(String legendOfThisDivision) {
        this.legendOfThisDivision = legendOfThisDivision;
    }

    @Override
    public Double getWeight(){
        return 245.00;
    }
}
