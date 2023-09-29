package team.aquatic.studios.manager;

import team.aquatic.studios.Emotes;

public class Builder {

    private String name;

    private String trigger;

    private String emote;

    private String permission;

    public Builder(String name){
        this.name = name;
        this.trigger = Emotes.GetEmotes().getString(this.name + ".trigger");
        this.emote = Emotes.GetEmotes().getString(this.name + ".emote");
        this.permission = Emotes.GetEmotes().getString(this.name + ".permission");
    }

}
