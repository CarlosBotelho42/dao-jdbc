package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable /*faz com que os objetos sejam transformados em bits*/ {

    private  Integer Id;
    private String name;

    public  Department(){

    }

    public Department(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Id == that.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return "department{ " +
                "Id= " + Id +
                ", name = " + name +
                '}';
    }


}
