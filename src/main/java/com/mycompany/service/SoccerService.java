package com.mycompany.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.dao.JogadorDAO;
import com.mycompany.dao.ProfissionalDAO;
import com.mycompany.model.Jogador;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SoccerService {

    public static void main(String[] args) throws Exception {
       insereJogador();
    }
    
    public static void insereJogador()
    {
        String username = "L.guilhermedesouza";
        String token = "adf6a771c690d96b0823b22c4d19bc57";
        int playerId = 19988;

        try {
            String apiUrl = "https://api.soccersapi.com/v2.2/players/?user={{USERNAME}}&token={{TOKEN}}&t=info&id={{PLAYER_ID}}";

            // Substituindo as variáveis no URL da API
            apiUrl = apiUrl.replace("{{USERNAME}}", encodeValue(username));
            apiUrl = apiUrl.replace("{{TOKEN}}", encodeValue(token));
            apiUrl = apiUrl.replace("{{PLAYER_ID}}", String.valueOf(playerId));

            // Criando a requisição
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
}
