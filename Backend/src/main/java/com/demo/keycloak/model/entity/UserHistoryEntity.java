package com.demo.keycloak.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users_history")
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoryEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user_history")
	private Long id;

	@Column(name = "login_date", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date loginDate;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity usersEntity;

	// Set creationDate to current time if it's null before persisting
	@PrePersist
	protected void onCreate() {
		if (this.loginDate == null) {
			this.loginDate = new Date();
		}
	}
}