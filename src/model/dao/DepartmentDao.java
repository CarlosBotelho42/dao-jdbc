package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao  {

    void insert(Department obj);  //inserir dados no departamento
    void update(Department obj);  //atualizar dados no departamento
    void deleteById(Integer id);  //deletar por id
    Department findById(Integer id); // procurar por id
    List<Department> findAll(); //listar todos os itens do departamento

}
