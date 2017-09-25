package com.eli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Match.
 */
@Entity
@Table(name = "jhi_match")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "home_player_sets")
    private Integer homePlayerSets;

    @Column(name = "away_player_sets")
    private Integer awayPlayerSets;

    @ManyToOne
    private Player homePlayer;

    @ManyToOne
    private Player awayPlayer;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHomePlayerSets() {
        return homePlayerSets;
    }

    public Match homePlayerSets(Integer homePlayerSets) {
        this.homePlayerSets = homePlayerSets;
        return this;
    }

    public void setHomePlayerSets(Integer homePlayerSets) {
        this.homePlayerSets = homePlayerSets;
    }

    public Integer getAwayPlayerSets() {
        return awayPlayerSets;
    }

    public Match awayPlayerSets(Integer awayPlayerSets) {
        this.awayPlayerSets = awayPlayerSets;
        return this;
    }

    public void setAwayPlayerSets(Integer awayPlayerSets) {
        this.awayPlayerSets = awayPlayerSets;
    }

    public Player getHomePlayer() {
        return homePlayer;
    }

    public Match homePlayer(Player player) {
        this.homePlayer = player;
        return this;
    }

    public void setHomePlayer(Player player) {
        this.homePlayer = player;
    }

    public Player getAwayPlayer() {
        return awayPlayer;
    }

    public Match awayPlayer(Player player) {
        this.awayPlayer = player;
        return this;
    }

    public void setAwayPlayer(Player player) {
        this.awayPlayer = player;
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
        Match match = (Match) o;
        if (match.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), match.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Match{" +
            "id=" + getId() +
            ", homePlayerSets='" + getHomePlayerSets() + "'" +
            ", awayPlayerSets='" + getAwayPlayerSets() + "'" +
            "}";
    }
}
