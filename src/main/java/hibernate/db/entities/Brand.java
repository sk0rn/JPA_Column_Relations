package hibernate.db.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="brand")
public class Brand {
    private Long id;
    private String name;
    private String country;
    private List<Mobile> mobiles;

    public Brand() {
    }

    public Brand(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Brand(String name, String country) {
        this.name = name;
        this.country = country;
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    public List<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<Mobile> mobiles) {
        this.mobiles = mobiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Brand brand = (Brand) o;

        if (id != null ? !id.equals(brand.id) : brand.id != null) return false;
        if (name != null ? !name.equals(brand.name) : brand.name != null) return false;
        return country != null ? country.equals(brand.country) : brand.country == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
               // ", mobiles=" + mobiles +
                '}';
    }
}
