package lk.ijse.jewelryshoprmi.business.custom.impl;

import lk.ijse.jewelryshoprmi.business.custom.JewelryBO;
import lk.ijse.jewelryshoprmi.dto.JewelryDTO;
import lk.ijse.jewelryshoprmi.entity.Jewelry;
import lk.ijse.jewelryshoprmi.repository.RepoFactory;
import lk.ijse.jewelryshoprmi.repository.custom.JewelryRepo;
import lk.ijse.jewelryshoprmi.resources.HibernateUtill;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class JewelryBOImpl implements JewelryBO {
    private JewelryRepo jewelryRepo;
    public JewelryBOImpl() {
        jewelryRepo=RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.JEWELRY);
    }

    @Override
    public boolean addJewelry(JewelryDTO dto) throws Exception {
        boolean response=false;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            jewelryRepo.setSession(session);
            session.beginTransaction();

             response= jewelryRepo.add(new Jewelry(dto.getId(),dto.getName(),dto.getMetal(),dto.getCarate(),dto.getWeight(),dto.getSize(),dto.getPrice()));

            session.getTransaction().commit();
        }
        return response;
    }

    @Override
    public boolean deleteJewelry(JewelryDTO dto) throws Exception {
        boolean response=false;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            jewelryRepo.setSession(session);
            session.beginTransaction();

            response= jewelryRepo.delete(new Jewelry(dto.getId(),dto.getName(),dto.getMetal(),dto.getCarate(),dto.getWeight(),dto.getSize(),dto.getPrice()));

            session.getTransaction().commit();
        }
        return response;
    }

    @Override
    public boolean updateJewelry(JewelryDTO dto) throws Exception {
        boolean response=false;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            jewelryRepo.setSession(session);
            session.beginTransaction();

            response= jewelryRepo.update(new Jewelry(dto.getId(),dto.getName(),dto.getMetal(),dto.getCarate(),dto.getWeight(),dto.getSize(),dto.getPrice()));

            session.getTransaction().commit();
        }
        return response;
    }

    @Override
    public JewelryDTO searchJewelry(String id) throws Exception {
        Jewelry response=null;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            jewelryRepo.setSession(session);
            session.beginTransaction();

             response= jewelryRepo.search(id);

            session.getTransaction().commit();
        }
        return new JewelryDTO(response.getId(),response.getName(),response.getMetal(),response.getCarate(),response.getWeight(),response.getSize(),response.getPrice());
    }

    @Override
    public List<JewelryDTO> getAllJewelry() throws Exception {
        List<Jewelry> list=null;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            jewelryRepo.setSession(session);
            session.beginTransaction();

            list = jewelryRepo.getAll();

            session.getTransaction().commit();
        }
        ArrayList<JewelryDTO> jewelryDTOS=new ArrayList<>();
        for (Jewelry response : list) {
            jewelryDTOS.add(new JewelryDTO(response.getId(),response.getName(),response.getMetal(),response.getCarate(),response.getWeight(),response.getSize(),response.getPrice()));
        }
        return jewelryDTOS;
    }
}
