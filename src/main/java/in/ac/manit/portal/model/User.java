package in.ac.manit.portal.model;

import lombok.* ;


public class User {
	
	@Getter @Setter private Long scholarNumber;

	@Getter @Setter private String name;

	@Getter @Setter private String emailAddress;

	@Getter @Setter private String gender;
 
	@Getter @Setter private String phoneNumber;

	@Getter @Setter private String handle;

	@Getter @Setter private String branch;
	
}
