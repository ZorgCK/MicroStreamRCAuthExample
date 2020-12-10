
package one.microstream.microstreamauthentication.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.rapidclipse.framework.security.authentication.CredentialsUsernamePassword;
import com.rapidclipse.framework.server.security.authorization.AuthorizationRole;
import com.rapidclipse.framework.server.security.authorization.AuthorizationSubject;


public class User implements CredentialsUsernamePassword, AuthorizationSubject
{
	private byte[] password;
	private String username;
	
	private final Set<Role> roles = new HashSet<>();

	public byte[] getPassword()
	{
		return this.password;
	}

	public void setPassword(final byte[] password)
	{
		this.password = password;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	public Set<Role> getRoles()
	{
		return this.roles;
	}

	@Override
	public String subjectName()
	{
		return this.username;
	}

	@Override
	public Collection<? extends AuthorizationRole> roles()
	{
		return this.roles;
	}

	@Override
	public String username()
	{
		return this.username;
	}

	@Override
	public byte[] password()
	{
		return this.password;
	}
	
}
