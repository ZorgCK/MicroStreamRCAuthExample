
package one.microstream.microstreamauthentication.microstream;

import java.util.HashSet;
import java.util.Set;

import one.microstream.microstreamauthentication.domain.Permission;
import one.microstream.microstreamauthentication.domain.Role;
import one.microstream.microstreamauthentication.domain.User;


/**
 * MicroStream data root. Create your data model from here.
 *
 * @see <a href="https://manual.docs.microstream.one/">Reference Manual</a>
 */
public class DataRoot
{
	private Set<Permission> permissions = new HashSet<>();
	private Set<User>       users       = new HashSet<>();
	private Set<Role>       userGroups  = new HashSet<>();
	
	public DataRoot()
	{
		super();
	}

	public Set<Permission> getPermissions()
	{
		return this.permissions;
	}

	public void setPermissions(final Set<Permission> permissions)
	{
		this.permissions = permissions;
	}

	public Set<User> getUsers()
	{
		return this.users;
	}

	public void setUsers(final Set<User> users)
	{
		this.users = users;
	}

	public Set<Role> getUserGroups()
	{
		return this.userGroups;
	}

	public void setUserGroups(final Set<Role> userGroups)
	{
		this.userGroups = userGroups;
	}

}
