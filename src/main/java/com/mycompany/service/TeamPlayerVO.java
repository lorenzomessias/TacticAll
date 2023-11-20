/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

/**
 *
 * @author Everymind
 */

public class TeamPlayerVO {
    private String teamName;
    private boolean isCurrent;
    private String role;
    private String startDate;
    private String endDate;
    private String shirtNumber;

    // Construtor
    public TeamPlayerVO(String teamName, boolean isCurrent, String role, String startDate, String endDate, String shirtNumber) {
        this.teamName = teamName;
        this.isCurrent = isCurrent;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shirtNumber = shirtNumber;
    }

    // Métodos getters

    public String getTeamName() {
        return teamName;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public String getRole() {
        return role;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getShirtNumber() {
        return shirtNumber;
    }

    // Método toString para facilitar a exibição
    @Override
    public String toString() {
        return "Role{" +
                "teamName='" + teamName + '\'' +
                ", isCurrent=" + isCurrent +
                ", role='" + role + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", shirtNumber='" + shirtNumber + '\'' +
                '}';
    }
}

