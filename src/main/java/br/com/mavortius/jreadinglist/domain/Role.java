package br.com.mavortius.jreadinglist.domain;

import br.com.mavortius.jreadinglist.security.RoleAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleAuthority authority = RoleAuthority.READER;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(RoleAuthority authority) {
        this.authority = authority;
    }
}
