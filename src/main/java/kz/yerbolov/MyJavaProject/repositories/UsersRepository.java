package kz.yerbolov.MyJavaProject.repositories;

import jakarta.transaction.Transactional;
import kz.yerbolov.MyJavaProject.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findAllByEmail(String email);
}
