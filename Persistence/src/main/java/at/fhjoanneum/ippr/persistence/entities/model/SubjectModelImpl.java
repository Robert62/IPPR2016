package at.fhjoanneum.ippr.persistence.entities.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import at.fhjoanneum.ippr.persistence.objects.model.State;
import at.fhjoanneum.ippr.persistence.objects.model.SubjectModel;

@Entity(name = "SUBJECT_MODEL")
public class SubjectModelImpl implements SubjectModel, Serializable {

	private static final long serialVersionUID = 6629392913045430776L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long smId;

	@Column
	@NotNull
	@Size(min = 1, max = 100)
	private String name;

	@Column
	@NotNull
	@Size(min = 1, max = 100)
	private String assignedGroup;

	@OneToMany(mappedBy = "subjectModel")
	@NotNull
	@Size(min = 1)
	private List<StateImpl> states;

	SubjectModelImpl() {
	}

	@Override
	public Long getSmId() {
		return smId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getGroup() {
		return assignedGroup;
	}

	@Override
	public List<State> getStates() {
		return ImmutableList.copyOf(states);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (!SubjectModel.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final SubjectModel other = (SubjectModel) obj;
		if ((this.smId == null) ? (other.getSmId() != null) : !this.smId.equals(other.getSmId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(smId);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("smId", smId).append("name", name)
				.toString();
	}
}
