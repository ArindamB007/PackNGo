package com.png.config;

/*@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }
  
  @Bean
  ServletRegistrationBean h2servletRegistration(){
      ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
      registrationBean.addUrlMappings("/h2-console/*");
      return registrationBean;
  }
 

  @Bean
  public ViewResolver internalResourceViewResolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      //resolver.setPrefix("/resources/static/");
      //resolver.setApplicationContext(applicationContext);
      //resolver.setViewNames("/views/*");
      resolver.setViewNames(new String[] {"/views/*"});
      resolver.setOrder(0);
      resolver.setSuffix(".html");
      return resolver;
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	  configurer.enable();
  }
  
  
  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine());
    resolver.setCharacterEncoding("UTF-8");
    resolver.setViewNames(new String[] {"/templates/*"});
    resolver.setExcludedViewNames(new String [] {"/views/*"});
    resolver.setOrder(1);
    return resolver;
  }

  public TemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setEnableSpringELCompiler(true);
    engine.setTemplateResolver(templateResolver());
    return engine;
  }

  private ITemplateResolver templateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("/templates/");
    resolver.setCheckExistence(true);
    resolver.setTemplateMode(TemplateMode.HTML);
    return resolver;
  } 
}*/
