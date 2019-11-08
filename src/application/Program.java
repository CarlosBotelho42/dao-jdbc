package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {

    public static void main(String[] args) {

        Department obj =  new Department(1,"Books");

        System.out.println(obj);

        Seller seller = new Seller(1,"Carlos","Carlos.carlos",new Date(),2500.0, obj);

        SellerDao sellerDao = DaoFactory.createSellerDao(); //pra instaciar é só chamar a fabrica(factory) assim ele reocnhece a interface*INjeção de dependencia*

        System.out.println(seller);



    }
}
