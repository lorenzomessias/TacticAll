/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

/**
 *
 * @author Everymind
 */
import java.util.Objects;
import java.util.Random;

public class CoachVO {
    public static class Data {
        public Data(){}
        private String id;
        private String teamId;
        private String name;
        private String commonName;
        private String firstname;
        private String lastname;
        private String birthday;
        private String birthcountry;
        private String birthplace;
        private String img;
        private Country country;

        // getters e setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCommonName() {
            return commonName;
        }

        public void setCommonName(String commonName) {
            this.commonName = commonName;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBirthcountry() {
            return birthcountry;
        }

        public void setBirthcountry(String birthcountry) {
            this.birthcountry = birthcountry;
        }

        public String getBirthplace() {
            return birthplace;
        }

        public void setBirthplace(String birthplace) {
            this.birthplace = birthplace;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public String getEspecialidade() {
            return getEspecialidadeAleatoria();
        }

    }
    private static final String[] ESPECIALIDADES = {"Defensivo", "Ofensivo", "Equilibrado"};

    // Método para obter uma especialidade aleatória com probabilidades iguais
    private static String getEspecialidadeAleatoria() {
        Random random = new Random();
        int indice = random.nextInt(ESPECIALIDADES.length);
        return ESPECIALIDADES[indice];
    }
    
    public static class Country {
        private String id;
        private String name;
        private String cc;

        // getters e setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }
    }

    private Data data;
    private Meta meta;

    // getters e setters

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static class Meta {
        private int requests_left;
        private String user;
        private String plan;
        private int pages;
        private int page;
        private int count;
        private int total;

        // getters e setters

        public int getRequests_left() {
            return requests_left;
        }

        public void setRequests_left(int requests_left) {
            this.requests_left = requests_left;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
     public int calcularNota() {
        Random random = new Random();
        return random.nextInt(30) + 70;  
    }
}




