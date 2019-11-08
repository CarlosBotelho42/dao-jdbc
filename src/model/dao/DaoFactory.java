package model.dao;

import model.dao.impl.SellerDapJDBC;

public class DaoFactory  {

    public static  SellerDao createSellerDao(){
        return new SellerDapJDBC();

    }


}
