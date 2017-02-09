package at.fhjoanneum.ippr.commons.dto.owlimport.jsonimport;

import java.io.Serializable;

public class ImportTransitionDTO implements Serializable {

  private static final long serialVersionUID = -3655665114907871668L;

  private String fromStateId;
  private String toStateId;

  public ImportTransitionDTO() {}

  public String getFromStateId() {
    return fromStateId;
  }

  public String getToStateId() {
    return toStateId;
  }
}
