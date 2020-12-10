
package one.microstream.microstreamauthentication.core.authentication;

import com.rapidclipse.framework.security.authorization.AuthorizationConfiguration;
import com.rapidclipse.framework.security.authorization.AuthorizationConfigurationProvider;

import one.microstream.microstreamauthentication.domain.Permission;
import one.microstream.microstreamauthentication.domain.Role;
import one.microstream.microstreamauthentication.domain.User;


public class AuthorizationProvider implements AuthorizationConfigurationProvider
{
	private static AuthorizationProvider INSTANCE;

	public static AuthorizationProvider getInstance()
	{
		if(AuthorizationProvider.INSTANCE == null)
		{
			AuthorizationProvider.INSTANCE = new AuthorizationProvider();
		}

		return AuthorizationProvider.INSTANCE;
	}

	private MicroStreamAuthorizer provider;

	private AuthorizationProvider()
	{

	}

	@Override
	public AuthorizationConfiguration provideConfiguration()
	{
		if(this.provider == null)
		{
			this.provider = new MicroStreamAuthorizer(User.class, Role.class, Permission.class);
		}

		return this.provider.provideConfiguration();
	}
}
