package jpahibernate.model;

import javax.persistence.*;

/**
 * Created by kings on 25-Feb-17.
 */

@Entity
public class Person {
    @Id
    @SequenceGenerator(name = "seqGenerator5", sequenceName = "practice_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator5")
    private Integer Id;
    private String name;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "street", column = @Column(name = "shipping_Street"))
    )
    private Address shippingAddress;

    public Person() {
    }

    public Person(String name, Address shippingAddress) {
        this.name = name;
        this.shippingAddress = shippingAddress;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
