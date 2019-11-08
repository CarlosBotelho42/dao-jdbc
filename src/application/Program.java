package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();  //pra instaciar é só chamar a fabrica(factory) assim ele reocnhece a interface*INjeção de dependencia*

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("=== TEST 2: seller findByDepartment ===");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.FindByDepartment(department);

        for(Seller obj : list){
            System.out.println(obj);
        }
    }
}
