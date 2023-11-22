/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.*;
import com.mycompany.dao.EsquemaTaticoDAO;
import com.mycompany.dao.RelacionamentoJogadorEsquemaDAO;
import com.mycompany.dao.RelacionamentoTimeProfissionalDAO;
import com.mycompany.dao.SimulacaoDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Jogador;
import com.mycompany.model.Simulacao;
import com.mycompany.model.Time;
import com.mycompany.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class SimulacaoController extends Sidebar implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ComboBox NomeTimeMandante;
    @FXML
    ComboBox NomeTimeVisitante;
    @FXML
    ComboBox EsquemaOfensivoVisitante;
    @FXML
    ComboBox EsquemaOfensivoMandante;
    @FXML
    ComboBox EsquemaDefensivoVisitante;
    @FXML
    ComboBox EsquemaDefensivoMandante;
    List<Time> times;
    List<EsquemaTatico> mandanteDefensivo;
    List<EsquemaTatico> mandanteOfensivo;
    List<EsquemaTatico> visitanteOfensivo;
    List<EsquemaTatico> visitanteDefensivo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VerificaLogin();
            PreencheComboBox();

        } catch (IOException ex) {
            Logger.getLogger(TimesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TacticAllException ex) {
            Logger.getLogger(SimulacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void PreencheComboBox() throws TacticAllException {
        String email = Sessao.getInstancia().getEmail();
        UsuarioDAO dao = new UsuarioDAO();
        Usuario user = dao.listarPorEmail(email);
        TimeDAO timeDAO = new TimeDAO();
        times = timeDAO.listarPorUsuarioId(user.getId());
        List<String> listaTime = new ArrayList<String>();
        for (Time time : times) {
            listaTime.add(time.getNome());
        }
        NomeTimeMandante.getItems().addAll(listaTime);
        NomeTimeVisitante.getItems().addAll(listaTime);

    }

    public void mudarTimeMandante() throws TacticAllException {
        Time time = getTimePorNome((String) NomeTimeMandante.getValue());
        mandanteOfensivo = retornaEsquemasPorTime(time.getId(), "Ofensivo");
        mandanteDefensivo = retornaEsquemasPorTime(time.getId(), "Defensivo");
        List<String> listaMandanteOfensivo = new ArrayList<String>();
        List<String> listaMandanteDefensivo = new ArrayList<String>();

        for (EsquemaTatico esquema : mandanteOfensivo) {
            listaMandanteOfensivo.add(esquema.getNome());
        }
        for (EsquemaTatico esquema : mandanteDefensivo) {
            listaMandanteDefensivo.add(esquema.getNome());
        }

        EsquemaOfensivoMandante.getItems().clear();
        EsquemaDefensivoMandante.getItems().clear();

        EsquemaOfensivoMandante.getItems().addAll(listaMandanteOfensivo);
        EsquemaDefensivoMandante.getItems().addAll(listaMandanteDefensivo);
        if (!EsquemaOfensivoMandante.getItems().isEmpty()) {
            EsquemaOfensivoMandante.getSelectionModel().selectFirst();
        } else {
            EsquemaOfensivoMandante.getSelectionModel().clearSelection();
        }
        if (!EsquemaDefensivoMandante.getItems().isEmpty()) {
            EsquemaDefensivoMandante.getSelectionModel().selectFirst();
        } else {
            EsquemaDefensivoMandante.getSelectionModel().clearSelection();
        }

    }

    public void mudarTimeVisitante() throws TacticAllException {
        Time time = getTimePorNome((String) NomeTimeVisitante.getValue());
        visitanteOfensivo = retornaEsquemasPorTime(time.getId(), "Ofensivo");
        visitanteDefensivo = retornaEsquemasPorTime(time.getId(), "Defensivo");
        List<String> listaVisitanteOfensivo = new ArrayList<String>();
        List<String> listaVisitanteDefensivo = new ArrayList<String>();

        for (EsquemaTatico esquema : visitanteOfensivo) {
            listaVisitanteOfensivo.add(esquema.getNome());
        }
        for (EsquemaTatico esquema : visitanteDefensivo) {
            listaVisitanteDefensivo.add(esquema.getNome());
        }

        EsquemaOfensivoVisitante.getItems().clear();
        EsquemaDefensivoVisitante.getItems().clear();

        EsquemaOfensivoVisitante.getItems().addAll(listaVisitanteOfensivo);
        EsquemaDefensivoVisitante.getItems().addAll(listaVisitanteDefensivo);
        if (!EsquemaOfensivoVisitante.getItems().isEmpty()) {
            EsquemaOfensivoVisitante.getSelectionModel().selectFirst();
        } else {
            EsquemaOfensivoVisitante.getSelectionModel().clearSelection();
        }
        if (!EsquemaDefensivoVisitante.getItems().isEmpty()) {
            EsquemaDefensivoVisitante.getSelectionModel().selectFirst();
        } else {
            EsquemaDefensivoVisitante.getSelectionModel().clearSelection();
        }
    }

    public Time getTimePorNome(String nomeProcurado) {
        for (Time time : times) {
            if (time.getNome().equals(nomeProcurado)) {
                return time;
            }
        }
        return null;
    }

    public EsquemaTatico getEsquemaPorNome(String nomeProcurado, List<EsquemaTatico> esquemas) {
        for (EsquemaTatico esquema : esquemas) {
            if (esquema.getNome().equals(nomeProcurado)) {
                return esquema;
            }
        }
        return null;
    }

    public Simulacao realizaSimulacao() throws TacticAllException {
        Time timeA = getTimePorNome((String) NomeTimeMandante.getValue());
        Time timeB = getTimePorNome((String) NomeTimeVisitante.getValue());
        EsquemaTatico ofensivoA = getEsquemaPorNome((String) EsquemaOfensivoMandante.getValue(), mandanteOfensivo);
        EsquemaTatico defensivoA = getEsquemaPorNome((String) EsquemaDefensivoMandante.getValue(), mandanteDefensivo);
        EsquemaTatico ofensivoB = getEsquemaPorNome((String) EsquemaOfensivoVisitante.getValue(), visitanteOfensivo);
        EsquemaTatico defensivoB = getEsquemaPorNome((String) EsquemaDefensivoVisitante.getValue(), visitanteDefensivo);
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

    public List<EsquemaTatico> retornaEsquemasPorTime(int idTime, String tipo) throws TacticAllException {
        EsquemaTaticoDAO esquemaDao = new EsquemaTaticoDAO();
        return esquemaDao.listarEsquemasPorTime(idTime, tipo);
    }

    private boolean posicaoCoincide(Jogador jogador, List<Jogador> ofensivo, List<Jogador> defensivo) {
        return ofensivo.stream().anyMatch(j -> j.getId() == jogador.getId() && j.getPosicao().equals(jogador.getPosicao()))
                || defensivo.stream().anyMatch(j -> j.getId() == jogador.getId() && j.getPosicao().equals(jogador.getPosicao()));
    }

}
