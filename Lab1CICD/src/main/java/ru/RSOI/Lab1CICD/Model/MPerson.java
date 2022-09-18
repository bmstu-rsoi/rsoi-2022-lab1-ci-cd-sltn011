package ru.RSOI.Lab1CICD.Model;

public class MPerson {

    private static int idCounter = 1;

    private int    id = -1;
    public String name = "";
    public int    age = -1;
    public String address = "";
    public String work = "";

    public MPerson()
    {
        this.id = assignId();
    }
    public MPerson(String name, int age, String address, String work)
    {
        this.id      = assignId();
        this.name    = name;
        this.age     = age;
        this.address = address;
        this.work    = work;
    }

    public int getId()
    {
        return id;
    }

    private static int assignId()
    {
        int newId = idCounter;
        idCounter++;
        return newId;
    }
}
