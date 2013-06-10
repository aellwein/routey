package net.ellwein.routey.core;

import java.lang.reflect.Method;

public class Routing {
	private final Object controller;
	private final Method mappedMethod;

	public Routing(final Object controller, final Method mappedMethod) {
		this.controller = controller;
		this.mappedMethod = mappedMethod;
	}

	public Object getController() {
		return controller;
	}

	public Method getMappedMethod() {
		return mappedMethod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((controller == null) ? 0 : controller.hashCode());
		result = prime * result + ((mappedMethod == null) ? 0 : mappedMethod.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Routing other = (Routing) obj;
		if (controller == null) {
			if (other.controller != null) {
				return false;
			}
		} else if (!controller.equals(other.controller)) {
			return false;
		}
		if (mappedMethod == null) {
			if (other.mappedMethod != null) {
				return false;
			}
		} else if (!mappedMethod.equals(other.mappedMethod)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Routing [controller=" + controller + ", mappedMethod=" + mappedMethod + "]";
	}

}
