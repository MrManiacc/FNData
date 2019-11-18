package me.jraynor.data.fortnite;

import java.util.List;

public class Player {
    private String accountID;
    private String platform;
    private String nickName;
    private List<League> leagues;

    public Player(String accountID, String platform, String nickName, List<League> leagues) {
        this.accountID = accountID;
        this.platform = platform;
        this.nickName = nickName;
        this.leagues = leagues;
    }

    /**
     * A UUID that is handled server side
     *
     * @return the account uuid
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * The platform that the player stats are for (pc, xb1, ps4 and switch)
     *
     * @return platform type
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * The player's nickname/username
     *
     * @return player's name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Gets the league as specified by the mode. It will loop through all of the
     * of leagues that the player owns and check their modes against the mode provided
     *
     * @param mode the mode to select the league
     * @return
     */
    public League getLeague(Mode mode) {
        for (League league : leagues) {
            if (league.getMode() == mode) {
                return league;
            }
        }
        return null;
    }
}
