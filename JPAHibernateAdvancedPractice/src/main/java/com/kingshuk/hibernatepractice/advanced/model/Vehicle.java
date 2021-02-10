package com.kingshuk.hibernatepractice.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "vehicle_kingshuk_test")
@DynamicUpdate
@DynamicInsert
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String vin;

    private String program;

    private String team;

    private String build;

    private String auth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return (this.id == vehicle.getId()) || new EqualsBuilder()
                .append(this.vin, vehicle.getVin())
                .append(this.program, vehicle.getProgram())
                .append(this.team, vehicle.getTeam())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.vin)
                .append(this.program)
                .append(this.team)
                .toHashCode();
    }
}
