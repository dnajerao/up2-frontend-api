package com.up2promisedland.api.util.enums;

public enum RolEnum {

	ADMIN(1), PROFESOR(2), STUDENT(3), SUPERVISED_STUDENT(4);

	private final int rol;

	private RolEnum(int rol) {
		this.rol = rol;
	}

	public int getRol() {
		return this.rol;
	}

}