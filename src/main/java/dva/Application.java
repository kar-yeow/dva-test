/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dva;

//import example.users.Password;
//import example.users.UserManagement;
//import example.users.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

import dva.domain.*;

/**
 * Central Spring Boot application class to bootstrap the application. Excludes Spring Security auto-configuration as we
 * don't need it for the example but only want to use a {@link PasswordEncoder} (see {@link #passwordEncoder()}).
 * <p>
 * Spring Data web support is transparently activated by Boot for you. In case you want to manually activate it, use
 * {@link EnableSpringDataWebSupport}. The core aspects of the enabled functionality shown in this example are:
 * <ol>
 * <li>Automatic population of a {@link Pageable} instances from request parameters (see
 * {@link example.users.web.UserController#users(Pageable)})</li>
 * <li>The ability to use proxy-backed interfaces to bind request payloads (see
 * {@link example.users.web.UserController.UserForm})</li>
 * </ol>
 * 
 * @author Oliver Gierke
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	ObjectManager personManagement;

	/**
	 * Creates a few sample users.
	 */
	@PostConstruct
	public void init() {
		IntStream.range(0, 10).forEach(index -> {
			Person person = personManagement.createPerson("name_" + index, "0000 000" + index, "email" + index + "@test.com");
			IntStream.range(0, 5).forEach(i -> {
				Claim claim = personManagement.addClaimToPerson(person, "00" + index + "0"+ i, "claim #" + i, (i + index) * 20.0);
			});
		});
		IntStream.range(0, 10).forEach(i -> {
			Person p = personManagement.findPersonByName("name_"+i);
			System.out.println("person=" + p + ",claims=" + p.getClaims());
		});

	}

}
