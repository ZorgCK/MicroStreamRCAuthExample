
package one.microstream.microstreamauthentication.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.rapidclipse.framework.server.resources.Caption;
import com.rapidclipse.framework.server.security.authorization.AuthorizationResource;
import com.rapidclipse.framework.server.security.authorization.AuthorizationRole;

import one.microstream.microstreamauthentication.core.authentication.AuthorizationResources;


public class Role implements java.io.Serializable, AuthorizationRole
{
	private String                      role;
	private Set<AuthorizationResources> permissions = new HashSet<>(0);
	private final Set<Role>             roles       = new HashSet<>();

	public Role()
	{
	}

	@Caption("GroupName")
	public String getGroupName()
	{
		return this.role;
	}

	public void setGroupName(final String role)
	{
		this.role = role;
	}

	@Caption("Permissions")
	public Set<AuthorizationResources> getPermissions()
	{
		return this.permissions;
	}

	public void setPermissions(final Set<AuthorizationResources> permissions)
	{
		this.permissions = permissions;
	}

	public Role(final String name)
	{
		this.setGroupName(name);
	}

	@Override
	public String roleName()
	{
		return this.role;
	}

	@Override
	public Collection<? extends AuthorizationRole> roles()
	{
		return this.roles;
	}

	@Override
	public Collection<? extends AuthorizationResource> resources()
	{
		// TODO Auto-generated method stub
		return this.permissions;
	}
}
