package io.sarl.ue4.influence;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
import io.sarl.ue4.Vector3D;

/**
 * Capacity to send influences to the world
 * @author Alexandre Lombard
 */
@FunctionalInterface
@SarlSpecification("0.8")
@SarlElementType(19)
@SuppressWarnings("all")
public interface SendInfluence extends Capacity {
  public abstract void sendInfluence(final Vector3D force);
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends SendInfluence> extends Capacity.ContextAwareCapacityWrapper<C> implements SendInfluence {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void sendInfluence(final Vector3D force) {
      try {
        ensureCallerInLocalThread();
        this.capacity.sendInfluence(force);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
