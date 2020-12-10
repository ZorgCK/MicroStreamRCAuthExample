
package one.microstream.microstreamauthentication.ui;

import java.util.Arrays;
import java.util.Collection;

import com.rapidclipse.framework.security.authentication.CredentialsUsernamePassword;
import com.rapidclipse.framework.security.util.PasswordHasher;
import com.rapidclipse.framework.server.security.authentication.Authentication;
import com.rapidclipse.framework.server.security.authentication.LoginView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.login.AbstractLogin.ForgotPasswordEvent;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import one.microstream.microstreamauthentication.core.authentication.AuthenticationProvider;
import one.microstream.microstreamauthentication.core.authentication.AuthorizationProvider;
import one.microstream.microstreamauthentication.domain.Role;
import one.microstream.microstreamauthentication.domain.User;
import one.microstream.microstreamauthentication.microstream.MicroStream;


@LoginView
@Route("")
@HtmlImport("frontend://styles/shared-styles.html")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class ViewLogin extends VerticalLayout
{
	public ViewLogin()
	{
		super();
		this.initUI();
	}

	/**
	 * Event handler delegate method for the {@link LoginForm} {@link #loginForm}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void loginForm_onLogin(final LoginEvent event)
	{
		final CredentialsUsernamePassword credentials = CredentialsUsernamePassword.New(
			event.getUsername(),
			event.getPassword());
		
		final AuthenticationProvider authenticatorProvider              = AuthenticationProvider.getInstance();
		final AuthorizationProvider  authorizationConfigurationProvider = AuthorizationProvider.getInstance();
		
		if(!Authentication.tryLogin(credentials, authenticatorProvider, authorizationConfigurationProvider))
		{
			this.loginForm.setError(true);
		}
	}

	/**
	 * Event handler delegate method for the {@link LoginForm} {@link #loginForm}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void loginForm_onForgotPassword(final ForgotPasswordEvent event)
	{
		// TODO provide password recovery
	}

	/**
	 * Event handler delegate method for the {@link Button} {@link #button}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button_onClick(final ClickEvent<Button> event)
	{
		ViewLogin.setupMasterAdmin();
	}

	public static void setupMasterAdmin()
	{
		final User user = new User();
		user.setPassword(PasswordHasher.Sha2().hashPassword(new String("admin").getBytes()));
		user.setUsername("Admin");
		user.getRoles().addAll(ViewLogin.setupRoles());

		MicroStream.root().getUsers().add(user);
		MicroStream.storageManager().store(MicroStream.root().getUsers());
	}

	public static Collection<Role> setupRoles()
	{
		final Role role = new Role("AdminRole");
		
		MicroStream.root().getUserGroups().add(role);
		MicroStream.storageManager().store(MicroStream.root().getUserGroups());

		return Arrays.asList(role);
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.button    = new Button();
		this.loginForm = new LoginForm();
		
		this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		this.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
		this.button.setText("Initialize Database");
		
		this.button.setSizeUndefined();
		this.add(this.button, this.loginForm);
		this.setSizeFull();
		
		this.button.addClickListener(this::button_onClick);
		this.loginForm.addLoginListener(this::loginForm_onLogin);
		this.loginForm.addForgotPasswordListener(this::loginForm_onForgotPassword);
	} // </generated-code>

	// <generated-code name="variables">
	private Button    button;
	private LoginForm loginForm;
	// </generated-code>

}
