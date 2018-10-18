package hibernate.db.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mobile")
public class Mobile {
    private Long id;
    private String model;
    private Integer cost;
    private Brand brand;
    private Set<MobileStore> stores = new HashSet<>();
    private Document document;

    public Mobile() {
    }

    public Mobile(String model, Integer cost, Brand brand) {
        this.model = model;
        this.cost = cost;
        this.brand = brand;
    }

    public Mobile(Long id, String model, Integer cost, Brand brand) {
        this.id = id;
        this.model = model;
        this.cost = cost;
        this.brand = brand;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "coast")
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "mobile_stores",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "mobile_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "store_id"))
    public Set<MobileStore> getStores() {
        return stores;
    }

    public boolean addStore(MobileStore store) {
        return stores.add(store);
    }

    public void setStores(Set<MobileStore> stores) {
        this.stores = stores;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mobile mobile = (Mobile) o;

        if (cost != mobile.cost) return false;
        if (id != null ? !id.equals(mobile.id) : mobile.id != null) return false;
        if (model != null ? !model.equals(mobile.model) : mobile.model != null) return false;
        if (brand != null ? !brand.equals(mobile.brand) : mobile.brand != null) return false;
        return stores != null ? stores.equals(mobile.stores) : mobile.stores == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + cost;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (stores != null ? stores.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", cost=" + cost +
                ", brand=" + brand +
                ", stores=" + stores +
                '}';
    }
}
