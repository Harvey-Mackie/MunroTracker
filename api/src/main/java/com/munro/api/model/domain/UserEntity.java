package com.munro.api.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {
    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<MunroCompletedEntity> munroCompletedEntities;

    @OneToMany(mappedBy = "from", fetch = FetchType.EAGER)
    private List<UserFollowersEntity> userFollowing;

    @OneToMany(mappedBy = "to", fetch = FetchType.EAGER)
    private List<UserFollowersEntity> userFollowers;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<MunroCompletedKudosEntity> userKudos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.USER.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
