package hibernate.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "document")
public class Document {
    private Long doc_id;
    private String name;

    private Mobile mobile;

    public Document() {
    }

    public Document(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "document", fetch = FetchType.EAGER)
    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id", nullable = false, insertable = true, updatable = true)
    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (doc_id != null ? !doc_id.equals(document.doc_id) : document.doc_id != null) return false;
        if (name != null ? !name.equals(document.name) : document.name != null) return false;
        return mobile != null ? mobile.equals(document.mobile) : document.mobile == null;
    }

    @Override
    public int hashCode() {
        int result = doc_id != null ? doc_id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "doc_id=" + doc_id +
                ", name='" + name + '\'' +
                ", mobile=" + mobile +
                '}';
    }
}
