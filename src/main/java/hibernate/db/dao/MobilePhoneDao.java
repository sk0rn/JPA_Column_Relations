package hibernate.db.dao;

import hibernate.db.entities.Brand;
import hibernate.db.entities.Document;
import hibernate.db.entities.MobileStore;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import hibernate.db.entities.Mobile;

import java.util.Arrays;

@Repository
public class MobilePhoneDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MobilePhoneDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Mobile getPhoneById(long id) {
        Session session = sessionFactory.openSession();
        Mobile mobile = session.get(Mobile.class, id);
        Brand brand = new Brand(13L, "Siemens", "Germany");
        Mobile mob2 = new Mobile(5L, "m35", 20000,
                brand);
        mob2.addStore(new MobileStore(10L, "Best store"));
        brand.setMobiles(Arrays.asList(mob2));

        session.close();
        return mobile;
    }

    public void addPhone(Mobile mobile) {
        Session session = sessionFactory.openSession();
        session.refresh(mobile.getBrand()); // если привязываться к имеющимуся id
        session.beginTransaction();
        session.save(mobile);
        mobile.setCost(30000);
        session.getTransaction().commit();
        session.close();
    }

    public void addStore(Mobile mobile) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(mobile.getBrand());
        session.save(mobile);
        session.getTransaction().commit();
        session.close();
    }

    public void addDocument(Document document, Mobile mobile) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        mobile = session.get(Mobile.class, mobile.getId());
        mobile.setDocument(document);
        session.saveOrUpdate(mobile);
        session.getTransaction().commit();
        session.close();
    }

    public void updatePhone(Mobile mobile) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(mobile);
        session.getTransaction().commit();
        session.close();
    }

    public void deletePhoneById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Mobile phone = session.get(Mobile.class, id);
        session.delete(phone);
        session.getTransaction().commit();
        session.close();

    }

    public Document getDocById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Document doc = session.get(Document.class, id);
        session.close();
        return doc;

    }
}
