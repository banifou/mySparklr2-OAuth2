
package org.springframework.model.entities;


/**
 * <p>
 * </p>
 * 
 * @author fouad
 */
public interface SparklUserDao {

	/**
         * 
	 */
	public String createUser(String username, String passwd);

	/**
	 * @param newPasswd
	 * @param user
	 * @return
	 */
	public String changePasswd(String newPasswd, Sparkluser user);

	/**
	 * 
	 * @param username
	 * @return
	 */
	public Sparkluser getUserByUsername(String username);


	/**
	 * @param id
	 * @return
	 */
	public Sparkluser getUserById(int id);

}
