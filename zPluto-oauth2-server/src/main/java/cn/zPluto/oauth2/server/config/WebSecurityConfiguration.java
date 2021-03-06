package cn.zPluto.oauth2.server.config;  /**
 * @Title: WebSecurityConfiguration
 * @ProjectName uus
 * @Description: TODO
 * @author DELL
 * @date 2018/8/3014:33
 */

import cn.zPluto.oauth2.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author zhouh
 * @description
 * @date 2018/8/30
 *
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     *  这里记得设置requestMatchers,不拦截需要token验证的url,
     *  不然会优先被这个filter拦截,走用户端的认证而不是token认证
     *   新增formLogin，用于页面授权
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestMatchers().antMatchers("/oauth/**","login/**","/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().permitAll();
    }

    /**
     * 需要配置这个支持password模式
     * support password grant type
     * @return
     * @throws Exception
     */
    //配置内存模式的用户
    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("123456").authorities("USER").build());
        manager.createUser(User.withUsername("user2").password("123456").authorities("USER").build());
        return manager;
    }

    //配置内存模式的用户
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Autowired
//    private CustomAuthenticationProvider authenticationProvider;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//          auth.authenticationProvider(authenticationProvider);
//    }


}
