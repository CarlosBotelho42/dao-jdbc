package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;

    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
       // ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO seller "
                +"(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                + "VALUES"
                + "(?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAfected = st.executeUpdate();

            if(rowsAfected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else{
                throw new DbException ("Erro ! nem uma linha foi alterada!!!!!");
            }

        }
        catch (SQLException e){
            throw new DbException(e.getMessage());

        }
        finally {
            DB.closeStatement(st);

        }

    }

    @Override
    public void update(Seller seller) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                    + "WHERE Id = ? ");

            st.setString(1,seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());
            st.setInt(6, seller.getId());

            st.executeUpdate();

        }
        catch (SQLException e){
            throw new DbException(e.getMessage());

        }
        finally {
            DB.closeStatement(st);

        }


    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM seller "
                    + "WHERE id = ? ");

            st.setInt(1, id);

            int rows = st.executeUpdate();
            if(rows == 0){
                throw new DbException("Não foi encontrado um usuario com esse Id! ");

            }
        }
        catch (SQLException e){
            throw  new DbException(e.getMessage());

        }
        finally {
            DB.closeStatement(st);

        }

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ? ");

            st.setInt(1, id);
            rs =st.executeQuery();

            if(rs.next()){ //intanciando departamento e setou os valores dele
                Department dep = instatiationDepartment(rs);
                Seller obj =  instatiationSeller(rs, dep);

                return obj;

            }
            return null;

        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            //DB.closeConnection(); não precisa fechar a connect

        }

    }

    private Seller instatiationSeller(ResultSet rs, Department dep) throws SQLException {

        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);

        return  obj;
    }

    private Department instatiationDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));

        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "ORDER BY Name ");

            rs =st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>(); //para não repetir os datos do departamento

            while(rs.next()){ //intanciando departamento e setou os valores dele
                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null){
                    dep = instatiationDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }

                Seller obj =  instatiationSeller(rs, dep);

                list.add(obj);
            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            //DB.closeConnection(); não precisa fechar a connect
        }
    }

    @Override
    public List<Seller> FindByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name ");

            st.setInt(1, department.getId());
            rs =st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>(); //para não repetir os datos do departamento

            while(rs.next()){ //intanciando departamento e setou os valores dele
                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null){
                    dep = instatiationDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }

                Seller obj =  instatiationSeller(rs, dep);

                list.add(obj);
            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            //DB.closeConnection(); não precisa fechar a connect

        }
    }
}
