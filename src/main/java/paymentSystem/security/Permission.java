package paymentSystem.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder 
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="privilege") 
public class Permission   {
	@Id
	@Column(name="code")
	private String code;
	@ManyToOne
	@JoinColumn(name="code_role")
	private Role role;
	@ManyToOne
	@JoinColumn(name="code_action")
	private Action action;

}
