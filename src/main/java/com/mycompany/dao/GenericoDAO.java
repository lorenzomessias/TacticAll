/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.exception.TacticAllException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CS
 */
public interface GenericoDAO<E> extends Serializable {
    List<E> listar() throws TacticAllException;

    void inserir(E e) throws TacticAllException;

    void alterar(E e) throws TacticAllException;

    void remover(E e) throws TacticAllException;

    E listarPorID(int ID) throws TacticAllException;
}
