
package one.microstream.microstreamauthentication.core.authentication;

import java.util.MissingResourceException;

import com.rapidclipse.framework.security.authorization.Resource;
import com.rapidclipse.framework.security.authorization.ResourceEnum;
import com.rapidclipse.framework.server.resources.Caption;
import com.rapidclipse.framework.server.resources.StringResourceUtils;
import com.rapidclipse.framework.server.security.authorization.Authorization;
import com.rapidclipse.framework.server.security.authorization.AuthorizationResource;


/**
 * Central collection of all authorization resources used in the project.
 */
@Caption("{%description}")
public enum AuthorizationResources implements ResourceEnum<AuthorizationResources>, AuthorizationResource
{
	ADMIN("ADMIN"),
	BACKEND_ACCESS("BACKEND_ACCESS"),
	USER_EDIT("USER_EDIT");
	
	/**
	 * Helper method to export all resource names.
	 * <p>
	 * Right click and select 'Run As' - 'Java Application'
	 * </p>
	 */
	public static void main(final String[] args)
	{
		for(final AuthorizationResources value : AuthorizationResources.values())
		{
			System.out.println(value.name);
		}
	}
	
	/////////////////////////////
	// implementation details //
	///////////////////////////
	
	private final String	name;
	private Resource		resource;
	private String			description;
	
	private AuthorizationResources(final String name)
	{
		this.name = name;
	}
	
	@Override
	public String resourceName()
	{
		return this.name;
	}
	
	@Override
	public Resource resource()
	{
		if(this.resource == null)
		{
			this.resource = Authorization.resource(this.name);
		}
		
		return this.resource;
	}
	
	public String getDescription()
	{
		if(this.description == null)
		{
			try
			{
				this.description = StringResourceUtils.getResourceString(name(), this);
			}
			catch(final MissingResourceException e)
			{
				this.description = this.name;
			}
		}
		
		return this.description;
	}
}
