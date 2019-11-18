package me.jraynor.data;

import me.jraynor.data.fortnite.League;
import me.jraynor.data.fortnite.Player;
import me.jraynor.data.fortnite.Stat;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Optional;

public class PlayerLookup {
    //The key needed to send a request to the backend
    private static final String key = "5f27dfae-9a2b-429f-ace3-5d5e15d7a42f";

    /**
     * A simple wrapper method that gets the player and parses it
     * if that player is not present, we simply return an empty optional;
     * this is preferable over just returning null and more elegant
     *
     * @param platform the platform to get the requested player for (pc, xb1, ps4, switch)
     * @param nickname the player's username to search for
     * @return the player object if found or an empty optinal if not
     */
    public static Optional<Player> getPlayer(String platform, String nickname) {
        String data = getRawData(platform, nickname);
        Player player = PlayerJsonParser.getDataFromJSON(data);
        if (player != null)
            return Optional.of(player);
        return Optional.empty();
    }

    /**
     * This method is responsible for sending the http request to the backend
     *
     * @param platform the platform to get the requested player for (pc, xb1, ps4, switch)
     * @param nickname the player's username to search for
     * @return the raw html response (which should be json)
     */
    private static String getRawData(String platform, String nickname) {
        try {
            URL url = new URL("https://api.fortnitetracker.com/v1/profile/" + platform + "/" + nickname);
            URLConnection urlConn = url.openConnection();
            urlConn.setRequestProperty("User-Agent", "Mozilla/5.0");
            urlConn.setRequestProperty("TRN-Api-Key", key);

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            return "{\"error\": \"MISSING TRN-API-KEY\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private static class PlayerJsonParser {

        /**
         * This method is responsible for taking a json string, and getting all of the important
         * data that is needed to create a player. Then the player is created and returned
         *
         * @param json the string of data returned by the http request
         * @return the newly created player
         */
        static Player getDataFromJSON(String json) {
            try {
                JSONObject jsonObject = new JSONObject(json);

                if (jsonObject.has("error")) {
                    System.out.println("error in json string!");
                } else {
                    JSONObject leagues = jsonObject.getJSONObject("stats");
                    JSONArray lifeTimeJson = jsonObject.getJSONArray("lifeTimeStats");
                    ArrayList<League> leaguesArray = new ArrayList<>();
                    for (int i = 0; i < leagues.length(); i++) {
                        String leagueName = "";
                        ArrayList<Stat> statArray = new ArrayList<>();
                        JSONObject league = null;
                        try {
                            switch (i) {
                                case 0:
                                    league = leagues.getJSONObject("p2");
                                    leagueName = "p2";
                                    break;
                                case 1:
                                    league = leagues.getJSONObject("p10");
                                    leagueName = "p10";
                                    break;
                                case 2:
                                    league = leagues.getJSONObject("p9");
                                    leagueName = "p9";
                                    break;
                                case 4:
                                    league = leagues.getJSONObject("curr_p2");
                                    leagueName = "curr_p2";
                                    break;
                                case 5:
                                    league = leagues.getJSONObject("curr_p10");
                                    leagueName = "curr_p10";
                                    break;
                                case 6:
                                    league = leagues.getJSONObject("curr_p9");
                                    leagueName = "curr_p9";
                                    break;
                                default:
                                    continue;
                            }
                        } catch (Exception e) {
                        }
                        for (int j = 0; j < league.length(); j++) {
                            boolean brk = false;
                            JSONObject stat = null;
                            try {
                                switch (j) {
                                    case 1:
                                        stat = league.getJSONObject("score");
                                        break;
                                    case 2:
                                        stat = league.getJSONObject("top1");
                                        break;
                                    case 3:
                                        stat = league.getJSONObject("top3");
                                        break;
                                    case 4:
                                        stat = league.getJSONObject("top5");
                                        break;
                                    case 5:
                                        stat = league.getJSONObject("top6");
                                        break;
                                    case 6:
                                        stat = league.getJSONObject("top10");
                                        break;
                                    case 7:
                                        stat = league.getJSONObject("top12");
                                        break;
                                    case 8:
                                        stat = league.getJSONObject("top25");
                                        break;
                                    case 9:
                                        stat = league.getJSONObject("kd");
                                        break;
                                    case 10:
                                        stat = league.getJSONObject("winRatio");
                                        break;
                                    case 11:
                                        stat = league.getJSONObject("matches");
                                        break;
                                    case 12:
                                        stat = league.getJSONObject("kills");
                                        break;
                                    case 13:
                                        stat = league.getJSONObject("kpg");
                                        break;
                                    case 14:
                                        stat = league.getJSONObject("avgTimePlayed");
                                        break;
                                    case 15:
                                        stat = league.getJSONObject("scorePerMatch");
                                        break;
                                    default:
                                        brk = true;
                                        break;
                                }
                            } catch (Exception e) {
                            }
                            if (!brk && stat != null)
                                statArray.add(new Stat(stat.getString("label"), stat.getString("category"), stat.getString("field"), stat.getString("value")));
                        }
                        leaguesArray.add(new League(leagueName, statArray));
                    }
                    ArrayList<Stat> lifeTimeStats = new ArrayList<>();
                    for (int i = 0; i < lifeTimeJson.length(); i++) {
                        JSONObject currObj = lifeTimeJson.getJSONObject(i);
                        lifeTimeStats.add(new Stat(currObj.getString("key"), currObj.getString("value")));
                    }
                    leaguesArray.add(new League("lifeTimeStats", lifeTimeStats));
                    return new Player(jsonObject.getString("accountId"), jsonObject.getString("epicUserHandle"), jsonObject.getString("platformNameLong"), leaguesArray);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }
}
