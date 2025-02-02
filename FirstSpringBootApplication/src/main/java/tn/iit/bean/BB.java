package tn.iit.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // c'est un bean de spring
public class BB {
	// 1er methode d'injection d'attribut
	@Autowired
	private Hello hello;
//2eme methode d'injection via setter
	/*
	 * @Autowired public void setHello(Hello hello) { this.hello = hello; }
	 */
//3eme methode d'injection via constructeur
//@Autowired//non recommandé

	public BB(Hello hello) {

		this.hello = hello;
	}

	public void callHello() {
		hello.a();
	}
}
