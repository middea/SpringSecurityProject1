package kz.yerbolov.MyJavaProject.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;


    @Override
    public String getAuthority() {
        return role;
    }
}
