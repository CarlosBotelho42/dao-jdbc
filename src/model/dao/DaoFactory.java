package model.dao;

import db.DB;
import model.dao.impl.SellerDapJDBC;

public class DaoFactory  {

    public static  SellerDao createSellerDao(){
        return new SellerDapJDBC(DB.getConnection());

    }


}
