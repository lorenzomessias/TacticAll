/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

/**
 *
 * @author Everymind
 */
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FootballPlayerVO {
    private String id;
    private String name;
    private String position;
    private String birthday;
    private String weight;
    private String height;
    private int age;
    private String foot;
    private String imgURL;
    private String marketValue;
    private String country;
    private List<String> leagues;
    private List<TeamPlayerVO> roles;

    // Construtor
    public FootballPlayerVO(String id, String name, String position, String birthday, String weight,
                          String height, int age, String foot, String imgURL, String marketValue,
                          String country, List<String> leagues, List<TeamPlayerVO> roles) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.foot = foot;
        this.imgURL = imgURL;
        this.marketValue = marketValue;
        this.country = country;
        this.leagues = leagues;
        this.roles = roles;
    }

    // Métodos getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public String getFoot() {
        return foot;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public String getCountry() {
        return country;
    }

    public List<String> getLeagues() {
        return leagues;
    }

    public List<TeamPlayerVO> getRoles() {
        return roles;
    }

    // Método toString para facilitar a exibição
    @Override
    public String toString() {
        return "FootballPlayer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", birthday='" + birthday + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", age=" + age +
                ", foot='" + foot + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", marketValue='" + marketValue + '\'' +
                ", country='" + country + '\'' +
                ", leagues=" + leagues +
                ", roles=" + roles +
                '}';
    }

    public double calcularNota() {
        int idade = this.age;
        List<String> ligasDoJogador = this.leagues;
        String[] principaisLigas = {"England", "Spain", "Germany", "Portugal"};
        long countPrincipaisLigas = Arrays.stream(principaisLigas)
                .filter(ligasDoJogador::contains)
                .count();
        double fatorIdade = idade / 35.0;
        double pesoLigasPrincipais = 2.0;
        double pesoOutrasLigas = 1.0;
        double pesoLigas = (countPrincipaisLigas <= 4) ? pesoLigasPrincipais : pesoOutrasLigas;
        Random random = new Random();
        double notaAleatoria = random.nextDouble() * 40;
        double notaFinal = ((notaAleatoria + fatorIdade) * pesoLigas) + 60.0;        
        notaFinal = Math.min(Math.max(notaFinal, 60.0), 100.0);
        return notaFinal;
    }
}

