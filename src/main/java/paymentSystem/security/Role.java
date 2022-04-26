package paymentSystem.security;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name="role") 
public class Role  {
	@Id
	@Column(name="code")
	private String code;

	@Column(name="designation",unique=true)
	private String designation;

	@Column(name="description")
	private String description;

	@OneToMany(mappedBy="role",fetch=FetchType.EAGER)
	private List<Permission> permissions;

	@Transient
	private List<Action> actions;


}
