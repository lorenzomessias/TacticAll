package com.mycompany.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.dao.JogadorDAO;
import com.mycompany.dao.ProfissionalDAO;
import com.mycompany.dao.TreinadorDAO;
import com.mycompany.model.Jogador;
import com.mycompany.model.Treinador;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class SoccerService {

    public static void main(String[] args) throws Exception {
        //iniciaServico();
    }

    public static void iniciaServico() {
        String idsJogadores = BUNDLE.getString("idsJogadores");
        List<String> listaIdsJogadores = Arrays.asList(idsJogadores.split(";"));
        for (String idString : listaIdsJogadores) {
            insereJogador(Integer.parseInt(idString));
        }
        String idsTreinadores = BUNDLE.getString("idsTreinadores");
        List<String> listaIdsTreinadores = Arrays.asList(idsTreinadores.split(";"));
        for (String idString : listaIdsTreinadores) {
            insereTreinador(Integer.parseInt(idString));
        }
    }
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("service", new Locale("pt", "BR"));

    public static void insereJogador(int playerId) {
        String username = BUNDLE.getString("user");
        String token = BUNDLE.getString("token");
        try {
            String apiUrl = "https://api.soccersapi.com/v2.2/players/?user={{USERNAME}}&token={{TOKEN}}&t=info&id={{PLAYER_ID}}";
            apiUrl = apiUrl.replace("{{USERNAME}}", encodeValue(username));
            apiUrl = apiUrl.replace("{{TOKEN}}", encodeValue(token));
            apiUrl = apiUrl.replace("{{PLAYER_ID}}", String.valueOf(playerId));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());
                FootballPlayerVO footballPlayer = createFootballPlayerFromJson(jsonNode);
                Jogador jogador = new Jogador();
                jogador.setNome(footballPlayer.getName());
                jogador.setDataDeNascimento(LocalDate.parse(footballPlayer.getBirthday(), formatter));
                jogador.setNacionalidade(footballPlayer.getCountry());
                jogador.setNotaGeral(footballPlayer.calcularNota());
                ProfissionalDAO daoProf = new ProfissionalDAO();
                daoProf.inserir(jogador);
                jogador.setPosicao(footballPlayer.getPosition());
                int idProfissional = daoProf.IdMaisRecente();
                jogador.setIdProfissional(idProfissional);
                JogadorDAO daoJogador = new JogadorDAO();
                daoJogador.inserir(jogador);
            } else {
                System.out.println("Erro na chamada à API. Código de status: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static FootballPlayerVO createFootballPlayerFromJson(JsonNode jsonNode) {
        String id = jsonNode.get("data").get("id").asText();
        String name = jsonNode.get("data").get("name").asText();
        String position = jsonNode.get("data").get("position").asText();
        String birthday = jsonNode.get("data").get("birthday").asText();
        String weight = jsonNode.get("data").get("weight").asText();
        String height = jsonNode.get("data").get("height").asText();
        int age = jsonNode.get("data").get("age").asInt();
        String foot = jsonNode.get("data").get("foot").asText();
        String imgURL = jsonNode.get("data").get("img").asText();
        String marketValue = jsonNode.get("data").get("market_value").asText();
        String country = jsonNode.get("data").get("country").get("name").asText();
        List<String> leagues = new ArrayList<>();
        for (JsonNode leagueNode : jsonNode.get("data").get("leagues")) {
            leagues.add(leagueNode.get("name").asText());
        }
        List<TeamPlayerVO> roles = new ArrayList<>();
        for (JsonNode roleNode : jsonNode.get("data").get("roles")) {
            String teamName = roleNode.get("team").get("name").asText();
            boolean isCurrent = roleNode.get("is_current").asBoolean();
            String role = roleNode.get("role").asText();
            String startDate = roleNode.get("start") != null ? roleNode.get("start").asText() : null;
            String endDate = roleNode.get("end") != null ? roleNode.get("end").asText() : null;
            String shirtNumber = roleNode.get("shirt") != null ? roleNode.get("shirt").asText() : null;

            roles.add(new TeamPlayerVO(teamName, isCurrent, role, startDate, endDate, shirtNumber));
        }
        return new FootballPlayerVO(id, name, position, birthday, weight, height, age, foot, imgURL, marketValue, country, leagues, roles);
    }

    private static String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    public static void insereTreinador(int coachId) {
        String username = BUNDLE.getString("user");
        String token = BUNDLE.getString("token");

        try {
            String apiUrl = "https://api.soccersapi.com/v2.2/coaches/?user={{USERNAME}}&token={{TOKEN}}&t=info&id={{COACH_ID}}";
            apiUrl = apiUrl.replace("{{USERNAME}}", encodeValue(username));
            apiUrl = apiUrl.replace("{{TOKEN}}", encodeValue(token));
            apiUrl = apiUrl.replace("{{COACH_ID}}", String.valueOf(coachId));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());
                CoachVO coach = createCoachFromJson(jsonNode);
                Treinador treinador = new Treinador();
                treinador.setDataDeNascimento(LocalDate.parse(coach.getData().getBirthday(), formatter));
                treinador.setNome(coach.getData().getName());
                treinador.setNacionalidade(coach.getData().getCountry().getName());
                treinador.setEspecialidade(coach.getData().getEspecialidade());
                treinador.setNotaGeral(coach.calcularNota());
                ProfissionalDAO profDao = new ProfissionalDAO();
                profDao.inserir(treinador);
                treinador.setIdProfissional(profDao.IdMaisRecente());
                TreinadorDAO treinadorDao = new TreinadorDAO();
                treinadorDao.inserir(treinador);
            } else {
                System.out.println("Erro na chamada à API. Código de status: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CoachVO createCoachFromJson(JsonNode jsonNode) {
        try {

            CoachVO coachVO = new CoachVO();

            CoachVO.Data data = new CoachVO.Data();
            data.setId(jsonNode.path("data").path("id").asText());
            data.setTeamId(jsonNode.path("data").path("team_id").asText());
            data.setName(jsonNode.path("data").path("name").asText());
            data.setCommonName(jsonNode.path("data").path("common_name").asText());
            data.setFirstname(jsonNode.path("data").path("firstname").asText());
            data.setLastname(jsonNode.path("data").path("lastname").asText());
            data.setBirthday(jsonNode.path("data").path("birthday").asText());
            data.setBirthcountry(jsonNode.path("data").path("birthcountry").asText());
            data.setBirthplace(jsonNode.path("data").path("birthplace").asText());
            data.setImg(jsonNode.path("data").path("img").asText());

            CoachVO.Country country = new CoachVO.Country();
            country.setId(jsonNode.path("data").path("country").path("id").asText());
            country.setName(jsonNode.path("data").path("country").path("name").asText());
            country.setCc(jsonNode.path("data").path("country").path("cc").asText());
            data.setCountry(country);

            coachVO.setData(data);

            CoachVO.Meta meta = new CoachVO.Meta();
            meta.setRequests_left(jsonNode.path("meta").path("requests_left").asInt());
            meta.setUser(jsonNode.path("meta").path("user").asText());
            meta.setPlan(jsonNode.path("meta").path("plan").asText());
            meta.setPages(jsonNode.path("meta").path("pages").asInt());
            meta.setPage(jsonNode.path("meta").path("page").asInt());
            meta.setCount(jsonNode.path("meta").path("count").asInt());
            meta.setTotal(jsonNode.path("meta").path("total").asInt());

            coachVO.setMeta(meta);

            return coachVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
