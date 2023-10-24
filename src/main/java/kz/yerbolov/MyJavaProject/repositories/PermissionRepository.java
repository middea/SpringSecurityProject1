package kz.yerbolov.MyJavaProject.repositories;

import jakarta.transaction.Transactional;
import kz.yerbolov.MyJavaProject.entites.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findAllByRole(String role);
}
