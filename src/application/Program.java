package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();  //pra instaciar é só chamar a fabrica(factory) assim ele reocnhece a interface*INjeção de dependencia*

        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

    }
}
