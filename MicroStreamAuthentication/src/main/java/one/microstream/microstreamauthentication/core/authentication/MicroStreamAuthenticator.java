
package one.microstream.microstreamauthentication.core.authentication;

import java.util.Optional;

import com.rapidclipse.framework.security.authentication.AuthenticationFailedException;
import com.rapidclipse.framework.security.authentication.Authenticator;
import com.rapidclipse.framework.security.authentication.CredentialsUsernamePassword;
import com.rapidclipse.framework.security.util.PasswordHasher;
import com.vaadin.flow.component.UI;

import one.microstream.microstreamauthentication.domain.User;
import one.microstream.microstreamauthentication.microstream.MicroStream;


public class MicroStreamAuthenticator
	implements Authenticator<CredentialsUsernamePassword, CredentialsUsernamePassword>
{
	PasswordHasher hashStrategy;
	
	/**
	 *
	 */
	public MicroStreamAuthenticator(
		final Class<? extends CredentialsUsernamePassword> authenticationEntityType)
	{
	}
	
	public final CredentialsUsernamePassword authenticate(
		final String username,
		final String password)
		throws AuthenticationFailedException
	{
		return this.authenticate(CredentialsUsernamePassword.New(username, password.getBytes()));
	}
	
	@Override
	public CredentialsUsernamePassword authenticate(final CredentialsUsernamePassword credentials)
		throws AuthenticationFailedException
	{
		return this.checkCredentials(credentials);
	}
	
	protected CredentialsUsernamePassword checkCredentials(
		final CredentialsUsernamePassword credentials)
		throws AuthenticationFailedException
	{
		
		final Optional<User> findFirst =
			MicroStream.root().getUsers()
				.stream()
				.filter(u -> u.getUsername().equals(credentials.username()) &&
					this.hashStrategy.validatePassword(credentials.password(), u.getPassword()))
				.findFirst();
		
		if(findFirst.isPresent())
		{
			final CredentialsUsernamePassword user = findFirst.get();
			UI.getCurrent().getSession().setAttribute(User.class, findFirst.get());
			return user;
		}
		
		throw new AuthenticationFailedException();
	}
	
	public PasswordHasher getHashStrategy()
	{
		return this.hashStrategy;
	}
	
	public void setHashStrategy(final PasswordHasher hashStrategy)
	{
		this.hashStrategy = hashStrategy;
	}
}
