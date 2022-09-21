package com.springboot.TresSolesApi.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	public Boolean existsByNombreUsuario(String nombreUsuario);
	
	@Query(value="Select * from usuarios join usuarios_roles on usuarios.id=usuarios_roles.usuario_id where usuarios_roles.rol_id=:query", nativeQuery = true)
	List<Usuario> findUsuariosByIdRol(Long query);
	
	@Query(value="Select * from usuarios join usuarios_roles on usuarios.id=usuarios_roles.usuario_id join roles on usuarios_roles.rol_id=roles.id  where roles.nombre=:query", nativeQuery = true)
	List<Usuario> findUsuariosByRol(String query);
	
	@Query(value="Select * from usuarios join usuarios_roles on usuarios.id=usuarios_roles.usuario_id join roles on usuarios_roles.rol_id=roles.id  where roles.nombre=:query1 or roles.nombre=:query2", nativeQuery = true)
	List<Usuario> findUsuariosByTwoRol(String query1,String query2);
	
	@Modifying
	@Query(value="Update usuarios set password=:password where id=:usuarioId", nativeQuery = true)
	void modificarPassword(@Param("usuarioId")Long usuarioId,@Param("password")String password);
	
	@Modifying
	@Query(value="Update usuarios set numero_contacto=:celular where id=:usuarioId", nativeQuery = true)
	void modificarCelular(@Param("usuarioId")Long usuarioId,@Param("celular")String celular);
	
}
