package at.fhjoanneum.ippr.persistence.entities.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import at.fhjoanneum.ippr.persistence.objects.model.BusinessObjectModel;
import at.fhjoanneum.ippr.persistence.objects.model.MessageFlow;
import at.fhjoanneum.ippr.persistence.objects.model.State;
import at.fhjoanneum.ippr.persistence.objects.model.SubjectModel;
import at.fhjoanneum.ippr.persistence.objects.model.Transition;
import at.fhjoanneum.ippr.persistence.objects.model.enums.StateEventType;
import at.fhjoanneum.ippr.persistence.objects.model.enums.StateFunctionType;

@Entity(name = "STATE")
public class StateImpl implements State, Serializable {

	private static final long serialVersionUID = 4443123919463434645L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sId;

	@Override
	public Long getSId() {
		return sId;
	}

	@Column
	@NotNull
	@Size(min = 1, max = 100)
	private String name;

	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private StateFunctionType functionType;

	@Column
	@Enumerated(EnumType.STRING)
	private StateEventType eventType;

	@ManyToOne
	@JoinColumn(name = "sm_id")
	@NotNull
	private SubjectModelImpl subjectModel;

	@OneToMany(mappedBy = "fromState")
	private List<TransitionImpl> fromStates;

	@OneToMany(mappedBy = "toState")
	private List<TransitionImpl> toStates;

	@OneToMany
	@JoinColumn(name = "s_id")
	private List<MessageFlowImpl> messageFlows;

	@NotNull
	@ManyToMany
	@Size(min = 1)
	@JoinTable(name = "state_business_object_model_map", joinColumns = {
			@JoinColumn(name = "s_id") }, inverseJoinColumns = { @JoinColumn(name = "bom_id") })
	private List<BusinessObjectModelImpl> businessObjectModels;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public SubjectModel getSubjectModel() {
		return subjectModel;
	}

	@Override
	public StateFunctionType getFunctionType() {
		return functionType;
	}

	@Override
	public StateEventType getEventType() {
		return eventType;
	}

	@Override
	public List<Transition> getFromStates() {
		return ImmutableList.copyOf(fromStates);
	}

	@Override
	public List<Transition> getToStates() {
		return ImmutableList.copyOf(toStates);
	}

	@Override
	public List<MessageFlow> getMessageFlow() {
		return ImmutableList.copyOf(messageFlows);
	}

	@Override
	public List<BusinessObjectModel> getBusinessObjectModels() {
		return ImmutableList.copyOf(businessObjectModels);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (!State.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final State other = (State) obj;
		if ((this.sId == null) ? (other.getSId() != null) : !this.sId.equals(other.getSId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(sId);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("sId", sId).append("name", name)
				.toString();
	}
}
