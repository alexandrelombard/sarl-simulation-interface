package io.sarl.ue4.events;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * Simulation step event
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class SimulationStep extends Event {
  @SyntheticMember
  public SimulationStep() {
    super();
  }
  
  @SyntheticMember
  public SimulationStep(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
