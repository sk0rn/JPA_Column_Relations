package hibernate.controller;

import hibernate.db.entities.Brand;
import hibernate.db.entities.Document;
import hibernate.db.entities.MobileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import hibernate.db.dao.MobilePhoneDao;
import hibernate.db.entities.Mobile;

@Controller
public class PhoneController {

    private MobilePhoneDao mobilePhoneDao;

    @Autowired
    public void setMobilePhoneDao(MobilePhoneDao mobilePhoneDao) {
        this.mobilePhoneDao = mobilePhoneDao;
    }

    @RequestMapping(value = "/getPhone", method = RequestMethod.GET)
    public String getAllPhones(Model model) {
        model.addAttribute("phone", mobilePhoneDao.getPhoneById(2));
        return "data";
    }

    @RequestMapping(value = "/createPhone", method = RequestMethod.GET)
    public String createPhone(Model model) {
        Mobile phone = new Mobile("m35", 20000,
                new Brand(5L,null, null));
        mobilePhoneDao.addPhone(phone);
        model.addAttribute("phone", phone);
        return "data";
    }

    @RequestMapping(value = "/addStore", method = RequestMethod.GET)
    public String addStore(Model model) {
        Mobile phone = new Mobile("m35", 20000,
                new Brand("Siemens", "Germany"));
        MobileStore store = new MobileStore("Best store");
        phone.addStore(store);
        mobilePhoneDao.addStore(phone);
        model.addAttribute("phone", phone);
        return "data";
    }

    @RequestMapping(value = "/addDocument", method = RequestMethod.GET)
    public String addDoc(Model model) {
        Mobile mobile = new Mobile(2L,null, null,
                new Brand(5L, null, null));
        Document doc = new Document("doc1");
        mobilePhoneDao.addDocument(doc, mobile);
        model.addAttribute("doc", doc);
        return "data";
    }

    @RequestMapping(value = "/updatePhone", method = RequestMethod.GET)
    public String updatePhone(Model model) {
        Mobile phone = mobilePhoneDao.getPhoneById(1);
        phone.setCost(25000);
        mobilePhoneDao.updatePhone(phone);
        model.addAttribute("phone", phone);
        return "data";
    }


    @RequestMapping(value = "/deletePhone", method = RequestMethod.GET)
    public String deletePhone(Model model) {
        mobilePhoneDao.deletePhoneById(1L);
        model.addAttribute("phone", mobilePhoneDao.getPhoneById(2));
        return "data";
    }

    @RequestMapping(value = "/getDocument", method = RequestMethod.GET)
    public String getDoc(Model model) {
        model.addAttribute("doc", mobilePhoneDao.getDocById(2));
        return "data";
    }
}
