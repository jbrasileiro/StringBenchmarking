package stringbenchmarking.result.beans;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import stringbenchmarking.commons.zuz.ZuzObjects;

public class Warmup
	implements
	Serializable {

	private Integer iterations;
	private String unit;

	public Integer getIterations() {
		return iterations;
	}

	public void setIterations(
		Integer nrIterations) {
		this.iterations = nrIterations;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(
		String unit) {
		this.unit = unit;
	}
	
	@Override
	public boolean equals(
		Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Warmup) {
			Warmup o = (Warmup) obj;
			return EqualsBuilder.reflectionEquals(this, o, true);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	@Override
	public String toString() {
		return ZuzObjects.reflectionToString(this);
	}
}
