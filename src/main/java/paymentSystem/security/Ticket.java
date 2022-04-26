package paymentSystem.security;

import java.io.Serializable;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Ticket  implements Serializable{

	private String userName;
	private List<Permission> permissions=null;
	private Utilisateur utilisateur;

	public static void main(String[] args) throws Exception{
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String foo = "(40+2 )*2";
		System.out.println(engine.eval(foo));
	} 

}
