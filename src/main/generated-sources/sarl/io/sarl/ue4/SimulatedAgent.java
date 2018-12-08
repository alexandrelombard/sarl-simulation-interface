package io.sarl.ue4;

import io.sarl.core.Initialize;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import io.sarl.ue4.Vector3D;
import io.sarl.ue4.events.SimulationStep;
import io.sarl.ue4.influence.SendInfluence;
import io.sarl.ue4.influence.UdpSendInfluence;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Agent able to send influences on the world
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(18)
@SuppressWarnings("all")
public class SimulatedAgent extends Agent {
  private final double initialScale = 1.0;
  
  private Vector3D direction = new Vector3D(Double.valueOf((this.initialScale * Math.random())), Double.valueOf((this.initialScale * Math.random())), Double.valueOf((this.initialScale * Math.random())));
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    UdpSendInfluence _udpSendInfluence = new UdpSendInfluence();
    this.<UdpSendInfluence>setSkill(_udpSendInfluence, SendInfluence.class);
  }
  
  private void $behaviorUnit$SimulationStep$1(final SimulationStep occurrence) {
    synchronized (this.direction) {
      double _random = Math.random();
      double _random_1 = Math.random();
      double _random_2 = Math.random();
      Vector3D _vector3D = new Vector3D(Double.valueOf(_random), Double.valueOf(_random_1), Double.valueOf(_random_2));
      Vector3D _plus = this.direction.operator_plus(_vector3D);
      this.direction = _plus;
      SendInfluence _$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE$CALLER = this.$castSkill(SendInfluence.class, (this.$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE == null || this.$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE = this.$getSkill(SendInfluence.class)) : this.$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE);
      _$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE$CALLER.sendInfluence(this.direction);
    }
  }
  
  @Extension
  @ImportedCapacityFeature(SendInfluence.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(SendInfluence.class, ($0$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE == null || $0$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE.get() == null) ? ($0$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE = $0$getSkill(SendInfluence.class)) : $0$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE)", imported = SendInfluence.class)
  private SendInfluence $CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE == null || this.$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE = $getSkill(SendInfluence.class);
    }
    return $castSkill(SendInfluence.class, this.$CAPACITY_USE$IO_SARL_UE4_INFLUENCE_SENDINFLUENCE);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SimulationStep(final SimulationStep occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SimulationStep$1(occurrence));
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SimulatedAgent other = (SimulatedAgent) obj;
    if (Double.doubleToLongBits(other.initialScale) != Double.doubleToLongBits(this.initialScale))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (int) (Double.doubleToLongBits(this.initialScale) ^ (Double.doubleToLongBits(this.initialScale) >>> 32));
    return result;
  }
  
  @SyntheticMember
  public SimulatedAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public SimulatedAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public SimulatedAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
