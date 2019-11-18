package me.jraynor.gui;

import me.jraynor.bootstrap.Window;
import me.jraynor.data.PlayerLookup;
import me.jraynor.data.fortnite.Mode;
import me.jraynor.data.fortnite.Player;
import me.jraynor.data.fortnite.StatType;
import me.jraynor.uison.controller.Component;
import me.jraynor.uison.controller.Event;
import me.jraynor.uison.controller.WindowController;
import me.jraynor.uison.controller.events.EnterEvent;
import me.jraynor.uison.elements.UIBlock;
import me.jraynor.uison.elements.UILabel;
import me.jraynor.uison.elements.UIVBox;

import java.text.DecimalFormat;
import java.util.Optional;

public class MainController extends WindowController {
    /**
     * -----solo------
     **/
    @Component(id = "solo_wins_val")
    public UILabel soloWinsLabel;
    @Component(id = "solo_kills_val")
    public UILabel soloKillsLabel;
    @Component(id = "solo_wins_percent_val")
    public UILabel soloWinPercentLabel;
    @Component(id = "solo_kd_val")
    public UILabel soloKdLabel;
    @Component(id = "solo_container")
    public UIVBox soloContainer;

    /**
     * -----duo------
     **/
    @Component(id = "duo_wins_val")
    public UILabel duoWinsLabel;
    @Component(id = "duo_kills_val")
    public UILabel duoKillsLabel;
    @Component(id = "duo_wins_percent_val")
    public UILabel duoWinPercentLabel;
    @Component(id = "duo_kd_val")
    public UILabel duoKdLabel;
    @Component(id = "duo_container")
    public UIVBox duoContainer;


    /**
     * -----squad------
     **/
    @Component(id = "squad_wins_val")
    public UILabel squadWinsLabel;
    @Component(id = "squad_kills_val")
    public UILabel squadKillsLabel;
    @Component(id = "squad_wins_percent_val")
    public UILabel squadWinPercentLabel;
    @Component(id = "squad_kd_val")
    public UILabel squadKdLabel;
    @Component(id = "squad_container")
    public UIVBox squadContainer;


    /**
     * -----lifetime------
     **/
    @Component(id = "lifetime_wins_val")
    public UILabel lifetimeWinsLabel;
    @Component(id = "lifetime_kills_val")
    public UILabel lifetimeKillsLabel;
    @Component(id = "lifetime_wins_percent_val")
    public UILabel lifetimeWinPercentLabel;
    @Component(id = "lifetime_kd_val")
    public UILabel lifetimeKdLabel;
    @Component(id = "lifetime_container")
    public UIBlock lifetimeContainer;


    @Component(id = "wrapper")
    public UIBlock wrapper;


    public MainController(Window window) {
        super(window);
    }

    @Override
    protected void onReady() {
        super.onReady();
        setVisibility(false);
    }

    /**
     * This event will be called when the enter key is pressed from the
     * username input text-box
     *
     * @param enterEvent the event containing the component and the value of textbox
     */
    @Event(id = "epic_input", action = "text_enter")
    public void onUsernameInput(EnterEvent enterEvent) {
        Optional<Player> player = PlayerLookup.getPlayer("pc", enterEvent.getValue());
        if (player.isPresent()) {
            DecimalFormat df = new DecimalFormat("#.##");
            DecimalFormat intdf = new DecimalFormat("#");
            //SOLO STATS
            soloWinsLabel.setText(intdf.format(player.get().getLeague(Mode.SOLO).getStat(StatType.WINS)));
            soloKillsLabel.setText(intdf.format(player.get().getLeague(Mode.SOLO).getStat(StatType.KILLS)));
            soloKdLabel.setText(df.format(player.get().getLeague(Mode.SOLO).getStat(StatType.KILL_DEATH_RATIO)));
            soloWinPercentLabel.setText(player.get().getLeague(Mode.SOLO).getStat(StatType.WIN_PERCENT).toString() + "%");
            //DUO STATS
            duoWinsLabel.setText(intdf.format(player.get().getLeague(Mode.DUO).getStat(StatType.WINS)));
            duoKillsLabel.setText(intdf.format(player.get().getLeague(Mode.DUO).getStat(StatType.KILLS)));
            duoKdLabel.setText(df.format(player.get().getLeague(Mode.DUO).getStat(StatType.KILL_DEATH_RATIO)));
            duoWinPercentLabel.setText(player.get().getLeague(Mode.DUO).getStat(StatType.WIN_PERCENT).toString() + "%");
            //SQUAD STATS
            squadWinsLabel.setText(intdf.format(player.get().getLeague(Mode.SQUAD).getStat(StatType.WINS)));
            squadKillsLabel.setText(intdf.format(player.get().getLeague(Mode.SQUAD).getStat(StatType.KILLS)));
            squadKdLabel.setText(df.format(player.get().getLeague(Mode.SQUAD).getStat(StatType.KILL_DEATH_RATIO)));
            squadWinPercentLabel.setText(player.get().getLeague(Mode.SQUAD).getStat(StatType.WIN_PERCENT).toString() + "%");
            //LIFETIME STATS
            lifetimeWinsLabel.setText(intdf.format(player.get().getLeague(Mode.LIFE_TIME).getStat(StatType.WINS)));
            lifetimeKillsLabel.setText(intdf.format(player.get().getLeague(Mode.LIFE_TIME).getStat(StatType.KILLS)));
            lifetimeKdLabel.setText(df.format(player.get().getLeague(Mode.LIFE_TIME).getStat(StatType.KILL_DEATH_RATIO)));
            lifetimeWinPercentLabel.setText(player.get().getLeague(Mode.LIFE_TIME).getStat(StatType.WIN_PERCENT).toString() + "%");


            setVisibility(true);
        } else
            setVisibility(false);
    }

    /**
     * This method is responsible for showing or hiding the data boxes
     *
     * @param visibility the boolean deciding if the boxes should be visible
     */
    private void setVisibility(boolean visibility) {
        soloContainer.setActive(visibility);
        duoContainer.setActive(visibility);
        squadContainer.setActive(visibility);
        lifetimeContainer.setActive(visibility);
        wrapper.setActive(visibility);
    }

}
