package hibernate.db.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store")
public class MobileStore {
    private Long id;
    private String name;
    private Set<Mobile> mobiles;

    {
        this.mobiles = new HashSet<>();
    }

    public MobileStore() {
    }

    public MobileStore(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MobileStore(String name) {
        this.name = name;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "mobile_stores",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "mobile_id"))
    public Set<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(Set<Mobile> mobiles) {
        this.mobiles = mobiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileStore store = (MobileStore) o;

        if (id != null ? !id.equals(store.id) : store.id != null) return false;
        return name != null ? name.equals(store.name) : store.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MobileStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", mobiles=" + mobiles +
                '}';
    }
}
