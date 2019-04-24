package stringbenchmarking.result.beans;

import java.io.File;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import stringbenchmarking.result.converter.line.beans.VMVersionLine;

public class VirtualMachine {

	private String jdkVersion;
	private String vmVersion;
	private File vmInvoker;
	private String vmOptions;

	public String getJdkVersion() {
		return jdkVersion;
	}

	public void setJdkVersion(
		String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}

	public String getVmVersion() {
		return vmVersion;
	}

	public void setVmVersion(
		String vmVersion) {
		this.vmVersion = vmVersion;
	}

	public void setVMVersion(
		VMVersionLine vmVersion) {
		this.vmVersion = vmVersion.getVmVersion();
		this.jdkVersion = vmVersion.getJdkVersion();
	}

	public File getVMInvoker() {
		return vmInvoker;
	}

	public void setVMInvoker(
		File invoker) {
		this.vmInvoker = invoker;
	}

	public void setVMOptions(
		String vmOptions) {
		this.vmOptions = vmOptions;
	}

	public String getVMOptions() {
		return this.vmOptions;
	}
	
	@Override
	public boolean equals(
		Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof WarmupMeasure) {
			WarmupMeasure o = (WarmupMeasure) obj;
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
		return ToStringBuilder.reflectionToString(this);
	}
}