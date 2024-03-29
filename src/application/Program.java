package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
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

        System.out.println("=== TEST 3: seller findByAll ===");
        list = sellerDao.findAll();

        for(Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("=== TEST 4: seller Inser ===");
        Seller newSeller = new Seller(null, "Carlos", "Carlos@gmail", new Date(), 5000.0, department );
        sellerDao.insert(newSeller);

        System.out.println("Usuario inserido! Dados: " + newSeller.getId());

        System.out.println("=== TEST 5: Update Seller hehehe ===");
        seller = sellerDao.findById(1); //procurar um vendedor para atualiar
        seller.setName("Carlão bolado");
        sellerDao.update(seller);

        System.out.println("Atualizou!!!");

        System.out.println("=== TEST 6: DELETe Seller hehehe ===");
        System.out.print("Entre com o Id a ser deletado; ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);

        System.out.println("Deletadoooo!!");


    }
}
