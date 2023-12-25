package com.example.db_mysql.tables_softs;

public class soft_inventory {

    Integer id;
    String kabina, Invent_nomer, Name, Counts, Primich,  Powers,  PI_a,  Name_OS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKabina() {
        return kabina;
    }

    public void setKabina(String kabina) {
        this.kabina = kabina;
    }

    public String getInvent_nomer() {
        return Invent_nomer;
    }

    public void setInvent_nomer(String invent_nomer) {
        Invent_nomer = invent_nomer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCounts() {
        return Counts;
    }

    public void setCounts(String counts) {
        Counts = counts;
    }

    public String getPrimich() {
        return Primich;
    }

    public void setPrimich(String primich) {
        Primich = primich;
    }

    public String getPowers() {
        return Powers;
    }

    public void setPowers(String powers) {
        Powers = powers;
    }

    public String getPI_a() {
        return PI_a;
    }

    public void setPI_a(String PI_a) {
        this.PI_a = PI_a;
    }

    public String getName_OS() {
        return Name_OS;
    }

    public void setName_OS(String name_OS) {
        Name_OS = name_OS;
    }

    public soft_inventory(Integer id, String kabina, String invent_nomer, String name, String counts, String primich, String powers, String PI_a, String name_OS) {
        this.id = id;
        this.kabina = kabina;
        this.Invent_nomer = invent_nomer;
        this.Name = name;
        this.Counts = counts;
        this.Primich = primich;
        this.Powers = powers;
        this.PI_a = PI_a;
        this.Name_OS = name_OS;
    }
}
