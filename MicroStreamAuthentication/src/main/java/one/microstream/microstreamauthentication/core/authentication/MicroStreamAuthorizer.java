
package one.microstream.microstreamauthentication.core.authentication;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.rapidclipse.framework.security.authorization.AuthorizationConfiguration;
import com.rapidclipse.framework.security.authorization.AuthorizationConfigurationProvider;
import com.rapidclipse.framework.server.security.authorization.AuthorizationResource;
import com.rapidclipse.framework.server.security.authorization.AuthorizationRole;
import com.rapidclipse.framework.server.security.authorization.AuthorizationSubject;

import one.microstream.microstreamauthentication.domain.Role;
import one.microstream.microstreamauthentication.domain.User;
import one.microstream.microstreamauthentication.microstream.MicroStream;


public class MicroStreamAuthorizer implements AuthorizationConfigurationProvider
{
	private final Class<? extends AuthorizationSubject>  subjectEntityType;
	private final Class<? extends AuthorizationRole>     roleEntityType;
	private final Class<? extends AuthorizationResource> resourceEntityType;

	public MicroStreamAuthorizer(
		final Class<? extends AuthorizationSubject> subjectEntityType,
		final Class<? extends AuthorizationRole> roleEntityType,
		final Class<? extends AuthorizationResource> resourceEntityType)
	{
		this.subjectEntityType  = subjectEntityType;
		this.roleEntityType     = roleEntityType;
		this.resourceEntityType = resourceEntityType;
	}

	/**
	 * @return the subjectEntityType
	 */
	public Class<? extends AuthorizationSubject> getSubjectEntityType()
	{
		return this.subjectEntityType;
	}

	/**
	 * @return the roleEntityType
	 */
	public Class<? extends AuthorizationRole> getRoleEntityType()
	{
		return this.roleEntityType;
	}

	/**
	 * @return the resourceEntityType
	 */
	public Class<? extends AuthorizationResource> getResourceEntityType()
	{
		return this.resourceEntityType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthorizationConfiguration provideConfiguration()
	{
		final Map<String, Set<String>>          resourceResources = new HashMap<>();
		final Map<String, Set<String>>          roleRoles         = new HashMap<>();
		final Map<String, Map<String, Integer>> rolePermissions   = new HashMap<>();
		final Map<String, Set<String>>          subjectRoles      = new HashMap<>();

		this.getUser().forEach(subject -> {
			subjectRoles.put(subject.subjectName(), this.unboxRoles(subject.roles()));
		});

		this.getRoles().forEach(role -> {
			rolePermissions.put(role.roleName(), this.unboxResources(role.resources()));
			roleRoles.put(role.roleName(), this.unboxRoles(role.roles()));
		});

		MicroStream.root().getPermissions().forEach(permission -> {
			resourceResources.put(permission.resourceName(), new HashSet<String>());
		});

		return AuthorizationConfiguration.New(resourceResources,
			roleRoles,
			rolePermissions,
			subjectRoles);
	}

	protected Set<String> unboxRoles(final Collection<? extends AuthorizationRole> roles)
	{
		if(roles == null)
		{
			return null;
		}

		final Set<String> collect = roles.stream()
			.filter(r -> this.getRoles().contains(r))
			.map(AuthorizationRole::roleName)
			.collect(Collectors.toSet());

		return collect;
	}

	protected Map<String, Integer> unboxResources(
		final Collection<? extends AuthorizationResource> resources)
	{
		if(resources == null)
		{
			return null;
		}

		return resources.stream()
			.collect(Collectors.toMap(AuthorizationResource::resourceName, r -> 1));
	}

	private Collection<Role> getRoles()
	{
		return MicroStream.root().getUserGroups();
	}

	private Collection<User> getUser()
	{
		return MicroStream.root().getUsers();
	}
}
