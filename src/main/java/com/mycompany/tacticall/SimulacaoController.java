/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.EsquemaTaticoDAO;
import com.mycompany.dao.RelacionamentoJogadorEsquemaDAO;
import com.mycompany.dao.RelacionamentoTimeProfissionalDAO;
import com.mycompany.dao.SimulacaoDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Jogador;
import com.mycompany.model.Simulacao;
import com.mycompany.model.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class SimulacaoController {

    public Simulacao realizaSimulacao(Time timeA, Time timeB, EsquemaTatico ofensivoA, EsquemaTatico defensivoB, EsquemaTatico ofensivoB, EsquemaTatico defensivoA) throws TacticAllException {
        RelacionamentoTimeProfissionalDAO relDao = new RelacionamentoTimeProfissionalDAO();
        RelacionamentoJogadorEsquemaDAO relJEDao = new RelacionamentoJogadorEsquemaDAO();
        List<Jogador> jogadoresTimeA = relDao.listarJogadoresDoTime(timeA.getId());
        List<Jogador> jogadoresTimeB = relDao.listarJogadoresDoTime(timeB.getId());
        List<Jogador> jogadoresOfensivoA = relJEDao.listarJogadoresPorEsquema(ofensivoA.getId());
        List<Jogador> jogadoresDefensivoA = relJEDao.listarJogadoresPorEsquema(defensivoA.getId());
        List<Jogador> jogadoresOfensivoB = relJEDao.listarJogadoresPorEsquema(ofensivoB.getId());
        List<Jogador> jogadoresDefensivoB = relJEDao.listarJogadoresPorEsquema(defensivoB.getId());
        int golsTimeA = 0;
        int golsTimeB = 0;
        double totalNotasTimeA = 0;
        double totalNotasTimeB = 0;

        for (Jogador jogador : jogadoresTimeA) {
            if (posicaoCoincide(jogador, jogadoresOfensivoA, jogadoresDefensivoA)) {
                totalNotasTimeA += jogador.getNotaGeral();
            } else {
                totalNotasTimeA += 0.7 * jogador.getNotaGeral();
            }
        }

        for (Jogador jogador : jogadoresTimeB) {
            if (posicaoCoincide(jogador, jogadoresOfensivoB, jogadoresDefensivoB)) {
                totalNotasTimeB += jogador.getNotaGeral();
            } else {
                totalNotasTimeB += 0.7 * jogador.getNotaGeral();
            }
        }
        Random random = new Random();
        int numChancesTimeA = 0;
        int numChancesTimeB = 0;
        int valorRandom1 = random.nextInt(9);  // Números de 0 a 8
        int valorRandom2 = random.nextInt(9);
        if (valorRandom1 > valorRandom2) {
            if (totalNotasTimeA > totalNotasTimeB) {
                numChancesTimeA = valorRandom1;
            } else {
                numChancesTimeB = valorRandom1;
            }
        } else {
            if (totalNotasTimeA > totalNotasTimeB) {
                numChancesTimeA = valorRandom2;
            } else {
                numChancesTimeB = valorRandom2;
            }
        }

        golsTimeA = golsConvertidos(numChancesTimeA);
        golsTimeB = golsConvertidos(numChancesTimeB);
        Simulacao simulacao = new Simulacao();
        simulacao.setDataSimulacao(LocalDate.now());
        simulacao.setGolsTimeMandante(golsTimeA);
        simulacao.setGolsTimeVisitante(golsTimeB);
        simulacao.setIdEsquemaDefensivoMandante(defensivoA.getId());
        simulacao.setIdEsquemaOfensivoMandante(ofensivoA.getId());
        simulacao.setIdEsquemaDefensivoVisitante(defensivoB.getId());
        simulacao.setIdEsquemaOfensivoVisitante(ofensivoB.getId());
        simulacao.setIdUsuario(timeA.getIdUsuario());
        SimulacaoDAO simuDao = new SimulacaoDAO();
        simuDao.inserir(simulacao);
        return simulacao;
    }

    public int golsConvertidos(int numChances) {
        int gols = 0;
        for (int chance = 0; chance < numChances; chance++) {
            if (golComProbabilidade(0.25)) {
                gols++;
            }
        }
        return gols;
    }

    public boolean golComProbabilidade(double probabilidade) {
        double numeroAleatorio = new Random().nextDouble();
        return numeroAleatorio <= probabilidade;
    }
    
    public List<EsquemaTatico> retornaEsquemasPorTime(int idTime) throws TacticAllException{
        EsquemaTaticoDAO esquemaDao = new EsquemaTaticoDAO();
        return esquemaDao.listarEsquemasPorTime(idTime);
    }

    private boolean posicaoCoincide(Jogador jogador, List<Jogador> ofensivo, List<Jogador> defensivo) {
        return ofensivo.stream().anyMatch(j -> j.getId() == jogador.getId() && j.getPosicao().equals(jogador.getPosicao()))
                || defensivo.stream().anyMatch(j -> j.getId() == jogador.getId() && j.getPosicao().equals(jogador.getPosicao()));
    }

}
