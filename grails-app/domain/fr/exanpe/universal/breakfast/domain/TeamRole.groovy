package fr.exanpe.universal.breakfast.domain

import org.apache.commons.lang.builder.HashCodeBuilder

class TeamRole implements Serializable {

    private static final long serialVersionUID = 1

    Team team
    Role role

    boolean equals(other) {
        if (!(other instanceof TeamRole)) {
            return false
        }

        other.team?.id == team?.id &&
                other.role?.id == role?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (team) builder.append(team.id)
        if (role) builder.append(role.id)
        builder.toHashCode()
    }

    static TeamRole get(long teamId, long roleId) {
        TeamRole.where {
            team == Team.load(teamId) &&
                    role == Role.load(roleId)
        }.get()
    }

    static TeamRole create(Team team, Role role, boolean flush = false) {
        new TeamRole(team: team, role: role).save(flush: flush, insert: true)
    }

    static boolean remove(Team t, Role r, boolean flush = false) {

        int rowCount = TeamRole.where {
            team == Team.load(t.id) &&
                    role == Role.load(r.id)
        }.deleteAll()

        rowCount > 0
    }

    static void removeAll(Team t) {
        TeamRole.where {
            team == Team.load(t.id)
        }.deleteAll()
    }

    static void removeAll(Role r) {
        TeamRole.where {
            role == Role.load(r.id)
        }.deleteAll()
    }

    static mapping = {
        id composite: ['role', 'team']
        version false
        team index: 'idx_teamrole_team'
    }
}
