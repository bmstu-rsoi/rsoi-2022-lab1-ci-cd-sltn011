package ru.RSOI.Lab1CICD.Model;

import javax.persistence.*;

@Entity
@Table(name="persons")
public class MPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id = -1;
    public String name = "";
    public int    age = -1;
    public String address = "";
    public String work = "";

    public MPerson()
    {

    }
    public MPerson(String name, int age, String address, String work)
    {
        this.name    = name;
        this.age     = age;
        this.address = address;
        this.work    = work;
    }

    public int getId()
    {
        return id;
    }

}
