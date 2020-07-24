package br.com.monitor_dashboard.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.monitor_dashboard.data.model.security.Usuario;

@Repository
public interface AuthenticationRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "CALL spLogin(:idUsuario, :passwd);", nativeQuery = true)
	int authenticate(@Param("idUsuario") long id, @Param("passwd") String password);
}
