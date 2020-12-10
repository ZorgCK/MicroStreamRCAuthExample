
package one.microstream.microstreamauthentication.core.authentication;

import com.rapidclipse.framework.security.authentication.Authenticator;
import com.rapidclipse.framework.security.authentication.AuthenticatorProvider;
import com.rapidclipse.framework.security.authentication.CredentialsUsernamePassword;
import com.rapidclipse.framework.security.util.PasswordHasher;

import one.microstream.microstreamauthentication.domain.User;


public class AuthenticationProvider
	implements AuthenticatorProvider<CredentialsUsernamePassword, CredentialsUsernamePassword>
{
	private static AuthenticationProvider INSTANCE;

	public static AuthenticationProvider getInstance()
	{
		if(AuthenticationProvider.INSTANCE == null)
		{
			AuthenticationProvider.INSTANCE = new AuthenticationProvider();
		}

		return AuthenticationProvider.INSTANCE;
	}

	private final PasswordHasher     hashStrategy = PasswordHasher.Sha2();
	private MicroStreamAuthenticator authenticator;

	private AuthenticationProvider()
	{
	}

	@Override
	public Authenticator<CredentialsUsernamePassword, CredentialsUsernamePassword> provideAuthenticator()
	{
		if(this.authenticator == null)
		{
			this.authenticator = new MicroStreamAuthenticator(User.class);
			this.authenticator.setHashStrategy(this.getHashStrategy());
		}

		return this.authenticator;
	}

	public PasswordHasher getHashStrategy()
	{
		return this.hashStrategy;
	}
}
