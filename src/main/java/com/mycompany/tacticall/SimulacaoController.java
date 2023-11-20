/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.RelacionamentoJogadorEsquemaDAO;
import com.mycompany.dao.RelacionamentoTimeProfissionalDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Jogador;
import com.mycompany.model.Simulacao;
import com.mycompany.model.Time;
import java.util.List;

/**
 *
 * @author Everymind
 */
public class SimulacaoController {
    public Simulacao realizaSimulacao(Time timeA, Time timeB, EsquemaTatico ofensivoA, EsquemaTatico defensivoB, EsquemaTatico ofensivoB,EsquemaTatico defensivoA) throws TacticAllException{
        RelacionamentoTimeProfissionalDAO relDao = new RelacionamentoTimeProfissionalDAO();
        RelacionamentoJogadorEsquemaDAO relJEDao = new RelacionamentoJogadorEsquemaDAO();

        List<Jogador> jogadoresTimeA = relDao.listarJogadoresDoTime(timeA.getId());
        List<Jogador> jogadoresTimeB = relDao.listarJogadoresDoTime(timeB.getId());
        List<Jogador> jogadoresOfensivoA = relJEDao.listarJogadoresPorEsquema(ofensivoA.getId());
        List<Jogador> jogadoresDefensivoA = relJEDao.listarJogadoresPorEsquema(defensivoA.getId());
        List<Jogador> jogadoresOfensivoB = relJEDao.listarJogadoresPorEsquema(ofensivoB.getId());
        List<Jogador> jogadoresDefensivoB = relJEDao.listarJogadoresPorEsquema(defensivoB.getId());

        return null;
    }
        
    }

