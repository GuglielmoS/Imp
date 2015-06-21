package it.unimi.di.fachini.imp.compiler;

public class Descriptor {
	private final String id;
	private int index;
	private boolean refFlag;

	public Descriptor(String id) {
		this.id = id;
		this.index = -1;
		this.refFlag = false;
	}

	public String getId() {
		return id;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public boolean isRef() {
		return refFlag;
	}

	public void setRef() {
		this.refFlag = true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Descriptor other = (Descriptor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (index != other.index)
			return false;
		return true;
	}
}
