package com.project.jpa.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.jpa.model.Perfil;
import com.project.jpa.model.Usuario;
import com.project.jpa.model.Vacante;
import com.project.jpa.service.ICategoriasService;
import com.project.jpa.service.IUsuariosService;
import com.project.jpa.service.IVacantesService;


@Controller
public class HomeController {
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
    @Autowired
	private IVacantesService serviceVacantes;
    
    @Autowired
   	private IUsuariosService serviceUsuarios;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
  
	@GetMapping("/")
	public String mostrarHome() {
		return "home";
	}

	
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);	
			//System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	

	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	/**
	 * Método que guarda en la base de datos el usuario registrado
	 * @param usuario
	 * @param attributes
	 * @return
	 */
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {

		String pwdPlano = usuario.getPassword();

		String pwdEncriptado = passwordEncoder.encode(pwdPlano); 

		
		usuario.setPassword(pwdEncriptado);	
		usuario.setStatus(1); 
		usuario.setFechaRegistro(new Date()); 
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(2L); // Perfil USUARIO
		usuario.agregar(perfil);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);
				
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login";
	}
	
	
	

	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
		
		/**
		 * La busqueda de vacantes desde el formulario debera de ser únicamente en Vacantes con estatus 
		 * "Aprobada". Entonces forzamos ese filtrado.
		 */
		vacante.setStatus("Aprobada");
		
		// Personalizamos el tipo de busqueda...
		ExampleMatcher matcher  = ExampleMatcher.matching().
			// and descripcion like '%?%'
			withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Vacante> example = Example.of(vacante, matcher);
		List<Vacante> lista = serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes", lista);
		return "home";
	}
	
	

	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	
	

	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
   	public String encriptar(@PathVariable("texto") String texto) {    	
   		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
   	}
	

	@ModelAttribute
	public void setGenericos(Model model){
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.reset();
		model.addAttribute("search", vacanteSearch);
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());	
		model.addAttribute("categorias", serviceCategorias.buscarTodas());	
	}
	
	
	
	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
