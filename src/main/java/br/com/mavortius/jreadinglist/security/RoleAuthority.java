package br.com.mavortius.jreadinglist.security;

public enum RoleAuthority {
    ADMINISTRATOR("Administrator"),
    OPERATOR("Operator"),
    READER("User");

    private final String label;

    RoleAuthority(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
