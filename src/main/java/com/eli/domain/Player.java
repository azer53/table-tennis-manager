package com.eli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Player.
 */
@Entity
@Table(name = "player")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "frenoy_id")
    private String frenoyId;

    @Column(name = "ranking")
    private String ranking;

    @ManyToMany(mappedBy = "players")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "homePlayer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Match> homeMatches = new HashSet<>();

    @OneToMany(mappedBy = "awayPlayer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Match> awayMatches = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Player firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Player lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFrenoyId() {
        return frenoyId;
    }

    public Player frenoyId(String frenoyId) {
        this.frenoyId = frenoyId;
        return this;
    }

    public void setFrenoyId(String frenoyId) {
        this.frenoyId = frenoyId;
    }

    public String getRanking() {
        return ranking;
    }

    public Player ranking(String ranking) {
        this.ranking = ranking;
        return this;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public Player teams(Set<Team> teams) {
        this.teams = teams;
        return this;
    }

    public Player addTeam(Team team) {
        this.teams.add(team);
        team.getPlayers().add(this);
        return this;
    }

    public Player removeTeam(Team team) {
        this.teams.remove(team);
        team.getPlayers().remove(this);
        return this;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Match> getHomeMatches() {
        return homeMatches;
    }

    public Player homeMatches(Set<Match> matches) {
        this.homeMatches = matches;
        return this;
    }

    public Player addHomeMatch(Match match) {
        this.homeMatches.add(match);
        match.setHomePlayer(this);
        return this;
    }

    public Player removeHomeMatch(Match match) {
        this.homeMatches.remove(match);
        match.setHomePlayer(null);
        return this;
    }

    public void setHomeMatches(Set<Match> matches) {
        this.homeMatches = matches;
    }

    public Set<Match> getAwayMatches() {
        return awayMatches;
    }

    public Player awayMatches(Set<Match> matches) {
        this.awayMatches = matches;
        return this;
    }

    public Player addAwayMatch(Match match) {
        this.awayMatches.add(match);
        match.setAwayPlayer(this);
        return this;
    }

    public Player removeAwayMatch(Match match) {
        this.awayMatches.remove(match);
        match.setAwayPlayer(null);
        return this;
    }

    public void setAwayMatches(Set<Match> matches) {
        this.awayMatches = matches;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        if (player.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), player.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Player{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", frenoyId='" + getFrenoyId() + "'" +
            ", ranking='" + getRanking() + "'" +
            "}";
    }
}
