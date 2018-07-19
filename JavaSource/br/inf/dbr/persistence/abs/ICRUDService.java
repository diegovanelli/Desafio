package br.inf.dbr.persistence.abs;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface criada para implementar os métodos padrões dos serviços deve ser utilizado sempre que o service tiver métodos de inserção, atualização e exclusão.
 *
 * @param <T> - Bean que será tratado nos métodos
 * @author Diego Vanelli
 * @since 17/07/2018 22:36:58
 */
public interface ICRUDService <T> {
	
	/**
	 * Insere um novo registro com os dados do bean informado.
	 * 
	 * @param bean
	 * @param conn
	 * @throws SQLException
	 * @author Diego Vanelli
	 * @since 17/07/2018 22:36:58
	 */
	public int inserir (T bean,
	                    Connection conn) throws SQLException;
	
	/**
	 * Atualiza o registro com as últimas informações do bean.
	 * 
	 * @param bean
	 * @param conn
	 * @throws SQLException
	 * @author Diego Vanelli
	 * @since 17/07/2018 22:36:58
	 */
	public void alterar (T bean,
	                     Connection conn) throws SQLException;
	
	/**
	 * Remove o registro indicado do sistema.
	 * 
	 * @param bean
	 * @param conn
	 * @throws SQLException
	 * @author Diego Vanelli
	 * @since 17/07/2018 22:36:58
	 */
	public void excluir (T bean,
	                     Connection conn) throws SQLException;
	
}
