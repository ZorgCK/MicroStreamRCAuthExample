
package one.microstream.microstreamauthentication.domain;

import com.rapidclipse.framework.server.resources.Caption;
import com.rapidclipse.framework.server.security.authorization.AuthorizationResource;


public class Permission implements java.io.Serializable, AuthorizationResource
{

	private int    permId;
	private String permissionName;

	public Permission()
	{
	}

	@Caption("PermId")
	public int getPermId()
	{
		return this.permId;
	}

	public void setPermId(final int permId)
	{
		this.permId = permId;
	}

	@Caption("PermissionName")
	public String getPermissionName()
	{
		return this.permissionName;
	}

	public void setPermissionName(final String permissionName)
	{
		this.permissionName = permissionName;
	}
	
	@Override
	public String resourceName()
	{
		return this.permissionName;
	}
}
